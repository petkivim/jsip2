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
package com.pkrete.jsip2.messages.requests;

import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;

/**
 * This abstract class is a base class of the 
 * {@link SIP2CheckoutRequest SIP2CheckoutRequest} and the
 * {@link SIP2CheckinRequest SIP2CheckinRequest} classes.
 *
 * @author Petteri Kivimäki
 */
public abstract class SIP2CirculationTransactionRequest extends SIP2MessageRequest {

    /**
     * Notifies the ILS SIP server that the item was already checked in or out
     * while while the SIP server wasn't on-line. When true the SIP server
     * should not block the transaction, because it has already been executed.
     * The system can perform transactions while the SIP server is off-line.
     * These transactions are stored and will be sent to the SIP server when
     * it comes back on-line.
     */
    protected boolean noBlock;
    /**
     * Should be set to true for a checkout command being used to cancel a 
     * failed checkin command, or for a checkin command being used to cancel
     * a failed chakout command. Should be set to false for all other checkout
     * or checkin commands. Optional field.
     */
    protected boolean cancel;
    /**
     * Tells if cancel field show be added to the request, as the
     * field is optional.
     */
    protected boolean useCancel;
    /**
     * No block due date items were given during off-line operation.
     * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS.
     */
    protected String nbDueDate;

    /**
     * Constructs and initializes a new SIP2CirculationTransactionRequestt 
     * with the given command code.
     * @param code command code
     */
    protected SIP2CirculationTransactionRequest(String code) {
        super(code);
        this.cancel = false;
        this.useCancel = false;
        this.nbDueDate = MessageUtil.getSipDateTime();
    }

    /**
     * Returns true if and only if the transaction happened when the ILS
     * SIP server was off-line. When true the SIP server should not block the 
     * transaction, because it has already been executed.
     * @return true if the transaction happened when the ILSnSIP server was 
     * off-line, otherwise false
     */
    public boolean isNoBlock() {
        return noBlock;
    }

    /**
     * Changes the boolean value that tells if the transaction happened when 
     * the ILS SIP server was off-line. When true the SIP server should not 
     * block the transaction, because it has already been executed.
     * @param noBlock new value
     */
    public void setNoBlock(boolean noBlock) {
        this.noBlock = noBlock;
    }

    /**
     * Returns true if and only if the checkout command is used to cancel
     * a failed checkin command. Otherwise returns false.
     * @return true if the checkout command is used to cancel
     * a failed checkin command, otherwise false
     */
    public boolean isCancel() {
        return cancel;
    }

    /**
     * Sets the value that tells if the checkout command is used to cancel
     * a failed checkin command.
     * @param cancel new value
     */
    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns true if and only if the cancel field should
     * be added to the request. Otherwise returns false.
     * @return true if the cancel field should
     * be added to the request, otherwise false
     */
    public boolean isUseCancel() {
        return useCancel;
    }

    /**
     * Sets the value that tells if the cancel field should
     * be added to the request.
     * @param useCancel new value
     */
    public void setUseCancel(boolean useCancel) {
        this.useCancel = useCancel;
    }

    /**
     * Returns the no block due date items were given during off-line operation.
     * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS
     * @return off-line due date
     */
    public String getNbDueDate() {
        return nbDueDate;
    }

    /**
     * Sets the no block due date items were given during off-line operation.
     * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @param nbDueDate new due date (18-char, 
     * fixed-length field: YYYYMMDDZZZZHHMMSS)
     */
    public void setNbDueDate(String nbDueDate) {
        this.nbDueDate = nbDueDate;
    }
}