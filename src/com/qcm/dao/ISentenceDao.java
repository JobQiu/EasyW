package com.qcm.dao;

import java.util.List;

/**
 * @author JobQ
 * */
public interface ISentenceDao {
	/**
	 * divide a sentence to String[] and deal with each word
	 * */
	public List<String> divideSentence(String sentence);

}
