package com.hit.structures.index_tree_nodes.node_collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import com.hit.structures.IIndexTreeNode;

public class NodeCollection<TVALUE> implements Collection<IIndexTreeNode<TVALUE>> {
    private HashMap<TVALUE, Integer> positions;
    private LinkedList<IIndexTreeNode<TVALUE>> nodes;

    public NodeCollection() {
        this.positions = new HashMap<>();
        this.nodes = new LinkedList<>();
    }

    @Override
    public int size() {
        return this.nodes.size();
    }

    public Map<TVALUE, Integer> getKeyCollection() {
        return this.positions;
    }

    @Override
    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.positions.containsKey(o);
    }

    @Override
    public Iterator<IIndexTreeNode<TVALUE>> iterator() {
        return this.nodes.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.nodes.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.nodes.toArray(a);
    }

    @Override
    public boolean add(IIndexTreeNode<TVALUE> e) {
        final Integer result = this.positions.putIfAbsent(e.getValue(), this.nodes.size());

        if (result == null) {
            return this.nodes.add(e);
        }

        return false;
    }

    public IIndexTreeNode<TVALUE> getByValue(TVALUE value) {
        final Integer position = this.positions.get(value);
        if (position != null) {
            return this.nodes.get(position);
        }

        return null;
    }

    public IIndexTreeNode<TVALUE> getByIndex(int index) {
        return this.nodes.get(index);
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof IIndexTreeNode<?>) {
            this.positions.remove(((IIndexTreeNode<?>) o).getValue());
            return this.nodes.remove(o);
        }

        throw new UnsupportedOperationException("Invalid type was provided.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object item : c) {
            if (!(item instanceof IIndexTreeNode<?>)) {
                return false;
            }

            result = result && this.positions.containsKey(((IIndexTreeNode<?>) item).getValue());
        }

        return result;
    }

    @Override
    public boolean addAll(Collection<? extends IIndexTreeNode<TVALUE>> c) {
        for (IIndexTreeNode<TVALUE> item : c) {
            this.add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            this.remove(item);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        this.positions.clear();
        this.nodes.clear();
    }
}
