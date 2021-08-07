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

import com.pkrete.jsip2.messages.responses.SIP2EndSessionResponse;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;

/**
 * This class represents the message that is sent when a patron has completed
 * all their transactions. The ILS may, upon receipt of this command, close
 * any open files or deallocate data structures pertaining to that patron.
 * The ILS should respond with {@link SIP2EndSessionResponse SIP2EndSessionResponse}
 * message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2EndPatronSessionRequest extends SIP2MessageRequest {

    /**
     * Constructs and initializes a new SIP2EndPatronSessionRequest object.
     */
    private SIP2EndPatronSessionRequest() {
        super("35");
    }

    /**
     * Constructs and initializes a new SIP2EndPatronSessionRequest object
     * with the given values.
     * @param institutionId library's institution id
     * @param patronId 
     */
    public SIP2EndPatronSessionRequest(String institutionId, String patronId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2EndPatronSessionRequest object
     * with the given values.
     * @param institutionId library's institution id
     * @param terminalPasswd password to login the ILS
     * @param patronId an identifying value for the patron
     * @param patronPasswd password (PIN) of the patron
     */
    public SIP2EndPatronSessionRequest(String institutionId, String terminalPasswd, String patronId, String patronPasswd) {
        this(institutionId, patronId);
        this.terminalPassword = terminalPasswd;
        this.patronPassword = patronPasswd;
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
        if (terminalPassword != null) {
            builder.append("|AC");
            builder.append(terminalPassword);
        }
        if (patronPassword != null) {
            builder.append("|AD");
            builder.append(patronPassword);
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

