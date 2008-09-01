package org.pool4j;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the unique implementation details of <code>ScalingResourcePoolTester</code>. 
 * @see ResourcePoolTester ResourcePoolTester - for basic API testing
 * @author Evan Worley
 */
public class ScalingResourcePoolTester {
    @Test
    public void scaleUpAndDown() throws Exception {
        // Create a scaling resource pool that will scale down every 500ms
        ResourcePool<Object> pool = new ScalingResourcePool<Object>(new IntFactory(), 25);
        
        assertTrue("Expecting pool to be empty", pool.isEmpty());
        
        final Object resource1 = pool.getResource();
        final Object resource2 = pool.getResource();
        pool.releaseResource(resource1);
        pool.releaseResource(resource2);
        
        // Sleep for 50ms, pool should have been scaled down by now
        Thread.sleep(50);
        assertTrue("Expecting pool to be empty due to down sizing", pool.isEmpty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequiresFactory() {
        new ScalingResourcePool<Object>(null);
    }
}
