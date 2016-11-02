package com.qcm.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.qcm.entity.WordEntity;

/**
 * @author JobQ
 * */
public interface IWordDao {
	/**
	 * insert a word
	 * */
	public boolean insertWord(WordEntity word);
	/**
	 * get the word according to the id
	 * */
	public WordEntity getWordById(Integer id);

	/**
	 * get the words according to the id array
	 * */
	public List<WordEntity> getWordByIds(Integer[] ids);
	
	/**
	 * get the synonym of a word
	 * */
	public LinkedHashMap<String, Integer> getSynonymById(Integer id);

	/**
	 * get id according to the word
	 * */
	public Integer getIdByWord(String word);

	/**
	 * get the word entity by word
	 * */
	public WordEntity getWordEntityByWord(String word);

	/**
	 * get the word's synonym and its usage times
	 * */
	public LinkedHashMap<String, Integer> getSynonymByWord(String word);
}
