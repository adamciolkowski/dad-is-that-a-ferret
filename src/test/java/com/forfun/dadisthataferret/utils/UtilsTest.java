package com.forfun.dadisthataferret.utils;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtilsTest {

    Random random = mock(Random.class);

    List<String> list = asList("a", "b", "c");

    @Test
    public void shouldReturnElementAtRandomlySelectedIndex() {
        when(random.nextInt(list.size())).thenReturn(1);

        String element = Utils.randomElement(list, random);

        assertThat(element).isEqualTo("b");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenListIsEmpty() {
        Utils.randomElement(emptyList());
    }
}
