package com.test.wordcloud.color;

import java.awt.Color;

/**
 * 
 * ColorCombinations.java
 *
 * @author arthur
 * @since 2018. 7. 9.
 * @version 1.0
 * @see <a href="http://www.colorcombos.com">can find more combinations from www.colorcombos.com</a>
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
public class ColorCombinations {
    public static String[] NORMAL = { "#D9CCB9", "#DF7782", "#E95D22", "#017890", "#613D2D" };
    public static String[] THEME2 = { "#0A224E", "#BF381A", "#A0D8F1", "#E9AF32", "#E07628" };
    public static String[] THEME3 = { "#37241E", "#94B3C8", "#4D4E24", "#BD8025", "#816A4A" };
    public static String[] WHITE_TO_RED = { "#FFFFFF", "#FFFF66", "#FFCC00", "#FF9900", "#FF0000"};
    public static String[] WHITE_TO_BLUE = { "#FFFFFF", "#94BAE7", "#639ACE", "#4A79A5", "#31659C"};
    public static String[] SELECT_THEME = WHITE_TO_BLUE;

    private int idx = 0;
    private final Color background;
    private final Color[] scheme;
    
    
    
    /**
     * 
     * @param colors
     */
    public ColorCombinations(String[] colors) {
        this(colors, Color.BLACK);
    }
    
    /**
     * 
     * @param colors
     * @param background
     */
    public ColorCombinations(String[] colors, Color background) {
        scheme = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            scheme[i] = Color.decode(colors[i]);
        }
        this.background = background;
    }

    /**
     * 
     * Method : next
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @return
     * Method explanation :
     */
    public Color next() {
        idx = idx % scheme.length;
        return scheme[idx++];
    }
    
    /**
     * 
     * Method : background
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @return
     * Method explanation :
     */
    public Color background() {
        return background;
    }
    
}
