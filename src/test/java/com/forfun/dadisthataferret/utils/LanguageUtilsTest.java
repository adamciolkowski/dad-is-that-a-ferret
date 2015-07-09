package com.forfun.dadisthataferret.utils;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LanguageUtilsTest {

    @Test
    public void shouldReturnArticleAnForWordsBeginningWithConsonants() {
        String article = LanguageUtils.articleFor("raccoon");

        assertThat(article).isEqualTo("a");
    }

    @Test
    public void shouldReturnArticleAnForWordsBeginningWithVowel() {
        String anArticle = "an";
        assertThat(LanguageUtils.articleFor("armadillo")).isEqualTo(anArticle);
        assertThat(LanguageUtils.articleFor("elephant")).isEqualTo(anArticle);
        assertThat(LanguageUtils.articleFor("eagle")).isEqualTo(anArticle);
        assertThat(LanguageUtils.articleFor("iguana")).isEqualTo(anArticle);
        assertThat(LanguageUtils.articleFor("owl")).isEqualTo(anArticle);
        assertThat(LanguageUtils.articleFor("umbrellabird")).isEqualTo(anArticle);
    }
}