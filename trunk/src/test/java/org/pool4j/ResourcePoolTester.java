package org.pool4j;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests the basic functionality of the all known ResourcePool implementation
 * @author Evan Worley
 */
@RunWith(Parameterized.class)
public class ResourcePoolTester {
    
    private static final Collection<Object> resources = new ArrayList<Object>(Arrays.asList(1, 2, 3));
    
    @Parameters 
    public static Collection<ResourcePool<Object>[]> data() {
        
        List<ResourcePool<Object>[]> resourcePools = new ArrayList<ResourcePool<Object>[]>();
        
        // Add a new entry here for every ResourcePool implementation
        resourcePools.add(new FiniteResourcePool[] {new FiniteResourcePool<Object>(resources)});
        resourcePools.add(new ScalingResourcePool[] {new ScalingResourcePool<Object>(new IntFactory())});
        
        return resourcePools;
    }
    
    private ResourcePool<Object> resourcePool;
    
    public ResourcePoolTester(ResourcePool<Object> resourcePool) {
        this.resourcePool = resourcePool;
    }
    
    @Before
    public void setupTest() {
        // Make sure the pool has the correct set of resources, this isolates
        // the changes that any one test method my make to the pool
        while (!resourcePool.isEmpty()) {
            resourcePool.getResource();
        }
        for (Object resource : resources) {
            resourcePool.releaseResource(resource);
        }
    }
    
    @Test
    public void isEmpty() {
        assertFalse("Expecting pool to have resources available", resourcePool.isEmpty());
        
        for (int i=0; i < resources.size(); i++) {
            resourcePool.getResource();
        }
        
        assertTrue("Expecting pool to not have any resources available", resourcePool.isEmpty());
    }
    
    @Test
    public void verifyReleasePopulatesPool() {
        while (!resourcePool.isEmpty()) {
            resourcePool.getResource();
        }
        
        assertTrue("Expecting the pool to be empty", resourcePool.isEmpty());

        // Now that the pool is empty, release one of the resources
        resourcePool.releaseResource(new Integer(5));
        
        assertFalse("Expecting the pool to not be empty", resourcePool.isEmpty());
    }

}
