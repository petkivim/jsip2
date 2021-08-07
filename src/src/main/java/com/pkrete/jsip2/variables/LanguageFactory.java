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

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;

/**
 * This class generates Language objects based on the language code. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class LanguageFactory {

    /**
     * Reference to the singleton object.
     */
    private static LanguageFactory ref;

    /**
     * Constructs and initializes a new LanguageFactory object.
     */
    private LanguageFactory() {
    }

    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static LanguageFactory getInstance() {
        if (ref == null) {
            ref = new LanguageFactory();
        }
        return ref;
    }

    /**
     * Returns a language matching the given language code.
     * @param code language code
     * @return language with the given language code
     * @throws InvalidSIP2ResponseValueException 
     */
    public Language getLanguage(String code) throws InvalidSIP2ResponseValueException {
        if (code.equals("000")) {
            return Language.UNKNOWN;
        } else if (code.equals("001")) {
            return Language.ENGLISH;
        } else if (code.equals("002")) {
            return Language.FRENCH;
        } else if (code.equals("003")) {
            return Language.GERMAN;
        } else if (code.equals("004")) {
            return Language.ITALIAN;
        } else if (code.equals("005")) {
            return Language.DUTCH;
        } else if (code.equals("006")) {
            return Language.SWEDISH;
        } else if (code.equals("007")) {
            return Language.FINNISH;
        } else if (code.equals("008")) {
            return Language.SPANISH;
        } else if (code.equals("009")) {
            return Language.DANISH;
        } else if (code.equals("010")) {
            return Language.PORTUGESE;
        } else if (code.equals("011")) {
            return Language.CANADIAN_FRENCH;
        } else if (code.equals("012")) {
            return Language.NORWEGIAN;
        } else if (code.equals("013")) {
            return Language.HEBREW;
        } else if (code.equals("014")) {
            return Language.JAPANESE;
        } else if (code.equals("015")) {
            return Language.RUSSIAN;
        } else if (code.equals("016")) {
            return Language.ARABIC;
        } else if (code.equals("017")) {
            return Language.POLISH;
        } else if (code.equals("018")) {
            return Language.GREEK;
        } else if (code.equals("019")) {
            return Language.CHINESE;
        } else if (code.equals("020")) {
            return Language.KOREAN;
        } else if (code.equals("021")) {
            return Language.NORTH_AMERICAN_SPANISH;
        } else if (code.equals("022")) {
            return Language.TAMIL;
        } else if (code.equals("023")) {
            return Language.MALAY;
        } else if (code.equals("024")) {
            return Language.UNITED_KINGDOM;
        } else if (code.equals("025")) {
            return Language.ICELANDIC;
        } else if (code.equals("026")) {
            return Language.BELGIAN;
        } else if (code.equals("027")) {
            return Language.TAIWANESE;
        } else {
            throw new InvalidSIP2ResponseValueException("Invalid language code! The given code \"" + code + "\" doesn't match with any language!");
        }
    }
}