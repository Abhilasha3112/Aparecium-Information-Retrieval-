package com;

import com.opencsv.CSVWriter;
import com.baseClass.Article;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputDataToCSV {
    public static void main(String[] args)
    {
        Article a=new Article();
        a.setDateExtracted("13-12-2019");
        a.setPublished("13-13-12");
        a.setText("sdsd");
        a.setLocationExtracted("Sdsd");
        a.setTitle("Sds");
        a.setActorAssociated("ggg");
        a.setGeoCoordinates("ds");
        Article a1=new Article();
        a1.setDateExtracted("13-12-2019");
        a1.setPublished("13-13-12");
        a1.setText("sdsd");
        a1.setLocationExtracted("Sdsd");
        a1.setTitle("Sds");
        a1.setActorAssociated("ggg");
        a1.setGeoCoordinates("ds");
        ArrayList<Article> av=new ArrayList<Article>();
        av.add(a);
        av.add(a1);
       // writeToCSV(av);
    }
    public static void writeToCSV(List<Article> listOfArticles,String filename) {
       // File file = new File("C:\\Users\\abhil\\Downloads\\Archive\\"+filename+".csv");
        File file = new File( "Set-CSV-FilePath-Here"+filename+".csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file,true);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            //String[] header = {"title", "Content", "Published Date","Actors associated","Event Date","Event Location","GeoCoordinates"};
         //   writer.writeNext(header);

            // add data to csv
            for(int i=0;i<listOfArticles.size();i++)
            {
                String[] row=new String[7];
               // row[0]=listOfArticles.get(i).getTitle();
            //    outputfile.append(listOfArticles.get(i).getTitle().replace(","," "));
              //ss  outputfile.append(",");
                if(listOfArticles.get(i).getText()!=null)
                outputfile.append(listOfArticles.get(i).getText().replace(","," "));
                outputfile.append(",");
                if(listOfArticles.get(i).getPublished()!=null)
                outputfile.append(listOfArticles.get(i).getPublished());
                outputfile.append(",");
                if(listOfArticles.get(i).getActorAssociated()!=null)
                outputfile.append(listOfArticles.get(i).getActorAssociated().replace(","," "));
                outputfile.append(",");
                if(listOfArticles.get(i).getDateExtracted()!=null)
                outputfile.append(listOfArticles.get(i).getDateExtracted());
                outputfile.append(",");
                if(listOfArticles.get(i).getLocationExtracted()!=null)
                outputfile.append(listOfArticles.get(i).getLocationExtracted().replace(","," "));
                outputfile.append(",");
                if(listOfArticles.get(i).getGeoCoordinates()!=null)
                outputfile.append(listOfArticles.get(i).getGeoCoordinates());
                outputfile.append(",");
                if(listOfArticles.get(i).getElectionViolence()!=null)
                outputfile.append(listOfArticles.get(i).getElectionViolence());
                outputfile.append(",");
                if(listOfArticles.get(i).getSummarizedText()!=null)
                outputfile.append(listOfArticles.get(i).getSummarizedText().replace(","," "));
                outputfile.append(",");
                outputfile.append("\n");

               /* row[1]=listOfArticles.get(i).getText();
                row[2]=listOfArticles.get(i).getPublished();
                row[3]=listOfArticles.get(i).getActorAssociated();
                row[4]=listOfArticles.get(i).getDateExtracted();
                row[5]=listOfArticles.get(i).getLocationExtracted();
                row[6]=listOfArticles.get(i).getGeoCoordinates();*/
              //  writer.writeNext(row);
            }

            writer.close();
        } catch (
                IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
