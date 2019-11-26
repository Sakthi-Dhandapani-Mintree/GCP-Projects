package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRatingController {

    @Test
    public void testName() {

        MessageBuilder obj = new MessageBuilder();
        assertEquals("Hello Sathi", obj.getMessage("Sakthi"));

    }
