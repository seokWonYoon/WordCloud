package com.test.wordcloud.words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * StringProcessor.java
 *
 * @author arthur
 * @since 2018. 7. 9.
 * @version 1.0
 * @see
 *
 * <pre>
 * Modification Infomation History
 * 
 * 	ModificationDate	Modificator		ModificationContent
 * ----------------------------------------------------------
 * 	2018. 7. 9.			arthur			first create
 * 
 * </pre>
 */
public class StringProcessor implements Iterable<WordCount>{
    private String str;
    private final HashSet<String> filter;
    private ArrayList<WordCount> words;
    
    /**
     * 
     * @param str : 
     * @param filter
     */
    public StringProcessor(String str, HashSet<String> filter) {
        this.str = str;
        this.filter = filter;
        processString();
    }
    
    /**
     * 
     * Method : processString
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * Method explanation : 
     */
    private void processString() {
        Scanner scan = new Scanner(str);
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        while (scan.hasNext()) {
            String word = removePunctuations(scan.next());
            if (filter.contains(word)) {	continue;	}
            if (word.equals("")) {	continue;	};
            Integer n = count.get(word);
            count.put(word, (n == null) ? 1 : n + 1);
        }
        PriorityQueue<WordCount> pq = new PriorityQueue<WordCount>();
        for (Entry<String, Integer> entry : count.entrySet()) {
            pq.add(new WordCount(entry.getKey(), entry.getValue()));
        }
        words = new ArrayList<WordCount>();
        while (!pq.isEmpty()) {
            WordCount wc = pq.poll();
            if (wc.word.length() > 1) {	words.add(wc); }
        }
    }
    
   /**
    * 
    * Method : print
    * First Create : 2018. 7. 9.
    * Writer : arthur
    * Change History : 
    * Method explanation :
    */
    public void print() {
        Iterator<WordCount> iter = iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    /**
     * 
     * Method : iterator
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @return
     * Method explanation :
     */
    @Override
    public Iterator<WordCount> iterator() {
        return words.iterator();
    }
    
    /**
     * 
     * Method : removePunctuations
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param str
     * @return
     * Method explanation :
     */
    private static String removePunctuations(String str) {
        return str.replaceAll("\\p{Punct}|\\p{Digit}", "");
    }
}
