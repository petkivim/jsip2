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

import com.pkrete.jsip2.messages.requests.SIP2PatronInformationRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;
import com.pkrete.jsip2.variables.ItemType;
import java.util.List;

/**
 * This class represents the response message that the ILS SIP server must 
 * send in response to the 
 * {@link SIP2PatronInformationRequest SIP2PatronInformationRequest}
 * message}.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2PatronInformationResponse extends SIP2PatronResponse {

    /**
     * The limiting value for fines and fees that the patron is allowed
     * to accumulate in his account. It's a money amount in whatever
     * currency type is defined in this message. This value is optional.
     */
    private String feeLimit;
    /**
     * Number of items on hold.
     */
    private int holdItemsCount;
    /**
     * Number of overdue items.
     */
    private int overdueItemsCount;
    /**
     * Number of charged items.
     */
    private int chargedItemsCount;
    /**
     * Number of fine items.
     */
    private int fineItemsCount;
    /**
     * Number of recall items.
     */
    private int recallItemsCount;
    /**
     * Unavailable holds count.
     */
    private int unavailableHoldsCount;
    /**
     * Limit number for hold items for this patron.
     */
    private int holdItemsLimit;
    /**
     * Limit number for overdue items for this patron.
     */
    private int overdueItemsLimit;
    /**
     * Limit number for charged items for this patron.
     */
    private int chargedItemsLimit;
    /**
     * Home address.
     */
    private String homeAddress;
    /**
     * Email address.
     */
    private String email;
    /**
     * Phone number.
     */
    private String phone;
    /**
     * Birth date of the patron. 8-char, fixed length optional field: YYYYMMDD.
     * This is an extension field and it's not included in the original SIP2 
     * definition.
     */
    private String birthDate;
    /**
     * Identifies patron's PAC access level. This is an extension field and 
     * it's not included in the original SIP2 definition.
     */
    private String pacAccessType;
    /**
     * Patron type, such as child, juvenile, adult, etc. This is an extension 
     * field and it's not included in the original SIP2 definition.
     */
    private String patronType;
    /**
     * Patron group code. This is a Voyager ESIP extension.
     */
    private String patronGroup;
    /**
     * Zero or more item barcodes of the item type defined in the summary field
     * of the PatronInformationRequest message.
     */
    private List<String> items;
    /**
     * Type of the items in the items list.
     */
    private ItemType itemType;

    /**
     * Constructs and initializes a new SIP2PatronInformationResponse object
     * containing the given data.
     * @param data the data that the message contains
     */
    public SIP2PatronInformationResponse(String data) {
        super("64", data);
        this.holdItemsLimit = -1;
        this.chargedItemsLimit = -1;
        this.overdueItemsLimit = -1;
    }

    /**
     * Returns the limiting value for fines and fees that the patron is allowed
     * to accumulate in his account. It's a money amount in whatever
     * currency type is defined in this message.
     * @return fee limit
     */
    public String getFeeLimit() {
        return feeLimit;
    }

    /**
     * Changes the limiting value for fines and fees that the patron is allowed
     * to accumulate in his account. It's a money amount in whatever
     * currency type is defined in this message.
     * @param feeLimit new value
     */
    public void setFeeLimit(String feeLimit) {
        this.feeLimit = feeLimit;
    }

    /**
     * Returns the number of hold items for this patron.
     * @return number of hold items for this patron
     */
    public int getHoldItemsCount() {
        return holdItemsCount;
    }

    /**
     * Changes the number of hold items for this patron.
     * @param holdItemsCount new value
     */
    public void setHoldItemsCount(int holdItemsCount) {
        this.holdItemsCount = holdItemsCount;
    }

    /**
     * Returns the number of overdue items for this patron.
     * @return number of overdue items for this patron
     */
    public int getOverdueItemsCount() {
        return overdueItemsCount;
    }

    /**
     * Changes the number of overdue items for this patron.
     * @param overdueItemsCount new value 
     */
    public void setOverdueItemsCount(int overdueItemsCount) {
        this.overdueItemsCount = overdueItemsCount;
    }

    /**
     * Returns the number of charged items for this patron.
     * @return number of charged items for this patron
     */
    public int getChargedItemsCount() {
        return chargedItemsCount;
    }

    /**
     * Changes the number of charged items for this patron.
     * @param chargedItemsCount new value 
     */
    public void setChargedItemsCount(int chargedItemsCount) {
        this.chargedItemsCount = chargedItemsCount;
    }

    /**
     * Returns the number of fine items for this patron.
     * @return number of fine items for this patron
     */
    public int getFineItemsCount() {
        return fineItemsCount;
    }

    /**
     * Changes the number of fine items for this patron.
     * @param fineItemsCount new value 
     */
    public void setFineItemsCount(int fineItemsCount) {
        this.fineItemsCount = fineItemsCount;
    }

    /**
     * Returns the number of recall items for this patron.
     * @return number of recall items for this patron
     */
    public int getRecallItemsCount() {
        return recallItemsCount;
    }

    /**
     * Changes the number of recall items for this patron.
     * @param recallItemsCount new value 
     */
    public void setRecallItemsCount(int recallItemsCount) {
        this.recallItemsCount = recallItemsCount;
    }

    /**
     * Return the number of unavailable holds for this patron.
     * @return number of unavailable holds for this patron
     */
    public int getUnavailableHoldsCount() {
        return unavailableHoldsCount;
    }

    /**
     * Changes the number of unavailable holds for this patron.
     * @param unavailableHoldsCount new value 
     */
    public void setUnavailableHoldsCount(int unavailableHoldsCount) {
        this.unavailableHoldsCount = unavailableHoldsCount;
    }

    /**
     * Returns the limit number for hold items for this patron. If the value
     * is not defined, returns -1. Optional field.
     * @return limit number for hold items for this patron
     */
    public int getHoldItemsLimit() {
        return holdItemsLimit;
    }

    /**
     * Changes the limit number for hold items for this patron.
     * Optional field.
     * @param holdItemsLimit new value 
     */
    public void setHoldItemsLimit(int holdItemsLimit) {
        this.holdItemsLimit = holdItemsLimit;
    }

    /**
     * Returns the limit number for overdue items for this patron. If the value
     * is not defined, returns -1. Optional field.
     * @return limit number for overdue items for this patron
     */
    public int getOverdueItemsLimit() {
        return overdueItemsLimit;
    }

    /**
     * Changes the limit number for overdue items for this patron.
     * Optional field.
     * @param overdueItemsLimit new value 
     */
    public void setOverdueItemsLimit(int overdueItemsLimit) {
        this.overdueItemsLimit = overdueItemsLimit;
    }

    /**
     * Returns the limit number for charged items for this patron. If the value
     * is not defined, returns -1. Optional field.
     * @return limit number for charged items for this patron
     */
    public int getChargedItemsLimit() {
        return chargedItemsLimit;
    }

    /**
     * Changes the limit number for charged items for this patron.
     * Optional field.
     * @param chargedItemsLimit new value
     */
    public void setChargedItemsLimit(int chargedItemsLimit) {
        this.chargedItemsLimit = chargedItemsLimit;
    }

    /**
     * Returns the home address of the patron.
     * @return home address of the patron
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * Changes the home address of the patron.
     * @param homeAddress new value
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Returns the email address of the patron.
     * @return email address of the patron
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the email address of the patron.
     * @param email new value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phnoe number of the patron.
     * @return phone number of the patron
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Changes the phone number of the patron.
     * @param phone new value
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the code of the patron group in which the patron belongs to.
     * This is an extension field and it's not included to the official 
     * SIP2 definition.
     * @return code of the patron group in which the patron belongs to
     */
    public String getPatronGroup() {
        return patronGroup;
    }

    /**
     * Changes the code of the patron group in which the patron belongs to.
     * This is an extension field and it's not included to the official 
     * SIP2 definition. 
     * @param patronGroup new value
     */
    public void setPatronGroup(String patronGroup) {
        this.patronGroup = patronGroup;
    }

    /**
     * Returns a list of item barcodes of the item type defined in the 
     * PatronInformationRequest message. If no item type was defined in the
     * request or the patron doesn't have any items of the defiend type, 
     * returns null.
     * @return list of item barcodes of the defined type or null
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Changes the lis of item barcodes of the item type defined in the 
     * PatronInformationRequest message
     * @param items new value
     */
    public void setItems(List<String> items) {
        this.items = items;
    }

    /**
     * Returns the type of the items in the items list. If no item type 
     * was defined in the PatronInformationRequest or if the list is empty,
     * returns null.
     * @return item type of the items in the items list or null
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * Changes the type of the items in the items list.
     * @param itemType new value
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * Returns the patron's birth date. 6-char, fixed-length field, MMDDYYYY.
     * @return patron's birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the patron's birth date.
     * @param birthDate new birth date, 6-char, fixed-length field, MMDDYYYY
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Returns the patron's PAC access level.
     * @return patron's PAC access level
     */
    public String getPacAccessType() {
        return pacAccessType;
    }

    /**
     * Sets the patron's PAC access level.
     * @param pacAccessType new level
     */
    public void setPacAccessType(String pacAccessType) {
        this.pacAccessType = pacAccessType;
    }

    /**
     * Returns the patron's patron type, such as child, juvenile, adult, etc
     * @return patron type
     */
    public String getPatronType() {
        return patronType;
    }

    /**
     * Sets the patron's patron type, such as child, juvenile, adult, etc
     * @param patronType new patron type
     */
    public void setPatronType(String patronType) {
        this.patronType = patronType;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2CharEmpty(status.isChargePrivilegesDenied()));
        builder.append(StringUtil.bool2CharEmpty(status.isRenewalPrivilegesDenied()));
        builder.append(StringUtil.bool2CharEmpty(status.isRecallPrivilegesDenied()));
        builder.append(StringUtil.bool2CharEmpty(status.isHoldPrivilegesDenied()));
        builder.append(StringUtil.bool2CharEmpty(status.isCardReportedLost()));
        builder.append(StringUtil.bool2CharEmpty(status.isTooManyItemsCharged()));
        builder.append(StringUtil.bool2CharEmpty(status.isTooManyItemsOverdue()));
        builder.append(StringUtil.bool2CharEmpty(status.isTooManyRenewals()));
        builder.append(StringUtil.bool2CharEmpty(status.isTooManyClaimsOfItemsReturned()));
        builder.append(StringUtil.bool2CharEmpty(status.isTooManyItemsLost()));
        builder.append(StringUtil.bool2CharEmpty(status.isExcessiveOutstandingFines()));
        builder.append(StringUtil.bool2CharEmpty(status.isExcessiveOutstandingFees()));
        builder.append(StringUtil.bool2CharEmpty(status.isRecallOverdue()));
        builder.append(StringUtil.bool2CharEmpty(status.isTooManyItemsBilled()));

        builder.append(this.language);
        builder.append(this.transactionDate);

        builder.append(StringUtil.intToFixedLengthString(this.holdItemsCount, 4));
        builder.append(StringUtil.intToFixedLengthString(this.overdueItemsCount, 4));
        builder.append(StringUtil.intToFixedLengthString(this.chargedItemsCount, 4));
        builder.append(StringUtil.intToFixedLengthString(this.fineItemsCount, 4));
        builder.append(StringUtil.intToFixedLengthString(this.recallItemsCount, 4));
        builder.append(StringUtil.intToFixedLengthString(this.unavailableHoldsCount, 4));

        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|");
        builder.append("AA");
        builder.append(this.patronIdentifier);
        builder.append("|");
        builder.append("AE");
        builder.append(this.personalName);
        builder.append("|");

        if (this.holdItemsLimit != -1) {
            builder.append("BZ");
            builder.append(StringUtil.intToFixedLengthString(this.holdItemsLimit, 4));
            builder.append("|");
        }
        if (this.overdueItemsLimit != -1) {
            builder.append("CA");
            builder.append(StringUtil.intToFixedLengthString(this.overdueItemsLimit, 4));
            builder.append("|");
        }
        if (this.chargedItemsLimit != -1) {
            builder.append("CB");
            builder.append(StringUtil.intToFixedLengthString(this.chargedItemsLimit, 4));
            builder.append("|");
        }

        if (validPatronUsed) {
            builder.append("BL");
            builder.append(StringUtil.bool2Char(validPatron));
            builder.append("|");
        }
        if (validPatronPasswordUsed) {
            builder.append("CQ");
            builder.append(StringUtil.bool2Char(validPatronPassword));
            builder.append("|");
        }

        if (currencyType != null) {
            builder.append("BH");
            builder.append(currencyType);
            builder.append("|");
        }

        if (feeAmount != null) {
            builder.append("BV");
            builder.append(feeAmount);
            builder.append("|");
        }
        if (feeLimit != null) {
            builder.append("CC");
            builder.append(feeLimit);
            builder.append("|");
        }

        if (this.items != null) {
            for (String barcode : items) {
                builder.append(itemType);
                builder.append(barcode);
                builder.append("|");
            }
        }

        if (this.homeAddress != null) {
            builder.append("BD");
            builder.append(this.homeAddress);
            builder.append("|");
        }
        if (this.email != null) {
            builder.append("BE");
            builder.append(this.email);
            builder.append("|");
        }
        if (this.phone != null) {
            builder.append("BF");
            builder.append(this.phone);
            builder.append("|");
        }
        if (this.birthDate != null) {
            builder.append("PB");
            builder.append(this.birthDate);
            builder.append("|");
        }
        if (this.pacAccessType != null) {
            builder.append("PA");
            builder.append(this.pacAccessType);
            builder.append("|");
        }
        if (this.patronType != null) {
            builder.append("ZY");
            builder.append(this.patronType);
            builder.append("|");
        }
        if (this.patronGroup != null) {
            builder.append("PT");
            builder.append(this.patronGroup);
            builder.append("|");
        }

        for (String msg : screenMessage) {
            builder.append("|AF");
            builder.append(msg);
        }

        for (String msg : printLine) {
            builder.append("|AG");
            builder.append(msg);
        }

        if (isSequence()) {
            builder.append("AY");
            builder.append(sequence);
        }
        builder.append("AZ");
        return MessageUtil.computeChecksum(builder.toString());
    }
}