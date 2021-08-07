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

import com.pkrete.jsip2.messages.responses.SIP2HoldResponse;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;
import com.pkrete.jsip2.variables.HoldMode;
import com.pkrete.jsip2.variables.HoldType;

/**
 * This class represents the message that can is used to create, modify, or delete
 * a hold. The ILS should respond with a {@link SIP2HoldResponse SIP2HoldResponse}
 * message. Either or both of the item identifier and title identifier fields must 
 * be present for the message to be useful.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2HoldRequest extends SIP2MessageRequest {

    /**
     * The type of the hold transaction: add, delete or modify. The
     * default is add.
     */
    private HoldMode holdMode;
    /**
     * The type of the hold: other, any copy, specific copy
     * or any copy at a single branch or sublocation.
     */
    private HoldType holdType;

    /**
     * Constructs and initializes a new SIP2HoldRequest object.
     */
    private SIP2HoldRequest() {
        super("15");
        this.institutionId = "";
        this.patronIdentifier = "";
        this.holdMode = HoldMode.ADD;
    }

    /**
     * Constructs and initializes a new SIP2HoldRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     */
    public SIP2HoldRequest(String patronId) {
        this();
        this.patronIdentifier = patronId;
    }

    /**
     * Constructs and initializes a new SIP2HoldRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     */
    public SIP2HoldRequest(String patronId, String itemId) {
        this();
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
    }

    /**
     * Constructs and initializes a new SIP2HoldRequest object with the
     * given values.
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     * @param titleId an identifying value for the title
     */
    public SIP2HoldRequest(String patronId, String itemId, String titleId) {
        this();
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
        this.titleIdentifier = titleId;
    }

    /**
     * Constructs and initializes a new SIP2HoldRequest object with the
     * given values.
     * @param institutionId library's institution id
     * @param patronId an identifying value for the patron
     * @param itemId an identifying value for the item
     * @param titleId an identifying value for the title
     */
    public SIP2HoldRequest(String institutionId, String patronId, String itemId, String titleId) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronId;
        this.itemIdentifier = itemId;
        this.titleIdentifier = titleId;
    }

    /**
     * Returns the type of the hold transaction: create, delete or modify.
     * @return type of the hold transaction
     */
    public HoldMode getHoldMode() {
        return holdMode;
    }

    /**
     * Sets the type of the hold transaction: create, delete or modify.
     * @param holdMode new hold mode
     */
    public void setHoldMode(HoldMode holdMode) {
        this.holdMode = holdMode;
    }

    /**
     * Returns the type of the hold: other, any copy, specific copy
     * or any copy at a single branch or sublocation.
     * @return type of the hold
     */
    public HoldType getHoldType() {
        return holdType;
    }

    /**
     * Sets the type of the hold: other, any copy, specific copy
     * or any copy at a single branch or sublocation.
     * @param holdType new hold type
     */
    public void setHoldType(HoldType holdType) {
        this.holdType = holdType;
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
        builder.append(holdMode);
        builder.append(transactionDate);

        if (this.expirationDate != null) {
            builder.append("BW");
            builder.append(expirationDate);
            builder.append("|");
        }
        if (this.pickupLocation != null) {
            builder.append("BS");
            builder.append(pickupLocation);
            builder.append("|");
        }
        if (this.holdType != null) {
            builder.append("BY");
            builder.append(holdType);
            builder.append("|");
        }

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
        if (this.useFeeAcknowledged) {
            builder.append("|BO");
            builder.append(StringUtil.bool2Char(this.feeAcknowledged));
        }
        if (bibId != null) {
            builder.append("|MA");
            builder.append(bibId);
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
