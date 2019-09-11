package com.computations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baseClass.Article;
import com.baseClass.GlobalVariables;
import com.data.NLP.Tokenize;

public class ComputeRougeSummarizedMetric {

	public List<Double> getEvalauationForSummary(List<Article> articles,List<String> refSummaries) {
		double f1=0.0;
		List<Double> f1Scores=new ArrayList();
		Double f1Results[]=new Double[articles.size()];
		GlobalVariables gv=new GlobalVariables();
		String puncts[]=gv.getPuncts();
		for(int i=0;i<articles.size();i++) {
			String sysSummary=articles.get(i).getSummarizedText();
			//System.out.println(sysSummary);
			sysSummary=sysSummary.replace("\\n", "");
			//System.out.println(sysSummary);
			String refSumm=refSummaries.get(i);
			//System.out.println(refSumm);
			refSumm=refSumm.replaceAll("\\n", "");
			//System.out.println(refSumm);
			for(int j=0;j<puncts.length;j++) {
				sysSummary=sysSummary.replace(puncts[j],"");
				refSumm=refSumm.replace(puncts[j],"");
			}
			//System.out.println(refSumm);
			sysSummary=sysSummary.toLowerCase();
			refSumm=refSumm.toLowerCase();
			double precision=0.0;
			double recall=0.0;
			

			System.out.println(sysSummary);
			System.out.println(refSumm);
			Tokenize tok=new Tokenize();
			List<String> systemSumm=tok.tokenize(sysSummary);
			List<String> referencedSumm=tok.tokenize(refSumm);
			int lengthOfSysSummary=systemSumm.size();
			int lengthOfrefSummary=referencedSumm.size();
			Map <String,Integer> unique=new HashMap();
			
			int matchCount=0;
			for(int j=0;j<lengthOfSysSummary;j++) {
				if(unique.containsKey(systemSumm.get(j))) {
					unique.put(systemSumm.get(j), unique.get(systemSumm.get(j))+1);
					//System.out.println("WORD->"+systemSumm.get(j)+" AND COUNT->"+unique.get(systemSumm.get(j)));
				}
				else {
					unique.put(systemSumm.get(j), 1);
					//System.out.println("WORD->"+systemSumm.get(j)+" AND COUNT->"+unique.get(systemSumm.get(j)));
				}
			}
			for(int j=0;j<lengthOfrefSummary;j++) {
				if(unique.containsKey(referencedSumm.get(j))&&unique.get(referencedSumm.get(j))>0) {
					matchCount++;
					unique.put(referencedSumm.get(j), unique.get(referencedSumm.get(j))-1);
				}
			}
			System.out.println("MatchCount="+matchCount);
			recall=matchCount*1.0/lengthOfrefSummary;
			precision=matchCount*1.0/lengthOfSysSummary;
			
			System.out.println("Length of sys="+lengthOfSysSummary);
			System.out.println("Length of ref="+lengthOfrefSummary);
			
			System.out.println("precision="+precision);
			System.out.println("recall="+recall);
			f1=(2*(precision*recall))/(precision+recall);
			
			f1Scores.add(f1);
			
		}
		
		
		return f1Scores;
	}
	public static void main(String args[]) {
		Article a=new Article();
		List<Article> temp=new ArrayList();
		a.setSummarizedText("the cat was found under the bed");
		temp.add(a);
		List<String> ref=new ArrayList();
		ref.add("the cat was under the bed");
		ComputeRougeSummarizedMetric obj=new ComputeRougeSummarizedMetric();
		obj.getEvalauationForSummary(temp, ref);
		//String text="how do you do...";
		//System.out.println(text.replace(".", ""));
	}
}
