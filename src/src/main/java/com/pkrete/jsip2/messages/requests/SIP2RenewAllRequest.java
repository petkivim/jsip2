/*
 * The MIT License
 *
 * Copyright 2012-2014 Petteri Kivimki
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

import com.pkrete.jsip2.messages.responses.SIP2RenewAllResponse;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * This class represents the message that is used to renew all items that the
 * patron has checked out. The ILS SIP server should respond with the 
 * {@link SIP2RenewAllResponse SIP2RenewAllResponse} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2RenewAllRequest extends SIP2MessageRequest {

    /**
     * Constructs and initializes a new SIP2RenewAllRequest object.
     */
    private SIP2RenewAllRequest() {
        super("65");
        this.institutionId = "";
        this.patronIdentifier = "";
    }

    /**
     * Constructs and initializes a new SIP2RenewAllRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     */
    public SIP2RenewAllRequest(String patronId) {
        this();
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2RenewAllRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     */
    public SIP2RenewAllRequest(String institutionId, String patronId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2RenewAllRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     */
    public SIP2RenewAllRequest(String institutionId, String patronId, String patronPassword) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.patronPassword = patronPassword;
    }

    /**
     * Constructs and initializes a new SIP2RenewAllRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param terminalPassword password to login the ILS
     * @param patronId an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     */
    public SIP2RenewAllRequest(String institutionId, String terminalPassword, String patronId, String patronPassword) {
        this();
        this.institutionId = institutionId;
        this.terminalPassword = terminalPassword;
        this.patronIdentifier = patronId;
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
        builder.append(transactionDate);

        builder.append("AO");
        builder.append(institutionId);
        builder.append("|AA");
        builder.append(patronIdentifier);

        if (patronPassword != null) {
            builder.append("|AD");
            builder.append(patronPassword);
        }
        if (terminalPassword != null) {
            builder.append("|AC");
            builder.append(terminalPassword);
        }
        if (this.useFeeAcknowledged) {
            builder.append("|BO");
            builder.append(StringUtil.bool2Char(this.feeAcknowledged));
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
