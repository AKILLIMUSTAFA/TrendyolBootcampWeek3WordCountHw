package org.mustafa.akilli;

import java.util.*;

public class WordCount {
    List<String> wordsArray;
    Map<String,Integer> uniqueWordArray = new HashMap<>();


    public int calculateWordCount(String sentence) {

        if(sentence == null){
            throw new IllegalArgumentException("Sentence must not be null!!");
        }

        if(sentence.length() == 0){
            return 0;
        }

        wordsArray = splitStringBySpace(sentence);
        wordsArray = separateWordsListThatHaveApostrophes(wordsArray);
        wordsArray = clearThePunctuationFromWordList(wordsArray);
        wordsArray = uppercaseToLowercaseForWordList(wordsArray);

        for (int i = 0; i < wordsArray.size(); i++) {
            if(wordsArray.get(i).length() > 0){
                uniqueWordArray.put(wordsArray.get(i),0);
            }
        }

        return uniqueWordArray.size();
    }

    public List<String> splitStringBySpace(String sentence) {
        String[] splited = sentence.split("\\s+");
        List<String> arrayList = new ArrayList<>();

        for (int i = 0; i < splited.length; i++) {
            if(!splited[i].equals("")){
                arrayList.add(splited[i]);
            }
        }
        return arrayList;
    }

    public List<String> clearThePunctuationFromWordList(List<String> wordsList) {
        List<String> wordListWithoutPunctuation = new ArrayList<>();

        for (int i = 0; i < wordsList.size(); i++) {
            wordListWithoutPunctuation.add(clearThePunctuationFromWord(wordsList.get(i)));
        }

        return wordListWithoutPunctuation;
    }

    public String clearThePunctuationFromWord(String word) {
        return word.replaceAll("[^a-zA-Z ]", "");
    }

    public List<String> separateWordsListThatHaveApostrophes(List<String> wordsList) {
        List<String> wordListWithoutApostrophes = new ArrayList<>();

        for (int i = 0; i < wordsList.size(); i++) {
            wordListWithoutApostrophes.addAll(separateWordsThatHaveApostrophes(wordsList.get(i)));
        }

        return wordListWithoutApostrophes;
    }

    public List<String> separateWordsThatHaveApostrophes(String word) {
        String[] splited = word.split("'");
        List<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, splited);
        return arrayList;
    }

    public List<String> uppercaseToLowercaseForWordList(List<String> wordsList) {
        List<String> wordListWithLowercase = new ArrayList<>();

        for (int i = 0; i < wordsList.size(); i++) {
            wordListWithLowercase.add(uppercaseToLowercaseForWord(wordsList.get(i)));
        }

        return wordListWithLowercase;
    }

    public String uppercaseToLowercaseForWord(String word) {
        return word.toLowerCase(Locale.ENGLISH);
    }
}
