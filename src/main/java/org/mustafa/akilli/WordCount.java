package org.mustafa.akilli;

import java.util.*;

public class WordCount {
    List<String> wordsArray;
    Map<String,Integer> uniqueWordArray;


    public int calculateWordCount(String sentence) {

        if(sentence == null){
            throw new IllegalArgumentException("Sentence must not be null!!");
        }

        if(sentence.length() == 0){
            return 0;
        }

        wordsArray = splitStringBySpace(sentence);
        separateWordsListThatHaveApostrophes(wordsArray);
        clearThePunctuationFromWordList(wordsArray);
        uppercaseToLowercaseForWordList(wordsArray);
        uniqueWordArray = arrayToUniqueMap(wordsArray);

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

    public void clearThePunctuationFromWordList(List<String> wordsList) {
        for (int i = 0; i < wordsList.size(); i++) {
            wordsList.set(i,clearThePunctuationFromWord(wordsList.get(i)));
        }
    }

    public String clearThePunctuationFromWord(String word) {
        return word.replaceAll("[^a-zA-Z ]", "");
    }

    public void separateWordsListThatHaveApostrophes(List<String> wordsList) {
        for (int i = 0; i < wordsList.size(); i++) {
            List<String> wordsWithoutApostrophes = separateWordsThatHaveApostrophes(wordsList.get(i));
            if(wordsWithoutApostrophes.size() > 1){
                wordsList.set(i,wordsWithoutApostrophes.get(0));

                for (int j = 1; j < wordsWithoutApostrophes.size(); j++) {
                    wordsList.add(wordsWithoutApostrophes.get(j));
                }
            }
        }
    }

    public List<String> separateWordsThatHaveApostrophes(String word) {
        String[] splited = word.split("'");
        List<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, splited);
        return arrayList;
    }

    public void uppercaseToLowercaseForWordList(List<String> wordsList) {
        for (int i = 0; i < wordsList.size(); i++) {
            wordsList.set(i,uppercaseToLowercaseForWord(wordsList.get(i)));
        }
    }

    public String uppercaseToLowercaseForWord(String word) {
        return word.toLowerCase(Locale.ENGLISH);
    }

    public Map<String,Integer> arrayToUniqueMap(List<String> wordsList){
        Map<String,Integer> uniqueWordArray = new HashMap<>();

        for (int i = 0; i < wordsArray.size(); i++) {
            if(wordsArray.get(i).length() > 0){
                uniqueWordArray.put(wordsArray.get(i),0);
            }
        }

        return uniqueWordArray;
    }
}
