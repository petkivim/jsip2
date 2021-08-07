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

import com.pkrete.jsip2.messages.requests.SIP2FeePaidRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * The ILS must send this message in response to the
 * {@link SIP2FeePaidRequest SIP2FeePaidRequest} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2FeePaidResponse extends SIP2MessageResponse {

    /**
     * True indicates that the ILS has accepted the payment from the patron
     * and the patron's account will be adjusted accordingly.
     */
    private boolean paymentAccepted;

    /**
     * Constructs and initializes a new SIP2FeePaidResponse object containing
     * the given data.
     * @param data the data that the message contains
     */
    public SIP2FeePaidResponse(String data) {
        super("38", data);
    }

    /**
     * Returns true if and only if the ILS has accepted the payment from 
     * the patron.
     * @return true the ILS has accepted the payment from the patron, otherwise
     * false
     */
    public boolean isPaymentAccepted() {
        return paymentAccepted;
    }

    /**
     * Sets the value that tells if the ILS has accepted the payment from the 
     * patron.
     * @param paymentAccepted true or false.
     */
    public void setPaymentAccepted(boolean paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2Char(paymentAccepted));
        builder.append(this.transactionDate);

        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|AA");
        builder.append(this.patronIdentifier);

        if (transactionId != null) {
            builder.append("|BK");
            builder.append(this.transactionId);
        }

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
