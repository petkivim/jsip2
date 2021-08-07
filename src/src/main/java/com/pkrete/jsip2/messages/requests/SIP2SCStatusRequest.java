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
package com.pkrete.jsip2.messages.requests;

import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.variables.StatusCode;

/**
 * This class represents the request that sends the current status of
 * the system to the ILS SIP server, which replies with the
 * {@link SIP2ACSStatusResponse SIP2ACSStatusResponse} message.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2SCStatusRequest extends SIP2MessageRequest {

    /**
     * Status of the system
     */
    private StatusCode statusCode;
    /**
     * Number of characters the system printer can print on one line
     */
    private String maxPrintWidth;
    /**
     * Version of the SIP protocol that's used: 2.00
     */
    private String protocolVersion;

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object.
     */
    public SIP2SCStatusRequest() {
        super("99");
        this.protocolVersion = "2.00";
        this.statusCode = StatusCode.OK;
        this.maxPrintWidth = "000";
    }

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object with
     * the given status code.
     * @param status the status of the system
     */
    public SIP2SCStatusRequest(StatusCode status) {
        this();
        this.statusCode = status;
    }

    /**
     * Constructs and initializes a new SIP2SCStatusRequest object with the
     * given status code and max print width.
     * @param status the status of the system
     * @param maxPrintWidth  number of characters the system printer can
     * print on one line
     */
    public SIP2SCStatusRequest(StatusCode status, String maxPrintWidth) {
        this(status);
        this.maxPrintWidth = maxPrintWidth;
    }

    /**
     * Returns the status of the system.
     * @return status of the system
     */
    public StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * Changes the status of the system.
     * @param statusCode new value
     */
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the maximum number of characters that the system
     * printer can print in one line. The value is 3-char, 
     * fixed-length.
     * @return the maxPrintWidth
     */
    public String getMaxPrintWidth() {
        return maxPrintWidth;
    }

    /**
     * Changes the maximum number of characters that the system
     * printer can print in one line. The value is 3-char, 
     * fixed-length.
     * @param maxPrintWidth new value, must be 3-char and 
     * fixed-length
     */
    public void setMaxPrintWidth(String maxPrintWidth) {
        this.maxPrintWidth = maxPrintWidth;
    }

    @Override
    /**
     * Returns a String presentation of this message, that is sent
     * to the ILS SIP server. The message contains all the variables
     * plus sequence and checksum values when error detection is enabled.
     */
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(statusCode);
        builder.append(maxPrintWidth);
        builder.append(protocolVersion);
        builder.append("|");

        if (errorDetectionEnabled) {
            builder.append("AY");
            builder.append(getSequence());
            builder.append("AZ");
            this.checkSum = MessageUtil.computeChecksum(builder.toString());
            builder.append(checkSum);
        }
        return builder.toString() + '\r';
    }
}
