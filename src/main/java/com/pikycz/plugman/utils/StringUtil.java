package com.pikycz.plugman.utils;

/**
 * Utilities for String manipulation.
 *
 * @author rylinaux modified for Nukkit by PikyCZ
 */
public class StringUtil {

    /**
     * Returns an array of Strings as a single String.
     *
     * @param args the array
     * @param start the index to start at
     * @return the array as a String
     */
    public static String consolidateStrings(String[] args, int start) {
        String ret = args[start];
        if (args.length > (start + 1)) {
            for (int i = (start + 1); i < args.length; i++) {
                ret = ret + " " + args[i];
            }
        }
        return ret;
    }

}
