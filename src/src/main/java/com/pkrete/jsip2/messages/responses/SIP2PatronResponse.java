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

import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.variables.Language;
import com.pkrete.jsip2.variables.PatronStatus;

/**
 * This abstract class is a base class of the 
 * {@link SIP2PatronStatusResponse SIP2PatronStatusResponse} and the
 * {@link SIP2PatronInformationResponse SIP2PatronInformationResponse}
 * classes.
 *
 * @author Petteri Kivimäki
 */
public abstract class SIP2PatronResponse extends SIP2MessageResponse {

    /**
     * Patron's status.
     */
    protected PatronStatus status;
    /**
     * Language.
     */
    protected Language language;
    /**
     * Patron's name.
     */
    protected String personalName;
    /**
     * Indicates if the patron barcode is valid. Optional field.
     */
    protected boolean validPatron;
    /**
     * Indicates if the validPatron value was used in the response
     * received from the ILS SIP server.
     */
    protected boolean validPatronUsed;
    /**
     * Indicates if the patron password is valid. Optional field.
     */
    protected boolean validPatronPassword;
    /**
     * Indicates if the validPatronPassword value was used in the response
     * received from the ILS SIP server.
     */
    protected boolean validPatronPasswordUsed;

    /**
     * Constructs and initializes a new SIP2PatronStatusResponse object
     * containing the given data.
     * @param code command identifier
     * @param data the data that the message contains
     */
    protected SIP2PatronResponse(String code, String data) {
        super(code, data);
        this.validPatronUsed = false;
        this.validPatronPasswordUsed = false;
    }

    /**
     * Returns a PatronStatus object that contains all the information
     * about the patron's status.
     * @return PatronStatus containing all the information about the patron's
     * status
     */
    public PatronStatus getStatus() {
        return status;
    }

    /**
     * Sets the patron status value that contains all the information
     * about the patron's status.
     * @param status new value
     */
    public void setStatus(PatronStatus status) {
        this.status = status;
    }

    /**
     * Returns the language
     * @return language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     * @param language new value
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Returns the patron name.
     * @return name of the patron
     */
    public String getPersonalName() {
        return personalName;
    }

    /**
     * Sets the patron name.
     * @param personalName new value
     */
    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    /**
     * Return true if and only if the patron barcode is valid, otherwise
     * returns false.
     * @return true if patron barcode is valid, otherwise false
     */
    public boolean isValidPatron() {
        return validPatron;
    }

    /**
     * Sets the boolean value that tells if the patron barcode is valid. 
     * @param validPatron true or false
     */
    public void setValidPatron(boolean validPatron) {
        this.validPatron = validPatron;
    }

    /**
     * Return true if and only if the patron password is valid, otherwise
     * returns false.
     * @return true if patron password is valid, otherwise false
     */
    public boolean isValidPatronPassword() {
        return validPatronPassword;
    }

    /**
     * Sets the boolean value that tells if the patron password is valid. 
     * @param validPatronPassword true or false
     */
    public void setValidPatronPassword(boolean validPatronPassword) {
        this.validPatronPassword = validPatronPassword;
    }

    /**
     * Returns true if and only if the valid patron field was used
     * in the response message received from the ILS SIP server.
     * @return true if the valid patron field was used
     * in the response message, otherwise false
     */
    public boolean isValidPatronUsed() {
        return validPatronUsed;
    }

    /**
     * Sets the boolean value that tells if the valid patron field 
     * was used in the response message received from the ILS SIP server.
     * @param validPatronUsed true or false
     */
    public void setValidPatronUsed(boolean validPatronUsed) {
        this.validPatronUsed = validPatronUsed;
    }

    /**
     * Returns true if and only if the valid patron password field was used
     * in the response message received from the ILS SIP server.
     * @return true if the valid patron password field was used
     * in the response message, otherwise false
     */
    public boolean isValidPatronPasswordUsed() {
        return validPatronPasswordUsed;
    }

    /**
     * Sets the boolean value that tells if the valid patron password field 
     * was used in the response message received from the ILS SIP server.
     * @param validPatronPasswordUsed true or false
     */
    public void setValidPatronPasswordUsed(boolean validPatronPasswordUsed) {
        this.validPatronPasswordUsed = validPatronPasswordUsed;
    }
}
