package com.test.wordcloud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 * CloudViewer.java
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
public class CloudViewer extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage bi;
    
    public CloudViewer(BufferedImage bi) {
        this.bi = bi;
    }
    
    /**
     * 
     * Method : paint
     * First Create : 2018. 7. 9.
     * Writer : arthur
     * Change History : 
     * @param g
     * Method explanation :
     */
    public void paint(Graphics g) {
        g.drawImage(bi, 0, 0, null);
    }
}
