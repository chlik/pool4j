package org.pool4j;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;


/**
 * Tests the unique implementation details of <code>FiniteResourcePool</code>. 
 * @see ResourcePoolTester ResourcePoolTester - for basic API testing
 * @author Evan Worley
 */
public class FiniteResourcePoolTester {
    
    @Test
    public void throwsExceptionWhenRequestOnEmptyPool() {
        final FiniteResourcePool<Integer> resourcePool = 
            new FiniteResourcePool<Integer>(Arrays.asList(new Integer[] {1,2,3}));
        
        while (!resourcePool.isEmpty()) {
            resourcePool.getResource();
        }
        
        try {
            resourcePool.getResource();
            fail("Expecting a EmptyResourcePoolException to be thrown");
        } catch (EmptyResourcePoolException e) {
            // Expected
        }
    }
    
    @Test(expected = EmptyResourcePoolException.class)
    public void requiresResources() {
        new FiniteResourcePool<Integer>(Collections.EMPTY_LIST);
    }
}
