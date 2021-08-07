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

package com.pkrete.jsip2.variables;

/**
 * This class represents the supported messages information that
 * is received as a part of the SIPSCStatusResponse response message. 
 * This class holds the information about the messages that are supported
 * by the ILS SIP server.
 * 
 * @author Petteri Kivimäki
 */
public class SupportedMessages {

    /**
     * Is patron status request/response message pair supported.
     */
    private boolean patronStatusRequest;
    /**
     * Is checkout request/response message pair supported.
     */
    private boolean checkout;
    /**
     * Is checkin request/response message pair supported.
     */
    private boolean checkin;
    /**
     * Is block patron request/response message pair supported.
     */
    private boolean blockPatron;
    /**
     * Is SC/ILS status request/response message pair supported.
     */
    private boolean SCILSStatus;
    /**
     * Is request SC/ILS resend request/response message pair supported.
     */
    private boolean requestSCILSResend;
    /**
     * Is login request/response message pair supported.
     */
    private boolean login;
    /**
     * Is patron information request/response message pair supported.
     */
    private boolean patronInformation;
    /**
     * Is end patron session request/response message pair supported.
     */
    private boolean endPatronSession;
    /**
     * Is fee paid request/response message pair supported.
     */
    private boolean feePaid;
    /**
     * Is item information request/response message pair supported.
     */
    private boolean itemInformation;
    /**
     * Is item status update request/response message pair supported.
     */
    private boolean itemStatusUpdate;
    /**
     * Is  request/response message pair supported.
     */
    private boolean patronEnable;
    /**
     * Is hold request/response message pair supported.
     */
    private boolean hold;
    /**
     * Is renew request/response message pair supported.
     */
    private boolean renew;
    /**
     * Is renew all request/response message pair supported.
     */
    private boolean renewAll;

    /**
     * Is patron status request message supported.
     * @return true or false
     */
    public boolean isPatronStatusRequest() {
        return patronStatusRequest;
    }

    /**
     * Changes the value that indicates if patron status request message 
     * is supported.
     * @param patronStatusRequest the patronStatusRequest to set
     */
    public void setPatronStatusRequest(boolean patronStatusRequest) {
        this.patronStatusRequest = patronStatusRequest;
    }

    /**
     * Is checkout request/response message pair supported.
     * @return true or false
     */
    public boolean isCheckout() {
        return checkout;
    }

    /**
     * Changes the value that indicates if checkout request/response message
     * pair is supported.
     * @param checkout the checkout to set
     */
    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    /**
     * Is checkin request/response message pair supported.
     * @return true or false
     */
    public boolean isCheckin() {
        return checkin;
    }

    /**
     * Changes the value that indicates if checkin request/response message
     * pair is supported.
     * @param checkin the checkin to set
     */
    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }

    /**
     * Is block patron request/response message pair supported.
     * @return true or false
     */
    public boolean isBlockPatron() {
        return blockPatron;
    }

    /**
     * Changes the value that indicates if block patron request/response message
     * pair is supported.
     * @param blockPatron the blockPatron to set
     */
    public void setBlockPatron(boolean blockPatron) {
        this.blockPatron = blockPatron;
    }

    /**
     * Is SC/ILS status request/response message pair supported.
     * @return true or false
     */
    public boolean isSCILSStatus() {
        return SCILSStatus;
    }

    /**
     * Changes the value that indicates if SC/ILS status request/response
     * message pair is supported.
     * @param SCILSStatus the SCILSStatus to set
     */
    public void setSCILSStatus(boolean SCILSStatus) {
        this.SCILSStatus = SCILSStatus;
    }

    /**
     * Is request SC/ILS resend request/response message
     * pair is supported.
     * @return true or false
     */
    public boolean isRequestSCILSResend() {
        return requestSCILSResend;
    }

    /**
     * Changes the value that indicates if request SC/ILS resend 
     * request/response message pair is supported.
     * @param requestSCILSResend the requestSCILSResend to set
     */
    public void setRequestSCILSResend(boolean requestSCILSResend) {
        this.requestSCILSResend = requestSCILSResend;
    }

    /**
     * Is login request/response message pair supported.
     * @return true or false
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * Changes the value that indicates if login request/response message
     * pair is supported.
     * @param login the login to set
     */
    public void setLogin(boolean login) {
        this.login = login;
    }

    /**
     * Is patron information request/response message pair supported.
     * @return true or false
     */
    public boolean isPatronInformation() {
        return patronInformation;
    }

    /**
     * Changes the value that indicates if patron information request/response
     * pair is supported.
     * @param patronInformation the patronInformation to set
     */
    public void setPatronInformation(boolean patronInformation) {
        this.patronInformation = patronInformation;
    }

    /**
     * Is end patron session request/response message pair supported.
     * @return true or false
     */
    public boolean isEndPatronSession() {
        return endPatronSession;
    }

    /**
     * Changes the value that indicates if end patron session request/response
     * pair is supported.
     * @param endPatronSession the endPatronSession to set
     */
    public void setEndPatronSession(boolean endPatronSession) {
        this.endPatronSession = endPatronSession;
    }

    /**
     * Is fee paid request/response message pair supported.
     * @return true or false
     */
    public boolean isFeePaid() {
        return feePaid;
    }

    /**
     * Changes the value that indicates if fee paid request/response message
     * pair is supported.
     * @param feePaid the feePaid to set
     */
    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }

    /**
     * Is item information request/response message pair supported.
     * @return true or false
     */
    public boolean isItemInformation() {
        return itemInformation;
    }

    /**
     * Changes the value that indicates if item information request/response
     * pair is supported.
     * @param itemInformation the itemInformation to set
     */
    public void setItemInformation(boolean itemInformation) {
        this.itemInformation = itemInformation;
    }

    /**
     * Is item status update request/response message pair supported.
     * @return true or false
     */
    public boolean isItemStatusUpdate() {
        return itemStatusUpdate;
    }

    /**
     * Changes the value that indicates if item status update request/response
     * pair is supported.
     * @param itemStatusUpdate the itemStatusUpdate to set
     */
    public void setItemStatusUpdate(boolean itemStatusUpdate) {
        this.itemStatusUpdate = itemStatusUpdate;
    }

    /**
     * Is  request/response message pair supported.
     * @return true or false
     */
    public boolean isPatronEnable() {
        return patronEnable;
    }

    /**
     * Changes the value that indicates if request/response message
     * pair is supported.
     * @param patronEnable the patronEnable to set
     */
    public void setPatronEnable(boolean patronEnable) {
        this.patronEnable = patronEnable;
    }

    /**
     * Is hold request/response message pair supported.
     * @return true or false
     */
    public boolean isHold() {
        return hold;
    }

    /**
     * Changes the value that indicates if hold request/response message
     * pair is supported.
     * @param hold the hold to set
     */
    public void setHold(boolean hold) {
        this.hold = hold;
    }

    /**
     * Is renew request/response message pair supported.
     * @return true or false
     */
    public boolean isRenew() {
        return renew;
    }

    /**
     * Changes the value that indicates if renew request/response message
     * pair is supported.
     * @param renew the renew to set
     */
    public void setRenew(boolean renew) {
        this.renew = renew;
    }

    /**
     * Is renew all request/response message pair supported.
     * @return true or false
     */
    public boolean isRenewAll() {
        return renewAll;
    }

    /**
     * Changes the value that indicates if renew all request/response message 
     * pair is supported.
     * @param renewAll the renewAll to set
     */
    public void setRenewAll(boolean renewAll) {
        this.renewAll = renewAll;
    }
}
