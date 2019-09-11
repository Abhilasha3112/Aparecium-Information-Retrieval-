package com.MainFunction;

import com.baseClass.Article;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExtractDataFrom1MonthCSV {
 public static void main(String args[])
 {
     readFromCSV("2019-05-03","2019-05-05");
 }
    public static ArrayList<Article> readFromCSV(String fromDate,String toDate)
    {
        ArrayList<Article> listOfArticles=new ArrayList<Article>();
        String csvFile = "C:\\Users\\abhil\\Downloads\\Archive\\"+"aparecium1MONTH.csv";
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
                    a.setActorAssociated(country[2]);
                   a.setDateExtracted(country[3]);
                   a.setLocationExtracted(country[4]);
                   a.setGeoCoordinates(country[5]+","+country[6]);
                   if(country[7].equals("NA")) {
                       a.setElectionViolence(country[8]);
                       a.setSummarizedText(country[9]);
                   }
                   else
                   {
                       a.setElectionViolence(country[7]);
                       a.setSummarizedText(country[8]);
                   }
                    a.setPublished(country[1]);
                    // format = eventDate;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date fdate = simpleDateFormat.parse(fromDate);
                        Date tdate = simpleDateFormat.parse(toDate);

                        Date adate = simpleDateFormat.parse(a.getPublished().substring(0,10));
                        if (adate.compareTo(fdate) >= 0 && tdate.compareTo(adate) >= 0) {

                            listOfArticles.add(a);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

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
        return listOfArticles;
    }


}
