package edu.ncsu.csc316.trail.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.trail.data.Landmark;
import edu.ncsu.csc316.trail.data.Trail;
import edu.ncsu.csc316.trail.dsa.DSAFactory;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.list.List;

/**
 * test class for TrailManager() 
 * 
 * @author Diya Patel
 */
public class TrailManagerTest {

	/** the trail manager instance */ 
    private TrailManager manager;

    /**
     * set up for all the test cases 
     * @throws FileNotFoundException if file is not found
     */
    @Before
    public void setUp() throws FileNotFoundException {
        manager = new TrailManager("input/landmark.txt", "input/trail.txt");
    }

    /**
     * tests the getLandmarkByID()
     */
    @Test
    public void testGetLandmarkByID() {
        Landmark lm = manager.getLandmarkByID("L01");
        assertNotNull("Landmark L01 should exist", lm);
        assertEquals("Park Entrance", lm.getDescription());
    }


    /**
     * test for valid call of getDistancesToDestination() method 
     */
    @Test
    public void testGetDistancesToDestinations() {
    	   Map<Landmark, Integer> distances = manager.getDistancesToDestinations("L01");
    	    
    	    // Check that the returned distances map is not null
    	    assertNotNull(distances);

    	    
    	    assertEquals((Integer)3013, distances.get(manager.getLandmarkByID("L02")));
    	    assertEquals((Integer)1046, distances.get(manager.getLandmarkByID("L03")));
    	    assertEquals((Integer)1179, distances.get(manager.getLandmarkByID("L04")));

    	    // null distance for unreachable landmarks
    	    assertNull(distances.get(manager.getLandmarkByID("L11"))); 
    }

    /**
     * test for getProposedFirstAidLocations()
     */
    @Test
    public void testGetProposedFirstAidLocations() {
        Map<Landmark, List<Trail>> firstAidMap = manager.getProposedFirstAidLocations(2);
        assertNotNull("First aid locations map should not be null", firstAidMap);
        assertEquals("Expected 7 landmarks meeting the threshold", 7, firstAidMap.size());

        List<Landmark> keys = DSAFactory.getIndexedList();
        for (Landmark lm : firstAidMap) {
            keys.addLast(lm);
        }

        assertEquals("L01", keys.get(0).getId());
        assertEquals("L02", keys.get(1).getId());
        assertEquals("L03", keys.get(2).getId());
        assertEquals("L04", keys.get(3).getId());
        assertEquals("L05", keys.get(4).getId());
        assertEquals("L06", keys.get(5).getId());
        assertEquals("L07", keys.get(6).getId());
    }

    /**
     * test when the locations map is null
     */
    @Test
    public void testGetProposedFirstAidLocationsNone() {
        Map<Landmark, List<Trail>> locations = manager.getProposedFirstAidLocations(4);
        assertNotNull("Locations map should not be null", locations);
        assertEquals("No landmarks should meet the threshold of 4", 0, locations.size());
    }
}
