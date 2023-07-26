package com.hit.structures.index_tree_nodes;

import com.hit.structures.IIndexTreeNode;

/**
 * Default index tree node.
 */
public class CharTreeNode extends IndexTreeNode<Character> {
    public CharTreeNode(Character nodeValue) {
        super(nodeValue);
    }

    public CharTreeNode(Character nodeValue, IIndexTreeNode<Character> parentNode) {
        super(nodeValue, parentNode);
    }
}
