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
import java.util.HashSet;
import java.util.List;

/**
 * @author Matt Cook
 *
 */
public class WordList {
	
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
				dict.add(line);
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
		
		return -1;
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
		return null;
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
	 *  with each word the user enters.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
