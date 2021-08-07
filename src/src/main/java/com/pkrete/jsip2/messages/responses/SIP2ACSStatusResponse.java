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
package com.pkrete.jsip2.messages.responses;

import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.util.StringUtil;
import com.pkrete.jsip2.variables.SupportedMessages;

/**
 * This class represents the response message that the ILS SIP server must 
 * send in response to the {@link SIP2SCStatusRequest SIP2SCStatusRequest}
 * message. This messages contains some of the rules to be followed in further
 * communication and establishes some parameters needed as well.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ACSStatusResponse extends SIP2MessageResponse {

    /**
     * Is the ILS SIP server online.
     */
    private boolean onLineStatus;
    /**
     * Is the system allowed to checkin items.
     */
    private boolean checkinOk;
    /**
     * Is the system allowe to checkout items.
     */
    private boolean checkoutOk;
    /**
     * Is the system allowed to process patron renewal requests.
     */
    private boolean ILSRenewalPolicy;
    /**
     * Is the system allowed to update patron status, e.g., a patron's
     * card status can be changed to blocked
     */
    private boolean statusUpdateOk;
    /**
     * Does the ILS SIP server support off-line operation feature.
     */
    private boolean offlineOk;
    /**
     * This timeout period until a transaction is
     * aborted should be a number expressed in tenths of a second. 000 indicates
     * that the ILS is not online. 999 indicates that the time-out is unknown.
     */
    private int timeoutPeriod;
    /**
     * The number of retries that are allowed for a specific transaction. 
     * 999 indicates that the retry number is unknown.
     */
    private int retriesAllowed;
    /**
     * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS. May be used to 
     * synchronize clocks.
     */
    private String dateTimeSync;
    /**
     * 4-char, fixed-length field: x.xx. The protocol version that the
     * ILS SIP server is currently using.
     */
    private String protocolVersion;
    /**
     * Variable-length field. The library's name.
     */
    private String libraryName;
    /**
     * This variable holds the information about which messages the
     * ILS supports.
     */
    private SupportedMessages supportedMessages;
    /**
     * Variable-length field. The ILS could put the system's location in this
     * field.
     */
    private String terminalLocation;

    /**
     * Constructs and initializes a new  SIP2ACSStatusResponse object containing
     * the given data.
     * @param data the data that the message contains
     */
    public SIP2ACSStatusResponse(String data) {
        super("98", data);
    }

    /**
     * Returns true if, and only if, the ILS SIP server is online.
     * @return true if the ILS SIP server is online, otherwise false
     */
    public boolean isOnLineStatus() {
        return onLineStatus;
    }

    /**
     * Sets the online status value of the ILS SIP server.
     * @param onLineStatus true or false
     */
    public void setOnLineStatus(boolean onLineStatus) {
        this.onLineStatus = onLineStatus;
    }

    /**
     * Returns true if, and only if, the system is allowed to checkin
     * items.
     * @return true the system is allowed to checkin item, otherwise false
     */
    public boolean isCheckinOk() {
        return checkinOk;
    }

    /**
     * Sets the checkinOk value, that tells if the system is allowed to
     * checkin items.
     * @param checkinOk true or false
     */
    public void setCheckinOk(boolean checkinOk) {
        this.checkinOk = checkinOk;
    }

    /**
     * Returns true if, and only if, the system is allowed to checkout
     * items.
     * @return true if the system is allowed to checkout, otherwise false
     */
    public boolean isCheckoutOk() {
        return checkoutOk;
    }

    /**
     * Sets the checkoutOk value, that tells if the system is allowed to
     * checkout items. 
     * @param checkoutOk true or false
     */
    public void setCheckoutOk(boolean checkoutOk) {
        this.checkoutOk = checkoutOk;
    }

    /**
     * Returns true if, and only if, the system is allowed to process
     * patrons renewal requests.
     * @return true if the system is allowed to process patrons 
     * renewal requests, otherwise false
     */
    public boolean isILSRenewalPolicy() {
        return ILSRenewalPolicy;
    }

    /**
     * Sets the ILSRenewalPolicy value, that tells if the system is 
     * allowed to process patrons renewal requests.
     * @param ILSRenewalPolicy true or false
     */
    public void setILSRenewalPolicy(boolean ILSRenewalPolicy) {
        this.ILSRenewalPolicy = ILSRenewalPolicy;
    }

    /**
     * Returns true if, and only if, the system is allowed to update
     * patron status.
     * @return true the system is allowed to update patron status,
     * otherwise false
     */
    public boolean isStatusUpdateOk() {
        return statusUpdateOk;
    }

    /**
     * Sets the statusUpdateOk value, that tells if the system is allowed
     * to update patron status.
     * @param statusUpdateOk true or false
     */
    public void setStatusUpdateOk(boolean statusUpdateOk) {
        this.statusUpdateOk = statusUpdateOk;
    }

    /**
     * Returns true if, and only if, the ILS SIP server supports off-line
     * operation feature.
     * @return true if the ILS SIP server supports off-line
     * operation feature, otherwise false
     */
    public boolean isOfflineOk() {
        return offlineOk;
    }

    /**
     * Sets the offlineOk value, that tells if the ILS SIP server supports 
     * off-line operation feature.
     * @param offlineOk true or false
     */
    public void setOfflineOk(boolean offlineOk) {
        this.offlineOk = offlineOk;
    }

    /**
     * Returns a timeout period until a transaction is aborted. The period
     * is a number expressed in tenths of a second. 000 indicates that the 
     * ILS is  not online. 999 indicates that the time-out is unknown.
     * @return timeout period expressed in tenths of a second
     */
    public int getTimeoutPeriod() {
        return timeoutPeriod;
    }

    /**
     * Changes the timeout period until a transaction is aborted. 
     * The timeout period is a number expressed in tenths of a second. 
     * 000 indicates that the ILS is not online. 999 indicates that the 
     * time-out is unknown.
     * @param timeoutPeriod new value expressed in tenths of a second
     */
    public void setTimeoutPeriod(int timeoutPeriod) {
        this.timeoutPeriod = timeoutPeriod;
    }

    /**
     * Returns the number of retries that are allowed for a specific 
     * transaction.
     * @return number of retries that are allowed for a specific 
     * transaction
     */
    public int getRetriesAllowed() {
        return retriesAllowed;
    }

    /**
     * Sets the retriesAllowed value, that tells the number of retries 
     * that are allowed for a specific transaction. 999 indicates that 
     * the retry number is unknown.
     * @param retriesAllowed number of retries
     */
    public void setRetriesAllowed(int retriesAllowed) {
        this.retriesAllowed = retriesAllowed;
    }

    /**
     * Returns the date and time received from the ILS SIP server. 
     * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @return ILS SIP server date and time, 18-char: YYYYMMDDZZZZHHMMSS
     */
    public String getDateTimeSync() {
        return dateTimeSync;
    }

    /**
     * Sets the dateTimeSync value, that tells the date and time of
     * the ILS SIP server. 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS.
     * @param dateTimeSync date and time, 18-char: YYYYMMDDZZZZHHMMSS
     */
    public void setDateTimeSync(String dateTimeSync) {
        this.dateTimeSync = dateTimeSync;
    }

    /**
     * Returns the protocol version that the ILS SIP server is currently
     * using. 4-char, fixed-length field: x.xx. 
     * @return protocol version that the ILS SIP server is currently
     * using. 4-char: x.xx.
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Sets the protocolVersion value, that tells the protocol version
     * that the ILS SIP version is currently using. 4-char, 
     * fixed-length field: x.xx. 
     * @param protocolVersion the protocol version that the ILS SIP server 
     * is currently using. 4-char: x.xx
     */
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * Returns the library's name.
     * @return library's name
     */
    public String getLibraryName() {
        return libraryName;
    }

    /**
     * Sets the libraryName value, that tells the library's name.
     * @param libraryName library's name
     */
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    /**
     * Returns the informations about the supported messages
     * by the ILS SIP server.
     * @return the supportedMessages supported messages by the ILS
     * SIP server
     */
    public SupportedMessages getSupportedMessages() {
        return supportedMessages;
    }

    /**
     * Sets the supportedMessages value, that tells the supported messages
     * by the ILS SIP server.
     * @param supportedMessages supported messages by the ILS SIP server
     */
    public void setSupportedMessages(SupportedMessages supportedMessages) {
        this.supportedMessages = supportedMessages;
    }

    /**
     * Returns the system's location received from the ILS.
     * @return system's location received from the ILS
     */
    public String getTerminalLocation() {
        return terminalLocation;
    }

    /**
     * Sets the terminalLocation value, that tells system's location
     * received from the ILS.
     * @param terminalLocation system's location received from the ILS
     */
    public void setTerminalLocation(String terminalLocation) {
        this.terminalLocation = terminalLocation;
    }

    /**
     * Counts the chekcsum of this response message.
     * @return checksum
     */
    @Override
    public String countChecksum() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(StringUtil.bool2Char(this.onLineStatus));
        builder.append(StringUtil.bool2Char(this.checkinOk));
        builder.append(StringUtil.bool2Char(this.checkoutOk));
        builder.append(StringUtil.bool2Char(this.ILSRenewalPolicy));
        builder.append(StringUtil.bool2Char(this.statusUpdateOk));
        builder.append(StringUtil.bool2Char(this.offlineOk));
        builder.append(StringUtil.intToFixedLengthString(this.timeoutPeriod, 3));
        builder.append(StringUtil.intToFixedLengthString(this.retriesAllowed, 3));
        builder.append(this.dateTimeSync);
        builder.append(this.protocolVersion);
        builder.append("AO");
        builder.append(this.institutionId);
        builder.append("|");
        if (!this.libraryName.isEmpty()) {
            builder.append("AM");
            builder.append(this.libraryName);
            builder.append("|");
        }
        builder.append("BX");
        builder.append(StringUtil.bool2Char(this.supportedMessages.isPatronStatusRequest()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isCheckout()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isCheckin()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isBlockPatron()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isSCILSStatus()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isRequestSCILSResend()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isLogin()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isPatronInformation()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isEndPatronSession()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isFeePaid()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isItemInformation()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isItemStatusUpdate()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isPatronEnable()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isHold()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isRenew()));
        builder.append(StringUtil.bool2Char(this.supportedMessages.isRenewAll()));
        builder.append("|");

        if (!this.terminalLocation.isEmpty()) {
            builder.append("AN");
            builder.append(this.terminalLocation);
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
