package com.data.NLP;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class TestAlgo {

    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        // use faster shift reduce parser
        props.setProperty("parse.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz");
        props.setProperty("parse.maxlen", "100");
        // set up Stanford CoreNLP pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // build annotation for a review
        Annotation annotation =
                new Annotation("The small red car turned very quickly around the corner.");
        // annotate
        pipeline.annotate(annotation);
        // get tree
        Tree tree =
                annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);
        System.out.println(tree);
        Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
        for (Constituent constituent : treeConstituents) {
            if (constituent.label() != null &&
                    (constituent.label().toString().equals("VP") || constituent.label().toString().equals("NP"))) {
                System.err.println("found constituent: " + constituent.toString());
                System.err.println(tree.getLeaves().subList(constituent.start(), constituent.end() + 1));
            }
        }
        System.out.println("end");
    }
}
