package org.pool4j;

import java.util.Collections;

import org.junit.Test;


/**
 * Tests the unique implementation details of <code>FiniteResourcePool</code>. 
 * @see ResourcePoolTester ResourcePoolTester - for basic API testing
 * @author Evan Worley
 */
public class FiniteResourcePoolTester {
    
    @Test(expected = EmptyResourcePoolException.class)
    public void requiresResources() {
        new FiniteResourcePool<Integer>(Collections.EMPTY_LIST);
    }
}
