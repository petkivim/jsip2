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

import com.pkrete.jsip2.messages.requests.SIP2ItemInformationRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.variables.CirculationStatus;
import com.pkrete.jsip2.variables.SecurityMarker;

/**
 * The ILS must send this message in response to the
 * {@link SIP2ItemInformationRequest SIP2ItemInformationRequest} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ItemInformationResponse extends SIP2MessageResponse {

    /**
     * The circulation status of the item.
     */
    private CirculationStatus circulationStatus;
    /**
     * The security marker of the item.
     */
    private SecurityMarker securityMarker;
    /**
     * Number of patrons requesting this item. Optional field.
     */
    private String holdQueueLength;
    /**
     * The name of the institution or library that owns the item.
     * Optional field.
     */
    private String owner;
    /**
     * The date that the recall was issued. 18-char, fixed-length 
     * field: YYYYMMDDZZZZHHMMSS. Optional field.
     */
    private String recallDate;
    /**
     * The date that the hold expires. 18-char, fixed-length 
     * field: YYYYMMDDZZZZHHMMSS. Optional field.
     */
    private String holdPickupDate;

    /**
     * Constructs and initializes a new SIP2ItemInformationResponse object containing
     * the given data.
     * @param data the data that the message contains
     */
    public SIP2ItemInformationResponse(String data) {
        super("18", data);
    }

    /**
     * Returns the circulation status of the item.
     * @return circulation status
     */
    public CirculationStatus getCirculationStatus() {
        return circulationStatus;
    }

    /**
     * Changes the circulation status of the item.
     * @param circulationStatus new status
     */
    public void setCirculationStatus(CirculationStatus circulationStatus) {
        this.circulationStatus = circulationStatus;
    }

    /**
     * Returns the security marker of the item.
     * @return security marker
     */
    public SecurityMarker getSecurityMarker() {
        return securityMarker;
    }

    /**
     * Changes the security marker of the item.
     * @param securityMarker new security marker
     */
    public void setSecurityMarker(SecurityMarker securityMarker) {
        this.securityMarker = securityMarker;
    }

    /**
     * Returns the number of patrons requesting this item.
     * @return number of patrons requesting this item
     */
    public String getHoldQueueLength() {
        return holdQueueLength;
    }

    /**
     * Sets the number of patrons requesting this item.
     * @param holdQueueLength new hold queue length
     */
    public void setHoldQueueLength(String holdQueueLength) {
        this.holdQueueLength = holdQueueLength;
    }

    /**
     * Returns the name of the institution or library that owns the item.
     * @return name of the institution or library that owns the item
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the name of the institution or library that owns the item.
     * @param owner new owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns the date that the recall was issued. 18-char, fixed-length 
     * field: YYYYMMDDZZZZHHMMSS.
     * @return date that the recall was issued
     */
    public String getRecallDate() {
        return recallDate;
    }

    /**
     * Sets the date that the recall was issued.
     * @param recallDate new recall date, 18-char, 
     * fixed-length: YYYYMMDDZZZZHHMMSS
     */
    public void setRecallDate(String recallDate) {
        this.recallDate = recallDate;
    }

    /**
     * Returns the date that the hold expires. 18-char, fixed-length 
     * field: YYYYMMDDZZZZHHMMSS.
     * @return the date that the hold expires.
     */
    public String getHoldPickupDate() {
        return holdPickupDate;
    }

    /**
     * Sets the date that the hold expires.
     * @param holdPickupDate new hold pickup date, 18-char, 
     * fixed-length: YYYYMMDDZZZZHHMMSS
     */
    public void setHoldPickupDate(String holdPickupDate) {
        this.holdPickupDate = holdPickupDate;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(this.circulationStatus);
        builder.append(this.securityMarker);
        builder.append(this.feeType);
        builder.append(this.transactionDate);

        if (this.holdQueueLength != null) {
            builder.append("CF");
            builder.append(this.holdQueueLength);
            builder.append("|");
        }
        if (this.dueDate != null) {
            builder.append("AH");
            builder.append(this.dueDate);
            builder.append("|");
        }
        if (this.recallDate != null) {
            builder.append("CJ");
            builder.append(this.recallDate);
            builder.append("|");
        }
        if (this.holdPickupDate != null) {
            builder.append("CM");
            builder.append(this.holdPickupDate);
            builder.append("|");
        }
        builder.append("AB");
        builder.append(itemIdentifier);

        builder.append("|AJ");
        builder.append(titleIdentifier);

        if (this.owner != null) {
            builder.append("|BG");
            builder.append(this.owner);
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
        if (this.permanentLocation != null) {
            builder.append("|AQ");
            builder.append(this.permanentLocation);
        }
        if (this.currentLocation != null) {
            builder.append("|AP");
            builder.append(this.currentLocation);
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
