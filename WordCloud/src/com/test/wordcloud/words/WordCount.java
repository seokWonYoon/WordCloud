package com.test.wordcloud.words;

/**
 * 
 * WordCount.java
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
public class WordCount implements Comparable<WordCount> {
    public String word;
    public int n;
    
    public WordCount(String word, int n) {
        this.word = word;
        this.n = n;
    }

    /**
     * 
     * Method : compareTo
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param another
     * @return
     * Method explanation :
     */
    @Override
    public int compareTo(WordCount another) {
        return another.n - n;
    }
    
    /**
     * 
     * Method : toString
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @return
     * Method explanation :
     */
    @Override
    public String toString() {
        return word + "(" + n + ")";
    }
}