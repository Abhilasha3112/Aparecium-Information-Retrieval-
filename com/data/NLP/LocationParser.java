package com.data.NLP;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.util.CoreMap;
import org.apache.commons.lang.ArrayUtils;

import com.computations.DifferenceBetweenIntentAndList;
import org.apache.commons.lang.ObjectUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LocationParser {
	public static void main(String args[]) {
		String news5 = "Panaji ( Goa ) [India], March 14 (ANI): Over 2000 people, who depend on mining , launched a protest march on Thursday accusing the state government of not being serious on the issue of resumption of work at the mines.\nThe protest ers, led by Puti Gaonkar , also submitted a memorandum to Chief Secretary Parimal Rai, requesting the government to take requisite measure on the issue.\n\"You are fully aware of the fact that due to the closure of the mines more than 31 lakh mining dependents are suffering since past one year,\" read the memorandum.\n\"The closure of mining industry in the state has had a devastating socio-economic effect on the livelihood of all mining dependent people and entities and is also impacting the economy of the state adversely,\" the memorandum read.\n\"Stopping of mining activities will gravely affect the state exchequer, as nearly 30 per cent of revenues from mining is provided to the state exchequer in the form of Goa Permanent Fund(GPF) (10 per cent of sale price), Royalty (15 per cent). District Mineral Fund (DMF) (4.5 per cent of royalty), National Mineral Exploration Trust (NMET) (0.3 per cent of royalty) by the mining companies,\" the memorandum added.\nBased on a few public interest litigations filed by three different petitioners, the Supreme Court on February 7 last year quashed the lease renewals. In effect, it restored the earlier position that all mining after November 22, 2007 were illegal.\nIn October 2012, the apex court suspended all iron ore mining and transportation in the state following a report submitted by Justice MB Shah Commission, which found that millions of tonnes of iron ore were mined illegally.\nThe report stated that illegal mining was being carried out in conspiracy with the state government including then Chief Minister Digambar Kamat, which had pegged the losses to the exchequer at about Rs 35,000 crore.\nIn 2015, the State government renewed 88 mining leases, all owned by those who were accused of illegal mining . (ANI)";
		String news = "Tens of thousands of women in a southern Indian state have formed a 620km human chain in support of a court order overturning a partial ban on women entering one of Hinduism's holiest temples.The Women's Wall rally on Tuesday was backed by the communist government in Kerala state, where the court order on the Sabarimala temple had triggered weeks of protests by opponents and supporters of the ban. Government employees took part in the demonstration, while schools were given a half-day and university exams delayed so that students could join the protest, the Press Trust of India news agency reported. A government statement issued before the event predicted five million women would participate in the protest.Kerala has become the venue of an angry showdown between Hindu traditionalists and supporters of September's Supreme Court ruling, which ended a centuries-old ban on women of menstrual age entering the temple.The top court said the ban infringed on the right to worship. Since then, several women have tried to reach the hilltop shrine, but were forced back by opposing activists.Opponents of the court ruling, which include Prime Minister Narendra Modi's nationalist Bharatiya Janata Party, say that the ban was essential to the rites related to the temple's chief deity Ayyappan, considered eternally celibate.In some Hindu communities, menstruating women are regarded as unclean, leading to restrictions and in a few cases outright bans from entering certain places.Police have clashed with devotees supporting the ban and have arrested more than 2,000 people.Elizabeth, a school teacher and a member of the left-backed Kerala State Teachers Association, told The Indian Express that women's right to pray was being violated by male-dominated protests at Sabarimala.Those who have faith in the deity must be able to enter and pray, she said.A 23-year-old IT worker who also participated in the rally added:We have to fight against blind traditions and superstitions. Hundreds of thousands of Hindus - men, young girls and elderly women - trek to the temple for an annual festival that usually falls around the end of the year.The Supreme Court is set to hear challenges to its landmark ruling from January 22.";
		String news2 = "Advertising BHU proctor booked for ‘beating up journalist’ The FIR was BHU chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. By Express News Service | Lucknow | Published: March 14, 2019 5:02:12 am Related News SC faculty posts down by half, ST by 80 per cent: Banaras Hindu University alert Outside Banaras Hindu University. (Express Photo by Anand Singh/File)\\nAn FIR has been lodged against Banaras Hindu University (BHU), Varanasi, chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. Advertising\\nThe search was conducted in the Birla hostel after a clash between the students of Lohia and Birla hostels earlier in the day, in which a student from Birla hostel was injured. An FIR was registered against Lohia hostel student, Pawan Mishra, and his two associates. no arrests have been made in connection with both the FIRs registered at the Lanka police station of Varanasi.\\n“On Tuesday evening, Prahlad Pandey, correspondent of a local web portal, was at the Birla hostel where chief proctor Royana Singh was conducting a search. Pandey was making a video of the proceedings for his portal when Royana allegedly snatched his phone and deleted all data from it. She also allegedly directed the guards accompanying her to beat up Pandey. Marks caused by beating by canes are visible all over his body,” informed Bharat Bhushan Tiwari, station house officer (SHO) of the Lanka police station.\\nThe case was registered against Royana under sections 147 (rioting) 323 (voluntarily causing hurt), 504 (intentional insult with intent to provoke breach of the peace), 506 (criminal intimidation), 342 (wrongful confinement) and 392 (robbery) of the Indian Penal Code (IPC), the SHO added. Advertising\\nSSP Sureshrao A Kulkarni, said, “Hours before the search, student Pawan Mishra of Lohia hostel along with his two associates beat up another student, Abhijit Mishra, of Birla hostel near the Arts faculty after an argument. Abhijeet was injured.”\\n“After the incident, students of the Birla hostel held a protest and tried to close the university gate. Police reached the spot, pacified the students and took them to the police station to register the FIR. In the meantime, when chief proctor went to conduct a search in the Birla hostel, students threw stones at the guards. The journalist was allegedly beaten up after that,” added SSP. Latest Videos Long duty hours, no week off: The story of real Chowkidars\\nDenying the charge, chief proctor Royana Singh said it was a routine search and the person “claiming to be a journalist” did not take any permission before making the video.\\n“On Tuesday evening, I was at the hostel to check if there were anyone staying there illegally. The allegations against me are baseless. Media persons generally take permission from us. The person being talked about did not take any permission. Also, there was no attack on anyone as far as I know,” said Royana, adding that during the search in hostels, she found bricks and around 200 glass bottles under the stairs.";
		String news3 = "Nagpur City news,Happening Nagpur, Nagpur Today news / News Today | By Nagpur Today Nagpur News Vet students ‘pay homage’ to LDO’s chair as part of protest\\nNagpur: The Maharashtra Veterinary Students Association (action committee) on Thursday ‘paid homage’ to the chair of Livestock Development Officer (LDO) chair as part of their ongoing protest against the alleged unlawful promotions in the department.\\nThe outfit has alleged that, the appointment of non graduate veterinary practitioner as LDO is a complete waste. Thus here will be no use of this position and hence the action committee had planned the homage.\\n“Any graduate cannot relate with the problems of the veterinary doctors. As he or she will not have a sufficient knowledge about the field. As a result they will ultimately fail to relate, understand and solve veterinary student’s problem. Hence there is no point of being appointing such persons to this crucial position,” said Dr Chetan Shembekar, member, Maharashtra Veterinary Students Association (action committee).\\n Two days later, the students staged demonstration inside the college premises demanding rolling back the promotions granted to assistant LDOs by government stating this is in contravention of the Indian Veterinary Act.\\nThe outfit has also threatened to turn their agitation violent if their demands are not fulfilled.";
		String news4 = "Edited By Vikas Sharma | By Soumyajit Ghose 22:55:32 Share\nAngul: Protesting unruly behavior of a lady teacher towards students, guardians and villagers of Banarpal block of Angul district today staged a demonstration and locked up Kumanda Upper Primary School. The guardians alleged that the teacher Upasi Routray used to misbehave and thrash students.\n“There were repeated complaints against the lady teacher. We had even lodged a complaint with BEO but as no action was taken, we locked up the school gates as a mark of protest,” said Satrughna Pradhan, one of the agitators. Also Read Elections 2019: Licensed gun holders asked to deposit…\nThe agitation was called off after the Block Education Officer (BEO) assured of sending a report in this regard to the District Education Officer (DEO) in connection with the allegations against the teacher.\n“I don’t know about the earlier complaints. I will prepare a report about the current protest against the teacher and will send it to the DEO,” said Banarpal Block Education Officer, Lalit Mohan Sahoo.\nMeanwhile, the teacher has refuted the allegations levelled against her. “There should be a review of all allegations against me,” said Upasi Routray. Trending News";

		String news6 = "Edited By Bikram Keshari Jena | By Suryakant Jena On Mar 19, 2019 - 18:01:58 Share\nBhubaneswar: Since the announcement of its candidates for the first and second phase general elections in Odisha, resentment continues to surface within the ruling-BJD camp with workers and leaders of the party unit protesting candidature of Achyuta Samanta in Kandhamal Lok Sabha segment.\nHundreds of leaders and party workers staged demonstration at Biju Patnaik chhak in Phulbani and blocked the road yesterday protesting selection of Samanta. Alleging BJD top brass’ gross negligence towards the local party unit leadership, the protesters blamed that just like the last time an outside leader has once again been selected to contest the polls from this parliamentary constituency. Also Read Royal Challenge: Pratyusha, Arka raise revolt banner post…\nThey alleged that due to the selection of an outsider, the local party leaders and workers are unable to express their grievances and issues. The party unit has also threatened to resort to a district-wide protest if party supremo fails to cancel Samanta’s candidature.\nSudhir Kanhar, Phulbani BJD Nagar president said, “We don’t know who Achyuta Samanta is, so we have demanded the party president to field a local candidate.”\nPunam Kanhar, Vice president of local unit of Biju Mahila Janata Dal, said there is still time to consider another candidate for the Lok Sabha seat. We want the party leadership to select a candidate whom we know and can share our problems face-to-face.” Trending News";
        String news7="NEW DELHI — Two women, accompanied by plainclothes police officers under the cover of darkness early on Wednesday morning, entered a centuries-old Hindu shrine in southern India that has long barred women of childbearing age. Their effort was part of a continuing push for women’s equality in the country.\n" +
				"" +
				"In response, protests broke out around Kerala, the state where the shrine, the Sabarimala Temple, is located. According to local news reports, the police moved relatives of one of the women who entered the temple into a safe house.\n" +
				"" +
				"When news broke that the women had made it inside the temple, a Hindu priest shut down the complex for “purification rituals,” which typically occur when blood is spilled or children accidentally urinate.\n" +
				"";
        System.out.println("Article text is : \n "+ news7);
		LocationExtracter(news7);
	}
	static LinkedHashSet<String> tempset;
	public static void LocationExtracter() {

	}

	public static String LocationExtracter(String text) {
		String model = "english.conll.4class.distsim.crf.ser.gz";
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

					// System.out.println(word+":"+category);
				}

			}

		}
		// System.out.println(map.toString());
		// System.out.println(map.get("LOCATION"));
		String[] str = { "doab", "lake", "steam", "river", "island", "valley", "mountain", "hill", "street", "st",
				"boulevard", "junction", "lane", "rd", "avenue", "bridge", "hospital", "school", "shrine", "cinema",
				"villa", "temple", "mosque", "city", "district", "village", "gram", "place", "town", "nagar", "south",
				"eastern", "NW", "SE", "west", "western", "north east" };
		String textToUse = text;
		List<String> list = getNounsFromSentence(textToUse);

		tempset=map.get("LOCATION");

		//calling to refine location list
		if(tempset!= null) {
			LinkedHashSet<String> refinedList = refineNouns(map.get("LOCATION"), text, tempset);
			if(refinedList.size()!=0) {
				Iterator<String> itr = refinedList.iterator();

				while (itr.hasNext()) {

					list.add(itr.next());
				}
			}
		}
		System.out.println("List="+list);
		ArrayList<String> finalList=new ArrayList<String>();

		for(int u=list.size()-1;u>=0;u--)
		{
			int flag=0;
			for(int i=0;i<u;i++)
			{
                 if (list.get(i).indexOf(list.get(u)) != -1) {
                 //	System.out.println("flag set while comparing "+list.get(u) +"--"+ list.get(i) );
						flag=1;
						break;
						//list.remove(list.get(u));
						//break;
					}

					}
		//	System.out.println("flag for "+list.get(u)+ "is "+ flag);


			if(flag==0)
				finalList.add(list.get(u));
		}
		System.out.println(" Final Candidate List="+finalList);
        list=finalList;
		return DifferenceBetweenIntentAndList.differenceCalculator(list, text,"Location");
		// getmaineventlocation
		// String coords;
		// System.out.println("latitude :" );
		// coords = GetLongAndLat.getCoordinates("Kumanda Upper Primary School
		// banarpal"+" India");

		// System.out.println("latitude :" + coords);
		// System.out.println("longitude:" + coords.get("lon"));
	}

 public static LinkedHashSet<String> refineNouns(LinkedHashSet<String> listFromNER,String text,LinkedHashSet<String> tempset)
 {
	 System.out.println("List before applying combining code is "+ listFromNER);
	 if(listFromNER.size()!=0) {
		 LinkedHashSet<String> refinedList = new LinkedHashSet<String>();
		 List<String> list = new ArrayList<String>();
		 Iterator<String> itr = listFromNER.iterator();
		 String temp = itr.next();
		 refinedList.add(temp);

		 while (itr.hasNext()) {
			 String loc2 = itr.next();
			 String loc1 = temp;
			 //System.out.println(loc1 + " " + loc2);
			 String regex = "(\\s|^)" + Pattern.quote(loc1.toLowerCase()) + "\\s+" + Pattern.quote(loc2.toLowerCase()) + "(\\s|$)";

			 Pattern p = Pattern.compile(regex);
			// System.out.println(p.matcher(text.toLowerCase()).find());
			 if (p.matcher(text.toLowerCase()).find()) {

				 refinedList.remove(loc1);
				 refinedList.add(loc1 + " " + loc2);
				 if (itr.hasNext())
					 temp = itr.next();

				 //System.out.println(temp);
				 refinedList.add(temp);

			 } else {
				 refinedList.add(loc2);
				 temp = loc2;
			 }
		 }
		 //System.out.println("t " + tempset);
		 //System.out.println("ref " + refinedList);
		 if (tempset.equals(refinedList))
			 return refinedList;
		 else {
			 tempset = refinedList;
			 if (tempset != null)
				 return refineNouns(refinedList, text, tempset);
			 else
				 return refinedList;
		 }
	 }
        return listFromNER;
 }
	public static List<String> getNounsFromSentence(String sent) {
		Properties props = new Properties();
		List<String> listOfTokens = new ArrayList<String>();
		props.setProperty("annotators", "tokenize, ssplit, pos");
		List<String> extraLocations = new ArrayList<String>();
		String[] str = { "doab", "lake", "steam", "river", "island", "valley", "mountain", "hill", "street", "st",
				"boulevard", "junction", "lane", "rd", "avenue", "bridge", "hospital", "school", "shrine", "cinema",
				"villa", "temple", "mosque", "city", "district", "village", "gram", "place", "town", "nagar", "south",
				"eastern", "NW", "SE", "west", "western", "north east" };
//  String[] str={"school"};
		// String[] str={"school"};
		// String sent="The incident happened in Kuman Bandhu School.";
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = new Annotation(sent);
		pipeline.annotate(annotation);
		CoreLabel temp = null;
		int index;
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				listOfTokens.add(token.word());

				// this is the POS tag of the token
				try {
					for (int k = 0; k < str.length; k++) {
						StringBuffer completeLoc = new StringBuffer();
						if (token.word().toLowerCase().equals(str[k])) {
							completeLoc.append(token.word() + " ");
							index = token.get(CoreAnnotations.IndexAnnotation.class);
							if (!(index - 1 == 0 || index - 1 == 1))
								temp = tokens.get(index - 1 - 1);
							//System.out.println(temp.get(CoreAnnotations.PartOfSpeechAnnotation.class));
							int flag = 0;
							// while(temp.get(CoreAnnotations.PartOfSpeechAnnotation.class).equalsIgnoreCase("NNP")||
							// temp.get(CoreAnnotations.PartOfSpeechAnnotation.class).equalsIgnoreCase("NNP"))
							while (temp.word().toCharArray()[0] == temp.word().toUpperCase().toCharArray()[0]) {
								if (!((temp.word().toCharArray()[0] >= 'a' && temp.word().toCharArray()[0] <= 'z')
										|| (temp.word().toCharArray()[0] >= 'A' && temp.word().toCharArray()[0] <= 'Z')))
									break;
								// completeLoc.append(temp.word());
								index = temp.get(CoreAnnotations.IndexAnnotation.class) - 1;
								completeLoc.append(temp.word() + " ");
								index = index - 1;
								if (index < 0)
									break;
								temp = tokens.get(index);
								flag = 1;

							}
							// ADDONEMORECONDITION
							if (flag != 0 && !completeLoc.toString().split(" ")[0].equals("")) {

								String[] s = completeLoc.toString().split(" ");
								// ArrayUtils.reverse(s);
								StringBuilder w = new StringBuilder();
								for (int y = s.length - 1; y >= 0; y--) {
									w.append(s[y] + " ");
								}
								// w.substring(0,w.length()-2);
								// String g=Arrays.toString(s);
								// System.out.println();
								extraLocations.add(w.substring(0, w.length() - 1));
								break;
							}

						}

					}
				}
				catch(NullPointerException e)
				{

				}
			}
		}
		return extraLocations;

	}
}
