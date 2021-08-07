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

import com.pkrete.jsip2.messages.requests.SIP2RenewAllRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;
import java.util.List;

/**
 * This class represents the response message that the ILS SIP server must 
 * send in response to the {@link SIP2RenewAllRequest SIP2RenewAllRequest}
 * message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2RenewAllResponse extends SIP2MessageResponse {

    /**
     * A count of the number of items that were renewed.
     */
    private int renewedCount;
    /**
     * A count of the number of items that were not renewed.
     */
    private int unrenewedCount;
    /**
     * List of renewed items.
     */
    private List<String> renewedItems;
    /**
     * List of unrenewd items.
     */
    private List<String> unrenewedItems;

    /**
     * Constructs and initializes a new SIP2RenewAllResponse object
     * containing the given data.
     * @param data the data that the message contains
     */
    public SIP2RenewAllResponse(String data) {
        super("66", data);
    }

    /**
     * Returns the count of the number of items that were renewed.
     * @return count of the number of items that were renewed
     */
    public int getRenewedCount() {
        return renewedCount;
    }

    /**
     * Sets the count of the number of items that were renewed.
     * @param renewedCount new value
     */
    public void setRenewedCount(int renewedCount) {
        this.renewedCount = renewedCount;
    }

    /**
     * Returns the count of the number of items that were not renewed.
     * @return count of the number of items that were not renewed
     */
    public int getUnrenewedCount() {
        return unrenewedCount;
    }

    /**
     * Sets the count of the number of items that were not renewed.
     * @param unrenewedCount new value
     */
    public void setUnrenewedCount(int unrenewedCount) {
        this.unrenewedCount = unrenewedCount;
    }

    /**
     * Returns a list of items that were renewed.
     * @return list of items that were renewed
     */
    public List<String> getRenewedItems() {
        return renewedItems;
    }

    /**
     * Sets the list of items that were renewed.
     * @param renewedItems new list
     */
    public void setRenewedItems(List<String> renewedItems) {
        this.renewedItems = renewedItems;
    }

    /**
     * Returns a list of items that were not renewed.
     * @return list of items that were not renewed
     */
    public List<String> getUnrenewedItems() {
        return unrenewedItems;
    }

    /**
     * Returns the list of items that were not renewed.
     * @param unrenewedItems new list
     */
    public void setUnrenewedItems(List<String> unrenewedItems) {
        this.unrenewedItems = unrenewedItems;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2Int(this.ok));
        builder.append(StringUtil.intToFixedLengthString(this.renewedCount, 4));
        builder.append(StringUtil.intToFixedLengthString(this.unrenewedCount, 4));
        builder.append(this.transactionDate);

        builder.append("AO");
        builder.append(this.institutionId);

        for (String item : renewedItems) {
            builder.append("|BM");
            builder.append(item);
        }

        for (String item : unrenewedItems) {
            builder.append("|BN");
            builder.append(item);
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
