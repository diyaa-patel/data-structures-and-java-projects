package edu.ncsu.csc316.trail.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ReportManager 
 * 
 * @author Diya Patel
 */
public class ReportManagerTest {

	/** manager instance */
    private ReportManager manager;

    /**
     * set up for each of the test cases
     * @throws Exception if there is an exception to throw 
     */
    @Before
    public void setUp() throws Exception {
        manager = new ReportManager("input/landmark.txt", "input/trail.txt");
    }

    /**
     * test for getProposedFirstAidLocationsReport()
     */
    @Test
    public void testGetProposedFirstAidLocationsThresholdTwo() {
        String report = manager.getProposedFirstAidLocations(2);
        assertNotNull("Report should not be null", report);

        String expectedOrder[] = {"Park Entrance (L01) - 3 intersecting trails",
                                  "Entrance Fountain (L02) - 2 intersecting trails",
                                  "Entrance Restrooms (L04) - 2 intersecting trails",
                                  "Overlook 1 (L05) - 2 intersecting trails",
                                  "Overlook 2 (L07) - 2 intersecting trails",
                                  "Rock Formation 1 (L06) - 2 intersecting trails",
                                  "Waste Station 1 (L03) - 2 intersecting trails"};

        int lastIndex = -1;
        for (String landmark : expectedOrder) {
            int currentIndex = report.indexOf(landmark);
            assertTrue("Expected landmark " + landmark + " in the report", currentIndex > lastIndex);
            lastIndex = currentIndex;
        }
    }

    /**
     * test for invalid input when getting the first aid location report 
     */
    @Test
    public void testGetProposedFirstAidLocationsThresholdZero() {
        String report = manager.getProposedFirstAidLocations(0);
        assertEquals("Number of intersecting trails must be greater than 0.", report);
    }

    /**
     * test when there are no landmarks that have the amount of intersecting trails
     */
    @Test
    public void testGetProposedFirstAidLocationsNoLandmarks() {
        String report = manager.getProposedFirstAidLocations(4);
        assertEquals("No landmarks have at least 4 intersecting trails.", report);
    }

    /**
     * test for valid getDistancesReport()
     */
    @Test
    public void testGetDistancesReportValidStart() {
        String report = manager.getDistancesReport("L02");
        System.out.println(report); // add this line temporarily to debug
        assertNotNull("Distances report should not be null", report);
    }


    /**
     * test for isolated landmark
     */
    @Test
    public void testGetDistancesReportIsolatedLandmark() {
        String report = manager.getDistancesReport("L11");
        assertNotNull(report);
        assertEquals("Landmarks Reachable from Campsite 1 (L11) {\n" +
                     "   1066 feet to Campsite Restrooms (L12)\n" +
                     "}", report.trim());
    }
}
