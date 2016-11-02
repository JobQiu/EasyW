package com.qcm.entity;

import java.util.LinkedHashMap;

import com.qcm.util.StringUtil;

public class WordEntity {
	private Integer id;
	private String word;
	private Integer word_prototype;
	private String word_synonym;
	private LinkedHashMap<String, Integer> lhm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getWord_prototype() {
		return word_prototype;
	}

	public void setWord_prototype(Integer word_prototype) {
		this.word_prototype = word_prototype;
	}

	public String getWord_synonym() {
		return word_synonym;
	}

	public void setWord_synonym(String word_synonym) {
		this.word_synonym = word_synonym;
	}

	public LinkedHashMap<String, Integer> getLhm() {
		return StringUtil.string2LinkedHashMap(word_synonym);
	}

	public void setLhm(LinkedHashMap<String, Integer> lhm) {
		this.lhm = lhm;
	}
}
