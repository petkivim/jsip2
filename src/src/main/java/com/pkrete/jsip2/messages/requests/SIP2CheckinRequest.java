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

import com.pkrete.jsip2.messages.responses.SIP2CheckinResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * This class represents the message that is used to checkin an item, and also
 * to cancel a {@link SIP2CheckoutRequest SIP2CheckoutRequest} that 
 * did not succesfully complete. The ILS SIP server must respond with the 
 * {@link SIP2CheckinResponse SIP2CheckinResponse} message.
 * 
 * This class extends the abstract {@link SIP2CirculationTransactionRequest SIP2CirculationTransactionRequest} 
 * class.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2CheckinRequest extends SIP2CirculationTransactionRequest {

    /**
     * The date that an item was returned to the library, that is not
     * necessarily the same date when the item was checked back in. 18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS.
     */
    private String returnDate;

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object.
     */
    private SIP2CheckinRequest() {
        super("09");
        this.currentLocation = "";
        this.institutionId = "";
        this.itemIdentifier = "";
        this.terminalPassword = "";
        this.returnDate = MessageUtil.getSipDateTime();
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param itemId an identifying value for the item
     */
    public SIP2CheckinRequest(String itemId) {
        this();
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param location current location of the item
     * @param itemId an identifying value for the item
     */
    public SIP2CheckinRequest(String location, String itemId) {
        this();
        this.currentLocation = location;
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param location current location of the item
     * @param institutionId library's institution id
     * @param itemId an identifying value for the item
     */
    public SIP2CheckinRequest(String location, String institutionId, String itemId) {
        this();
        this.currentLocation = location;
        this.institutionId = institutionId;
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param location current location of the item
     * @param terminalPassword passwort to login the ILS
     * @param institutionId library's institution id
     * @param itemId an identifying value for the item
     */
    public SIP2CheckinRequest(String location, String terminalPassword, String institutionId, String itemId) {
        this();
        this.currentLocation = location;
        this.institutionId = institutionId;
        this.itemIdentifier = itemId;
        this.terminalPassword = terminalPassword;
    }

    /**
     * Returns the date that an item was returned to the library, that is not
     * necessarily the same date when the item was checked back in. 18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @return date that an item was returned to the library
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date that an item was returned to the library, that is not
     * necessarily the same date when the item was checked back in. 18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @param returnDate new value
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    /**
     * Returns a String presentation of this message, that is sent
     * to the ILS SIP server. The message contains all the variables
     * plus sequence and checksum values when error detection is enabled.
     */
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2Char(this.noBlock));
        builder.append(transactionDate);
        builder.append(returnDate);
        builder.append("AP");
        builder.append(currentLocation);
        builder.append("|AO");
        builder.append(institutionId);
        builder.append("|AB");
        builder.append(itemIdentifier);
        builder.append("|AC");
        builder.append(terminalPassword);
        if (itemProperties != null) {
            builder.append("|CH");
            builder.append(itemProperties);
        }
        if (this.useCancel) {
            builder.append("|BI");
            builder.append(StringUtil.bool2Char(this.cancel));
        }
        builder.append("|");

        if (errorDetectionEnabled) {
            builder.append("AY");
            builder.append(getSequence());
            builder.append("AZ");
            this.checkSum = MessageUtil.computeChecksum(builder.toString());
            builder.append(checkSum);
        }
        return builder.toString() + '\r';
    }
}
