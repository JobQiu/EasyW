package com.qcm.dao;


/**
 * @author JobQ
 * */
public interface IIgnoredWordDao {
	/**
	 * if the word is ignored
	 * */
	boolean isIgnored(String word);

	/**
	 * check a list of word, make them empty if they are ignored
	 * */
	String[] removeIgnored(String[] words);

	/**
	 * to ignore a word
	 * */
	void ingoreWord(String word);
}
