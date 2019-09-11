package com.dataExtraction;

import com.baseClass.Article;
import com.testCode.ExtractDataFromAcled;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.ArrayList;
import java.util.List;

public class TOIExtraction {
public static void main(String args[])
{
    getContent("https://www.hindustantimes.com/india-news/supreme-court-declines-urgent-hearing-on-hardik-patel-s-plea-for-stay-on-conviction/story-ZTDatLOlDFlT31LJqvAbYL.html");
}
public static List<Article> crawlNet(List<Article> listOfArticleURLs)
{

for(int i=0;i<listOfArticleURLs.size();i++)
{

    String s=null;
    try {
    String content=getContent(listOfArticleURLs.get(i).getUrl());

        if (content.equals("") || content.equals(null))
            continue;
        s = content.replaceAll("[\\n]", " ");
        listOfArticleURLs.get(i).setText(s.replace(","," "));
    }
    catch(Exception e)
    {
        System.out.println(e.toString());
      System.out.println("exception while getting content");
    }


}
return listOfArticleURLs;
}
public static String getContent(String url)
{
    StringBuilder finalContent=new StringBuilder();
    //System.setProperty("webdriver.chrome.driver","C:\\Users\\abhil\\Downloads\\chromedriver_win32\\chromedriver.exe");
    System.setProperty("webdriver.chrome.driver","Set-Your-Chrome-Driver-Path-Here");
  // WebDriver driver= new HtmlUnitDriver();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
   ChromeDriver driver = new ChromeDriver(options);
    try {
    ArrayList<String> sendData=new ArrayList<String>();
    String content="";
  //url="https://www.indiatimes.com/news/india/17-people-get-a-new-lease-of-life-as-families-of-3-brain-dead-patients-agree-to-organ-donation-364995.html";
    String ele="";
    System.out.println(url);
   // url="https://economictimes.indiatimes.com/news/elections/lok-sabha/west-bengal/want-to-ensure-that-nobody-is-scared-while-approaching-polling-booths-special-police-observer/articleshow/68770357.cms";
    Document doc = null;

    if(url.contains("https://www.indiatimes.com"))
        return content;

    //    Connection.Response response =  Jsoup.connect(url).timeout(30000).ignoreHttpErrors(true).followRedirects(true).execute();


    //  driver.get("http://www.syriahr.com/en/?p=116014");
    driver.get(url);
    WebDriverWait wait = new WebDriverWait(driver, 20);
    By item=null;


  /*  {
        String newsID=url.substring(url.lastIndexOf("-")+1,url.lastIndexOf("."));
        System.out.println(newsID);
        String refinedURL="//div[@id='content_description_loader_"+newsID+"']";
       item = By.xpath(refinedURL);
    }
    else*/

  int flag=0;
  if(url.contains("www.thehindu.com"))
  {

      item=  By.xpath("//div[starts-with(@id,'content')]");
  }
  else if(url.contains("www.news18.com"))
  {
      item = By.xpath("//div[@id='article_body']");
  }
  else if(url.contains("www.hindustantimes.com"))
  {
      flag=1;
      List<WebElement> allElements=driver.findElements(By.xpath("//div[@class='story-details']//p"))   ;
      System.out.println(allElements.size());

      for(int i=1;i<allElements.size();i++)
      {
          String s="//div[@class='story-details']//p["+i+"]";
          item =By.xpath(s);

          WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(item));
          content = driver.findElement(item).getText();
          finalContent.append(content);
         // Thread.sleep(100);
      }
      System.out.println(finalContent);
  }
  else if(url.contains("indianexpress.com"))
  {
        flag=1;
      List<WebElement> allElements=driver.findElements(By.xpath("//div[@class='o-story-content__main a-wysiwyg']//p"))   ;
      System.out.println(allElements.size());

      for(int i=1;i<allElements.size();i++)
      {
          String s="//div[@class='o-story-content__main a-wysiwyg']//p["+i+"]";
          item =By.xpath(s);

          WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(item));
          content = driver.findElement(item).getText();
          finalContent.append(content);
          System.out.println(finalContent);
          Thread.sleep(100);
      }
  }
  else
     item = By.xpath("//div[@class='Normal']");

        if(flag==0) {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(item));

            System.out.println("the content is:");
            content = driver.findElement(item).getText();
            finalContent.append(content);
        }
     // System.out.println(content);
        //By item1 = By.xpath("//div[@class='post-meta'][1]/span[1]");

    }
    catch(Exception e)
    {
        System.out.println(e.toString());
System.out.println("toi exception "+url);
    }
    //  System.out.println(s1);
    //System.out.println("DFd");
    driver.quit();

    //  Elements text = doc.select("div[class=entry-content entry clearfix");
    // content=text.text();
    return finalContent.toString();
}
}
