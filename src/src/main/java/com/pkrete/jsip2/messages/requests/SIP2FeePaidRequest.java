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

import com.pkrete.jsip2.messages.responses.SIP2FeePaidResponse;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.variables.CurrencyType;
import com.pkrete.jsip2.variables.FeeType;
import com.pkrete.jsip2.variables.PaymentType;

/**
 * This class represents the message that can be used to notify the ILS that
 * a fee has been collected from the patron. The ILS should record this
 * information in its database and respond with a
 * {@link SIP2FeePaidResponse SIP2FeePaidResponse} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2FeePaidRequest extends SIP2MessageRequest {

    /**
     * The type of the payment: cash, VISA or credit card.
     */
    private PaymentType paymentType;
    /**
     * Identifies a specific fee, possible together with fee type.
     * This identifier would have to be user-selected from a list of fees.
     */
    private String feeIdentifier;

    /**
     * Constructs and initializes a new SIP2FeePaidRequest object.
     */
    private SIP2FeePaidRequest() {
        super("37");
        this.institutionId = "";
        this.patronIdentifier = "";
        this.feeType = FeeType.OTHER_UNKNONW;
        this.paymentType = PaymentType.CASH;
        this.currencyType = CurrencyType.EURO;
        this.feeAmount = "0.00";
    }

    /**
     * Constructs and initializes a new SIP2FeePaidRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     * @param feeAmount fee amount that the patron has paid
     */
    public SIP2FeePaidRequest(String patronId, String feeAmount) {
        this();
        this.patronIdentifier = patronId;
        this.feeAmount = feeAmount;
    }

    /**
     * Constructs and initializes a new SIP2FeePaidRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param feeAmount fee amount that the patron has paid
     */
    public SIP2FeePaidRequest(String institutionId, String patronId, String feeAmount) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.feeAmount = feeAmount;
    }

    /**
     * Returns the type of the payment: cash, VISA or credit card.
     * @return type of the payment
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     * @param paymentType new payment type
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Returns a value that identifies a specific fee, possible together 
     * with fee type.
     * @return value that identifies a specific fee
     */
    public String getFeeIdentifier() {
        return feeIdentifier;
    }

    /**
     * Sets the value that identifies a specific fee, possible together 
     * with fee type.
     * @param feeIdentifier new identifier
     */
    public void setFeeIdentifier(String feeIdentifier) {
        this.feeIdentifier = feeIdentifier;
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
        builder.append(feeType);
        builder.append(paymentType);
        builder.append(currencyType);

        builder.append("BV");
        builder.append(feeAmount);
        builder.append("|AO");
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
        if (feeIdentifier != null) {
            builder.append("|CG");
            builder.append(feeIdentifier);
        }
        if (transactionId != null) {
            builder.append("|BK");
            builder.append(transactionId);
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
