package com.data.NLP;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.baseClass.GlobalVariables;

public class NamedEntityRecognition {
	/**
	 * identify Name,organization location etc entities and return Map<List>
	 * 
	 */

	public static LinkedHashMap<String, LinkedHashSet<String>> identifyNER(String text, String model) {
		LinkedHashMap<String, LinkedHashSet<String>> map = new LinkedHashMap<String, LinkedHashSet<String>>();
		String serializedClassifier = model;
		// System.out.println(serializedClassifier);
		CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		List<List<CoreLabel>> classify = classifier.classify(text);
		for (List<CoreLabel> coreLabels : classify) {
			for (CoreLabel coreLabel : coreLabels) {

				String word = coreLabel.word();
				String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
				if (!"O".equals(category)) {
					if (map.containsKey(category)) {
						// key is already their just insert in arraylist
						map.get(category).add(word);
					} else {
						LinkedHashSet<String> temp = new LinkedHashSet<String>();
						temp.add(word);
						map.put(category, temp);
					}
					System.out.println(word + ":" + category);
				}

			}

		}
		return map;
	}

	public static List<String> identifyAllNER(String text, String model) {
		LinkedHashMap<String, LinkedHashSet<String>> map = new LinkedHashMap<String, LinkedHashSet<String>>();
		List<String> allNER = new ArrayList();
		String serializedClassifier = model;
		// System.out.println(serializedClassifier);
		CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		List<List<CoreLabel>> classify = classifier.classify(text);
		for (List<CoreLabel> coreLabels : classify) {
			for (CoreLabel coreLabel : coreLabels) {

				String word = coreLabel.word();
				String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
				if (!"O".equals(category)) {
					if (map.containsKey(category)) {
						// key is already their just insert in arraylist
						map.get(category).add(word);
					} else {
						LinkedHashSet<String> temp = new LinkedHashSet<String>();
						temp.add(word);
						map.put(category, temp);
					}
					// System.out.println(word + ":" + category);
					if (!(category.equalsIgnoreCase("location") || category.equalsIgnoreCase("misc"))) {
						allNER.add(word.toLowerCase());
					}

				}

			}

		}
		return allNER;
	}
	static LinkedHashSet<String> tempset;
	public static String getActorAssociated(String sentence) {

		try {
			Lemmatization lemma = new Lemmatization();
			POSTagger posTagger = new POSTagger();
			GlobalVariables globalVariables = new GlobalVariables();
			String[] locationNames = globalVariables.getLocationNames();
			String intent_riot[] = globalVariables.getIntent_riot();
			// String newSent1 = "Advertising BHU proctor booked for ‘beating up journalist’
			// The FIR was BHU chief proctor and around half-a-dozen security guards for
			// allegedly beating up a journalist who was making video of a search being
			// carried out in a varsity hostel on Tuesday evening. By Express News Service |
			// Lucknow | Published: March 14, 2019 5:02:12 am Related News SC faculty posts
			// down by half, ST by 80 per cent: Banaras Hindu University alert Outside
			// Banaras Hindu University. (Express Photo by Anand Singh/File)\\nAn FIR has
			// been lodged against Banaras Hindu University (BHU), Varanasi, chief proctor
			// and around half-a-dozen security guards for allegedly beating up a journalist
			// who was making video of a search being carried out in a varsity hostel on
			// Tuesday evening. Advertising\\nThe search was conducted in the Birla hostel
			// after a clash between the students of Lohia and Birla hostels earlier in the
			// day, in which a student from Birla hostel was injured. An FIR was registered
			// against Lohia hostel student, Pawan Mishra, and his two associates. no
			// arrests have been made in connection with both the FIRs registered at the
			// Lanka police station of Varanasi.\\n“On Tuesday evening, Prahlad Pandey,
			// correspondent of a local web portal, was at the Birla hostel where chief
			// proctor Royana Singh was conducting a search. Pandey was making a video of
			// the proceedings for his portal when Royana allegedly snatched his phone and
			// deleted all data from it. She also allegedly directed the guards accompanying
			// her to beat up Pandey. Marks caused by beating by canes are visible all over
			// his body,” informed Bharat Bhushan Tiwari, station house officer (SHO) of the
			// Lanka police station.\\nThe case was registered against Royana under sections
			// 147 (rioting) 323 (voluntarily causing hurt), 504 (intentional insult with
			// intent to provoke breach of the peace), 506 (criminal intimidation), 342
			// (wrongful confinement) and 392 (robbery) of the Indian Penal Code (IPC), the
			// SHO added. Advertising\\nSSP Sureshrao A Kulkarni, said, “Hours before the
			// search, student Pawan Mishra of Lohia hostel along with his two associates
			// beat up another student, Abhijit Mishra, of Birla hostel near the Arts
			// faculty after an argument. Abhijeet was injured.”\\n“After the incident,
			// students of the Birla hostel held a protest and tried to close the university
			// gate. Police reached the spot, pacified the students and took them to the
			// police station to register the FIR. In the meantime, when chief proctor went
			// to conduct a search in the Birla hostel, students threw stones at the guards.
			// The journalist was allegedly beaten up after that,” added SSP. Latest Videos
			// Long duty hours, no week off: The story of real Chowkidars\\nDenying the
			// charge, chief proctor Royana Singh said it was a routine search and the
			// person “claiming to be a journalist” did not take any permission before
			// making the video.\\n“On Tuesday evening, I was at the hostel to check if
			// there were anyone staying there illegally. The allegations against me are
			// baseless. Media persons generally take permission from us. The person being
			// talked about did not take any permission. Also, there was no attack on anyone
			// as far as I know,” said Royana, adding that during the search in hostels, she
			// found bricks and around 200 glass bottles under the stairs.";
			// String newSent2 = "13 Mar 2019 Image for representational use only.Image
			// Courtesy : The Indian Express\\nAssam Chief Minister Sarbananda Sonowal once
			// used to roar about the “zero tolerance to corruption” policy of his
			// government. But now his own government has become entangled in job scams. In
			// the latest development, at least two departments— Panchayat & Rural
			// Development (P&RD) and the Information and Public Relation Department (IPR)
			// have been brought to the spotlight following the announcement of results of
			// selected candidates.\\nBoth the departments’ appointments came back to back
			// just before the announcement of dates for the Lok Sabha election 2019. The
			// declarations of the results of the selected candidates of these departments
			// have caused waves of protests across Assam. People are arguing that these
			// appointments are completely bogus and are filled with candidates who are
			// either BJP workers, supporters or are relatives of them. Also, the
			// examinations for the recruitments were full of anomalies.\\nLet’s first look
			// at the P&RD department’s appointments. The department conducted examination
			// in more than hundred centres across the state. The examination was held for
			// 945 posts where more than one lakh students appeared for the recruitment
			// tests. When the tests were conducted on May 20, 2018, anomalies in large
			// scale surfaced in many of the exam centres. Local media was flooded with the
			// reports of the anomalies. Also Read: Governor Reveals Scam in J&K Bank
			// Recruitment\\nCandidates who had appeared in the examination told that the
			// exam halls were full of chaos and there was no apparent control by the
			// authority. Many candidates allegedly could use their mobile phones to contact
			// their friends outside through WhatsApp or text messages to know the answers.
			// After large scale protests, the case was referred to the CID department for
			// an investigation, and meanwhile a PIL in the Gauhati High Court was filed.
			// The court announced a stay order. The CID report said that there were huge
			// anomalies in 18 exam centres in 9 districts.\\nAfter almost a year, the final
			// list of selected candidates was declared on March 8. However, again protests
			// erupted across the state. In addition to the questions on the process of
			// selection, the protesting candidates have claimed that the document
			// verification process has also not been carried out properly and the
			// appointment letters were distributed in a hurried manner. Several people
			// including Akhil Gogoi have alleged that candidates related to or close to
			// Naba Doley have got the maximum numbers of selections. Naba Doley is the P&RD
			// minister in the BJP-led government in Assam. An FIR has filed against him at
			// the Dispur police station in Guwahati for the same.\\nInformation and Public
			// Relation Department\\nThe second case has come out in terms of anomalies in
			// the appointments and conduct of examinations in the Information and Public
			// Relation Department, which is headed by none other than Sonowal himself.
			// Exams for various posts in this department were conducted in four phases in
			// 2019—the first one on January 6 and the subsequent ones on January 12,
			// February 3 and March 3. According to the report in the ‘ Asomiya Pratidin’, a
			// leading Assamese daily, some 30 thousand candidates appeared in the exam held
			// on March 3. The answer scripts were deposited on the same day by late
			// evening. Surprisingly, the final list of candidates was declared on the next
			// very day. Allegedly this hurried declaration of results was done before the
			// model code of conduct for the general election comes into force. CM Sonowal
			// has ordered enquiries to both the cases. Also Read: Elections 2019: Jobs
			// Failure Will Sink Modi\\nSpeaking to NewsClick , eminent lawyer of the
			// Gauhati High Court and activist Santanu Borthakur said, “The appointments are
			// utterly questionable. A CBI enquiry is the need of the hour. In the case of
			// the P&RD appointments, the document verification process was not done
			// properly. The documents that were only uploaded in the internet have a fair
			// chance of being fraudulent. Without proper verification of the original
			// documents no one can guarantee a fair selection. The maximum numbers of the
			// selected candidates are those who are close to BJP.”\\nTalking about the
			// protests against the recruitments, Kashyap Choudhury, president of Students’
			// Federation of India in Assam, told NewsClick , “We have protested the
			// selection process in the P&RD and the IPR departments. In the joint protest
			// held in Guwahati on March 9, where several organisations like All India
			// Students Association, SFI, and Asom Chatra Yuva Sanmelan, etc. participated,
			// a joint memorandum demanding a CBI enquiry was drafted. What we are
			// witnessing now is one of the biggest job scams ever in Assam. The BJP
			// government who campaigned for zero tolerance to corruption, is now seemingly
			// involved in scams.";
			String weekDays[] = globalVariables.getWeekDays();
			String redundantWords[] = globalVariables.getRedundantWords();
			String[] redWords = globalVariables.getNoMeaningWords();

			// List<String> wordNNP = posTagger.getNounsFromSentence(sentence);

			List<String> wordNNP = identifyAllNER(sentence, "english.conll.4class.distsim.crf.ser.gz");

			System.out.println("POS TAGS:" + wordNNP);
			LinkedHashMap<String, LinkedHashSet<String>> nameOrg = identifyNER(sentence,
					"english.conll.4class.distsim.crf.ser.gz");
			List<String> orgPosTags = new ArrayList<String>();
			Iterator it = nameOrg.entrySet().iterator();
			List<String> NNWordsLemma = lemma.lemmatize(wordNNP);
			// List<String> orgSet=new ArrayList<String>();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				// System.out.println(pair.getKey() + " = " + pair.getValue());
				// List<String> pairSet=(List<String>) pair.getValue();

				if (pair.getKey().toString().equalsIgnoreCase("LOCATION")) {
					// System.out.println(pair.getKey() + " = " + pair.getValue());
					Set<String> pairSet = (Set<String>) pair.getValue();
					List<String> pairList = new ArrayList(pairSet);
					for (int j = 0; j < locationNames.length; j++) {
						NNWordsLemma.removeAll(Collections.singleton(locationNames[j].toLowerCase()));
					}
					for (int j = 0; j < weekDays.length; j++) {
						NNWordsLemma.removeAll(Collections.singleton(weekDays[j].toLowerCase()));
					}
					for (int j = 0; j < redundantWords.length; j++) {
						NNWordsLemma.removeAll(Collections.singleton(redundantWords[j].toLowerCase()));
					}

					for (int j = 0; j < redWords.length; j++) {
						NNWordsLemma.removeAll(Collections.singleton(redWords[j].toLowerCase()));
					}
					for (int j = 0; j < intent_riot.length; j++) {
						NNWordsLemma.removeAll(Collections.singleton(intent_riot[j].toLowerCase()));
					}

				}

				it.remove(); // avoids a ConcurrentModificationException
			}
			// System.out.println("After removal of Location Word Set=" + wordNNP);

			for (int i = 0; i < NNWordsLemma.size(); i++) {
				if (NNWordsLemma.get(i).length() <= 3) {
					NNWordsLemma.remove(i);
				}
			}
			List<String> textNews = lemma.lemmatize(sentence.toLowerCase());

			String globalActors[] = globalVariables.getActors();
			for (int i = 0; i < globalActors.length; i++) {
				if (textNews.contains(globalActors[i])) {
					wordNNP.add(globalActors[i]);
				}
			}


			Set<String> set = new LinkedHashSet<String>();
			set.addAll(NNWordsLemma);
			NNWordsLemma.clear();
			NNWordsLemma.addAll(set);
			List<Integer> indexOfRootWords = new ArrayList();
			for (int j = 0; j < intent_riot.length; j++) {
				for (int k = 0; k < textNews.size(); k++) {
					if (textNews.get(k).equalsIgnoreCase(intent_riot[j])) {
						indexOfRootWords.add(k);
					}
				}

			}
			/*
			 * System.out.println("Indexes of root words in sent are:" + indexOfRootWords);
			 * for (int j = 0; j < indexOfRootWords.size(); j++) {
			 * System.out.println("Checking for confirmation:............" +
			 * textNews.get(indexOfRootWords.get(j))); }
			 */
			List<Double> differencesBetweenRootAndNN = new ArrayList();
			for (int k = 0; k < NNWordsLemma.size(); k++) {
				List<Integer> occurencesOfWord = new ArrayList();
				for (int j = 0; j < textNews.size(); j++) {
					if (textNews.get(j).equalsIgnoreCase(NNWordsLemma.get(k))) {
						occurencesOfWord.add(j);
					}
				}
				// if (k == 0) {
				// System.out.println("Checking if we get the right NN word Index from the text:
				// "
				// + textNews.get(occurencesOfWord.get(0)));
				// }

				System.out.println("NN WORD===============" + NNWordsLemma.get(k));

				System.out.println("OCCURENCE OF NN WORD===========" + occurencesOfWord);

				System.out.println("------------");

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
			// System.out.println("SIZE OF DIFFERENCE AND ACTUAL WORDS=:" +
			// differencesBetweenRootAndNN.size() + " & "
			// + NNWordsLemma.size());
			for (int j = 0; j < differencesBetweenRootAndNN.size(); j++) {
				System.out.println("Actual Word: " + NNWordsLemma.get(j) + " & Difference Mean: "
						+ differencesBetweenRootAndNN.get(j));
			}

			return NNWordsLemma.get(differencesBetweenRootAndNN.indexOf(Collections.min(differencesBetweenRootAndNN)));
		}
		catch(Exception e) {
			return "";
		}
	}

	public static void main(String args[]) {
		String sentence = "Home States Tamil Nadu Kamal Haasan daughter is mad.";
		// System.out.println(getActorAssociated(
		// "Challenge Aseemanand’s acquittal if you are a chowkidar, Owaisi dares PM
		// Modi The Talwandi Sabo Thermal Power Plant at Banawala village in Mansa.
		// Gurmeet Singh\nDespite being a power surplus state, Punjab has seen spiraling
		// a electricity tariff over the past few years irrespective of which party is
		// in power. In the first two years of the Congress government, the trend has
		// continued and despite being one of the more professionally-run branches of
		// the government with improved functioning, the power sector has continued to
		// face flak over tariff structure, closure of thermal plants and failure to
		// re-negotiate financially crippling power purchase agreements. Advertising
		// High power tariff\nThe government has come under consistent fire for high
		// power tariffs in the state and the repeated hikes imposed after Congress came
		// to power.\nThe Aam Aadmi Party (AAP) launched a ‘bijli andolan’ (power
		// agitation) in villages in protest against the high power bills allegedly
		// being received by villagers who use a handful of bulbs and a fan in their
		// houses. State AAP president and MP from Sangrur, Bhagwant Mann, who led the
		// agitation, said people having only two bulbs and two fans in their homes had
		// received power bills of Rs 20,000 – Rs 25,000. Under the campaign, Mann and
		// other AAP leaders said they had also re-connected power connections
		// disconnected by the electricity department in various villages.\nFormer
		// Finance Minister and SAD MLA Parminder Dhindsa has claimed that power
		// consumers were being levied fixed charges whether they consumed power or not.
		// SAD has alleged that power tariff has been increased 11 times in the past two
		// years, making electricity costliest in Punjab as compared to other
		// states.\nDhindsa has gone on record saying power was available for Rs 3 per
		// unit in Delhi, Rs 2 in Haryana and Rs 5.31 in Punjab. He added that the
		// domestic power slab went up to Rs 7.78 per unit. SAD has also attacked the
		// government for claiming that industry was being supplied power at the rate of
		// Rs 5 per unit, while the actual falls between Rs 7 to Rs 8 per unit. Power
		// subsidy to SC/BC issue\nThe state government was caught on the backfoot after
		// it came under opposition fire for removing the upper annual limit of 3,000
		// units for electricity consumption by SC/BC and BPL families. Immediately
		// after the issue was raised by SAD- BJP and AAP in the budget session, the
		// government reversed its power subsidy order for SC/BC and BPL families.
		// Within hours of the issue being raised, the state government issued orders to
		// extend the benefit of monthly 200 free power units to SC/non-SC/BC and BPL
		// families whose annual consumption exceeded 3,000 units. Latest Videos People
		// from all parts of country celebrate ‘Holika Dahan’\nThe move came hours after
		// the Punjab Vidhan Sabha witnessed a debate and a walkout by the members
		// protesting against the January 31 decision. The government was also accused
		// of increasing the power tariff by as much as 30 per cent in the last two
		// years. Lopsided power purchase agreements\nThe failure of the government to
		// review the lopsided Power Purchase Agreements (PPA) which had been signed by
		// the previous SAD-BJP government has also led to widespread criticism. Leader
		// of Opposition Harpal Cheema said that the government should cancel all PPAs
		// with private companies. Best Of Express"));

		System.out.println("Actor : "+getActorAssociated(sentence));
	}
}
