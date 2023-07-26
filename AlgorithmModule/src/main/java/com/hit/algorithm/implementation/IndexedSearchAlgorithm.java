package com.hit.algorithm.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.*;

import com.hit.structures.IIndexTreeNode;
import com.hit.structures.index_tree_nodes.CharTreeNode;
import com.hit.structures.index_trees.CharTree;

/**
 * <p>
 * Indexed partial search algorithm.
 * </p>
 */
public class IndexedSearchAlgorithm implements com.hit.algorithm.ILooseQueryAlgorithm {

    private ArrayList<CharTree> catalog;
    int i = 0;

    @Override
    public void setCatalog(Collection<String> catalog) {
        this.catalog = new ArrayList<>();

        for (String text : catalog) {
            int level = 0;
            CharTree tree = new CharTree(text);
            final CharTreeNode treeRoot = tree.createRoot(CharTree.STOP);
            CharTreeNode currentNode = treeRoot;
            Character[] charArray = ArrayUtils.toObject(text.toCharArray());

            currentNode.setChildren(charArray);

            for (IIndexTreeNode<Character> child : currentNode.children()) {
                String subString = text.substring(++level, text.length());

                this.createIndexTree(child, subString);
            }

            this.catalog.add(tree);
        }
    }

    @Override
    public List<String> queryCatalog(String pattern) {
        List<String> result = new LinkedList<>();

        for (CharTree entry : this.catalog) {
            if (entry.patternExits(pattern)) {
                result.add(0, entry.text());
            }
        }

        return result;
    }

    private void createIndexTree(IIndexTreeNode<Character> currentNode, String text) {
        final IntStream stream = text.chars();
        final Character[] subText = stream
                .mapToObj(x -> (char) x)
                .toArray(Character[]::new);
        int index = 0;
        for (Character character : subText) {
            if (currentNode.hasChild(character) ||
                    (currentNode.getValue().equals(character)
                            && !currentNode.getValue().equals(character))) {
                this.createIndexTree(
                        currentNode.goToChildByValue(character),
                        text.substring(index, text.length()));
            }

            currentNode.appendChildIfAbsent(character);
            index++;
        }
    }

}
