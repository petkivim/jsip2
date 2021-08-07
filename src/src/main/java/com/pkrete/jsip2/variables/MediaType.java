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
 * This enum defines a set of media types that can be used in SIP2
 * response messages to describe the media type of an item.
 * 
 * @author Petteri Kivimäki
 */
public enum MediaType {

    OTHER("000"), BOOK("001"), MAGAZINE("002"),
    BOUND_JOURNAL("003"), AUDIO_TAPE("004"), VIDEO_TAPE("005"),
    CD_CDROM("006"), DISKETTE("007"), BOOK_WITH_DISKETTE("008"),
    BOOK_WITH_CD("009"), BOOK_WITH_AUDIO_TAPE("010");
    private String value;

    private MediaType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
