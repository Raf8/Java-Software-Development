/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer> > rate_map = new TreeMap<>();
		if(allUsersRatings == null || allUsersRatings.isEmpty()) {
			return rate_map;
		}
		
		for(UserMovieRating elem: allUsersRatings) {
			if(elem == null || elem.getMovie() == null || elem.getMovie().isEmpty() || elem.getUserRating() < 0) {
				continue;
			}
			else {
				PriorityQueue<Integer> movie_rating = new PriorityQueue<>();
				String movie = elem.getMovie().toLowerCase();
				if(!rate_map.containsKey(movie)) {
					movie_rating.add(elem.getUserRating());
					rate_map.put(movie, movie_rating);
				}else {
					rate_map.get(movie).add(elem.getUserRating());
				}		
			}
		}
		return rate_map;
	}
}

