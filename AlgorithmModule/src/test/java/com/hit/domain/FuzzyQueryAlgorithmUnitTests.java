package com.hit.domain;

import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.Assert;
import com.hit.algorithm.ILooseQueryAlgorithm;
import com.hit.algorithm.implementation.IndexedSearchAlgorithm;
import com.hit.algorithm.implementation.OnlineSearchAlgorithm;


public class FuzzyQueryAlgorithmUnitTests {

    /**
     * Checks if the catalog is indexed and quried properly.
     */
    @Test
    public void canQueryIndexedSearch() {
        Logger log = Logger.getAnonymousLogger();

        ILooseQueryAlgorithm fuzzyQueryAlgorithm;
        fuzzyQueryAlgorithm = new IndexedSearchAlgorithm();

        fuzzyQueryAlgorithm.setCatalog(Arrays.asList("adirr Melec ba moba"));

        List<String> results = fuzzyQueryAlgorithm.queryCatalog("ad");

        log.log(Level.INFO, "First \"ad\" query:");

        Assert.assertTrue(!results.isEmpty());

        for (String result : results) {
            log.log(Level.INFO,result);
        }

        results = fuzzyQueryAlgorithm.queryCatalog("aaaa");

        Assert.assertTrue(!results.isEmpty());

        log.log(Level.INFO,"Second \"aaaa\" query:");

        for (String result : results) {
            log.log(Level.INFO,result);
        }

        results = fuzzyQueryAlgorithm.queryCatalog("oba");

        log.log(Level.INFO," \"oba\" query:");

        Assert.assertTrue(!results.isEmpty());

        for (String result : results) {
            log.log(Level.INFO,result);
        }

        results = fuzzyQueryAlgorithm.queryCatalog("obadfgh");

        log.log(Level.INFO," \"oba\" query:");

        Assert.assertTrue(results.isEmpty());

        for (String result : results) {
            log.log(Level.INFO,result);
        }
    }
    
    /**
     * Checks if the catalog is set and quried properly.
     */
    @Test
    public void canQueryOnlineSearch() {
        Logger log = Logger.getAnonymousLogger();

        ILooseQueryAlgorithm fuzzyQueryAlgorithm;
        fuzzyQueryAlgorithm = new OnlineSearchAlgorithm();

        fuzzyQueryAlgorithm.setCatalog(Arrays.asList("adir", "Melech", "ba", "moba", " Igal halash ba moba"));

        List<String> results = fuzzyQueryAlgorithm.queryCatalog("ad");

        log.log(Level.INFO," First \"ad\" query:");

        Assert.assertTrue(!results.isEmpty());

        for (String result : results) {
            log.log(Level.INFO,result);
        }

        results = fuzzyQueryAlgorithm.queryCatalog("aaaa");

        log.log(Level.INFO,"Second \"aaaa\" query:");
        
        Assert.assertTrue(!results.isEmpty());

        for (String result : results) {
            log.log(Level.INFO,result);
        }

        results = fuzzyQueryAlgorithm.queryCatalog("oba");

        log.log(Level.INFO," \"oba\" query:");

        Assert.assertTrue(!results.isEmpty());

        for (String result : results) {
            log.log(Level.INFO,result);
        }
    }
}
