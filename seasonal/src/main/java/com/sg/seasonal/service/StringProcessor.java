package com.sg.seasonal.service;

/**
 *
 * @author jackelder
 */
public class StringProcessor {
    
    public static String removePlural(String word){
        if(word.endsWith("s")){
            word = removeLastChar(word);
            if(word.endsWith("e")){
                word = removeLastChar(word);
                if(word.endsWith("i")){
                    word = removeLastChar(word);
                }
            }
        }
        return word;
    }

    private static String removeLastChar(String word){
        return word.substring(0, word.length()-1);
    }
    
    public static String restrictLength(String input, int limit){
        return input.length() <= limit ? input : input.substring(0, limit);
    }
    
}
