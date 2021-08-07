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

import com.pkrete.jsip2.messages.responses.SIP2RenewResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * This class represents the message that is used to renew an item. The ILS SIP 
 * server should respond with the {@link SIP2RenewResponse SIP2RenewResponse} 
 * message. Either or both the item identifier and title identifier fields
 * must be present for the message to be useful.
 * 
 * This class extends the abstract {@link SIP2CirculationTransactionRequest SIP2CirculationTransactionRequest} 
 * class.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2RenewRequest extends SIP2CirculationTransactionRequest {

    /**
     * If this value is false then ILS should not allow third party renewals.
     * This allows the library staff to prevent third part renewals from
     * this terminal.
     */
    private boolean thirdPartyAllowed;

    /**
     * Constructs and initializes a new SIP2RenewRequest object.
     */
    private SIP2RenewRequest() {
        super("29");
        this.institutionId = "";
        this.patronIdentifier = "";
    }

    /**
     * Constructs and initializes a new SIP2RenewRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     */
    public SIP2RenewRequest(String patronId) {
        this();
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2RenewRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     */
    public SIP2RenewRequest(String patronId, String itemId) {
        this();
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2RenewRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     * @param titleId an identifying value for the title
     */
    public SIP2RenewRequest(String patronId, String itemId, String titleId) {
        this();
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
        this.titleIdentifier = titleId;
    }

    /**
     * Constructs and initializes a new SIP2RenewRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     * @param titleId an identifying value for the title
     */
    public SIP2RenewRequest(String institutionId, String patronId, String itemId, String titleId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
        this.titleIdentifier = titleId;
    }

    /**
     * Returns true if and only if third party renewals are allowed.
     * @return true if third party renewals are allowed, otherwise false
     */
    public boolean isThirdPartyAllowed() {
        return thirdPartyAllowed;
    }

    /**
     * Sets the value that tells if third party renewals are allowed.
     * @param thirdPartyAllowed true or false
     */
    public void setThirdPartyAllowed(boolean thirdPartyAllowed) {
        this.thirdPartyAllowed = thirdPartyAllowed;
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
        builder.append(StringUtil.bool2Char(this.thirdPartyAllowed));
        builder.append(StringUtil.bool2Char(this.noBlock));
        builder.append(transactionDate);
        builder.append(nbDueDate);

        builder.append("AO");
        builder.append(institutionId);
        builder.append("|AA");
        builder.append(patronIdentifier);

        if (patronPassword != null) {
            builder.append("|AD");
            builder.append(patronPassword);
        }
        if (itemIdentifier != null) {
            builder.append("|AB");
            builder.append(itemIdentifier);
        }
        if (titleIdentifier != null) {
            builder.append("|AJ");
            builder.append(titleIdentifier);
        }
        if (terminalPassword != null) {
            builder.append("|AC");
            builder.append(terminalPassword);
        }
        if (itemProperties != null) {
            builder.append("|CH");
            builder.append(itemProperties);
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
