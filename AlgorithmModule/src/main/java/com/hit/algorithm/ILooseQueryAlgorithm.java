package com.hit.algorithm;

import java.util.Collection;
import java.util.List;

/*
 * Loose search algorithm contract.
 */
public interface ILooseQueryAlgorithm {
    void setCatalog(Collection<String> catalog);

    /**
     * Query catalog values.
     * @param pattern
     * @return
     */
    List<String> queryCatalog(String pattern);
}