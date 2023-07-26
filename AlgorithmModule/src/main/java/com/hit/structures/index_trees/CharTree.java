package com.hit.structures.index_trees;

import com.hit.structures.IIndexTree;
import com.hit.structures.IIndexTreeNode;
import com.hit.structures.index_tree_nodes.CharTreeNode;

/**
 * Suffix tree data structure.
 */
public class CharTree implements IIndexTree<Character> {
    public static final Character STOP = '$';

    private String text;
    private CharTreeNode root;

    public CharTree(String indexedText) {
        this.text = indexedText;
    }

    @Override
    public CharTreeNode createRoot(Character rootValue) {
        this.root = new CharTreeNode(rootValue);

        return this.root;
    }

    @Override
    public CharTreeNode getRoot() {
        return this.root;
    }

    public String text() {
        return this.text;
    }

    public boolean patternExits(String pattern) {
        IIndexTreeNode<Character> treeNode = this.root;
        char prevChar = STOP;
        for (char character : pattern.toCharArray()) {          
            if (treeNode.hasChild(character)) {
                if (character == prevChar) {
                    treeNode = treeNode.goToChildByValue(character);
                }
                else {
                    treeNode = this.root;
                }
            }
            else {
                return false;
            }

            prevChar = character;
        }

        return true;
    }
}
