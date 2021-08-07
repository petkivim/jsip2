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
package com.pkrete.jsip2.variables;

import com.pkrete.jsip2.util.StringUtil;
import com.pkrete.jsip2.messages.requests.SIP2PatronInformationRequest;

/**
 * This class represents a summary that can be requested as a part of
 * the {@link SIP2PatronInformationRequest SIP2PatronInformationRequest}. A
 * true value in any of the variables indicates that detailed as well
 * as summary information about the corresponding category of items
 * can be sent in the response.
 * 
 * Only one category of item should be requested at a time, i.e. it would
 * take 6 of these messages, each with a different variable set to true, 
 * to get all the detailed information about the patron's items.
 * 
 * @author Petteri Kivimäki
 */
public class Summary {

    private boolean holdItems;
    private boolean overdueItems;
    private boolean chargedItems;
    private boolean fineItems;
    private boolean recallItems;
    private boolean unavailableHolds;

    /**
     * Constructs and initializes a new Summary object with all the
     * boolean values set to false.
     */
    public Summary() {
        this.holdItems = false;
        this.overdueItems = false;
        this.chargedItems = false;
        this.fineItems = false;
        this.recallItems = false;
        this.unavailableHolds = false;
    }

    /**
     * Returns true if and only if detailed as well as summary information 
     * about the hold items category can be sent in the response.
     * @return true if detailed and summary information about the hold
     * items can be sent in the response, otherwise false
     */
    public boolean isHoldItems() {
        return holdItems;
    }

    /**
     * Sets the boolean value that indicates if detailed as well as 
     * summary information about the hold items category can be sent 
     * in the response.
     * @param holdItems true or false
     */
    public void setHoldItems(boolean holdItems) {
        this.holdItems = holdItems;
    }

    /**
     * Returns true if and only if detailed as well as summary information 
     * about the overdue items category can be sent in the response.
     * @return true if detailed and summary information about the overdue
     * items can be sent in the response, otherwise false
     */
    public boolean isOverdueItems() {
        return overdueItems;
    }

    /**
     * Sets the boolean value that indicates if detailed as well as summary 
     * information about the overdue items category can be sent in the response.
     * @param overdueItems true or false
     */
    public void setOverdueItems(boolean overdueItems) {
        this.overdueItems = overdueItems;
    }

    /**
     * Returns true if and only if detailed as well as summary information 
     * about the charged items category can be sent in the response.
     * @return true if detailed and summary information about the charged
     * items can be sent in the response, otherwise false
     */
    public boolean isChargedItems() {
        return chargedItems;
    }

    /**
     * Sets the boolean value that indicates if detailed as well as summary 
     * information about the charged items category can be sent in the response.
     * @param chargedItems true or false 
     */
    public void setChargedItems(boolean chargedItems) {
        this.chargedItems = chargedItems;
    }

    /**
     * Returns true if and only if detailed as well as summary information 
     * about the fine items category can be sent in the response.
     * @return true if detailed and summary information about the fine
     * items can be sent in the response, otherwise false
     */
    public boolean isFineItems() {
        return fineItems;
    }

    /**
     * Sets the boolean value that indicates if detailed as well as summary 
     * information about the fine items category can be sent in the response.
     * @param fineItems true or false 
     */
    public void setFineItems(boolean fineItems) {
        this.fineItems = fineItems;
    }

    /**
     * Returns true if and only if detailed as well as summary information 
     * about the recall items category can be sent in the response.
     * @return true if detailed and summary information about the recall
     * items can be sent in the response, otherwise false
     */
    public boolean isRecallItems() {
        return recallItems;
    }

    /**
     * Sets the boolean value that indicates if detailed as well as summary 
     * information about the recall items category can be sent in the response.
     * @param recallItems true or false 
     */
    public void setRecallItems(boolean recallItems) {
        this.recallItems = recallItems;
    }

    /**
     * Returns true if and only if detailed as well as summary information 
     * about the unavailable holds category can be sent in the response.
     * @return true if detailed and summary information about the unavailable
     * holds can be sent in the response, otherwise false
     */
    public boolean isUnavailableHolds() {
        return unavailableHolds;
    }

    /**
     * Sets the boolean value that indicates if detailed as well as summary 
     * information about the unavailable holds category can be sent in the response.
     * @param unavailableHolds true or false 
     */
    public void setUnavailableHolds(boolean unavailableHolds) {
        this.unavailableHolds = unavailableHolds;
    }
    
    @Override
    /**
     * Returns a string presentation of this Summary object that's 10
     * characters long. Each boolean value is converted to a character in
     * the following way: true = 'Y', false = ' '. As the class has only
     * 6 boolean variables, 4 spaces are added in the end of the string
     * to reach the required length.
     * @return string presentation of this Summary object
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtil.bool2CharEmpty(holdItems));
        builder.append(StringUtil.bool2CharEmpty(overdueItems));
        builder.append(StringUtil.bool2CharEmpty(chargedItems));
        builder.append(StringUtil.bool2CharEmpty(fineItems));
        builder.append(StringUtil.bool2CharEmpty(recallItems));
        builder.append(StringUtil.bool2CharEmpty(unavailableHolds));
        builder.append("    ");
        return builder.toString();
    }
}