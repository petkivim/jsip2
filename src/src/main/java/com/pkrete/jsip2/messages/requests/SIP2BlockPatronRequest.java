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
import com.pkrete.jsip2.messages.responses.SIP2PatronStatusResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * This class represents the message that is used to request the ILS to block
 * the patron card. This is, for example, sent when the patron is detected
 * tampering with the system or when a patron forgets to take their card. The
 * ILS should invalidate the patron's card and respond with a
 * {@link SIP2PatronStatusResponse SIP2PatronStatusResponse} message. The
 * ILS could also notify the library staff that the card has been blocked.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2BlockPatronRequest extends SIP2MessageRequest {

    /**
     * Indicates that the patron'n library card has been retained by
     * the system. The ILS may ignore this field or notify the library staff
     * that the patron's card has been retained by the system.
     */
    private boolean cardRetained;
    /**
     * The reason why the patron's card was blocked.
     */
    private String blockedCardMsg;

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object.
     */
    private SIP2BlockPatronRequest() {
        super("01");
        this.institutionId = "";
        this.blockedCardMsg = "";
        this.patronIdentifier = "";
        this.terminalPassword = "";
    }

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object with
     * the given values.
     * @param patronId an identifying value for the patron
     */
    public SIP2BlockPatronRequest(String patronId) {
        this();
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object with
     * the given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     */
    public SIP2BlockPatronRequest(String institutionId, String patronId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object with
     * the given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param message reason why the patron's card was blocked
     */
    public SIP2BlockPatronRequest(String institutionId, String patronId, String message) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.blockedCardMsg = message;
    }

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object with
     * the given values.
     * @param institutionId library's institution id
     * @param terminalPasswd passwort to login the ILS
     * @param patronId an identifying value for the patron
     * @param message reason why the patron's card was blocked
     */
    public SIP2BlockPatronRequest(String institutionId, String terminalPasswd, String patronId, String message) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.blockedCardMsg = message;
        this.terminalPassword = terminalPasswd;
    }

    /**
     * Returns true if and only if the patron'n library card has been retained by
     * the system.
     * @return true if the patron'n library card has been retained by
     * the system, otherwise false
     */
    public boolean isCardRetained() {
        return cardRetained;
    }

    /**
     * Sets the value that tells if the patron'n library card has been retained 
     * by the system.
     * @param cardRetained true or false
     */
    public void setCardRetained(boolean cardRetained) {
        this.cardRetained = cardRetained;
    }

    /**
     * Returns the reason why the patron's card was blocked.
     * @return the reason why the patron's card was blocked
     */
    public String getBlockedCardMsg() {
        return blockedCardMsg;
    }

    /**
     * Sets the reason why the patron's card was blocked,
     * @param blockedCardMsg new reason
     */
    public void setBlockedCardMsg(String blockedCardMsg) {
        this.blockedCardMsg = blockedCardMsg;
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
        builder.append(StringUtil.bool2Char(this.cardRetained));
        builder.append(transactionDate);

        builder.append("AO");
        builder.append(institutionId);
        builder.append("|AL");
        builder.append(blockedCardMsg);
        builder.append("|AA");
        builder.append(patronIdentifier);
        builder.append("|AC");
        builder.append(terminalPassword);

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
