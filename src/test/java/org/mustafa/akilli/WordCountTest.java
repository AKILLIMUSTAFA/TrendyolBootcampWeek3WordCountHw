package org.mustafa.akilli;

import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCountTest {

    WordCount sut;

    @BeforeEach
    public void setup() {
        sut = new WordCount();
    }

    @AfterEach
    public void cleanup() {
        sut = null;
    }

    @Test
    public void calculateWordCount_WhenInputIsNull_ShouldThrowIllegalArgumentException(){
        //Arrange
        String sentence = null;

        //Act
        Throwable throwable = Java6Assertions.catchThrowable(() -> sut.calculateWordCount(sentence));

        //Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Sentence must not be null!!");
    }

    @Test
    public void calculateWordCount_WhenInputIsEmpty_ShouldReturn0(){
        //Arrange
        String sentence = "Hi all";

        //Act
        int result = sut.calculateWordCount(sentence);

        //Assert
        assertEquals(2, result);
    }

    @Test
    public void calculateWordCount_WhenInputStringOnlyContains1SpaceAnd2DifferentWords_ShouldReturn2(){
        //Arrange
        String sentence = "Hi all";

        //Act
        int result = sut.calculateWordCount(sentence);

        //Assert
        assertEquals(2, result);
    }

    @Test
    public void calculateWordCount_WhenInputStringOnlyContains1SpaceAnd2IdenticalWordsWithDifferentLetterCase_ShouldReturn1(){
        //Arrange
        String sentence = "David davId";

        //Act
        int result = sut.calculateWordCount(sentence);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void calculateWordCount_WhenInputStringOnlyContains1SpaceAnd2IdenticalWordsExceptTheApostrophe_ShouldReturn3(){
        //Arrange
        String sentence = "David's Davids";

        //Act
        int result = sut.calculateWordCount(sentence);

        //Assert
        assertEquals(1, result);
    }

    @ParameterizedTest
    @MethodSource("provideWordCountData")
    public void calculateWordCount_WhenInputStringContainsSpaceOrApostropheOrDifferentLetterCaseOrPunctuation_ShouldReturnWordCount(String sentence, int expectedResult){
        //Arrange

        //Act
        int result = sut.calculateWordCount(sentence);

        //Assert
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideWordCountData() {
        return Stream.of(
                Arguments.of("David and David's friend Davids.", 5),
                Arguments.of("David.", 1),
                Arguments.of("David, Davids and Henry will go to Sivas.", 8),
                Arguments.of("Happiness is not just happiness.", 4),
                Arguments.of("David is awesome.", 3));
    }

    @Test
    public void splitStringBySpace_WhenInputStringContains0Space_ShouldReturn1(){
        //Arrange
        String sentence = "David";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(1, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContains1Space_ShouldReturn2(){
        //Arrange
        String sentence = "David's Davids";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(2, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContains2Space_ShouldReturn3(){
        //Arrange
        String sentence = "David is Davids";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(3, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContainssideBySide2SpaceAnd2Words_ShouldReturn2(){
        //Arrange
        String sentence = "David  friends";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(2, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContainsASpaceAtTheBeginningAnd1Word_ShouldReturn1(){
        //Arrange
        String sentence = " David";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(1, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContainsTwoSpaceAtTheBeginningAnd1Word_ShouldReturn1(){
        //Arrange
        String sentence = "  David";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(1, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContainsTwoSpaceAtTheEndAnd1Word_ShouldReturn1(){
        //Arrange
        String sentence = "David  ";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(1, resultList.size());
    }

    @Test
    public void splitStringBySpace_WhenInputStringContainsTwoSpaceAtTheBeginningAndTwoSpaceAtTheEndAnd1Word_ShouldReturn1(){
        //Arrange
        String sentence = "  David  ";

        //Act
        ArrayList<String> resultList = sut.splitStringBySpace(sentence);

        //Assert
        assertEquals(1, resultList.size());
    }

    @Test
    public void clearThePunctuationFromWord_WhenInputStringContainsDot_ShouldReturnWithoutDot(){
        //Arrange
        String word = "david.";

        //Act
        String result = sut.clearThePunctuationFromWord(word);

        //Assert
        assertThat(result).isEqualTo("david");
    }

    @Test
    public void clearThePunctuationFromWord_WhenInputStringContainsComma_ShouldReturnWithoutComma(){
        //Arrange
        String word = "david,";

        //Act
        String result = sut.clearThePunctuationFromWord(word);

        //Assert
        assertThat(result).isEqualTo("david");
    }

    @Test
    public void clearThePunctuationFromWord_WhenInputStringDoesNotContainsPunctuation_ShouldReturnSameString(){
        //Arrange
        String word = "david";

        //Act
        String result = sut.clearThePunctuationFromWord(word);

        //Assert
        assertThat(result).isEqualTo("david");
    }

    @Test
    public void separateWordsThatHaveApostrophes_WhenInputStringDoesNotContainsApostrophes_ShouldReturn1(){
        //Arrange
        String word = "David";

        //Act
        ArrayList<String> resultList = sut.separateWordsThatHaveApostrophes(word);

        //Assert
        assertEquals(1, resultList.size());
    }

    @Test
    public void separateWordsThatHaveApostrophes_WhenInputStringContainsApostrophes_ShouldReturn2(){
        //Arrange
        String word = "David's";

        //Act
        ArrayList<String> resultList = sut.separateWordsThatHaveApostrophes(word);

        //Assert
        assertEquals(2, resultList.size());
    }

    @Test
    public void uppercaseToLowercase_WhenInputStringContainsUppercase_ShouldReturnStringWithLowercase(){
        //Arrange
        String word = "DAVID";

        //Act
        String result = sut.uppercaseToLowercase(word);

        //Assert
        assertThat(result).isEqualTo("david");
    }

    @Test
    public void uppercaseToLowercase_WhenInputStringDoesNotContainsUppercase_ShouldReturnSameString(){
        //Arrange
        String word = "david";

        //Act
        String result = sut.uppercaseToLowercase(word);

        //Assert
        assertThat(result).isEqualTo("david");
    }




}
