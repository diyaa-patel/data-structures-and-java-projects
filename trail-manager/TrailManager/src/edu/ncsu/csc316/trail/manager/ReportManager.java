package edu.ncsu.csc316.trail.manager;

import java.io.FileNotFoundException;
import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.trail.data.Landmark;
import edu.ncsu.csc316.trail.data.Trail;
import edu.ncsu.csc316.trail.dsa.Algorithm;
import edu.ncsu.csc316.trail.dsa.DSAFactory;
import edu.ncsu.csc316.trail.dsa.DataStructure;

/**
 * The ReportManager class generates reports based on trail and landmark data.
 * It provides methods to compute shortest distances and identify first aid locations.
 *
 * @author Jayani Sivakumar
 */
public class ReportManager {

/** Manages landmark and trail data */
private TrailManager trailManager;

/**
* Constructs ReportManager and loads data from the specified files.
*
* @param pathToLandmarkFile the path to the landmark input file
* @param pathToTrailFile the path to the trail input file
* @throws FileNotFoundException if the file paths are invalid
*/
public ReportManager(String pathToLandmarkFile, String pathToTrailFile) throws FileNotFoundException {
// initializing trail manager

// using our merge sorter as the sorting algorithm
//DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
	
this(pathToLandmarkFile, pathToTrailFile, DataStructure.UNORDEREDLINKEDMAP);

}

public ReportManager(String pathToLandmarkFile, String pathToTrailFile, DataStructure mapType) throws FileNotFoundException {
	this.trailManager = new TrailManager(pathToLandmarkFile, pathToTrailFile);
	DSAFactory.setMapType(mapType);
    DSAFactory.setListType(DataStructure.SINGLYLINKEDLIST);
    DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
    DSAFactory.setNonComparisonSorterType(Algorithm.COUNTING_SORT);
} 

/**
* Generates report of distances from a given landmark.
*
* @param originLandmark ID of the starting landmark
* @return string containing the distances report
*/
public String getDistancesReport(String originLandmark) {
// getting distances from trail manager
Map<Landmark, Integer> distances = trailManager.getDistancesToDestinations(originLandmark);

// check if invalid landmark
if (distances.isEmpty()) {
return "The provided landmark ID (" + originLandmark + ") is invalid for the park.";
}

// no reachable landmarks
if (distances.size() == 1) {
return "No landmarks are reachable from " + trailManager.getLandmarkByID(originLandmark).getDescription() + " (" + originLandmark + ").";
}

// converting distances map into list for sorting
List<Entry<Landmark, Integer>> entryList = DSAFactory.getIndexedList();
for (Entry<Landmark, Integer> entry : distances.entrySet()) {
entryList.add(entryList.size(), entry);
}

// converting List to an array
@SuppressWarnings("unchecked")
Entry<Landmark, Integer>[] entries = new Entry[entryList.size()];
for (int i = 0; i < entryList.size(); i++) {
entries[i] = entryList.get(i);
}

// sorting list by distance in ascending order, then alphabetically
Sorter<Entry<Landmark, Integer>> sorter = DSAFactory.getComparisonSorter(new DistanceComparator());

// mergesort to sort entries
sorter.sort(entries);

// list with sorted elements
List<Entry<Landmark, Integer>> sortedList = DSAFactory.getIndexedList();
for (int i = 0; i < entries.length; i++) {
sortedList.add(sortedList.size(), entries[i]);
}

//printing our output
StringBuilder report = new StringBuilder("Landmarks Reachable from " + trailManager.getLandmarkByID(originLandmark).getDescription() + " (" + originLandmark + ") {\n");
   for (Entry<Landmark, Integer> entry : sortedList) {
       int distance = entry.getValue();
       if (entry.getKey().getId().equals(originLandmark)) {
           continue;
       }
       report.append("   ").append(distance).append(" feet");

// if distance is greater than 5280 feet, convert to miles
       if (distance > 5280) {
           double miles = distance / 5280.0;
           report.append(" (").append(String.format("%.2f", miles)).append(" miles)");
       }
report.append(" to ").append(entry.getKey().getDescription()).append(" (").append(entry.getKey().getId()).append(")");
report.append("\n");
}
   report.append("}");
return report.toString();
}

/**
     * Generates report of proposed first aid station locations that
     * have at least the specified number of intersecting trails.
     *
     * @param numberOfIntersectingTrails minimum number of intersecting trails required
     * @return string containing first aid station report
     */
    public String getProposedFirstAidLocations(int numberOfIntersectingTrails) {
    // if 0 or a negative number
        if (numberOfIntersectingTrails <= 0) {
            return "Number of intersecting trails must be greater than 0.";
        }

        // getting proposed first aid station locations from trail manager
        Map<Landmark, List<Trail>> locations = trailManager.getProposedFirstAidLocations(numberOfIntersectingTrails);

        // if no landmarks
        if (locations.isEmpty()) {
            return "No landmarks have at least " + numberOfIntersectingTrails + " intersecting trails.";
        }

        // map to store landmark and number of intersecting trails
        Map<Landmark, Integer> landmarkTrailCount = DSAFactory.getMap(null);
       
        // landmarks and their trail counts
        for (Entry<Landmark, List<Trail>> entry : locations.entrySet()) {
            landmarkTrailCount.put(entry.getKey(), entry.getValue().size());
        }

        // converting map entries into list for sorting
        List<Entry<Landmark, Integer>> entryList = DSAFactory.getIndexedList();
        for (Entry<Landmark, Integer> entry : landmarkTrailCount.entrySet()) {
            entryList.add(entryList.size(), entry);
        }

        // converting list to a generic array
        @SuppressWarnings("unchecked")
        Entry<Landmark, Integer>[] entries = (Entry<Landmark, Integer>[]) new Entry[entryList.size()];

        for (int i = 0; i < entryList.size(); i++) {
            entries[i] = entryList.get(i);
        }

        // sorting list by number of intersecting trails in descending order, then alphabetically
        Sorter<Entry<Landmark, Integer>> sorter = DSAFactory.getComparisonSorter(new FirstAidComparator());

        // mergesort to sort the entries
        sorter.sort(entries);

        // list that contains the sorted elements
        List<Entry<Landmark, Integer>> sortedList = DSAFactory.getIndexedList();
        for (int i = 0; i < entries.length; i++) {
            sortedList.add(sortedList.size(), entries[i]);
        }

        // printing our output
        StringBuilder report = new StringBuilder("Proposed Locations for First Aid Stations {\n");
        for (Entry<Landmark, Integer> entry : sortedList) {
            report.append("   ").append(entry.getKey().getDescription()).append(" (")
                  .append(entry.getKey().getId()).append(") - ")
                  .append(entry.getValue()).append(" intersecting trails\n");
        }
        report.append("}");
        return report.toString();
    }
       
    /**
     * Private static class for sorting distances in ascending order.
     *
     * @author Jayani Sivakumar
     */
    private static class DistanceComparator implements Comparator<Entry<Landmark, Integer>> {
   
    /**
* Compare method used for sorting distances in ascending order.
*
* @param e1 first entry to compare with
         * @param e2 second entry to compare with
         * @return negative integer if e1 has more trails than e2, a positive integer
         *     if e1 has fewer trails than e2, or 0 if they are equal
*/
    @Override
        public int compare(Entry<Landmark, Integer> e1, Entry<Landmark, Integer> e2) {
            int distanceCompare = Integer.compare(e1.getValue(), e2.getValue());
            if (distanceCompare != 0) {
                return distanceCompare;
            }
            return e1.getKey().getDescription().compareTo(e2.getKey().getDescription());
        }
    }


    /**
     * Private static class for sorting first aid station locations.
     * Sorts by number of intersecting trails in descending order, then alphabetically.
     *
     * @author Jayani Sivakumar
     */
    private static class FirstAidComparator implements Comparator<Entry<Landmark, Integer>> {
   
    /**
         * Compare method for sorting first aid station locations.
         *
         * @param e1 first entry to compare with
         * @param e2 second entry to compare with
         * @return negative integer if e1 comes before e2, a positive integer
         *     if e1 comes after e2, or 0 if they are equal
         */
    @Override
        public int compare(Entry<Landmark, Integer> e1, Entry<Landmark, Integer> e2) {
            int trailCompare = Integer.compare(e2.getValue(), e1.getValue());
            if (trailCompare != 0) {
                return trailCompare;
            }
            return e1.getKey().getDescription().compareTo(e2.getKey().getDescription());
        }
    }

}