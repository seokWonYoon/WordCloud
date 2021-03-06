package com.test.wordcloud.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.test.wordcloud.color.ColorCombinations;
import com.test.wordcloud.words.WordCount;

/**
 * 
 * CloudImageGenerator.java
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
public class CloudImageGenerator {
    private static final int REJECT_COUNT = 100;
    private static final int LARGEST_FONT_SIZE = 200;//160
    private static final int FONT_STEP_SIZE = 5; //10
    private static final int MINIMUM_FONT_SIZE = 20;//10
    
    private static final int MINIMUM_WORD_COUNT = 2;
//    public static final String FONT_FAMILY = "나눔명조";
    public static final String FONT_FAMILY = "Helvetica";
    public static final String[] THEME = ColorCombinations.SELECT_THEME;
    
    private String fontFamily;
    private final int width;
    private final int height;
    private final int padding;
    private BufferedImage bi;
    private ColorCombinations colorTheme;
    private ArrayList<Shape> occupied = new ArrayList<Shape>();
    
    public CloudImageGenerator(int width, int height, int padding) {
        this.width = width;
        this.height = height;
        this.fontFamily = FONT_FAMILY;
        this.padding = padding;
    }

    /**
     * 
     * Method : generateImage
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param words
     * @param seed
     * @return
     * Method explanation : handling word's size and drawing 
     * @see <a href="http://static.mrfeinberg.com/bv_ch03.pdf">http://static.mrfeinberg.com/bv_ch03.pdf</a><br>
     */
    public BufferedImage generateImage(Iterable<WordCount> words, long seed) {
        Random rand = new Random(seed);
        bi = new BufferedImage(width + 2 * padding, height + 2 * padding, BufferedImage.TYPE_INT_ARGB);
        colorTheme = new ColorCombinations(THEME);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(colorTheme.background());
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.translate(padding, padding);
        
        Iterator<WordCount> iter = words.iterator();
        int k = LARGEST_FONT_SIZE;
        while (iter.hasNext()) {
            WordCount wc = iter.next();
            if (wc.n < MINIMUM_WORD_COUNT) break;
            int prevK = k;
            if (k > MINIMUM_FONT_SIZE) k = k - FONT_STEP_SIZE;
            Font font = new Font(fontFamily, Font.BOLD, k);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            Shape s = stringShape(font, fm, wc.word, rand);
            boolean fitted = false;
            for (int i = 0; i < REJECT_COUNT * wc.n; i++) {
                s = stringShape(font, fm, wc.word, rand);
                if (!collision(s.getBounds())) {
                    fitted = true;
                    break;
                }
            }
            if (!fitted) {
                k = prevK;
                continue;
            }
            g.setColor(colorTheme.next());
            g.fill(s);
            occupied.add(s);
        }
        return bi;
    }
    
    private boolean collision(Rectangle area) {
        for (Shape shape : occupied) {
            if (shape.intersects(area)) {	return true;	}
        }
        return false;
    }
    
    /**
     * 
     * Method : stringShape
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param font
     * @param fm
     * @param word
     * @param rand
     * @return
     * Method explanation :  use public BufferedImage generateImage()'s set
     */
    private Shape stringShape(Font font, FontMetrics fm, String word, Random rand) {
        int strWidth = fm.stringWidth(word);
        int strHeight = fm.getAscent();
        int x = rand.nextInt(width - strWidth);
        int y = rand.nextInt(height- strHeight) + strHeight;
        GlyphVector v = font.createGlyphVector(fm.getFontRenderContext(), word);
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        v.setGlyphTransform(0, at);
        return v.getOutline();
    }
    
    /**
     * 
     * Method : setColorTheme
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param colorCodes
     * @param background
     * Method explanation :
     */
    public void setColorTheme(String[] colorCodes, Color background) {
        colorTheme = new ColorCombinations(colorCodes, background);
    }
    
    /**
     * 
     * Method : setFontFamily
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param fontFamily
     * Method explanation :
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
}
