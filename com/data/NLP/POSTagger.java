package com.data.NLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class POSTagger {
	public static List<String> getNounsFromSentence(String sent,List<String> wordlist) {
		Properties props = new Properties();

		props.setProperty("annotators", "tokenize, ssplit, pos");
		List<String> wordNNP = new ArrayList<String>();
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = new Annotation(sent);
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				// this is the POS tag of the token

				if(wordlist.contains(word)) {
					String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
					if (pos.equalsIgnoreCase("NNP") || pos.equalsIgnoreCase("NN")|| pos.equalsIgnoreCase("NNS")|| pos.equalsIgnoreCase("NNPS") ) {
						wordNNP.add(word.toLowerCase());
					}
				}
			}
		}
		return wordNNP;

	}
	public static List<String> getNouns(String sent) {
		Properties props = new Properties();

		props.setProperty("annotators", "tokenize, ssplit, pos");
		List<String> wordNNP = new ArrayList<String>();
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = new Annotation(sent);
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				// this is the POS tag of the token


					String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
					if (pos.equalsIgnoreCase("NNP") || pos.equalsIgnoreCase("NN")|| pos.equalsIgnoreCase("NNS")|| pos.equalsIgnoreCase("NNPS") ) {
						wordNNP.add(word);

				}
			}
		}
		return wordNNP;

	}
}
