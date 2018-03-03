/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> movie_list = new ArrayList<>();
		if(movieRatings == null || movieRatings.isEmpty()) {
			return movie_list;
		}
		for (String title : movieRatings.keySet()) {
			movie_list.add(title);
		}
		return movie_list;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		
		List<String> movie_list = new ArrayList<>();
		if(movieRatings == null || movieRatings.isEmpty()) {
			return movie_list;
		}
		Set< Entry<String, PriorityQueue<Integer> > > entries = movieRatings.entrySet();
		for(Map.Entry<String, PriorityQueue<Integer> > entry : entries) {
			if(entry.getValue().peek() > rating) {
				movie_list.add(entry.getKey());
			}
		}
		
		return movie_list; 
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		if (movieRatings == null || movieRatings.isEmpty()) {
			return new TreeMap<String, Integer>();
		}
		
		TreeMap<String, Integer> result = new TreeMap<>();
		
		/*
		 * use Iterator to iterate the Map and the PriorityQueue of ratings 
		 * in order to avoid ConcurrentModificationException
		 */
		Iterator<Map.Entry<String, PriorityQueue<Integer>>> entries = movieRatings.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, PriorityQueue<Integer>> entry = entries.next();
			
			Iterator<Integer> ratingsIT = entry.getValue().iterator();
			while (ratingsIT.hasNext()) {
				if (ratingsIT.next() < rating) {
		    		ratingsIT.remove();
		    		
		    		if (!result.containsKey(entry.getKey())) { // title already in the result map
		    			result.put(entry.getKey(), 1);
		    		} else {
		    			result.put(entry.getKey(), result.get(entry.getKey()) + 1);
		    		}
		    	}
			}
		    
		    if (entry.getValue().isEmpty()) { // all ratings are removed
		    	entries.remove();
		    }
		}

		return result;
}
}
