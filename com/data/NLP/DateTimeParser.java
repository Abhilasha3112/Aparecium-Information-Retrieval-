package com.data.NLP;

import com.baseClass.Article;
import com.baseClass.DateExtractor;
import com.computations.DifferenceBetweenIntentAndList;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.time.SUTime;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;

public class DateTimeParser {
	String listOfProtestKeywords[] = { "protest", "riot", "strike" };

	public static void main(String args[]) {
		ArrayList<Article> list = new ArrayList<Article>();
		Article a = new Article();
		String news = "NEW DELHI: Employees of cash-strapped Jet Airways . NSE -29.15 % Saturday protested outside Terminal 3 of the Indira Gandhi International Airport here seeking release of pending salaries.  The beleaguered airline  which is flying just six-seven aircraft on Saturday  has asked its lenders to grant some \"interim funding\". Its employees are expecting to be paid their pending salaries once the fresh funding comes through.  Employees of Jet Airways gathered outside Terminal 3 in their uniform for a silent protest on Saturday afternoon. They carried banners that read \" Save Jet Airways  Save our future\" and \"Hear our cry  Let 9W fly\".  9W is the code for Jet Airways flights  just like 6E is for IndiGoNSE 0.98 % flights.  On Friday  Jet Airways employees took out a silent march in Mumbai.  Civil Aviation Secretary Pradeep Singh Kharola had earlier said that the airline would fly just 6-7 aircraft on Saturday and Sunday.  At its peak  the airline flew a 119-aircraft fleet.  On Monday  the lenders - lead by SBINSE -1.44 % - would again meet the management of Jet Airways which will present a plan to use the \"interim funding\"  if granted.  Jet Airways on Thursday grounded its services to and from the East and Northeast regions and suspended its international operations till Monday. Consequently  many passengers were left stranded at the airports.";
		String news2 = "Advertising BHU proctor booked for ‘beating up journalist’ The FIR was BHU chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. By Express News Service | Lucknow | Published: March 14, 2019 5:02:12 am Related News SC faculty posts down by half, ST by 80 per cent: Banaras Hindu University alert Outside Banaras Hindu University. (Express Photo by Anand Singh/File)\\nAn FIR has been lodged against Banaras Hindu University (BHU), Varanasi, chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. Advertising\\nThe search was conducted in the Birla hostel after a clash between the students of Lohia and Birla hostels earlier in the day, in which a student from Birla hostel was injured. An FIR was registered against Lohia hostel student, Pawan Mishra, and his two associates. no arrests have been made in connection with both the FIRs registered at the Lanka police station of Varanasi.\\n“On Tuesday evening, Prahlad Pandey, correspondent of a local web portal, was at the Birla hostel where chief proctor Royana Singh was conducting a search. Pandey was making a video of the proceedings for his portal when Royana allegedly snatched his phone and deleted all data from it. She also allegedly directed the guards accompanying her to beat up Pandey. Marks caused by beating by canes are visible all over his body,” informed Bharat Bhushan Tiwari, station house officer (SHO) of the Lanka police station.\\nThe case was registered against Royana under sections 147 (rioting) 323 (voluntarily causing hurt), 504 (intentional insult with intent to provoke breach of the peace), 506 (criminal intimidation), 342 (wrongful confinement) and 392 (robbery) of the Indian Penal Code (IPC), the SHO added. Advertising\\nSSP Sureshrao A Kulkarni, said, “Hours before the search, student Pawan Mishra of Lohia hostel along with his two associates beat up another student, Abhijit Mishra, of Birla hostel near the Arts faculty after an argument. Abhijeet was injured.”\\n“After the incident, students of the Birla hostel held a protest and tried to close the university gate. Police reached the spot, pacified the students and took them to the police station to register the FIR. In the meantime, when chief proctor went to conduct a search in the Birla hostel, students threw stones at the guards. The journalist was allegedly beaten up after that,” added SSP. Latest Videos Long duty hours, no week off: The story of real Chowkidars\\nDenying the charge, chief proctor Royana Singh said it was a routine search and the person “claiming to be a journalist” did not take any permission before making the video.\\n“On Tuesday evening, I was at the hostel to check if there were anyone staying there illegally. The allegations against me are baseless. Media persons generally take permission from us. The person being talked about did not take any permission. Also, there was no attack on anyone as far as I know,” said Royana, adding that during the search in hostels, she found bricks and around 200 glass bottles under the stairs.";
		String news3 = "Nagpur City news,Happening Nagpur, Nagpur Today news / News Today | By Nagpur Today Nagpur News Vet students ‘pay homage’ to LDO’s chair as part of protest\\nNagpur: The Maharashtra Veterinary Students Association (action committee) on Thursday ‘paid homage’ to the chair of Livestock Development Officer (LDO) chair as part of their ongoing protest against the alleged unlawful promotions in the department.\\nThe outfit has alleged that, the appointment of non graduate veterinary practitioner as LDO is a complete waste. Thus here will be no use of this position and hence the action committee had planned the homage.\\n“Any graduate cannot relate with the problems of the veterinary doctors. As he or she will not have a sufficient knowledge about the field. As a result they will ultimately fail to relate, understand and solve veterinary student’s problem. Hence there is no point of being appointing such persons to this crucial position,” said Dr Chetan Shembekar, member, Maharashtra Veterinary Students Association (action committee).\\n On Wednesday, the students staged protested inside the college premises demanding rolling back the promotions granted to assistant LDOs by government stating this is in contravention of the Indian Veterinary Act.\\nThe outfit has also threatened to turn their agitation violent if their demands are not fulfilled.";
		String news4 = "Edited By Vikas Sharma | By Soumyajit Ghose 22:55:32 Share\nAngul: Protesting unruly behavior of a lady teacher towards students, guardians and villagers of Banarpal block of Angul district today staged a demonstration and locked up Kumanda Upper Primary School. The guardians alleged that the teacher Upasi Routray used to misbehave and thrash students.\n“There were repeated complaints against the lady teacher. We had even lodged a complaint with BEO but as no action was taken, we locked up the school gates as a mark of protest,” said Satrughna Pradhan, one of the agitators. Also Read Elections 2019: Licensed gun holders asked to deposit…\nThe agitation was called off after the Block Education Officer (BEO) assured of sending a report in this regard to the District Education Officer (DEO) in connection with the allegations against the teacher.\n“I don’t know about the earlier complaints. I will prepare a report about the current protest against the teacher and will send it to the DEO,” said Banarpal Block Education Officer, Lalit Mohan Sahoo.\nMeanwhile, the teacher has refuted the allegations levelled against her. “There should be a review of all allegations against me,” said Upasi Routray. Trending News";
		String news5 = "Panaji ( Goa ) [India], March 13 (ANI): Over 2000 people, who depend on mining , launched a protest march on Thursday accusing the state government of not being serious on the issue of resumption of work at the mines.\nThe protest ers, led by Puti Gaonkar , also submitted a memorandum to Chief Secretary Parimal Rai, requesting the government to take requisite measure on the issue.\n\"You are fully aware of the fact that due to the closure of the mines more than 31 lakh mining dependents are suffering since past one year,\" read the memorandum.\n\"The closure of mining industry in the state has had a devastating socio-economic effect on the livelihood of all mining dependent people and entities and is also impacting the economy of the state adversely,\" the memorandum read.\n\"Stopping of mining activities will gravely affect the state exchequer, as nearly 30 per cent of revenues from mining is provided to the state exchequer in the form of Goa Permanent Fund(GPF) (10 per cent of sale price), Royalty (15 per cent). District Mineral Fund (DMF) (4.5 per cent of royalty), National Mineral Exploration Trust (NMET) (0.3 per cent of royalty) by the mining companies,\" the memorandum added.\nBased on a few public interest litigations filed by three different petitioners, the Supreme Court on February 7 last year quashed the lease renewals. In effect, it restored the earlier position that all mining after November 22, 2007 were illegal.\nIn October 2012, the apex court suspended all iron ore mining and transportation in the state following a report submitted by Justice MB Shah Commission, which found that millions of tonnes of iron ore were mined illegally.\nThe report stated that illegal mining was being carried out in conspiracy with the state government including then Chief Minister Digambar Kamat, which had pegged the losses to the exchequer at about Rs 35,000 crore.\nIn 2015, the State government renewed 88 mining leases, all owned by those who were accused of illegal mining . (ANI)";
		a.setText(news5);
		//a.setDateExtracted("13-Apr-2019");
		a.setPublished("2019-03-13T20:07:00");
        System.out.println("Article content is : \n"+news5);
		list.add(a);
		getDateAndTime(a);
		// String finalDate = javaParser("2019-03-05", "2019-03-04");
		// System.out.println("Final" + finalDate);

	}

	public static String getDateAndTime(Article newArticle) {
		Properties props = new Properties();
		String defs_file = "edu/stanford/nlp/models/sutime/defs.sutime.txt";

		String holiday_file = "edu/stanford/nlp/models/sutime/english.holidays.sutime.txt";

		String sutime_file = "edu/stanford/nlp/models/sutime/english.sutime.txt";

		props.setProperty("sutime.rules", defs_file + "," + holiday_file + "," + sutime_file);

		props.setProperty("sutime.binders", "0");

		props.setProperty("sutime.markTimeRanges", "true");

		props.setProperty("sutime.includeRange", "true");

		props.setProperty("customAnnotatorClass.sutime", "edu.stanford.nlp.time.TimeAnnotator");

		props.setProperty("annotators", "tokenize, sutime");
		ArrayList<String> extractedDatesFromArticle = new ArrayList<String>();
		ArrayList<String> sourceOfExtractedDates = new ArrayList<String>();

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		/*
		 * Parser parser = new Parser(); List<DateGroup> groups =
		 * parser.parse(listOfArticles.get(i).getText()); for(DateGroup group:groups) {
		 * // group = groups.get(0); System.out.println(group.getText()); List<Date>
		 * dates = group.getDates(); Date date = dates.get(0); LocalDate lessonLocalDate
		 * = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 * System.out.println(lessonLocalDate); }
		 */
		Annotation annotation = new Annotation(newArticle.getText());

		// System.out.println("Example sentence: The meeting is 3 hours from now and
		// dinner will be at 6 pm.");

		annotation.set(CoreAnnotations.DocDateAnnotation.class,newArticle.getPublished());

		pipeline.annotate(annotation);

		List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);

		List<DateExtractor> dateExtractorList=new ArrayList();
		for (CoreMap cm : timexAnnsAll) {
			DateExtractor dateExtractor=new DateExtractor();
			//System.out.println("---");

			SUTime.Temporal temporal = cm.get(TimeExpression.Annotation.class).getTemporal();

			//System.out.println("time expression: " + cm.toString());
			dateExtractor.setDateText(cm.toString());

			//System.out.println("temporal value: " + temporal.toString());

			//System.out.println("timex value: " + temporal.getTimexValue());
			dateExtractor.setDateValue(temporal.getTimexValue());

			dateExtractorList.add(dateExtractor);
			// Pattern pattern = Pattern.compile("\\w+");
			// Matcher matcher = pattern.matcher(EXAMPLE_TEST);
			// changeforweeklater
			if(temporal!=null && temporal.getTimexValue()!=null) {
				if (!(temporal.getTimexValue().matches("^P.*(Y|DE|W)"))) {
					if (!temporal.getTimexValue().matches("PTX.*")
							&& temporal.getTimexValue().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
						// if(temporal.getTimexValue().matches("^P.*(W)"))
						sourceOfExtractedDates.add(cm.toString());

						extractedDatesFromArticle.add(temporal.getTimexValue().split("T")[0]);
						//listOfArticles.get(i).setDateExtracted(temporal.getTimexValue().split("T")[0]);
						//System.out.println("Date saved " + extractedDatesFromArticle);
					}
				}
			}
		}
		String dateOfEvent="";
		System.out.println("The list of date string is: " + sourceOfExtractedDates);
		if(sourceOfExtractedDates.size()!=0) {
			dateOfEvent = DifferenceBetweenIntentAndList.differenceCalculator(sourceOfExtractedDates, newArticle.getText(), "Date");
			String dateOfEventValue = "";
			for (int i = 0; i < dateExtractorList.size(); i++) {
				if (dateExtractorList.get(i).getDateText().equalsIgnoreCase(dateOfEvent)) {
					dateOfEventValue = dateExtractorList.get(i).getDateValue();
				}
			}

			System.out.println("This is the actual date: "+dateOfEvent);
			System.out.println("This is the actual date Value: "+dateOfEventValue);
			System.out.println("This is the published date: "+newArticle.getPublished());

              if(newArticle.getPublished().length()>10)
			return javaParser(dateOfEventValue, newArticle.getPublished().substring(0, 10));
              else
				  return javaParser(dateOfEventValue, newArticle.getPublished());

		}
			return "No date available"	;	// callanmol'sfunction to get event date
		// String finalCorrectEventDate=
		// javaParser("2019-03-05",listOfArticles.get(i).getPublished());

		// String token = "last saturday,something happened at 2pm.Meeting was on
		// thursday";

	}

	public static String javaParser(String eventDate, String publishedDate) {
		String format = eventDate;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date edate = simpleDateFormat.parse(eventDate);
			Date pdate = simpleDateFormat.parse(publishedDate);

			if (edate.compareTo(pdate) > 0) {
				System.out.println("Published Date is "+pdate + " and event date parsed by SUTime is "+ edate + " which is a future date.Hence rectifying it...");
				Date date = new Date(); // Or where ever you get it from
				Date daysAgo = new DateTime(edate).minusDays(7).toDate();
				//System.out.println(daysAgo + "");
				// date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				format = formatter.format(daysAgo);
				System.out.println("Rectified date is "+ format);
			}

		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return format;
	}

	public static List<String> getListOfAllDates(Article newArticle){
		Properties props = new Properties();
		String defs_file = "edu/stanford/nlp/models/sutime/defs.sutime.txt";

		String holiday_file = "edu/stanford/nlp/models/sutime/english.holidays.sutime.txt";

		String sutime_file = "edu/stanford/nlp/models/sutime/english.sutime.txt";

		props.setProperty("sutime.rules", defs_file + "," + holiday_file + "," + sutime_file);

		props.setProperty("sutime.binders", "0");

		props.setProperty("sutime.markTimeRanges", "true");

		props.setProperty("sutime.includeRange", "true");

		props.setProperty("customAnnotatorClass.sutime", "edu.stanford.nlp.time.TimeAnnotator");

		props.setProperty("annotators", "tokenize, sutime");
		ArrayList<String> extractedDatesFromArticle = new ArrayList<String>();
		ArrayList<String> sourceOfExtractedDates = new ArrayList<String>();

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		/*
		 * Parser parser = new Parser(); List<DateGroup> groups =
		 * parser.parse(listOfArticles.get(i).getText()); for(DateGroup group:groups) {
		 * // group = groups.get(0); System.out.println(group.getText()); List<Date>
		 * dates = group.getDates(); Date date = dates.get(0); LocalDate lessonLocalDate
		 * = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 * System.out.println(lessonLocalDate); }
		 */
		Annotation annotation = new Annotation(newArticle.getDateExtracted().replace("Apr","April").replace("-"," "));

		// System.out.println("Example sentence: The meeting is 3 hours from now and
		// dinner will be at 6 pm.");

		annotation.set(CoreAnnotations.DocDateAnnotation.class,"2019-04-13T20:07:00");

		pipeline.annotate(annotation);

		List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);

		List<String> dateExtractorList=new ArrayList();
		for (CoreMap cm : timexAnnsAll) {
			DateExtractor dateExtractor = new DateExtractor();
			//System.out.println("---");

			SUTime.Temporal temporal = cm.get(TimeExpression.Annotation.class).getTemporal();

			//System.out.println("time expression: " + cm.toString());
			dateExtractor.setDateText(cm.toString());

			//System.out.println("temporal value: " + temporal.toString());

			//System.out.println("timex value: " + temporal.getTimexValue());
			dateExtractor.setDateValue(temporal.getTimexValue());

			dateExtractorList.add(dateExtractor.getDateValue());
			//System.out.println("Date is:"+dateExtractor.getDateText()+"&"+dateExtractor.getDateValue());
		}
		return dateExtractorList;
	}

}
