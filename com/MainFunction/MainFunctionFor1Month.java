package com.MainFunction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.baseClass.Article;
import com.data.NLP.DateTimeParser;
import com.data.NLP.GetLongAndLat;
import com.data.NLP.LocationParser;
import com.data.NLP.NamedEntityRecognition;
import com.dataExtraction.TOIExtraction;
import com.dataExtraction.Webhose;
import com.runPythonScripts.ElectionViolence;
import com.runPythonScripts.SummarizationNLTK;
import com.runPythonScripts.TOIdataExtraction;

import static com.OutputDataToCSV.writeToCSV;

public class MainFunctionFor1Month {

	public static void main(String args[]) throws URISyntaxException, IOException, ParseException {
		List<Article> newArticlesGathered =new ArrayList<Article>();
		//Uncomment
	/*	newArticlesGathered=Webhose.getSocialUnrestData();

		TOIdataExtraction toiData=new TOIdataExtraction();
		//Uncomment
		List<Article>listOfArticles=toiData.getTOIData();
	List<Article> newArticlesGathered2= TOIExtraction.crawlNet(listOfArticles);
		//newArticlesGathered2.addAll(newArticlesGathered);
	//int count=newArticlesGathered2.size();
	for(int k=0;k<newArticlesGathered2.size();k++)
	{
		System.out.println(k);
		if(newArticlesGathered2.get(k).getText()=="" || newArticlesGathered2.get(k).getText()==null)
		{
			newArticlesGathered2.remove(k);
			k--;
		}
		try {
			System.out.println(newArticlesGathered2.get(k).getText());
		}
		catch(Exception e)
		{
			System.out.println(k + "df "+ newArticlesGathered2.size());
		}
	}
		newArticlesGathered.addAll(newArticlesGathered2);
        writeToCSV(newArticlesGathered,"TOIonly1Month"); */
        /*******START******/


/*************************/
	String csvFile = "C:\\Users\\abhil\\Downloads\\Archive\\"+"apareciumonlywitharticles.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);
				if(country.length>1) {
					Article a = new Article();
					String s = country[0];
					a.setText(s.replace(",", " "));

					a.setPublished(country[1]);
					newArticlesGathered.add(a);
				}
				// System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/******END******/
		System.out.println("Main size"+newArticlesGathered.size());
		for (int i = 0; i < newArticlesGathered.size(); i++) {
			System.out.println("*************************************************");
			System.out.println("Article Number : "+i);
			newArticlesGathered.get(i).setActorAssociated(
					NamedEntityRecognition.getActorAssociated(newArticlesGathered.get(i).getText()));
			//System.out.println(i+1);
			//System.out.println(newArticlesGathered.get(i).getText());
			newArticlesGathered.get(i).setDateExtracted(DateTimeParser.getDateAndTime(newArticlesGathered.get(i)));
			newArticlesGathered.get(i)
					.setLocationExtracted(LocationParser.LocationExtracter(newArticlesGathered.get(i).getText()));
			String longlat=GetLongAndLat.getCoordinates(newArticlesGathered.get(i)
					.getLocationExtracted());
			/*if(longlat.split(",").length>2)
			{
				System.out.println("News article removed since the event happened outside India");
				newArticlesGathered.remove(i);
				i--;
				continue;
			} */
			newArticlesGathered.get(i).setGeoCoordinates(longlat);
			newArticlesGathered.get(i)
			.setSummarizedText(SummarizationNLTK.getSummarizedText(newArticlesGathered.get(i).getText()));
			newArticlesGathered.get(i)
					.setElectionViolence(ElectionViolence.getElectionViolence(newArticlesGathered.get(i).getText()));

			
		}
		
		System.out.println("Data Extraction Results");
		System.out.println(newArticlesGathered.size());
		
		for(int i=0;i<newArticlesGathered.size();i++) {

			System.out.println("--------------------------------------------");
			System.out.println("Text:---->" + newArticlesGathered.get(i).getText());
			System.out.println("Published Date:---->" + newArticlesGathered.get(i).getPublished());
			System.out.println("Actor:---->" + newArticlesGathered.get(i).getActorAssociated());
			System.out.println("Date Of Event:---->" + newArticlesGathered.get(i).getDateExtracted());
			System.out.println("Location:---->" + newArticlesGathered.get(i).getLocationExtracted());
			System.out.println("GeoCoordinates of Location:---->" + newArticlesGathered.get(i).getGeoCoordinates());
			System.out.println("Summarization:---->" + newArticlesGathered.get(i).getSummarizedText());
			System.out.println("Election Violence:---->" + newArticlesGathered.get(i).getElectionViolence());

		}
		writeToCSV(newArticlesGathered,"aparecium1MONTH");

	}
}
