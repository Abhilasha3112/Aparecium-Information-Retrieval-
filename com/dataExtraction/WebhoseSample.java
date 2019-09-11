package com.dataExtraction;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import com.baseClass.Article;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.webhoseio.sdk.WebhoseIOClient;
import com.data.NLP.Lemmatization;
import com.baseClass.GlobalVariables;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import static com.OutputDataToCSV.writeToCSV;

public class WebhoseSample {

    public static List<Article> getSocialUnrestData(String url) throws URISyntaxException, IOException {

        List<Article> socialUnrestArticles = new ArrayList();
        String API_KEY = "372a89d9-d960-498d-959b-69d0aef1d581";
        WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance(API_KEY);
        GlobalVariables globalVariables=new GlobalVariables();
       // String intent_riot[] = globalVariables.getIntent_riot();
        int threshold = 1;
        Map<String, String> queries = new HashMap<String, String>();
        queries.put("q",url );     //queries.put("ts", "1551156055943");
        queries.put("sort", "crawled");

        String[] locationNames=globalVariables.getLocationNames();
        JsonElement result = webhoseClient.query("filterWebContent", queries);

        System.out.println(result.getAsJsonObject().get("totalResults")); // Print posts count
        // System.out.println(result.getAsJsonObject());
        JsonArray postArray = result.getAsJsonObject().getAsJsonArray("posts");

        // System.out.println(postArray.get(0).getAsJsonObject());

        List<Integer> newsIndex = new ArrayList<Integer>();
        Lemmatization lemma = new Lemmatization();
        System.out.println(postArray.size());
        for (int i = 0; i < postArray.size(); i++) {
            int count = 0;
            // System.out.println(i);
         /*   List<String> textNews = lemma
                    .lemmatize(postArray.get(i).getAsJsonObject().get("text").toString().toLowerCase());
            List<String> titleNews = lemma
                    .lemmatize(postArray.get(i).getAsJsonObject().get("title").toString().toLowerCase());
            // List<String>
            // published=lemmatize(postArray.get(i).getAsJsonObject().get("published").toString().toLowerCase());
            // System.out.println(titleNews.size());
            // List<String>
            // country=lemmatize(postArray.get(i).getAsJsonObject().get("thread").toString());

            // System.out.println(textNews);

          /*  for (int j = 0; j < intent_riot.length; j++) {
                for (int k = 0; k < textNews.size(); k++) {
                    if (textNews.get(k).equalsIgnoreCase(intent_riot[j])) {
                        count++;
                    }
                }
            } */

            boolean IndianNews = false;
           // if (count > threshold) {
                for (int z = 0; z < locationNames.length; z++) {
                    if (postArray.get(i).getAsJsonObject().get("text").toString().toLowerCase()
                            .contains(locationNames[z].toLowerCase())) {
                        IndianNews = true;
                        break;
                    }
                }
              //  if (IndianNews) {
                    newsIndex.add(i);
                //}
            }
            // System.out.println(postArray.get(0).getAsJsonObject().get("text")); // Print
            // title
            // System.out.println(o.getAsJsonObject().get("title")); // Print title

      //  }
        for (int i = 0; i < 100; i++) {
            Article newArticle = new Article();
            System.out.println(i);
            String tempText=postArray.get(newsIndex.get(i)).getAsJsonObject().get("text").toString();
            tempText=tempText.replaceAll("protest ers", "protesters");
            tempText=tempText.replaceAll("protest er", "protester");
            tempText=tempText.replaceAll("protest ors", "protesters");
            tempText=tempText.replaceAll("protest or", "protester");
            //tempText=tempText.replaceAll("\\r\\n|\\r|\\n", " ");
            //tempText=tempText.replaceAll("\n", "");
            newArticle.setText(tempText);
            newArticle.setTitle(postArray.get(newsIndex.get(i)).getAsJsonObject().get("title").toString());
            newArticle.setPublished(postArray.get(newsIndex.get(i)).getAsJsonObject().get("published").toString());
            newArticle.setUrl(postArray.get(newsIndex.get(i)).getAsJsonObject().get("thread").getAsJsonObject().get("site").toString());
            socialUnrestArticles.add(newArticle);
            //System.out.println(postArray.get(newsIndex.get(i)).getAsJsonObject().get("thread").getAsJsonObject().get("site_section").toString());
        }
        System.out.println("Size of all articles is" + socialUnrestArticles.size());
        return socialUnrestArticles;
    }

    public static void main(String args[]) throws URISyntaxException, IOException {

     //   List<Article> newArticles=getSocialUnrestData("language:english site_type:news thread.country:IN site_category:entertainment");
       /* for(int i=0;i<newArticles.size();i++) {
            System.out.println("Article No:"+(i+1));
            System.out.println("Text of News Article: "+newArticles.get(i).getText());
            System.out.println("Published Date of News Article: "+newArticles.get(i).getPublished());
            System.out.println("URL: "+newArticles.get(i).getUrl());
            writeToCSV(newArticles,"apareciumForTraining");

        } */
        List<Article> newArticles1=getSocialUnrestData("language:english site_type:news thread.country:IN site:indiatimes.com");
        writeToCSV(newArticles1,"apareciumForTraining");
        List<Article> newArticles2=getSocialUnrestData("language:english site_type:news thread.country:IN site_category:sports");
        writeToCSV(newArticles2,"apareciumForTraining");
        List<Article> newArticles3=getSocialUnrestData("language:english site_type:news thread.country:IN site_category:tech");
        writeToCSV(newArticles3,"apareciumForTraining");
    }

}
