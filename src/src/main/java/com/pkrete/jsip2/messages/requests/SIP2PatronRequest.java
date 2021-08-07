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
package com.pkrete.jsip2.messages.requests;

import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.variables.Language;

/**
 * This abstract class is a base class of the 
 * {@link SIP2PatronStatusRequest SIP2PatronStatusRequest} and the
 * {@link SIP2PatronInformationRequest SIP2PatronInformationRequest}
 * classes.
 *
 * @author Petteri Kivimäki
 */
public abstract class SIP2PatronRequest extends SIP2MessageRequest {

    /**
     * Language requested by the patron.
     */
    protected Language language;

    /**
     * Constructs and initializes a new SIP2PatronRequest with the given
     * command code.
     * @param code command code
     */
    protected SIP2PatronRequest(String code) {
        super(code);
        this.language = Language.UNKNOWN;
    }

    /**
     * Returns the language requested by the patron.
     * @return language language code of the selected language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     * @param language new value
     */
    public void setLanguage(Language language) {
        this.language = language;
    }
}
