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
 * This class generates MediaType objects based on the media code. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class MediaTypeFactory {

    /**
     * Reference to the singleton object.
     */
    private static MediaTypeFactory ref;

    /**
     * Constructs and initializes a new MediaTypeFactory object.
     */
    private MediaTypeFactory() {
    }

    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static MediaTypeFactory getInstance() {
        if (ref == null) {
            ref = new MediaTypeFactory();
        }
        return ref;
    }

    /**
     * Returns the media type that matches the given code.
     * @param code media type code
     * @return media type that matches the given code
     * @throws InvalidSIP2ResponseValueException
     */
    public MediaType getMediaType(String code) throws InvalidSIP2ResponseValueException {
        if (code.equals("000")) {
            return MediaType.OTHER;
        } else if (code.equals("001")) {
            return MediaType.BOOK;
        } else if (code.equals("002")) {
            return MediaType.MAGAZINE;
        } else if (code.equals("003")) {
            return MediaType.BOUND_JOURNAL;
        } else if (code.equals("004")) {
            return MediaType.AUDIO_TAPE;
        } else if (code.equals("005")) {
            return MediaType.VIDEO_TAPE;
        } else if (code.equals("006")) {
            return MediaType.CD_CDROM;
        } else if (code.equals("007")) {
            return MediaType.DISKETTE;
        } else if (code.equals("008")) {
            return MediaType.BOOK_WITH_DISKETTE;
        } else if (code.equals("009")) {
            return MediaType.BOOK_WITH_CD;
        } else if (code.equals("010")) {
            return MediaType.BOOK_WITH_AUDIO_TAPE;
        } else {
            throw new InvalidSIP2ResponseValueException("Invalid media type code! The given code \"" + code + "\" doesn't match with any media type!");
        }
    }
}
