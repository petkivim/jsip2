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

import com.pkrete.jsip2.messages.responses.SIP2PatronStatusResponse;
import com.pkrete.jsip2.util.MessageUtil;

/**
 * This class represents the message that is used to request the patron
 * information from the ILS SIP server. The ILS SIP server must respond
 * with the {@link SIP2PatronStatusResponse SIP2PatronStatusResponse}
 * message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2PatronStatusRequest extends SIP2PatronRequest {

    /**
     * Constructs and initializes a new SIP2PatronStatusRequest object.
     */
    private SIP2PatronStatusRequest() {
        super("23");
        this.institutionId = "";
        this.patronPassword = "";
        this.terminalPassword = "";
    }

    /**
     * Constructs and initializes a new SIP2PatronStatusRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * @param patronIdentifier an identifying value for the patron
     */
    public SIP2PatronStatusRequest(String patronIdentifier) {
        this();
        this.patronIdentifier = patronIdentifier;
    }

    /**
     * Constructs and initializes a new SIP2PatronStatusRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * @param institutionId library's institution id
     * @param patronIdentifier an identifying value for the patron
     */
    public SIP2PatronStatusRequest(String institutionId, String patronIdentifier) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronIdentifier;
    }

    /**
     * Constructs and initializes a new SIP2PatronStatusRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * @param institutionId library's institution id
     * @param patronIdentifier an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     */
    public SIP2PatronStatusRequest(String institutionId, String patronIdentifier, String patronPassword) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronIdentifier;
        this.patronPassword = patronPassword;
    }

    /**
     * Constructs and initializes a new SIP2PatronStatusRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * @param institutionId library's institution id
     * @param terminalPassword passwort to login the ILS
     * @param patronIdentifier an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     */
    public SIP2PatronStatusRequest(String institutionId, String terminalPassword, String patronIdentifier, String patronPassword) {
        this();
        this.institutionId = institutionId;
        this.terminalPassword = terminalPassword;
        this.patronIdentifier = patronIdentifier;
        this.patronPassword = patronPassword;
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
        builder.append(language);
        builder.append(transactionDate);
        builder.append("AO");
        builder.append(institutionId);
        builder.append("|AA");
        builder.append(patronIdentifier);
        builder.append("|AC");
        builder.append(terminalPassword);
        builder.append("|AD");
        builder.append(patronPassword);
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
