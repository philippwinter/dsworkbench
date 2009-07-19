/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util;

import java.awt.Color;
import java.util.Hashtable;

/**
 *
 * @author Jejkal
 */
public class Constants {

    public final static double VERSION = 1.7;
    public final static String VERSION_ADDITION = "";
    public final static Color DS_BACK = new Color(225, 213, 190);
    public final static Color DS_BACK_LIGHT = new Color(239, 235, 223);
    public final static String SERVER_DIR = "./servers";
    public static Hashtable<String, Integer> LAYERS = null;
    public final static int LAYER_COUNT = 10;


    static {
        LAYERS = new Hashtable<String, Integer>();
        LAYERS.put("Markierungen", 0);
        LAYERS.put("Dörfer", 1);
        LAYERS.put("Dorfsymbole", 2);
        LAYERS.put("Dorfinformation", 3);
        LAYERS.put("Truppendichte", 4);
        LAYERS.put("Notizmarkierungen", 5);
        LAYERS.put("Angriffe", 6);
        LAYERS.put("Unterstützungen", 7);
        LAYERS.put("Formen", 8);
        LAYERS.put("Kirchenradien", 9);
    }
}
