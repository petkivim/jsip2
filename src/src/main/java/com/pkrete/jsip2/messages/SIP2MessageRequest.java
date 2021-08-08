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

import com.pkrete.jsip2.util.MessageUtil;

/**
 * This abstract class is a base class of the SIP2 request messages
 * sent to the ILS SIP server.
 * 
 * @author Petteri Kivimäki
 */
public abstract class SIP2MessageRequest extends SIP2Message {

    /**
     * Password for the system to login to the ILS. If this feature is not 
     * used by the ILS in the library then the value should be empty if it's 
     * required in the request, and can be omitted entirely if the field is 
     * optional in the message.
     */
    protected String terminalPassword;
    /**
     * Password (PIN) of the patron. If this feature is not used by the
     * ILS in the library then the value should be empty if it's required
     * in the request, and can be omitted entirely if the field is optional
     * in the message.
     */
    protected String patronPassword;
    /**
     * If false and there's a fee associated with the transaction,
     * the ILS SIP server should tell the system in the response that
     * there's a fee, and refuse to complete the transaction. If the system and the
     * patron then interact and the patron agrees to pay the fee, this field
     * will be set to true on a second request message, indicatin to the ILS
     * SIP server that the patron has acknowledged the fee and the transaction 
     * should not be refused just because there is a fee associated with it.
     */
    protected boolean feeAcknowledged;
    /**
     * Tells if fee acknowledged field show be added to the request.
     */
    protected boolean useFeeAcknowledged;
    /**
     * When error detection is enabled, a sequence number field, followed
     * by a checksum number field, is appended to every message. 
     */
    protected boolean errorDetectionEnabled;

    /**
     * Constructs and initializes a new SIP2MessageRequest object with
     * the given command identifier and sets the transacation date. 
     * By default the error detection is not enabled.
     * @param code command identifier
     */
    protected SIP2MessageRequest(String code) {
        super(code);
        this.feeAcknowledged = false;
        this.useFeeAcknowledged = false;
        this.transactionDate = MessageUtil.getSipDateTime();
    }

    /**
     * Returns true if and only if the error detection is enabled. When error 
     * detection is enabled, a sequence number field, followed by a checksum 
     * number field, is appended to every message. 
     * @return true if error detection is enabled, otherwise false
     */
    public boolean isErrorDetectionEnabled() {
        return this.errorDetectionEnabled;
    }

    /**
     * Sets the value that defines if the error detection is enabled.
     * @param errorDetection true or false
     */
    public void setErrorDetectionEnabled(boolean errorDetection) {
        this.errorDetectionEnabled = errorDetection;
    }

    /**
     * Returns the password for the system to login to the ILS. 
     * @return password for the system to login to the ILS
     */
    public String getTerminalPassword() {
        return terminalPassword;
    }

    /**
     * Sets the password for the system to login to the ILS.
     * @param terminalPassword new value
     */
    public void setTerminalPassword(String terminalPassword) {
        this.terminalPassword = terminalPassword;
    }

    /**
     * Returns the password (PIN) of the patron.
     * @return password (PIN) of the patron
     */
    public String getPatronPassword() {
        return patronPassword;
    }

    /**
     * Sets the password (PIN) of the patron.
     * @param patronPassword new value
     */
    public void setPatronPassword(String patronPassword) {
        this.patronPassword = patronPassword;
    }

    /**
     * Returns true if and only if there's a fee associated with 
     * the transaction and the patron has agreed to pay the fee. Otherwise
     * returns false. Optional field.
     * @return true if there's a fee associated with the transaction and 
     * the patron has agreed to pay the fee, otherwise false
     */
    public boolean isFeeAcknowledged() {
        return feeAcknowledged;
    }

    /**
     * Changes the boolean value that tells if the patron has agreed to pay
     * the fee associated with the transaction.
     * @param feeAcknowledged new value
     */
    public void setFeeAcknowledged(boolean feeAcknowledged) {
        this.feeAcknowledged = feeAcknowledged;
    }

    /**
     * Returns true if and only if the fee acknowledged should
     * be added to the request. Otherwise returns false.
     * @return true if the fee acknowledged field should
     * be added to the request, otherwise false
     */
    public boolean isUseFeeAcknowledged() {
        return useFeeAcknowledged;
    }

    /**
     * Sets the value that tells if the fee acknowledged field should
     * be added to the request.
     * @param useFeeAcknowledged new value
     */
    public void setUseFeeAcknowledged(boolean useFeeAcknowledged) {
        this.useFeeAcknowledged = useFeeAcknowledged;
    }

    /**
     * Returns the checksum representing the binary sum of the characters 
     * included the message. The checksum is formed with four ASCII 
     * charater digits.
     * @return the checkSum
     */
    @Override
    public String getCheckSum() {
        getData();
        return checkSum;
    }
}
