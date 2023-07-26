package com.hit.algorithm.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.hit.algorithm.ILooseQueryAlgorithm;

public class OnlineSearchAlgorithm implements ILooseQueryAlgorithm {
    List<String> catalog;

    @Override
    public void setCatalog(Collection<String> catalog) {
        this.catalog = new ArrayList<>(catalog);
    }

    @Override
    public List<String> queryCatalog(String pattern) {
        LinkedList<String> result = new LinkedList<>();
        char[] patternCharacters = pattern.toCharArray();
        int i = 0;
        for (String text : this.catalog) {
            for (Character c : text.toCharArray()) {
                if (patternCharacters[i] == c) {
                    i++;

                    if (pattern.length() == i) {
                        result.add(text);
                        i = 0;
                        break;
                    }
                }
            }

            i = 0;
        }

        return result;
    }
}
