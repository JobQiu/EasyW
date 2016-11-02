package com.qcm.other.learnpage;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TestJsoup {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.thesaurus.com/browse/bad?s=t")
				.get();
		try {
			doc = Jsoup.connect(
					"http://www.thesaurus.com/browse/efiejifbad?s=t").get();
		} catch (HttpStatusException e) {
			// TODO: handle exception
			System.out.println("no this word");
		}

		String title = doc.title();
		int i = 1;
		Elements content = doc.getElementsByClass("relevancy-list");
		for (org.jsoup.nodes.Element element : content) {
			Elements words = element.getElementsByClass("text");
			for (Element element2 : words) {
				System.out.println(i++ + " " + element2.html());
			}
		}
	}
}
