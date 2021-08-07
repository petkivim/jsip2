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

import com.pkrete.jsip2.messages.requests.SIP2EndPatronSessionRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * The ILS must send this message in response to the
 * {@link SIP2EndPatronSessionRequest SIP2EndPatronSessionRequest} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2EndSessionResponse extends SIP2MessageResponse {

    /**
     * Tells if the ILS has ended the patron's session. False means
     * an error condition.
     */
    private boolean endSession;

    /**
     * Constructs and initializes a new SIP2EndSessionResponse object containing
     * the given data.
     * @param data the data that the message contains
     */
    public SIP2EndSessionResponse(String data) {
        super("36", data);
    }

    /**
     * Returns true if and only if the ILS has ended the patron's session.
     * @return true if the ILS has ended the patron's session, otherwise false
     */
    public boolean isEndSession() {
        return endSession;
    }

    /**
     * Sets the value that tells if the ILS has ended the patron's session.
     * @param endSession true or false
     */
    public void setEndSession(boolean endSession) {
        this.endSession = endSession;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2Char(endSession));
        builder.append(this.transactionDate);

        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|AA");
        builder.append(this.patronIdentifier);

        for (String msg : screenMessage) {
            builder.append("|AF");
            builder.append(msg);
        }

        for (String msg : printLine) {
            builder.append("|AG");
            builder.append(msg);
        }

        builder.append("|");
        
        if (isSequence()) {
            builder.append("AY");
            builder.append(sequence);
        }
        builder.append("AZ");
        return MessageUtil.computeChecksum(builder.toString());
    }
}
