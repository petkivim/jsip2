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

import com.pkrete.jsip2.messages.requests.SIP2CheckinRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;
import com.pkrete.jsip2.variables.AlertType;

/**
 * This class represents the response message that the ILS SIP server must 
 * send in response to the {@link SIP2CheckinRequest SIP2CheckinRequest}
 * message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2CheckinResponse extends SIP2CirculationTransactionResponse {

    /**
     * Tells if the item should be resensitized.
     */
    private boolean resensitize;
    /**
     * Tells if there's an alert related to the item or the the patron.
     * The alert indicates conditions like items on hold, items belonging to
     * another library branch, or other alert conditions as determined by
     * the ILS.
     */
    private boolean alert;
    /**
     * A bin number that indicates how the item should be sorted. 
     * Optional field.
     */
    private String sortBin;
    /**
     * The item collection code. Collection codes represent a set of item within
     * library or a location for a group of item. One use of this field is in
     * sortation systems for high level sorting of materials.
     */
    private String collectionCode;
    /**
     * Code which indicates the item's permanent physical location.
     * This is an extension field and it's not included in the original SIP2 
     * definition.
     */
    private String callNumber;
    /**
     * The description or identifier for the location where the item should go
     * next. This is an extension field and it's not included in the original SIP2 
     * definition.
     */
    private String destinationLocation;
    /**
     * The type of the alert. This is an extension field and it's not included
     * in the original SIP2 definition.
     */
    private AlertType alertType;
    /**
     * The patron id of the patron assigned to the hold for the item.
     * This is an extension field and it's not included in the original SIP2 
     * definition.
     */
    private String holdPatronId;
    /**
     * The patron name of the patron assigned to the hold for the item.
     * This is an extension field and it's not included in the original SIP2 
     * definition.
     */
    private String holdPatronName;

    /**
     * Constructs and initializes a new SIP2CheckinResponse object
     * containing the given data.
     * @param data the data that the message contains
     */
    public SIP2CheckinResponse(String data) {
        super("10", data);
    }

    /**
     * Returns true if and only if the item should be resensitized.
     * @return true if the item should be resensitized, otherwise false
     */
    public boolean isResensitize() {
        return resensitize;
    }

    /**
     * Sets the value that tells if the item should be resensitized.
     * @param resensitize true or false
     */
    public void setResensitize(boolean resensitize) {
        this.resensitize = resensitize;
    }

    /**
     * Returns true if and only if there's an alert related to the item or 
     * the the patron. The alert indicates conditions like items on hold, 
     * items belonging to another library branch, or other alert conditions 
     * as determined by the ILS.
     * @return if there's an alert related to the item or 
     * the the patron, otherwise false
     */
    public boolean isAlert() {
        return alert;
    }

    /**
     * Sets the value that tells if there's an alert related to the item or 
     * the the patron.
     * @param alert true or false
     */
    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    /**
     * Returns the bin number that indicates how the item should be sorted.
     * @return bin number that indicates how the item should be sorted
     */
    public String getSortBin() {
        return sortBin;
    }

    /**
     * Sets the bin number that indicates how the item should be sorted.
     * @param sortBin new bin number
     */
    public void setSortBin(String sortBin) {
        this.sortBin = sortBin;
    }

    /**
     * Returns the item collection code. Collection codes represent a set 
     * of item within library or a location for a group of item. One use
     * of this field is in sortation systems for high level sorting of materials.
     * @return collection code
     */
    public String getCollectionCode() {
        return collectionCode;
    }

    /**
     * Sets the item collection code. Collection codes represent a set 
     * of item within library or a location for a group of item. One use
     * of this field is in sortation systems for high level sorting of materials.
     * @param collectionCode new collection code
     */
    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }

    /**
     * Returns the code which indicates the item's permanent physical location.
     * @return code which indicates the item's permanent physical location
     */
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * Sets the code which indicates the item's permanent physical location.
     * @param callNumber new code
     */
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    /**
     * Returns the description or identifier for the location where the item 
     * should go next.
     * @return description or identifier for the location where the item 
     * should go next.
     */
    public String getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * Sets the description or identifier for the location where the item 
     * should go next.
     * @param destinationLocation new location
     */
    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    /**
     * Return the type of the alert.
     * @return type of the alert
     */
    public AlertType getAlertType() {
        return alertType;
    }

    /**
     * Sets the type of the alert.
     * @param alertType new alert type
     */
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    /**
     * Returns the patron id of the patron assigned to the hold for the item.
     * @return id of the patron assigned to the hold for the item
     */
    public String getHoldPatronId() {
        return holdPatronId;
    }

    /**
     * Sets the patron id of the patron assigned to the hold for the item.
     * @param holdPatronId patron id
     */
    public void setHoldPatronId(String holdPatronId) {
        this.holdPatronId = holdPatronId;
    }

    /**
     * Returns the patron name of the patron assigned to the hold for the item.
     * @return name of the patron assigned to the hold for the item
     */
    public String getHoldPatronName() {
        return holdPatronName;
    }

    /**
     * Sets the patron name of the patron assigned to the hold for the item.
     * @param holdPatronName name of the patron
     */
    public void setHoldPatronName(String holdPatronName) {
        this.holdPatronName = holdPatronName;
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
        builder.append(StringUtil.bool2Char(this.resensitize));

        if (!this.magneticMediaSupported) {
            builder.append("U");
        } else {
            builder.append(StringUtil.bool2Char(this.magneticMedia));
        }

        builder.append(StringUtil.bool2Char(this.alert));
        builder.append(this.transactionDate);

        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|AB");
        builder.append(this.itemIdentifier);
        builder.append("|AQ");
        builder.append(this.permanentLocation);
        builder.append("|AJ");
        builder.append(this.titleIdentifier);

        if (this.sortBin != null) {
            builder.append("|CL");
            builder.append(this.sortBin);
        }
        if (this.patronIdentifier != null) {
            builder.append("|AA");
            builder.append(this.patronIdentifier);
        }
        if (this.mediaType != null) {
            builder.append("|CK");
            builder.append(this.mediaType);
        }
        if (this.itemProperties != null) {
            builder.append("|CH");
            builder.append(this.itemProperties);
        }
        if (this.collectionCode != null) {
            builder.append("|CR");
            builder.append(this.collectionCode);
        }
        if (this.callNumber != null) {
            builder.append("|CS");
            builder.append(this.callNumber);
        }
        if (this.destinationLocation != null) {
            builder.append("|CT");
            builder.append(this.destinationLocation);
        }
        if (this.alertType != null) {
            builder.append("|CV");
            builder.append(this.alertType);
        }
        if (this.holdPatronId != null) {
            builder.append("|CY");
            builder.append(this.holdPatronId);
        }
        if (this.holdPatronName != null) {
            builder.append("|DA");
            builder.append(this.holdPatronName);
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
