/*
 * The MIT License
 *
 * Copyright 2012- Petteri Kivimäki
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.pkrete.jsip2.variables;

/**
 * This enum defines a set of languages that can be used in SIP2
 * request and response messages. Each language has a code that is
 * used in the communication between the system and the ILS.
 * 
 * @author Petteri Kivimäki
 */
public enum Language {

    UNKNOWN("000"), ENGLISH("001"), FRENCH("002"), GERMAN("003"),
    ITALIAN("004"), DUTCH("005"), SWEDISH("006"), FINNISH("007"),
    SPANISH("008"), DANISH("009"), PORTUGESE("010"), CANADIAN_FRENCH("011"),
    NORWEGIAN("012"), HEBREW("013"), JAPANESE("014"), RUSSIAN("015"),
    ARABIC("016"), POLISH("017"), GREEK("018"), CHINESE("019"),
    KOREAN("020"), NORTH_AMERICAN_SPANISH("021"), TAMIL("022"),
    MALAY("023"), UNITED_KINGDOM("024"), ICELANDIC("025"), BELGIAN("026"),
    TAIWANESE("027");
    
    private String value;

    private Language(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
