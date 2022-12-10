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

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.apache.ignite.internal.util.typedef.F;
import org.apache.ignite.spi.systemview.view.NodeAttributeView;
import org.apache.ignite.spi.systemview.view.SystemViewRowAttributeWalker;

/**
 * Generated by {@code org.apache.ignite.codegen.SystemViewRowAttributeWalkerGenerator}.
 * {@link NodeAttributeView} attributes walker.
 * 
 * @see NodeAttributeView
 */
public class NodeAttributeViewWalker implements SystemViewRowAttributeWalker<NodeAttributeView> {
    /** Filter key for attribute "nodeId" */
    public static final String NODE_ID_FILTER = "nodeId";

    /** Filter key for attribute "name" */
    public static final String NAME_FILTER = "name";

    /** List of filtrable attributes. */
    private static final List<String> FILTRABLE_ATTRS = Collections.unmodifiableList(F.asList(
        "nodeId", "name"
    ));

    /** {@inheritDoc} */
    @Override public List<String> filtrableAttributes() {
        return FILTRABLE_ATTRS;
    }

    /** {@inheritDoc} */
    @Override public void visitAll(AttributeVisitor v) {
        v.accept(0, "nodeId", UUID.class);
        v.accept(1, "name", String.class);
        v.accept(2, "value", String.class);
    }

    /** {@inheritDoc} */
    @Override public void visitAll(NodeAttributeView row, AttributeWithValueVisitor v) {
        v.accept(0, "nodeId", UUID.class, row.nodeId());
        v.accept(1, "name", String.class, row.name());
        v.accept(2, "value", String.class, row.value());
    }

    /** {@inheritDoc} */
    @Override public int count() {
        return 3;
    }
}
