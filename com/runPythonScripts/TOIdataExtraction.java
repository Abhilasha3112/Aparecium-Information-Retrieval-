package com.runPythonScripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.baseClass.Article;
import com.baseClass.GlobalVariables;

public class TOIdataExtraction {

	public List<Article> getTOIData() throws ParseException {

		String[] sources= {"sources=the-times-of-india","domains=thehindu.com","domains=hindustantimes.com","domains=indianexpress.com","domains=news18.com","domains=economictimes.com"};
		//String[] sources= {"domains=indianexpress.com"};
		String s = null;
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println(modifiedDate);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date datePrev = format.parse(modifiedDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datePrev);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		String prevDate = format.format(calendar.getTime());
		System.out.println(prevDate);
		String fromToDate = "from=" + "2019-05-01" + "&to=" + "2019-05-02";
	   //	String[] datesList= {"from=" + "2019-05-02" + "&to=" + "2019-05-03","from=" + "2019-04-04" + "&to=" + "2019-04-06","from=" + "2019-04-07" + "&to=" + "2019-04-09","from=" + "2019-04-10" + "&to=" + "2019-04-12","from=" + "2019-04-13" + "&to=" + "2019-04-15"};

		//Set the date here
		String[] datesList= {"from=" + "2019-05-01" + "&to=" + "2019-05-02"};
		//String[] datesList= {"from=" + "2019-04-05" + "&to=" + "2019-04-10","from=" + "2019-04-11" + "&to=" + "2019-04-15","from=" + "2019-04-16" + "&to=" + "2019-04-20","from=" + "2019-04-21" + "&to=" + "2019-04-25","from=" + "2019-04-26" + "&to=" + "2019-04-30","from=" + "2019-05-01" + "&to=" + "2019-05-04"};

		List<Article> toiDataExtracted = new ArrayList();
		List<String> uniqueArticles = new ArrayList();
int artcount=0;
		for(int i=0;i<sources.length;i++) {
		try {

			for(int j=0;j<datesList.length;j++) {
				
			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			
				fromToDate=datesList[j];
			String[] cmd = { "python", "dataTOI.py", fromToDate,sources[i] };
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			// System.out.println("Here is the standard output of the command:\n");
			String toiData = "";
			int count = 0;
			boolean intentWordFound = true;
			while ((s = stdInput.readLine()) != null) {
				toiData += " " + s;
			}
			System.out.println(toiData);
			//System.out.println(toiData.length());
			while (toiData.contains("--->URLofNews:")) {
				String url = toiData.substring(toiData.indexOf("--->URLofNews:") + 14,
						toiData.indexOf("----->PublishedDate:"));
				int index=20;
			//	System.out.println("Bad Published Date "+toiData.substring(toiData.indexOf("----->PublishedDate:") + index,toiData.indexOf("--->ENDOFTHEARTICLE")));
				if(toiData.substring(toiData.indexOf("----->PublishedDate:") + index,toiData.indexOf("--->ENDOFTHEARTICLE")).contains("\""))
					index=21;

				String publishedDate = toiData.substring(toiData.indexOf("----->PublishedDate:") + index,
						toiData.indexOf("--->ENDOFTHEARTICLE"));
				publishedDate=publishedDate.replace("\"", "");

				// System.out.println(count+".] URL="+url);
				// System.out.println("Published Date="+publishedDate);
				toiData = toiData.substring(toiData.indexOf("--->ENDOFTHEARTICLE") + 18);
				if (!(url.contains("/international/") || url.contains("/technology/")||url.contains("https://www.indiatimes.com")||url.contains("/sports/")||url.contains("/bollywood/")||url.contains("/movies/"))) {
					if (!uniqueArticles.contains(url)) {
						uniqueArticles.add(url);
						Article temp = new Article();
						temp.setUrl(url);
						temp.setPublished(publishedDate);

						toiDataExtracted.add(temp);
					}
				}
			}

			
		}
		}catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}
	}
		System.out.println("Size of toiDataExtracted" + toiDataExtracted.size());
		return toiDataExtracted;
	}

	public static void main(String args[]) throws ParseException {
		TOIdataExtraction toiDataExtraction = new TOIdataExtraction();
		List<Article> result = toiDataExtraction.getTOIData();
		System.out.println(result.size());
		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + 1 + ".] URL=" + result.get(i).getUrl());
			System.out.println("Published Date=" + result.get(i).getPublished());
		}
	}
}
