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

package org.apache.ignite.internal.managers.systemview.walker;

import java.util.Date;
import org.apache.ignite.spi.systemview.view.PagesTimestampHistogramView;
import org.apache.ignite.spi.systemview.view.SystemViewRowAttributeWalker;

/**
 * Generated by {@code org.apache.ignite.codegen.SystemViewRowAttributeWalkerGenerator}.
 * {@link PagesTimestampHistogramView} attributes walker.
 * 
 * @see PagesTimestampHistogramView
 */
public class PagesTimestampHistogramViewWalker implements SystemViewRowAttributeWalker<PagesTimestampHistogramView> {
    /** {@inheritDoc} */
    @Override public void visitAll(AttributeVisitor v) {
        v.accept(0, "dataRegionName", String.class);
        v.accept(1, "intervalStart", Date.class);
        v.accept(2, "intervalEnd", Date.class);
        v.accept(3, "pagesCount", long.class);
    }

    /** {@inheritDoc} */
    @Override public void visitAll(PagesTimestampHistogramView row, AttributeWithValueVisitor v) {
        v.accept(0, "dataRegionName", String.class, row.dataRegionName());
        v.accept(1, "intervalStart", Date.class, row.intervalStart());
        v.accept(2, "intervalEnd", Date.class, row.intervalEnd());
        v.acceptLong(3, "pagesCount", row.pagesCount());
    }

    /** {@inheritDoc} */
    @Override public int count() {
        return 4;
    }
}