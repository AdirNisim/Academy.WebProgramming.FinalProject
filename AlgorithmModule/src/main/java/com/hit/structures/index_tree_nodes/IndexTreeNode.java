package com.hit.structures.index_tree_nodes;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import com.hit.structures.IIndexTreeNode;
import com.hit.structures.index_tree_nodes.node_collections.NodeCollection;

/**
 * Default index tree node.
 */
public class IndexTreeNode<TVALUE> implements IIndexTreeNode<TVALUE> {
    private IIndexTreeNode<TVALUE> parent;
    private NodeCollection<TVALUE> children;
    private TVALUE value;
    private Map<TVALUE, Integer> parentKeyCollection;

    public IndexTreeNode(TVALUE nodeValue) {
        this.children = new NodeCollection<>();
        this.value = nodeValue;
    }

    public IndexTreeNode(TVALUE nodeValue, IIndexTreeNode<TVALUE> parentNode) {
        this.children = new NodeCollection<>();
        this.value = nodeValue;
        this.parent = parentNode;
    }

    /**
     * Set a collection of children.
     */
    @Override
    public void setChildren(TVALUE[] childrenValues) {
        this.children.clear();

        for (TVALUE childValue : childrenValues) {
            IndexTreeNode<TVALUE> child = new IndexTreeNode<>(childValue, this);
            child.setParentNodeCollection(this.children.getKeyCollection());
            this.children.add(child);
        }
    }

    /**
     * Set a collection of children.
     */
    @Override
    public void setParentNodeCollection(Map<TVALUE, Integer> collection) {
        this.parentKeyCollection = collection;
    }

    /**
     * Set a collection of children.
     */
    @Override
    public void appendChildIfAbsent(TVALUE childValue) {
        if (this.children.getByValue(childValue) == null) {
            IndexTreeNode<TVALUE> child = new IndexTreeNode<>(childValue, this);
            child.setParentNodeCollection(this.children.getKeyCollection());
            this.children.add(child);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IIndexTreeNode<TVALUE>> children() {
        return this.children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<TVALUE> values() {
        return this.children.stream().map(x -> x.getValue()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChild(TVALUE value) {
        return this.children.getByValue(value) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIndexTreeNode<TVALUE> getParent() {
        return this.parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIndexTreeNode<TVALUE> goToChildByIndex(int index) {
        return this.children.getByIndex(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIndexTreeNode<TVALUE> goToChildByValue(TVALUE input) {
        return this.children.getByValue(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getIndex(TVALUE input) {
        final Integer position = this.children.getKeyCollection().get(value);
        if (position != null) {
            return position;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(TVALUE value) {
        int position = this.parentKeyCollection.get(this.value);

        this.parentKeyCollection.remove(this.value);
        this.parentKeyCollection.putIfAbsent(value, position);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TVALUE getValue() {
        return this.value;
    }
}
