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

package org.apache.ignite.internal.ducktest.tests.self_test;

import org.apache.ignite.cache.affinity.AffinityKeyMapper;

/**
 * Affinity mapper used to test configuration of the custom affinity mapper in the server node config XML.
 */
public class TestAffinityMapper implements AffinityKeyMapper {
    /** {@inheritDoc} */
    @Override public Object affinityKey(Object key) {
        return key;
    }

    /** {@inheritDoc} */
    @Override public void reset() {
    }
}
