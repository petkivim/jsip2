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

import com.pkrete.jsip2.messages.requests.SIP2ItemStatusUpdateRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * The ILS must send this message in response to the
 * {@link SIP2ItemStatusUpdateRequest SIP2ItemStatusUpdateRequest} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ItemStatusUpdateResponse extends SIP2MessageResponse {

    /**
     * True means that the item properties have been stored on the ILS
     * database. False means that the properties were not stored.
     */
    private boolean itemPropertiesOk;

    /**
     * Constructs and initializes a new SIP2ItemStatusUpdateResponse  object containing
     * the given data.
     * @param data the data that the message contains
     */
    public SIP2ItemStatusUpdateResponse(String data) {
        super("20", data);
    }

    /**
     * Returns true if and only if the item properties have been stored on 
     * the ILS database.
     * @return true if the item properties have been stored on the ILS
     * database, otherwise false
     */
    public boolean isItemPropertiesOk() {
        return itemPropertiesOk;
    }

    /**
     * Sets the value that tells if the item properties have been stored on 
     * the ILS database.
     * @param itemPropertiesOk true or false
     */
    public void setItemPropertiesOk(boolean itemPropertiesOk) {
        this.itemPropertiesOk = itemPropertiesOk;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2Int(this.itemPropertiesOk));
        builder.append(this.transactionDate);

        builder.append("AB");
        builder.append(itemIdentifier);
        if (titleIdentifier != null) {
            builder.append("|AJ");
            builder.append(titleIdentifier);
        }
        if (this.itemProperties != null) {
            builder.append("|CH");
            builder.append(this.itemProperties);
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
