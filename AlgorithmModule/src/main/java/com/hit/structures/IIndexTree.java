package com.hit.structures;

import com.hit.infrastructure.entities.IAggregateRoot;

/**
 * Index tree convention.
 * 
 * @param <TNODEVALUE> Tree node's value type.
 */
public interface IIndexTree<TNODEVALUE> extends IAggregateRoot {

    /**
     * Create a root node for the current tree instance.
     * @param rootValue
     */
    IIndexTreeNode<TNODEVALUE> createRoot(TNODEVALUE rootValue);

    /**
     * Get the root of the current tree instance.
     * @return The root node of the tree.
     */
    IIndexTreeNode<TNODEVALUE> getRoot();
}
