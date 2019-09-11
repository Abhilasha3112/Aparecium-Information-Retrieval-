package com.data.NLP;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;

import java.util.*;

public class Tokenize {
	public List<String> tokenize(String text) {
		List<String> result=new ArrayList();
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		CoreDocument document = new CoreDocument(text);
		pipeline.annotate(document);
		List<CoreLabel> firstSentenceTokens = document.sentences().get(0).tokens();
		for (CoreLabel token : firstSentenceTokens) {
			result.add(token.word());
		}
		return result;
	}
	public static void main(String args[]) {
		Tokenize tok=new Tokenize();
		System.out.println(tok.tokenize("This is how you do it"));
	}
}
