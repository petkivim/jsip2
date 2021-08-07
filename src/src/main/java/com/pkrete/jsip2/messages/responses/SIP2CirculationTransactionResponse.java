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
package com.pkrete.jsip2.messages.responses;

import com.pkrete.jsip2.messages.SIP2MessageResponse;

/**
 * This abstract class is a base class of the 
 * {@link SIP2CheckoutResponse SIP2CheckoutResponse } and the
 * {@link SIP2CheckinResponse SIP2CheckinResponse} classes.
 *
 * @author Petteri Kivimäki
 */
public abstract class SIP2CirculationTransactionResponse extends SIP2MessageResponse {
    /**
     * Tells if the ILS identifies magnetic media items. If this value is false,
     * then also magneticMedia is false.
     */
    protected boolean magneticMediaSupported;
    /**
     * Tells if the item is magnetic media and the system will then handle 
     * the security discharge accordingly. This value should be used if and
     * only if the ILS identifies magnetic media items, which means that
     * magneticMediaSupported should be true.
     */
    protected boolean magneticMedia;

    
    /**
     * Constructs and initializes a new SIP2CirculationTransactionResponse 
     * object containing the given data.
     * @param code command identifier
     * @param data the data that the message contains
     */
    protected SIP2CirculationTransactionResponse(String code, String data) {
        super(code, data);
    }

    /**
     * Returns true if and only if the ILS identifies magnetic media items.
     * Otherwise returns false.
     * @return true if the ILS identifies magnetic media item, otherwise false
     */
    public boolean isMagneticMediaSupported() {
        return magneticMediaSupported;
    }

    /**
     * Sets the value that tells if the ILS identifies magnetic media items.
     * @param magneticMediaSupported new value
     */
    public void setMagneticMediaSupported(boolean magneticMediaSupported) {
        this.magneticMediaSupported = magneticMediaSupported;
    }

    /**
     * Returns true if and only if the item is magnetic media and the ILS 
     * identifies magnetic media. Otherwise returns false.
     * @return true if item is magnetic media and ILS identifies magnetic media,
     * otherwise false
     */
    public boolean isMagneticMedia() {
        return magneticMedia;
    }

    /**
     * Sets the value that tells if the item is magnetic media and the ILS 
     * identifies magnetic media.
     * @param magneticMedia new value
     */
    public void setMagneticMedia(boolean magneticMedia) {
        this.magneticMedia = magneticMedia;
    }
}
