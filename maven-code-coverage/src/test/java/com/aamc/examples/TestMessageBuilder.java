package com.aamc.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMessageBuilder {

    @Test
    public void testammc() {

        MessageBuilder obj = new MessageBuilder();
        assertEquals("Hello ", obj.getMessage("Hello"));

    }

 }
