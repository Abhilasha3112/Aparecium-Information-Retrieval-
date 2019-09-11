package com.computations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.baseClass.GlobalVariables;
import com.data.NLP.Lemmatization;

public class DifferenceBetweenIntentAndList {
	static GlobalVariables globalVariables = new GlobalVariables();
	public static String differenceCalculator(List<String> objects,String sentence, String entity) {
		Set<String> set = new LinkedHashSet<String>();
		set.addAll(objects);
		objects.clear();
		objects.addAll(set);
		List<String> concatObjects = new ArrayList();
		for (int i = 0; i < objects.size(); i++) {
			String concat = objects.get(i);
			concat = concat.replaceAll(" ", "");
			concat = concat.replaceAll(",", "");
			concatObjects.add(concat.toLowerCase());
		}
		//System.out.println("Concatenated object list=:"+concatObjects);
		for (int i = 0; i < objects.size(); i++) {
			sentence = sentence.replaceAll(objects.get(i), concatObjects.get(i));
		}
		//System.out.println("Actual Sentence now = ");
		//System.out.println(sentence);
		Lemmatization lemma = new Lemmatization();
		List<String> textNews = lemma.lemmatize(sentence.toLowerCase());
		List<Integer> indexOfRootWords = locOfRootWords(textNews);

		//System.out.println("Indexes of root words in sent are:" + indexOfRootWords);
		for (int j = 0; j < indexOfRootWords.size(); j++) {
			//System.out.println("Chescking for confirmation:............" + textNews.get(indexOfRootWords.get(j)));
		}
		List<Double> differencesBetweenRootAndNN = new ArrayList();
		for (int k = 0; k < concatObjects.size(); k++) {
			List<Integer> occurencesOfWord = new ArrayList();
			for (int j = 0; j < textNews.size(); j++) {
				if (textNews.get(j).equalsIgnoreCase(concatObjects.get(k))) {
					occurencesOfWord.add(j);
				}
			}
			//if (k == 0) {
			//	System.out.println("Checking if we get the right location word Index from the text: "
			//			+ textNews.get(occurencesOfWord.get(0)));
			//}

			int sum = 0;
			for (int j = 0; j < indexOfRootWords.size(); j++) {
				int minDiff = 100000;
				for (int z = 0; z < occurencesOfWord.size(); z++) {
					if (Math.abs(indexOfRootWords.get(j) - occurencesOfWord.get(z)) < minDiff) {
						minDiff = Math.abs(indexOfRootWords.get(j) - occurencesOfWord.get(z));
					}
				}
				sum += minDiff;
			}
			differencesBetweenRootAndNN.add(sum / (indexOfRootWords.size() * 1.0));
		}
		//System.out.println("SIZE OF DIFFERENCE AND ACTUAL WORDS=:" + differencesBetweenRootAndNN.size() + " & "
		//		+ concatObjects.size());
		for (int j = 0; j < differencesBetweenRootAndNN.size(); j++) {
			System.out.println("Actual Word: " + concatObjects.get(j) + " & Difference Mean: "
					+ differencesBetweenRootAndNN.get(j));
		}
		try {
			System.out.println(entity + " FOUND........." + objects
					.get(differencesBetweenRootAndNN.indexOf(Collections.min(differencesBetweenRootAndNN))));


			return objects
					.get(differencesBetweenRootAndNN.indexOf(Collections.min(differencesBetweenRootAndNN)));
		} catch (Exception e) {
               return "";
		}
	}

	public static List<Integer> locOfRootWords(List<String> textNews) {
		String intent_riot[] = globalVariables.getIntent_riot();
		List<Integer> indexOfRootWords = new ArrayList();
		for (int j = 0; j < intent_riot.length; j++) {
			for (int k = 0; k < textNews.size(); k++) {
				if (textNews.get(k).equalsIgnoreCase(intent_riot[j])) {
					indexOfRootWords.add(k);
				}
			}

		}

		return indexOfRootWords;
	}
}
