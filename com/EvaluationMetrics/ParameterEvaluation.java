package com.EvaluationMetrics;

import com.OutputDataToCSV;
import com.baseClass.Article;
import com.baseClass.GlobalVariables;
import com.data.NLP.DateTimeParser;
import com.data.NLP.POSTagger;
import java.lang.Math;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.testCode.ExtractDataFromAcled.writeToCSV;

public class ParameterEvaluation {
    static List<Article> newArticlesFromSys=new ArrayList<Article>();
    static List<Article> newArticlesACLED=new ArrayList<Article>();
    static List<Article> newArticlesSystem=new ArrayList<Article>();
    public static void main(String args[])
    {
        //String csvFile = "C:\\Users\\abhil\\Downloads\\"+"2019-04-01-2019-04-15-India (1).csv";
        //Set the path of CSV file downloaded from ACLED for a time period that you want to evaluate against.
        String csvFile = "Set-CSV-FilePath-Here";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        newArticlesFromSys =readFile();

        List<Article> newArticlesGathered =new ArrayList<Article>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                    Article a = new Article();

                    a.setGeoCoordinates(country[22] + "," + country[23]);
                        a.setAssosActor1(country[10]);
                a.setAssosActor2(country[12]);
                a.setActorAssociated(country[9].replace(","," "));
                    a.setLocationExtracted((country[18]+country[19]+country[20]).replace(","," "));
                    a.setDateExtracted(country[4]);
                    a.setSummarizedText(country[27].replace(","," "));
                    // a.setSource(nextLine[25].replace(","," "));

                    String s1= country[0];
                    a.setText(country[4].replace("Apr","April").replace("-"," "));
                    a.setPublished("2019-03-14T20:07:00");
                    newArticlesGathered.add(a);

                ArrayList<String> arr=new ArrayList<String>();
                arr=getSRLParams(a.getSummarizedText());
                matchWithSystemEvents(a,arr);
                // System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

            }
            int counta=0;
            int countd=0;
            int countl=0;
            int countg=0;
            int counts=0;
                   if(newArticlesACLED!=null) {
                       System.out.println(newArticlesACLED.size());
                       System.out.println(newArticlesSystem.size());
                       for (int i = 0; i < newArticlesACLED.size(); i++) {
                           System.out.println("Article No. " + i);
                           System.out.println(newArticlesACLED.get(i).getSummarizedText());
                           System.out.println(newArticlesSystem.get(i).getText());
                           System.out.println(newArticlesSystem.get(i).getGeoCoordinates());
                           System.out.println(newArticlesSystem.get(i).getLocationExtracted());
                                   System.out.println(newArticlesSystem.get(i).getActorAssociated());
                                   System.out.println(newArticlesSystem.get(i).getDateExtracted());
                                   System.out.println(newArticlesSystem.get(i).getAssosActor1() + newArticlesSystem.get(i).getAssosActor2());
                                   System.out.println("ACLED");
                           System.out.println(newArticlesACLED.get(i).getSummarizedText());
                           System.out.println(newArticlesACLED.get(i).getText());
                           System.out.println(newArticlesACLED.get(i).getGeoCoordinates());
                           System.out.println(newArticlesACLED.get(i).getLocationExtracted());
                           System.out.println(newArticlesACLED.get(i).getActorAssociated());
                           System.out.println(newArticlesACLED.get(i).getDateExtracted());
                           System.out.println(newArticlesACLED.get(i).getAssosActor1() + newArticlesACLED.get(i).getAssosActor2());

                           if(newArticlesACLED.get(i).getActorAssociated().toLowerCase().contains(newArticlesSystem.get(i).getActorAssociated().toLowerCase())||
                                   newArticlesACLED.get(i).getAssosActor1().toLowerCase().contains(newArticlesSystem.get(i).getActorAssociated().toLowerCase()) ||
                                   newArticlesACLED.get(i).getAssosActor2().toLowerCase().contains(newArticlesSystem.get(i).getActorAssociated().toLowerCase()))
                               counta++;
                           if(newArticlesACLED.get(i).getDateExtracted().equals(newArticlesSystem.get(i).getDateExtracted()))
                               countd++;
                           if(newArticlesACLED.get(i).getLocationExtracted().toLowerCase().contains(newArticlesSystem.get(i).getLocationExtracted().toLowerCase()))
                               countl++;
                           float longitude=Float.parseFloat(newArticlesSystem.get(i).getGeoCoordinates().split(",")[0].replace("[","").replace("]",""));
                           float latitude=Float.parseFloat(newArticlesSystem.get(i).getGeoCoordinates().split(",")[1].replace("[","").replace("]",""));
                           float longitudes=Float.parseFloat(newArticlesACLED.get(i).getGeoCoordinates().split(",")[0]);
                           float latitudes=Float.parseFloat(newArticlesACLED.get(i).getGeoCoordinates().split(",")[1]);
                           if(Math.abs(longitude-longitudes)<5 && Math.abs(latitude-latitudes)<5 )
                               countg++;

                       }
                   }

            System.out.println("Data Evaluation Results");
            System.out.println("Actors accuracy"+ (counta/newArticlesSystem.size())*100);
            System.out.println("Date accuracy"+ (countd/newArticlesSystem.size())*100);
            System.out.println("Location accuracy"+ (countl/newArticlesSystem.size())*100);
            System.out.println("Geocoodinates accuracy"+ (countg/newArticlesSystem.size())*100);
            OutputDataToCSV.writeToCSV(newArticlesSystem,"newArticlesSystem");
            OutputDataToCSV.writeToCSV(newArticlesACLED,"newArticlesACLED");
        } catch (Exception e) {
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

    }
    public static List<Article> readFile()
    {
       // String csvFile = "C:\\Users\\abhil\\Downloads\\Archive\\"+"apareciumsyseval.csv";
        //Give the path of final CSV file obtained after running the application (Aparecium.csv)
        String csvFile = "Set-CSV-FilePath-Here";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Article> newArticlesFromSys =new ArrayList<Article>();
        try {
            int count=0;
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                Article a1 = new Article();
                System.out.println(count);
                a1.setGeoCoordinates(country[5] + "," + country[6]);
                a1.setActorAssociated(country[2].replace(","," "));
                a1.setLocationExtracted(country[4].replace(","," "));
                a1.setDateExtracted(country[3]);
                a1.setSummarizedText(country[8].replace(","," "));
                // a.setSource(nextLine[25].replace(","," "));

                String s1= country[0];
                a1.setText(s1.replace(","," "));
                a1.setPublished(country[1]);
                newArticlesFromSys.add(a1);

count++;
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
        return newArticlesFromSys;
    }
    public static void matchWithSystemEvents(Article a,ArrayList<String> arr)
    {

        for(int i=0;i<newArticlesFromSys.size();i++)
        {
           String content=newArticlesFromSys.get(i).getText();
           int count=0;
           for(int j=2;j<arr.size();j++)
           {
               if(content.toLowerCase().contains(" "+arr.get(j).toLowerCase()+" "))
                   count++;

           }
           String dates= DateTimeParser.getListOfAllDates(a).get(0);
           List<String> sysdates=DateTimeParser.getListOfAllDates(a);
           List<String> locs=POSTagger.getNouns(arr.get(0));
           int countl=0;
           for(int k=0;k<locs.size();k++)
           {
               if(content.toLowerCase().contains(locs.get(k).toLowerCase()))
                   countl++;
           }
           System.out.println("Match of "+a.getActorAssociated()+ " and "+i+" :args"+ count+" and loc"+ countl);
           System.out.println("Threshold was " + (Math.ceil(0.6*(arr.size()-2)))  + "for loc "+ Math.ceil((0.6*locs.size())));
           if(arr.size()-2!=0) {
               int threshold1=(int)Math.ceil(0.6 * (arr.size() - 2));
               int threshold2=(int)Math.ceil((0.6 * locs.size()));
               if(threshold1==0)
                   threshold1=arr.size()-2;
               if(threshold2==0)
                   threshold2=locs.size();

               if (count >= threshold1 && countl >= threshold2) {
                   newArticlesACLED.add(a);
                   newArticlesSystem.add(newArticlesFromSys.get(i));
                   newArticlesFromSys.remove(i);
                   break;
               }
           }

        }
/*try {
    System.out.println(newArticlesACLED.size());
    System.out.println(newArticlesSystem.size());
    for (int i = 0; i < newArticlesACLED.size(); i++) {
        System.out.println("Article No. " + i);
        System.out.println(newArticlesACLED.get(i).getSummarizedText());
        System.out.println(newArticlesSystem.get(i).getText());
        break;
    }
}
catch(Exception e)
{

} */

    }
    public static ArrayList<String> getSRLParams(String text)
    {
        String s="";
        ArrayList<String> SRLparams =new ArrayList<String>();
        try {

            // run the Unix "ps -ef" command
            // using the Runtime exec method:

            String[] cmd = {
                    "python",
                    "practNLPTools-master\\SRLParams.py",
                    text,
            };
            Process p = Runtime.getRuntime().exec(cmd);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");

            GlobalVariables globalVariables=new GlobalVariables();

            String summary="";
            int count=0;
            StringBuilder arg=new StringBuilder() ;

            String loc="" ;
            String date="";
            while ((s = stdInput.readLine()) != null) {

               if(s.contains("ARG")&& s.length()>4)
                   arg.append(s.substring(4)+" ");
               else if(s.contains("Location")&& s.length()>9)
                   loc=s.substring(9);
               else if(s.contains("Time") && s.length()>5)
                   date=s.substring(5);

            }
            List<String> arglist=POSTagger.getNouns(arg.toString());
            SRLparams.add(loc);
            SRLparams.add(date);
            SRLparams.addAll(arglist);


        }
        catch (Exception e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        return SRLparams;
    }
}
