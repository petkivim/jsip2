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

import com.pkrete.jsip2.messages.requests.SIP2CheckoutRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * This class represents the response message that the ILS SIP server must 
 * send in response to the {@link SIP2CheckoutRequest SIP2CheckoutRequest}
 * message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2CheckoutResponse extends SIP2CirculationTransactionResponse {

    /**
     * Tells if the item is already checked out. True means that the item was
     * already checked out to the same patron, so the item is actually being
     * renewed. False means that the patron did not already have the item 
     * checked out.
     */
    protected boolean renewalOk;
    /**
     * Tells if the ILS recognizes if the item should or should not be
     * desensitized. If this value is false, then also desensitize must
     * be false.
     */
    protected boolean desensitizeSupported;
    /**
     * Tells if the system should desensitize the item. If the ILS does not
     * know if the item should be desensitized or not, then this value must
     * be false.
     */
    protected boolean desensitize;
    /**
     * Tells if the system should ignore the security status of the item.
     * Optional field.
     */
    protected boolean securityInhibit;
    /**
     * Indicates if the securityInhibit value was used in the response
     * received from the ILS SIP server.
     */
    protected boolean securityInhibitUsed;

    /**
     * Constructs and initializes a new SIP2CheckoutResponse object
     * containing the given data.
     * @param code command identifier
     * @param data the data that the message contains
     */
    protected SIP2CheckoutResponse(String code, String data) {
        super(code, data);
    }

    /**
     * Constructs and initializes a new SIP2CheckoutResponse object
     * containing the given data.
     * @param data the data that the message contains
     */
    public SIP2CheckoutResponse(String data) {
        this("12", data);
    }

    /**
     * Returns true if and only if the item is already checked out. 
     * True means that the item was  already checked out to the same patron, 
     * so the item is actually being renewed. False means that the patron 
     * did not already have the item checked out.
     * @return true if the item is already checked out, otherwise false
     */
    public boolean isRenewalOk() {
        return renewalOk;
    }

    /**
     * Changes the value that tells if the item is already checked out. 
     * @param renewalOk true or false
     */
    public void setRenewalOk(boolean renewalOk) {
        this.renewalOk = renewalOk;
    }

    /**
     * Returns true if and only if the ILS recognizes that the item should 
     * or should not be desensitized.
     * @return true if the ILS recognizes that the item should 
     * or should not be desensitized, otherwise false
     */
    public boolean isDesensitizeSupported() {
        return desensitizeSupported;
    }

    /**
     * Sets the value that tells if the ILS recognizes that the item should 
     * or should not be desensitized.
     * @param desensitizeSupported true or false
     */
    public void setDesensitizeSupported(boolean desensitizeSupported) {
        this.desensitizeSupported = desensitizeSupported;
    }

    /**
     * Returns true if and only if the system should desensitize the item. 
     * If the ILS does not know if the item should be desensitized or not, 
     * then this value must be false.
     * @return true if the item should be desensitized, otherwise false
     */
    public boolean isDesensitize() {
        return desensitize;
    }

    /**
     * Sets the value that tells if the system should desensitize the item. 
     * If the ILS does not know if the item should be desensitized or not, 
     * then this value must be false.
     * @param desensitize true or false
     */
    public void setDesensitize(boolean desensitize) {
        this.desensitize = desensitize;
    }

    /**
     * Returns true if and only if the system should ignore the security 
     * status of the item.
     * @return true if the system should ignore the security 
     * status of the item, otherwise false
     */
    public boolean isSecurityInhibit() {
        return securityInhibit;
    }

    /**
     * Sets the value that tells if the system should ignore the security 
     * status of the item.
     * @param securityInhibit true or false
     */
    public void setSecurityInhibit(boolean securityInhibit) {
        this.securityInhibit = securityInhibit;
    }

    /**
     * Returns true if and only if the securityInhibit field was used
     * in the response message received from the ILS SIP server.
     * @return true if the securityInhibit field was used
     * in the response message, otherwise false
     */
    public boolean isSecurityInhibitUsed() {
        return securityInhibitUsed;
    }

    /**
     * Sets the boolean value that tells if the securityInhibit field 
     * was used in the response message received from the ILS SIP server.
     * @param securityInhibitUsed true or false
     */
    public void setSecurityInhibitUsed(boolean securityInhibitUsed) {
        this.securityInhibitUsed = securityInhibitUsed;
    }


    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.code);
        builder.append(StringUtil.bool2Int(this.ok));
        builder.append(StringUtil.bool2Char(this.renewalOk));

        if (!this.magneticMediaSupported) {
            builder.append("U");
        } else {
            builder.append(StringUtil.bool2Char(this.magneticMedia));
        }

        if (!this.desensitizeSupported) {
            builder.append("U");
        } else {
            builder.append(StringUtil.bool2Char(this.desensitize));
        }

        builder.append(this.transactionDate);

        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|AA");
        builder.append(this.patronIdentifier);
        builder.append("|AB");
        builder.append(this.itemIdentifier);
        builder.append("|AJ");
        builder.append(this.titleIdentifier);
        builder.append("|AH");
        builder.append(this.dueDate);

        if (this.feeType != null) {
            builder.append("|BT");
            builder.append(this.feeType);
        }

        if (this.isSecurityInhibitUsed()) {
            builder.append("|CI");
            builder.append(StringUtil.bool2Char(this.securityInhibit));
        }

        if (this.currencyType != null) {
            builder.append("|BH");
            builder.append(this.currencyType);
        }

        if (this.feeAmount != null) {
            builder.append("|BV");
            builder.append(this.feeAmount);
        }

        if (this.mediaType != null) {
            builder.append("|CK");
            builder.append(this.mediaType);
        }

        if (this.itemProperties != null) {
            builder.append("|CH");
            builder.append(this.itemProperties);
        }

        if (this.transactionId != null) {
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
