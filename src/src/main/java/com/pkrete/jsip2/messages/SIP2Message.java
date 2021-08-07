/*
 *  The MIT License
 * 
 *  Copyright 2012- Petteri Kivimäki
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.pkrete.jsip2.messages;

import com.pkrete.jsip2.variables.CurrencyType;
import com.pkrete.jsip2.variables.FeeType;
import java.io.Serializable;

/**
 * This abstract class is a base class for all the SIP2 request and
 * response messages.
 * 
 * @author Petteri
 */
public abstract class SIP2Message implements Serializable {

    /**
     * Sequence number of the message (0-9)
     */
    protected int sequence;
    /**
     * Checksum is four ASCII charater digits representing the binary
     * sum of the characters included the message
     */
    protected String checkSum;
    /**
     * Command identifier
     */
    protected String code;
    /**
     * Library's institution id.
     */
    protected String institutionId;
    /**
     * Date and time of the request. 18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS.
     */
    protected String transactionDate;
    /**
     * An identifying value for the patron, library card's barcode
     * number for example.
     */
    protected String patronIdentifier;
    /**
     * Identifying value for the item.
     */
    protected String itemIdentifier;
    /**
     * Specific item information that can be used for identifying an item,
     * such as item weight, size, security marker, etc.
     */
    protected String itemProperties;
    /**
     * Identifying value for the title.
     */
    protected String titleIdentifier;
    /**
     * A transaction id that is assigned by the ILS or by a payment device,
     * for auditing purposes to track cash flow.
     */
    protected String transactionId;
    /**
     * The currency in which the fee amount is given.
     */
    protected CurrencyType currencyType;
    /**
     * The amount of fees. The amount is given in currency 
     * specified by the currency type field.
     */
    protected String feeAmount;
    /**
     * The type of the fee.
     */
    protected FeeType feeType;
    /**
     * The current location of the item.
     */
    protected String currentLocation;
    /**
     * The expiration date of the hold.
     */
    protected String expirationDate;
    /**
     * The pickup location of the hold.
     */
    protected String pickupLocation;
    /**
     * Bibliographic id of the record. This is a Voyager ESIP extension.
     */
    protected String bibId;

    /**
     * Returns a string presentation of the message.
     * @return string presentation of the message
     */
    public abstract String getData();

    /**
     * Constructs and initializes a new SIP2Message object.
     * @param code command identifier
     */
    protected SIP2Message(String code) {
        this.code = code;
        this.sequence = -1;
    }

    /**
     * Returns the command identifier of this message.
     * @return code command identifier of this message
     */
    public String getCode() {
        return code;
    }

    /**
     * Changes the command identifier of this message
     * @param code new command identifier
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the sequence nnumber of the message that is between
     * 0 and 9.
     * @return sequence number of the message
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Changes the sequence number of the message. Aallowed values
     * are between 0 and 9.
     * @param sequence new value
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * Returns true, if and only if, the sequence variable is present
     * in the message.
     * @return true if sequence variable is used, otherwise false
     */
    public boolean isSequence() {
        if (sequence != -1) {
            return true;
        }
        return false;
    }

    /**
     * Returns true, if and only if, the checksum variable is present
     * in the message.
     * @return true if checksum variable is used, otherwise false
     */
    public boolean isChecksum() {
        return !checkSum.isEmpty();
    }

    /**
     * Returns the checksum representing the binary sum of the characters 
     * included the message. The checksum is formed with four ASCII 
     * charater digits.
     * @return the checkSum
     */
    public String getCheckSum() {
        return checkSum;
    }

    /**
     * Changes the checksum representing the binary sum of the characters 
     * included the message. The checksum is formed with four ASCII 
     * charater digits.
     * @param checkSum new value
     */
    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    /**
     * Returns the library's id.
     * @return library's id
     */
    public String getInstitutionId() {
        return institutionId;
    }

    /**
     * Sets the library's id.
     * @param institutionId new value
     */
    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * Return the transaction date in the SIP2 format:
     * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @return message date and time in SIP2 format
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date and time. 
     * @param transactionDate new value: 18-char, 
     * fixed-length: YYYYMMDDZZZZHHMMSS
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Returns the patron identifier, library card's barcode number
     * for example.
     * @return  patron identifier
     */
    public String getPatronIdentifier() {
        return patronIdentifier;
    }

    /**
     * Sets the patron idetifier.
     * @param patronIdentifier new value
     */
    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    /**
     * Returns the item identifier.
     * @return item identifier
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Sets the item idetifier.
     * @param itemIdentifier new value
     */
    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    /**
     * Returns a specific item information that can be used for identifying 
     * an item, such as item weight, size, security marker, etc. Optional field.
     * @return specific item information
     */
    public String getItemProperties() {
        return itemProperties;
    }

    /**
     * Changes the specific item information that can be used for identifying 
     * an item, such as item weight, size, security marker, etc. Optional field.
     * @param itemProperties new value
     */
    public void setItemProperties(String itemProperties) {
        this.itemProperties = itemProperties;
    }

    /**
     * Returns an identifying value for the title.
     * @return identifying value for the title
     */
    public String getTitleIdentifier() {
        return titleIdentifier;
    }

    /**
     * Sets the identifying value for the title.
     * @param titleIdentifier new identifying value for the title
     */
    public void setTitleIdentifier(String titleIdentifier) {
        this.titleIdentifier = titleIdentifier;
    }

    /**
     * Returns the transaction id that is assigned by the ILS or by a payment 
     * device, for auditing purposes to track cash flow.
     * @return transaction id that is assigned by the ILS or by a payment 
     * device, for auditing purposes to track cash flow
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction id that is assigned by the ILS or by a payment 
     * device, for auditing purposes to track cash flow.
     * @param transactionId new value
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Returns the currency type of fees.
     * @return currency type of fees
     */
    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    /**
     * Sets the currency type of fees.
     * @param currencyType currency type
     */
    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * Returns the amount of fees. The amount is given in 
     * currency specified by the currency type field.
     * @return fee amount
     */
    public String getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets the amount of fees. The new value must be given 
     * in the currency specified by the currency type.
     * @param feeAmount new value
     */
    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * Returns the fee type. 
     * @return fee type
     */
    public FeeType getFeeType() {
        return feeType;
    }

    /**
     * Sets the fee type.
     * @param feeType new fee type
     */
    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    /**
     * Returns the current location of the item.
     * @return current location of the item
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the current location of the item.
     * @param currentLocation new value
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Returns the expiration date of the hold. 18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @return expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the hold. 18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @param expirationDate new expiration date
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the pickup location of the hold.
     * @return pickup location of the hold
     */
    public String getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Sets the pickup location of the hold.
     * @param pickupLocation new pickup location
     */
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * Returns the bibliographic id of a record. This is a 
     * Voyager ESIP extension and it's not included in the original SIP2 
     * definition.
     * @return bibliographic id of the record that will be put on hold
     */
    public String getBibId() {
        return bibId;
    }

    /**
     * Sets the bibliographic id of a record. This is a 
     * Voyager ESIP extension and it's not included in the original SIP2 
     * definition.
     * @param bibId bib id
     */
    public void setBibId(String bibId) {
        this.bibId = bibId;
    }
}
