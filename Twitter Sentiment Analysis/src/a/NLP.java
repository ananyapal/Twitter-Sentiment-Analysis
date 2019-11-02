package a;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NLP {
    static StanfordCoreNLP pipeline;
    public static void init() 
    {
        pipeline = new StanfordCoreNLP("Configure.properties");
    }
    public static int computeSentiment(String text)
    {
 		int score = 2; // Default as Neutral. 1 = Negative, 2 = Neutral, 3 = Positive
    	String scoreStr; 
    	Annotation annotation = pipeline.process(text);
    	for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
    	{
    		scoreStr = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
    		Tree tree = sentence.get(SentimentAnnotatedTree.class);
    		score = RNNCoreAnnotations.getPredictedClass(tree);
    		System.out.println(scoreStr + "\t\t" + score + "\t" + sentence);
    	}
    	return(score);
    }
}
