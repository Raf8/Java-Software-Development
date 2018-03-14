

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * SD2x Homework #11
 * Improve the efficiency of the code below according to the guidelines in the assignment description.
 * Please be sure not to change the signature of the detectPlagiarism method!
 * However, you may modify the signatures of any of the other methods as needed.
 */

public class PlagiarismDetector {

	public static Map<String, Integer> detectPlagiarism(String dirName, int windowSize, int threshold) {
		/*File dirFile = new File(dirName);
		String[] files = dirFile.list();
		
		Map<String, Integer> numberOfMatches = new HashMap<String, Integer>();
		
		for (int i = 0; i < files.length; i++) {
			String file1 = files[i];

			for (int j = 0; j < files.length; j++) { 
				String file2 = files[j];
				
				Set<String> file1Phrases = createPhrases(dirName + "/" + file1, windowSize); 
				Set<String> file2Phrases = createPhrases(dirName + "/" + file2, windowSize); 
				
				if (file1Phrases == null || file2Phrases == null)
					return null;
				
				Set<String> matches = findMatches(file1Phrases, file2Phrases);
				
				if (matches == null)
					return null;
								
				if (matches.size() > threshold) {
					String key = file1 + "-" + file2;
					if (numberOfMatches.containsKey(file2 + "-" + file1) == false && file1.equals(file2) == false) {
						numberOfMatches.put(key,matches.size());
					}
				}				
			}
			
		}		
		
		return sortResults(numberOfMatches);*/
		
		File dirFile = new File(dirName);
		String[] files = dirFile.list();
		
		Map<String, Integer> numberOfMatches = new HashMap<String, Integer>();
		
		// aha moment
		// get all the phrases 1 time, put in a Map to lookup
		Map<String, Set<String>> filePhrases = new HashMap<>();
		for (String file : files) {
			if (file != null) {
				filePhrases.put(file, createPhrases(dirName + "/" + file, windowSize));
			}
		}
		
		/*
		 * from looping n^2
		 * to (n-1) + (n-2) + ... 1 ~ n^2 / 2
		 * reduce runtime by half
		 */
		for (int i = 0; i < files.length - 1; i++) { 
			String file1 = files[i];
			
			Set<String> file1Phrases = filePhrases.get(file1);
			if (file1Phrases == null) {
				return null;
			}
			
			for (int j = i + 1; j < files.length; j++) { 
				String file2 = files[j];				 
				
				
				Set<String> file2Phrases = filePhrases.get(file2); 				
				if (file2Phrases == null)
					return null;
				
				int matches = findMatches(file1Phrases, file2Phrases);
				if (matches > threshold) {
					String key = file1 + "-" + file2;
					numberOfMatches.put(key,matches);
				}	
												
			}
			
		}		

		return sortResults(numberOfMatches);

	}

	
	/*
	 * This method reads the given file and then converts it into a Collection of Strings.
	 * It does not include punctuation and converts all words in the file to uppercase.
	 */
	protected static List<String> readFile(String filename) {
		if (filename == null) return null;
		
		//List<String> words = new LinkedList<String>();  // Change 1 time 2.96 -> 2.1
		List<String> words = new ArrayList<String>();
		
		try {
			Scanner in = new Scanner(new File(filename));
			while (in.hasNext()) {
				words.add(in.next().replaceAll("[^a-zA-Z]", "").toUpperCase());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return words;
	}

	
	/*
	 * This method reads a file and converts it into a Set/List of distinct phrases,
	 * each of size "window". The Strings in each phrase are whitespace-separated.
	 */
	protected static Set<String> createPhrases(String filename, int window) {
		if (filename == null || window < 1) return null;
				
		List<String> words = readFile(filename);
		
		Set<String> phrases = new HashSet<String>(); // Change 2 HashSet -> TreeSet Time 2.1 -> 2.255
		
		for (int i = 0; i < words.size() - window + 1; i++) {
			String phrase = "";
			for (int j = 0; j < window; j++) {
				phrase += words.get(i+j) + " ";
			}

			phrases.add(phrase);

		}
		
		return phrases;		
	}

	
	/*
	 * Returns a Set of Strings that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	/*protected static Set<String> findMatches(Set<String> myPhrases, Set<String> yourPhrases) {
	
		Set<String> matches = new TreeSet<String>(); // Change 3 HashSet to TreeSet time 2.1 -> 2.064
		
		if (myPhrases != null && yourPhrases != null) {
		
			for (String mine : myPhrases) {
				for (String yours : yourPhrases) {
					if (mine.equalsIgnoreCase(yours)) {
						matches.add(mine);
					}
				}
			}
		}
		return matches; 
	}*/
	
	/*
	 * Returns the number of Strings that occur in both of the List parameters.
	 */	
	protected static int findMatches(Set<String> myPhrases, Set<String> yourPhrases) {
		int count = 0;
		// convert myPhrases to List
		// because iterates through HashSet is not O(1)
		List<String> myPhrasesList = new ArrayList<>(myPhrases);
		
		if (myPhrasesList != null && yourPhrases != null) {
		
			for (String mine : myPhrasesList) {
				if (yourPhrases.contains(mine)) { // HashSet contains is O(1)
					count++;
				}
			}
		}
		return count;
}
	
	/*
	 * Returns a LinkedHashMap in which the elements of the Map parameter
	 * are sorted according to the value of the Integer, in non-ascending order.
	 */
	protected static LinkedHashMap<String, Integer> sortResults(Map<String, Integer> possibleMatches) {
		
		// Because this approach modifies the Map as a side effect of printing 
		// the results, it is necessary to make a copy of the original Map
		Map<String, Integer> copy = new TreeMap<String, Integer>(); //change 4 HashMap to TreeMap time 2.064 -> 2

		for (String key : possibleMatches.keySet()) {
			copy.put(key, possibleMatches.get(key));
		}	
		
		LinkedHashMap<String, Integer> list = new LinkedHashMap<String, Integer>(); //change 5 Linked -> Treemap
		//TreeMap<String, Integer> list = new TreeMap<String, Integer>(); // Time 2 -> 2.05
		for (int i = 0; i < copy.size(); i++) {
			int maxValue = 0;
			String maxKey = null;
			for (String key : copy.keySet()) {
				if (copy.get(key) > maxValue) {
					maxValue = copy.get(key);
					maxKey = key;
				}
			}
			
			list.put(maxKey, maxValue);
			
			copy.put(maxKey, -1);
		}

		return list;
	}
	
	/*
	 *     public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	 * 
	 * 
	 * */
	/*
	 * This method is here to help you measure the execution time and get the output of the program.
	 * You do not need to consider it for improving the efficiency of the detectPlagiarism method.
	 */
    public static void main(String[] args) {
    	/*if (args.length == 0) {
    		System.out.println("Please specify the name of the directory containing the corpus.");
    		System.exit(0);
    	}*/
    	String directory = "corpus";
    	if(directory == null || directory.isEmpty()) {
    		return;
    	}
    	long start = System.currentTimeMillis();
    	Map<String, Integer> map = PlagiarismDetector.detectPlagiarism(directory, 4, 5);
    	long end = System.currentTimeMillis();
    	double timeInSeconds = (end - start) / (double)1000;
    	System.out.println("Execution time (wall clock): " + timeInSeconds + " seconds");
    	if(map == null || map.isEmpty()) {
    		System.out.println(" Nothing common detected ");
    	}
    	Set<Map.Entry<String, Integer>> entries = map.entrySet();
    	for (Map.Entry<String, Integer> entry : entries) {
    		System.out.println(entry.getKey() + ": " + entry.getValue());
    	}
    }

}
