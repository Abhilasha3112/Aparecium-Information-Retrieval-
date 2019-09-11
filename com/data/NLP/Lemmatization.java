package com.data.NLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.baseClass.GlobalVariables;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Lemmatization {
	public static List<String> lemmatize(String documentText) {
		StanfordCoreNLP pipeline;
		Properties props;
		props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma");
		pipeline = new StanfordCoreNLP(props);
		List<String> lemmas = new ArrayList<String>();
		// Create an empty Annotation just with the given text
		Annotation document = new Annotation(documentText);
		// run all Annotators on this text
		pipeline.annotate(document);
		// Iterate over all of the sentences found
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			// Iterate over all tokens in a sentence
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// Retrieve and add the lemma for each word into the
				// list of lemmas
				lemmas.add(token.get(LemmaAnnotation.class));
			}
		}
		return lemmas;
	}

	public static List<String> lemmatize(List<String> documentText) {
		List<String> lemmas = new ArrayList<String>();
		for (int i = 0; i < documentText.size(); i++) {

			StanfordCoreNLP pipeline;
			Properties props;
			props = new Properties();
			props.put("annotators", "tokenize, ssplit, pos, lemma");
			pipeline = new StanfordCoreNLP(props);

			// Create an empty Annotation just with the given text
			Annotation document = new Annotation(documentText.get(i));
			// run all Annotators on this text
			pipeline.annotate(document);
			// Iterate over all of the sentences found
			List<CoreMap> sentences = document.get(SentencesAnnotation.class);
			for (CoreMap sentence : sentences) {
				// Iterate over all tokens in a sentence
				for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
					// Retrieve and add the lemma for each word into the
					// list of lemmas
					lemmas.add(token.get(LemmaAnnotation.class));
				}
			}
		}
		return lemmas;
	}
	
	public static void main(String args[]) {
		GlobalVariables globalVariables=new GlobalVariables();
		String sentence="Home States Tamil Nadu Kamal Haasan's Makkal Needhi Maiam gives ticket to retired cop, film producer\nThe party released names of candidates for 20 constituencies in Tamil Nadu and for the lone seat in the union territory Puducherry. Share Via Email Published: 20th March 2019 07:40 PM | Last Updated: 21st March 2019 05:13 AM | A+ A A- Makkal Needhi Maiam President Kamal Haasan releases a list of candidates for the upcoming Lok Sabha elections in Chennai Wednesday March 20 2019. (Photo| Ashwin Prasath, EPS) By Express News Service CHENNAI: Retired Inspector General of Police A G Mourya and film producer Kameela Nasser will make their electoral debuts in the upcoming parliamentary polls for the Makkal Needhi Maiam. Mourya will contest from Chennai North and Kameela from Chennai Central.\nMNM president Kamal Haasan on Monday announced 21 candidates for the Lok Sabha elections. “The remaining candidates will be announced in Coimbatore on Sunday,” he said, explaining that interviews for candidates from some constituencies were still on. Kamal, however, refused to confirm or deny his own candidature, even as rumours about his contesting from hometown Ramanathapuram are doing the rounds. “All speculations will be put to rest on Sunday,” Haasan said.\nThe list of candidates announced includes seven businessmen, three advocates, and two doctors. Retired district judge K Guruviah and founder of the Dalit Munnetra Kazhagam, Anbin Poyyamozhi have also been given seats and will be contesting from Nagapattinam and Villupuram respectively. Standing true to their promise of allowing non-party members to contest the polls, Makkal Needhi Maiam has given a seat to G Ebenezer, a businessman from Chennai, who caught the party president’s eye when he actively led jallikattu protests at the Marina in 2017. Ebenezer, who had formed his own party with youngsters after the protests, will be contesting from Kanniyakumari. When I was approached by the MNM to take part in interviews, I readily accepted and I am thrilled to represent the party from Kanniyakumari, which is close to my home town,” Ebenezar told Express.\nSpeaking to press after announcing the candidates, Kamal Haasan criticised the increasingly visible dynasty politics of the Dravidian majors and their manifestos that were released on Monday. “They used to say it was the land of the Rising Sun (on dynasty politics in the DMK) but they clearly seem to have misspelled the word,” Haasan, said in a jocular vein. Stay up to date on all the latest Tamil Nadu news with The New Indian Express App. Download now (Get the news that matters from New Indian Express on WhatsApp. Click this link and hit 'Click to Subscribe' . Follow the instructions after that.) TAGS ";


			System.out.println(lemmatize(sentence));

	}

}
