package com.qcm.util;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.qcm.dao.impl.WordDaoImpl;
import com.qcm.entity.WordEntity;

public class SynonymUtil extends Thread {
	private String word = "bad";
	private String info;
	private WordEntity wordE;
	private WordDaoImpl wordDaoImpl;
	public SynonymUtil() {
		// TODO Auto-generated constructor stub
	}

	public SynonymUtil(String word) {
		super();
		this.word = word;
	}
	/**
	 * get and store the synonym using word
	 * 
	 * @author JobQ
	 * */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("***start the thread to catch the synonyms");
		try {
			Document doc = Jsoup.connect(
					"http://www.thesaurus.com/browse/" + word + "?s=t").get();
			// 1.5 if this word have prototype, get null if it's prototypr
			// else get the prototype
			// e.g. if the word is dictionaries, we should get dictionary
			String prototype = isPrototype(doc);
			if (StringUtil.isEmpty(prototype)) {
				wordE.setWord(word);
				wordE.setWord_prototype(0);
				wordE.setWord_synonym(getSynonyms(doc));
				wordDaoImpl.insertWord(wordE);
			} else {
				// 2.2 if it's not a prototype
				// we insert a data like dictionaries and prototype = id of
				// dictionary, and no synonyms
				// 2.2.1 if we don't have the prototype, insert before the
				// variant version
				if (wordDaoImpl.getIdByWord(prototype) == -1) {
					wordE.setWord(prototype);
					wordE.setWord_prototype(0);
					wordE.setWord_synonym(getSynonyms(doc));
					wordDaoImpl.insertWord(wordE);
				}

				wordE.setWord(word);
				wordE.setWord_synonym("");
				wordE.setWord_prototype(wordDaoImpl.getIdByWord(prototype));
				wordDaoImpl.insertWord(wordE);
			}

		} catch (HttpStatusException e) {
			// TODO: handle exception
			// if no this word, catch the exception and return the info
			System.out.println("no this word");
			info = "There is no this word, please check.";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("***end of the thread to catch the synonyms");
		super.run();

	}

	public WordEntity getWordE() {
		return wordE;
	}

	public void setWordE(WordEntity wordE) {
		this.wordE = wordE;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	private String isPrototype(Document doc) {
		// 1. get the prototype
		Elements mains = doc.getElementsByClass("main");
		Element main = mains.get(0);
		Elements h1s = main.getElementsByTag("h1");
		Element h1 = h1s.get(0);
		System.out.println("protype is " + h1.html());
		if (h1.html().equals(word))
			return null;
		return h1.html();
	}

	private String getSynonyms(Document doc) {
		// 2. get the word page and catch the synonyms
		int i = 0;
		LinkedHashMap lgm = new LinkedHashMap<String, Integer>();
		String[] results = new String[8];
		Elements content = doc.getElementsByClass("relevancy-list");
		for (org.jsoup.nodes.Element element : content) {
			Elements words = element.getElementsByClass("text");
			for (Element element2 : words) {
				if (i < 8) {
					lgm.put(element2.html(), 0);
					results[i] = element2.html();
					System.out.println(i++ + " " + element2.html());
				} else {
					break;
				}
			}
		}
		// 3. if it need to search, means we dont have this word in database
		String result = JSON.toJSONString(lgm);
		return result;
	}

	public WordDaoImpl getWordDaoImpl() {
		return wordDaoImpl;
	}

	public void setWordDaoImpl(WordDaoImpl wordDaoImpl) {
		this.wordDaoImpl = wordDaoImpl;
	}
}
