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

import com.pkrete.jsip2.messages.responses.SIP2CheckoutResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * This class represents the message that is used to checkout an item, and also
 * to cancel a {@link SIP2CheckinRequest SIP2CheckinRequest} that did not 
 * succesfully complete. The ILS SIP server must respond with the 
 * {@link SIP2CheckoutResponse SIP2CheckoutResponse} message.
 * 
 * This class extends the abstract {@link SIP2CirculationTransactionRequest SIP2CirculationTransactionRequest} 
 * class.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2CheckoutRequest extends SIP2CirculationTransactionRequest {

    /**
     * Tells if the system has been configured to do renewals by the library
     * staff. False means that the system has been configured not to do
     * renewals.
     */
    private boolean scRenewalPolicy;

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object.
     */
    private SIP2CheckoutRequest() {
        super("11");
        this.institutionId = "";
        this.patronIdentifier = "";
        this.itemIdentifier = "";
        this.terminalPassword = "";
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     */
    public SIP2CheckoutRequest(String patronId, String itemId) {
        this();
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     */
    public SIP2CheckoutRequest(String institutionId, String patronId, String itemId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;      
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     * @param itemId an identifying value for the item
     */
    public SIP2CheckoutRequest(String institutionId, String patronId, String patronPassword, String itemId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.patronPassword = patronPassword;
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2CheckoutRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param terminalPassword password to login the ILS
     * @param patronId an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     * @param itemId an identifying value for the item
     */
    public SIP2CheckoutRequest(String institutionId, String terminalPassword, String patronId, String patronPassword, String itemId) {
        this();
        this.institutionId = institutionId;
        this.terminalPassword = terminalPassword;
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
        this.patronPassword = patronPassword;
    }

    /**
     * Returns true if and only if the system has been configured
     * to do renewals, otherwise return false.
     * @return true if the system has been configured to do renewals, 
     * otherwise false
     */
    public boolean isScRenewalPolicy() {
        return scRenewalPolicy;
    }

    /**
     * Changes the boolean value that tells if the system does renewals.
     * @param scRenewalPolicy new value
     */
    public void setScRenewalPolicy(boolean scRenewalPolicy) {
        this.scRenewalPolicy = scRenewalPolicy;
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
        builder.append(StringUtil.bool2Char(this.scRenewalPolicy));
        builder.append(StringUtil.bool2Char(this.noBlock));
        builder.append(transactionDate);
        builder.append(nbDueDate);
        builder.append("AO");
        builder.append(institutionId);
        builder.append("|AA");
        builder.append(patronIdentifier);
        builder.append("|AB");
        builder.append(itemIdentifier);
        builder.append("|AC");
        builder.append(terminalPassword);
        if (itemProperties != null) {
            builder.append("|CH");
            builder.append(itemProperties);
        }
        if (patronPassword != null) {
            builder.append("|AD");
            builder.append(patronPassword);
        }
        if (this.useFeeAcknowledged) {
            builder.append("|BO");
            builder.append(StringUtil.bool2Char(this.feeAcknowledged));
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
