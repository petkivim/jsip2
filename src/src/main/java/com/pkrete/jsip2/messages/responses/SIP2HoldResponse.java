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

import com.pkrete.jsip2.messages.requests.SIP2HoldRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;

/**
 * The ILS must send this message in response to the
 * {@link SIP2HoldRequest SIP2HoldRequest} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2HoldResponse extends SIP2CirculationTransactionResponse {

    /**
     * True means that the item is available, it's not checked out or on hold.
     */
    private boolean available;
    /**
     * A numeric value for the patron's position in the hold queue for an item.
     */
    private String queuePosition;
    /**
     * ISBN number of the record that was put on hold. 
     * This is a Voyager ESIP extension.
     */
    private String isbn;
    /**
     * LCCN number of the record that was put on hold. This is a Voyager 
     * ESIP extension.
     */
    private String lccn;

    /**
     * Constructs and initializes a new SIP2HoldResponse object
     * containing the given data.
     * @param data the data that the message contains
     */
    public SIP2HoldResponse(String data) {
        super("16", data);
    }

    /**
     * Returns true if and only if the item is available, it's not checked out 
     * or on hold.
     * @return true the item is available, otherwise false 
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the value that tells the item is available.
     * @param available true or false
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns a numeric value for the patron's position in the hold queue 
     * for an item.
     * @return numeric value for the patron's position in the hold queue 
     * for an item 
     */
    public String getQueuePosition() {
        return queuePosition;
    }

    /**
     * Sets the patron's position in the hold queue for an item.
     * @param queuePosition numeric value
     */
    public void setQueuePosition(String queuePosition) {
        this.queuePosition = queuePosition;
    }

    /**
     * Returns the ISBN number of the record that was put on hold. This is an 
     * extension field and it's not included in the original SIP2 definition.
     * @return ISBN number of the record that was put on hold
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN number of the record that was put on hold. This is an 
     * extension field and it's not included in the original SIP2 definition.
     * @param isbn ISBN number
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns the LCCN number of the record that was put on hold. This is a 
     * Voyager ESIP extension and it's not included in the original SIP2 
     * definition.
     * @return LCCN number of the record that was put on hold
     */
    public String getLccn() {
        return lccn;
    }

    /**
     * Sets the LCCN number of the record that was put on hold. This is a 
     * Voyager ESIP extension and it's not included in the original SIP2 
     * definition.
     * @param lccn LCCN number
     */
    public void setLccn(String lccn) {
        this.lccn = lccn;
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
        builder.append(StringUtil.bool2Char(this.available));
        builder.append(this.transactionDate);

        if (this.expirationDate != null) {
            builder.append("BW");
            builder.append(this.expirationDate);
            builder.append("|");
        }
        if (this.queuePosition != null) {
            builder.append("BR");
            builder.append(this.queuePosition);
            builder.append("|");
        }
        if (this.pickupLocation != null) {
            builder.append("BS");
            builder.append(this.pickupLocation);
            builder.append("|");
        }

        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|AA");
        builder.append(patronIdentifier);
        if (itemIdentifier != null) {
            builder.append("|AB");
            builder.append(this.itemIdentifier);
        }
        if (titleIdentifier != null) {
            builder.append("|AJ");
            builder.append(this.titleIdentifier);
        }
        if (bibId != null) {
            builder.append("|MA");
            builder.append(this.bibId);
        }
        if (isbn != null) {
            builder.append("|MB");
            builder.append(this.isbn);
        }
        if (lccn != null) {
            builder.append("|MC");
            builder.append(this.lccn);
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
