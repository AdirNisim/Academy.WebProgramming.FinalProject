package com.hit.structures;

import java.util.Collection;
import java.util.Map;

/**
 * Index tree node convention.
 * 
 * @param <TVALUE> Node's value type.
 */
public interface IIndexTreeNode<TVALUE>  {
    /**
     * Set tree node's children.
     */
    void setChildren(TVALUE[] children);

    /**
     * Add a new child.
     */
    void appendChildIfAbsent(TVALUE childValue);

    /**
     * @return The children of the current node.
     */
    Collection<IIndexTreeNode<TVALUE>> children();

    Collection<TVALUE> values();

    void setParentNodeCollection(Map<TVALUE, Integer> collection);

    /**
     * @return The parent of the current node.
     */
    IIndexTreeNode<TVALUE> getParent();

    /**
     * @return The child node by index of the child.
     */
    IIndexTreeNode<TVALUE> goToChildByIndex(int index);

    /**
     * @return The child node by value of the child
     */
    IIndexTreeNode<TVALUE> goToChildByValue(TVALUE input);

    /**
     * @return The child's index by value of the child
     */
    Integer getIndex(TVALUE input);

    /**
     * @return Sets the value of the current node.
     */
    void setValue(TVALUE value);

    /**
     * @return Gets if the value of the current node exits.
     */
    boolean hasChild(TVALUE value);

    /**
     * @return Gets the value of the current node.
     */
    TVALUE getValue();
}
