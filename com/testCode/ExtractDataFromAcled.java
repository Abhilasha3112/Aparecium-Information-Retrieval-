package com.testCode;

import com.OutputDataToCSV;
import com.baseClass.Article;
import com.data.NLP.DateTimeParser;
import com.data.NLP.GetLongAndLat;
import com.data.NLP.LocationParser;
import com.data.NLP.NamedEntityRecognition;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.runPythonScripts.ElectionViolence;
import com.runPythonScripts.SummarizationNLTK;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.OutputDataToCSV.writeToCSV;

public class ExtractDataFromAcled {
    public static void main(String[] args) {
        getContent("DFDF");
        Article a = new Article();
        a.setDateExtracted("13-12-2019");
        a.setPublished("13-13-12");
        a.setText("sdsd");
        a.setLocationExtracted("Sdsd");
        a.setTitle("Sds");
        a.setActorAssociated("ggg");
        a.setGeoCoordinates("ds");
        Article a1 = new Article();
        a1.setDateExtracted("13-12-2019");
        a1.setPublished("13-13-12");
        a1.setText("sdsd");
        a1.setLocationExtracted("Sdsd");
        a1.setTitle("Sds");
        a1.setActorAssociated("ggg");
        a1.setGeoCoordinates("ds");
        ArrayList<Article> av = new ArrayList<Article>();
        av.add(a);
        av.add(a1);
        //getContent("asa");
    writeToCSV();
    }

    public static void writeToCSV() {
        List<Article> testArticleList = new ArrayList<Article>();
        // create FileWriter object with file as parameter
       /*   CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("C:\\Users\\abhil\\Downloads\\1989-01-01-2019-03-22.csv"));
           // reader = new CSVReader(new FileReader("D:\\Book1.csv"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] nextLine=null;

        List<Article> testArticleList = new ArrayList<Article>();
      while (true) {
            try {
                if (!((nextLine = reader.readNext()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (nextLine[25].contains("http://www.syriahr.com/en/")) {


                Article a = new Article();
                a.setGeoCoordinates(nextLine[22] + "," + nextLine[23]);
                a.setActorAssociated(nextLine[9].replace(","," "));
                a.setLocationExtracted(nextLine[21].replace(","," "));
                a.setDateExtracted(nextLine[4]);
                a.setSummarizedText(nextLine[27].replace(","," "));
                a.setSource(nextLine[25].replace(","," "));
                ArrayList<String> content=  getContent(a.getSource());
              if(content.get(0).equals(""))
                  continue;
               String s= content.get(0).replaceAll("[\\n]", " ");
                a.setText(s.replace(","," "));
              a.setPublished(content.get(1));
                testArticleList.add(a);

            }

//System.out.println(testArticleList.get(0).getText());
            // adding header to csv
            //String[] header = {"title", "Content", "Published Date","Actors associated","Event Date","Event Location","GeoCoordinates"};
            //   writer.writeNext(header);

            // add data to csv

        }
        OutputDataToCSV.writeToCSV(testArticleList,"AcledTestData"); */
        List<Article> testOutput=new ArrayList<Article>();
        int counta=0;
        int countd=0;
        int countl=0;
        int countg=0;
        int counts=0;
/*************************/
        //String csvFile = "C:\\Users\\abhil\\Downloads\\Archive\\"+"AcledTestData.csv";
        String csvFile = "Set-CSV-FilePath-Here";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                Article a = new Article();
                a.setGeoCoordinates(country[5] + "," + country[6]);
                a.setActorAssociated(country[2].replace(","," "));
                a.setLocationExtracted(country[4].replace(","," "));
                a.setDateExtracted(country[3]);
                a.setSummarizedText(country[8].replace(","," "));
               // a.setSource(nextLine[25].replace(","," "));

                String s= country[0];
                a.setText(s.replace(","," "));
                a.setPublished(country[1]);
                testArticleList.add(a);
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


/**************************/


        for (int i = 0; i < testArticleList.size(); i++) {
           Article a=new Article();
            a.setActorAssociated(
                    NamedEntityRecognition.getActorAssociated(testArticleList.get(i).getText()));
            //System.out.println(i+1);
            //System.out.println(newArticlesGathered.get(i).getText());
            a.setDateExtracted(DateTimeParser.getDateAndTime(testArticleList.get(i)));
            a.setLocationExtracted(LocationParser.LocationExtracter(testArticleList.get(i).getText()));
            a.setGeoCoordinates(GetLongAndLat.getCoordinates(testArticleList.get(i)
                    .getLocationExtracted()));
            a.setSummarizedText(SummarizationNLTK.getSummarizedText(testArticleList.get(i).getText()));
            a.setElectionViolence(ElectionViolence.getElectionViolence(testArticleList.get(i).getText()));

            testOutput.add(a);
            if(a.getActorAssociated().equals(testArticleList.get(i).getActorAssociated()))
                counta++;
            if(a.getDateExtracted().equals(testArticleList.get(i).getDateExtracted()))
                countd++;
            if(a.getLocationExtracted().equals(testArticleList.get(i).getLocationExtracted()))
                countl++;
            if(a.getGeoCoordinates().equals(testArticleList.get(i).getGeoCoordinates()))
                countg++;
            if(a.getSummarizedText().equals(testArticleList.get(i).getSummarizedText()))
                counts++;
            System.out.println("Data Evaluation Results");
            System.out.println("Actors accuracy"+ (counta/testArticleList.size())*100);
            System.out.println("Date accuracy"+ (countd/testArticleList.size())*100);
            System.out.println("Location accuracy"+ (countl/testArticleList.size())*100);
            System.out.println("Geocoodinates accuracy"+ (countg/testArticleList.size())*100);

        }

        System.out.println("Data Extraction Results");
       // System.out.println(newArticlesGathered.size());

        for(int i=0;i<testOutput.size();i++) {

            System.out.println("--------------------------------------------");
            System.out.println("Text:---->" + testOutput.get(i).getText());
            System.out.println("Published Date:---->" + testOutput.get(i).getPublished());
            System.out.println("Actor:---->" + testOutput.get(i).getActorAssociated());
            System.out.println("Date Of Event:---->" + testOutput.get(i).getDateExtracted());
            System.out.println("Location:---->" + testOutput.get(i).getLocationExtracted());
            System.out.println("GeoCoordinates of Location:---->" + testOutput.get(i).getGeoCoordinates());
            System.out.println("Summarization:---->" + testOutput.get(i).getSummarizedText());
            System.out.println("Election Violence:---->" + testOutput.get(i).getElectionViolence());
            //scoringmetrics
        }
       // writeToCSV(newArticlesGathered);

    }
    public static ArrayList<String> getContent(String source)
    {
        ArrayList<String> sendData=new ArrayList<String>();
        String content="";
        String url="";
        String ele="";
       int index=source.indexOf("SOHR_http");
         if(source.contains(";") && source.indexOf(";")>index)
         {
             int indexOfColon=source.indexOf(";");
             url=source.substring(index+5,indexOfColon);

         }
         else
             url="https://www.hindustantimes.com/india-news/jet-airways-operating-5-planes-seeks-emergency-funds-kharola/story-rpK04itLdAny6ESdVjWgmL.html";
        //    Connection.Response response =  Jsoup.connect(url).timeout(30000).ignoreHttpErrors(true).followRedirects(true).execute();
       //  System.setProperty("webdriver.chrome.driver","C:\\Users\\abhil\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","Set-Your-Chrome-Driver-Path-Here");

        WebDriver driver= new ChromeDriver();

      //  driver.get("http://www.syriahr.com/en/?p=116014");
        driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            By item = By.xpath("//div[@class='story-details']");
        List<WebElement> allElements=driver.findElements(By.xpath("//div[@class='story-details']//p"))   ;
        System.out.println(allElements.size());
        try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(item));

                System.out.println("the content is:");
                content = element.getAttribute("innerHTML");
                System.out.println("texxt"+content);
               // By item1 = By.xpath("//div[@class='post-meta'][1]/span[1]");
                //WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(item1));
                //ele = driver.findElement(item1).getText();
            }
            catch(TimeoutException e)
            {

            }
        //  System.out.println(s1);
            //System.out.println("DFd");
        driver.quit();
        sendData.add(content);
        sendData.add(ele);
      //  Elements text = doc.select("div[class=entry-content entry clearfix");
       // content=text.text();
        return sendData;
    }
}
