/**
 * Name: Matt Cook
 * Course: CS182E Distributed Systems
 * Date: January 22, 2014
 * Assignment 1 - Word List
 * 
 * This program reads in a list of dictionary words from a file, and checks
 * for the spelling of a given word against the words in the dictionary. If
 * the word is spelled incorrectly, it returns all the words in the dictionary
 * whose Levenshtein distance is less than a specified value from the
 * incorrectly-spelled word.
 * 
 */
package words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Matt Cook
 *
 */
public class WordList {
	
	private static final int threshold = 1;
	private static HashSet<String> dict;
	
	/*
	 * Constructor
	 * 
	 * Input: A string, the path of the dictionary
	 * 
	 * This constructor loads words from the dictionary file at path 
	 * the_file into a java.util.Collection<String>. If an IOException
	 * occurs, the stored Collection<String> is left empty. 
	 */
	public WordList(final String the_file) throws IOException {
		dict = new HashSet<String>();
		BufferedReader file = new BufferedReader(new FileReader(the_file));
		try {	
			String line = file.readLine();
			while (line != null) {
				dict.add(line.toLowerCase());
				line = file.readLine();
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe.toString());
		}
		finally {
			file.close();
		}
	}
	
	/*
	 * Method: distance
	 * 
	 * Input: two strings, which are the two words to compare, and an
	 *  int, which is the threshold distance.
	 *  
	 * Output: The distance between word_1 and word_2 up to the_threshold.
	 *  If the distance is greater than the_threshold, the method returns
	 *  an integer greater than the_threshold. 
	 */
	public static int distance(final String word_1,
			final String word_2,
			final int the_threshold) {
		// if we are comparing to the empty string, or if the threshold
		// is zero, then return the max length word between the two
		if (word_1.length() == 0 || word_2.length() == 0 ||
				the_threshold < 0) {
			return Math.max(word_1.length(), word_2.length());
		}
		// recurse to count edit operations (character deletions, additions,
		// or changes
		int deletion = 1 + distance(word_1.substring(1), word_2, the_threshold-1);
		int addition = 1 + distance(word_1, word_2.substring(1), the_threshold-1);
		int substitute;
		// if the first characters are the same, we do not add anything to the
		// distance and recurse
		if (word_1.charAt(0) == word_2.charAt(0)) {
			substitute = distance(word_1.substring(1), word_2.substring(1),
					the_threshold);
		} else {
			substitute = 1 + distance(word_1.substring(1), word_2.substring(1),
					the_threshold-1);
		}
		// return the min distance of the possible edit operations
		return Math.min(Math.min(deletion, addition), substitute);
	}
	

	/*
	 * Method: correct
	 * 
	 * Input: A string that is the word to be checked
	 * 
	 * Output: A boolean, that is true if the_word is in the word list,
	 *  and false otherwise.
	 */
	public boolean correct(final String the_word) {
		if (dict.contains(the_word))
			return true;
		return false;
	}
	
	/*
	 * Method: suggestions
	 * 
	 * Input: A string, which is the word to be checked
	 * 
	 * Output: A List<String> which contains all the suggested spellings
	 *  in the word list for the_word in alphabetical order. If there
	 *  are no suggested spellings, an empty list is returned.
	 */
	public List<String> suggestions(final String the_word) {
		SortedSet<String> possible = new TreeSet<String>();
		for (String word_2: dict) {
			int dist = distance(the_word, word_2, threshold);
			if (dist <= threshold) {
				possible.add(word_2);
			}
		}
		List<String> suggList = new ArrayList<String>();
		suggList.addAll(possible);
		return suggList;
	}
	

	/**
	 * Method: main
	 * 
	 * Input: A single command line argument, which is the word list's
	 *  file path.
	 *  
	 * Output: void
	 * 
	 * Details: Loads the word list, and allows the user to repeatedly type
	 *  words (one per line) until the user inputs an EOF character.
	 *  The output of correct() and suggestions() is printed to the console
	 *  with each word the user enters. Throws IOException with incorrect 
	 *  path to dictionary or bad dictionary file.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Error: Please provide path to dictionary");
			System.exit(0);
		} 
		WordList dictionary = new WordList(args[0]);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				String word = input.readLine();
				boolean correct = dictionary.correct(word);
				System.out.println(correct);
				List<String> suggestions = dictionary.suggestions(word);
				for (int i = 0; i < suggestions.size(); ++i) {
					System.out.println(suggestions.get(i));
				}
			}
		} catch(IOException ioe) {
			System.out.println(ioe.toString());
		} 
		finally {
			input.close();
		}
	}
}
