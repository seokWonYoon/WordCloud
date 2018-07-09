package com.test.wordcloud;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JFrame;

import com.test.wordcloud.image.CloudImageGenerator;
import com.test.wordcloud.words.StringProcessor;

/**
 * 
 * WordCloud.java
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
public class WordCloud {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final int PADDING = 30;

	public static final String TEXT_ENG = "textResource/eng/english_test.txt";
	public static final String TEXT_KOR = "textResource/kor/korean_test_미중무역전쟁.txt";

	public static final String FILTER_ENG = "textResource/filter/english_filtering.txt";
	public static final String FILTER_KOR = "textResource/filter/korean_filtering.txt";

	public static final String SELECTED_LANG = TEXT_KOR;
	public static final String SELECTED_FILTER = FILTER_KOR;

	
	/**
	 * 
	 * Method : main
	 * First Create : 2018. 7. 9.
	 * Writer : arthur
	 * Change History : 
	 * @param args
	 * @throws IOException
	 * Method explanation :
	 * determine title and size(WIDTH,HEIGHT,PADDING) of JFrame of word-cloud-window and file(SELECTED_LANG) and filter(SELECTED_FILTER)
	 */
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Word Cloud Practice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.pack();
		Insets insets = frame.getInsets();
		frame.setSize(calcScreenSize(insets));
		StringProcessor strProcessor = new StringProcessor(readFile(SELECTED_LANG), filteringList(SELECTED_FILTER));
		CloudImageGenerator generator = new CloudImageGenerator(WIDTH, HEIGHT, PADDING);
		frame.setContentPane(new CloudViewer(generator.generateImage(strProcessor, System.currentTimeMillis())));
		frame.setVisible(true);
	}

	/**
	 * 
	 * Method : readFile
	 * First Create : 2018. 7. 9.
	 * Writer : 
	 * Change History : 
	 * @param path
	 * @return
	 * @throws IOException
	 * Method explanation :
	 * 		step 1) text in file to buffer (this is byteType text)
	 * 		step 2) return decoded text 
	 */
	private static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	/**
	 * 
	 * Method : filteringList
	 * First Create : 2018. 7. 9.
	 * Writer : arthur
	 * Change History : 
	 * @param path
	 * @return
	 * @throws IOException
	 * Method explanation : This function generates a list of words to be filtered when a cloud is generated
	 */
	private static HashSet<String> filteringList(String path) throws IOException {
		HashSet<String> filter = new HashSet<String>();
		Scanner scan = new Scanner(new File(path));
		while (scan.hasNext()) {
			filter.add(scan.next());
		}
		return filter;
	}

	/**
	 * 
	 * Method : calcScreenSize
	 * First Create : 2018. 7. 9.
	 * Writer : arthur
	 * Change History : 
	 * @param insets
	 * @return
	 * Method explanation : determine ScreenSize 
	 */
	private static Dimension calcScreenSize(Insets insets) {
		int width = insets.left + insets.right + WIDTH + PADDING * 2;
		int height = insets.top + insets.bottom + HEIGHT + PADDING * 2;
		return new Dimension(width, height);
	}
}
