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

import com.pkrete.jsip2.messages.responses.SIP2LoginResponse;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;

/**
 * This class represents the message that is used to login to an ILS SIP 
 * server program. The ILS SIP server should respond with the 
 * {@link SIP2LoginResponse SIP2LoginResponse} message. This is the first
 * message sent to the ILS.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2LoginRequest extends SIP2MessageRequest {

    /**
     * Specifies the algorithm used to encrypt userName
     */
    private char UIDAlgorithm;
    /**
     * Specifies the algorithm used to encrypt password
     */
    private char PWDAlgorithm;
    /**
     * Username to login to the ILS
     */
    private String userName;
    /**
     * Password to login to the ILS
     */
    private String password;
    /**
     * ILS's circulation location to be used
     */
    private String circulationLocation;

    /**
     * Constructs and initializes a new SIP2LoginRequest object.
     */
    private SIP2LoginRequest() {
        super("93");
        this.UIDAlgorithm = '0';
        this.PWDAlgorithm = '0';
    }

    /**
     * Constructs and initializes a new SIP2LoginRequest object with 
     * the given userName and password.
     * @param userName ILS username
     * @param password ILS password
     */
    public SIP2LoginRequest(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    /**
     * Constructs and initializes a new SIP2LoginRequest object with 
     * the given userName, password, and circulationLocation.
     * @param userName ILS username
     * @param password ILS password
     * @param circulationLocation ILS circulation location
     */
    public SIP2LoginRequest(String userName, String password, String circulationLocation) {
        this();
        this.userName = userName;
        this.password = password;
        this.circulationLocation = circulationLocation;
    }

    /**
     * Returns the username to login to the ILS.
     * @return username to login to the ILS
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username to login to the ILS.
     * @param userName username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password to login to the ILS.
     * @return password to login to the ILS
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to login to the ILS.
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the ILS's circulation location to be used.
     * @return ILS's circulation location to be used
     */
    public String getCirculationLocation() {
        return circulationLocation;
    }

    /**
     * Sets the ILS's circulation location to be used.
     * @param circulationLocation new location
     */
    public void setCirculationLocation(String circulationLocation) {
        this.circulationLocation = circulationLocation;
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
        builder.append(UIDAlgorithm);
        builder.append(PWDAlgorithm);
        builder.append("CN");
        builder.append(userName);
        builder.append("|CO");
        builder.append(password);
        if (circulationLocation != null) {
            builder.append("|CP");
            builder.append(circulationLocation);
        }
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
