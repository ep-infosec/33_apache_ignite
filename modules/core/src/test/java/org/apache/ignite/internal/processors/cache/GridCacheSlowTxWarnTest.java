/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.cache;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.internal.IgniteKernal;
import org.apache.ignite.testframework.junits.common.GridCommonAbstractTest;
import org.apache.ignite.transactions.Transaction;
import org.junit.Test;
import static org.apache.ignite.cache.CacheMode.PARTITIONED;
import static org.apache.ignite.cache.CacheMode.REPLICATED;

/**
 * Test to check slow TX warning timeout defined by
 * {@link org.apache.ignite.IgniteSystemProperties#IGNITE_SLOW_TX_WARN_TIMEOUT}
 * system property.
 */
public class GridCacheSlowTxWarnTest extends GridCommonAbstractTest {
    /** Partitioned cache name. */
    protected static final String PARTITIONED_CACHE_NAME = "partitioned";

    /** Replicated cache name. */
    private static final String REPLICATED_CACHE_NAME = "replicated";

    /** {@inheritDoc} */
    @Override protected IgniteConfiguration getConfiguration(String igniteInstanceName) throws Exception {
        IgniteConfiguration c = super.getConfiguration(igniteInstanceName);

        CacheConfiguration cc1 = defaultCacheConfiguration()
            .setName(PARTITIONED_CACHE_NAME)
            .setCacheMode(PARTITIONED)
            .setBackups(1);

        CacheConfiguration cc2 = defaultCacheConfiguration()
            .setName(REPLICATED_CACHE_NAME)
            .setCacheMode(REPLICATED);

        c.setCacheConfiguration(cc1, cc2);

        return c;
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testWarningOutput() throws Exception {
        try {
            IgniteKernal g = (IgniteKernal)startGrid(1);

            info(">>> Slow tx timeout is not set, long-live txs simulated.");

            checkCache(g, PARTITIONED_CACHE_NAME, true, false);
            checkCache(g, REPLICATED_CACHE_NAME, true, false);

            info(">>> Slow tx timeout is set, long-live tx simulated.");

            checkCache(g, PARTITIONED_CACHE_NAME, true, true);
            checkCache(g, REPLICATED_CACHE_NAME, true, true);

            info(">>> Slow tx timeout is set, no long-live txs.");

            checkCache(g, PARTITIONED_CACHE_NAME, false, true);
            checkCache(g, REPLICATED_CACHE_NAME, false, true);
        }
        finally {
            stopAllGrids();
        }
    }

    /**
     * @param g Grid.
     * @param cacheName Cache.
     * @param simulateTimeout Simulate timeout.
     * @param configureTimeout Alter configuration of TX manager.
     * @throws Exception If failed.
     */
    private void checkCache(Ignite g, String cacheName, boolean simulateTimeout,
        boolean configureTimeout) throws Exception {
        if (configureTimeout) {
            GridCacheAdapter<Integer, Integer> cache = ((IgniteKernal)g).internalCache(cacheName);

            cache.context().tm().slowTxWarnTimeout(500);
        }

        IgniteCache<Object, Object> cache1 = g.cache(cacheName);

        Transaction tx = g.transactions().txStart();

        try {
            cache1.put(1, 1);

            if (simulateTimeout)
                Thread.sleep(800);

            tx.commit();
        }
        finally {
            tx.close();
        }

        tx = g.transactions().txStart();

        try {
            cache1.put(1, 1);

            if (simulateTimeout)
                Thread.sleep(800);

            tx.rollback();
        }
        finally {
            tx.close();
        }
    }
}
