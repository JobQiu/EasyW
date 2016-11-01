package com.qcm.dao;

import com.qcm.entity.WordEntity;

public interface IWordDao {
	/**
	 * insert a word
	 * 
	 * @author JobQ
	 * */
	public boolean insertWord(WordEntity word);
}
