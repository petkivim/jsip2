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

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseException;
import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import com.pkrete.jsip2.parser.SIP2CheckinResponseParser;
import com.pkrete.jsip2.parser.SIP2CheckoutResponseParser;
import com.pkrete.jsip2.parser.SIP2EndSessionResponseParser;
import com.pkrete.jsip2.parser.SIP2FeePaidResponseParser;
import com.pkrete.jsip2.parser.SIP2HoldResponseParser;
import com.pkrete.jsip2.parser.SIP2ItemInformationResponseParser;
import com.pkrete.jsip2.parser.SIP2ItemStatusUpdateResponseParser;
import com.pkrete.jsip2.parser.SIP2LoginResponseParser;
import com.pkrete.jsip2.parser.SIP2PatronEnableResponseParser;
import com.pkrete.jsip2.parser.SIP2PatronInformationResponseParser;
import com.pkrete.jsip2.parser.SIP2PatronStatusResponseParser;
import com.pkrete.jsip2.parser.SIP2RenewAllResponseParser;
import com.pkrete.jsip2.parser.SIP2RenewResponseParser;
import com.pkrete.jsip2.parser.SIP2ResponseParser;
import com.pkrete.jsip2.parser.SIP2ACSStatusResponseParser;

/**
 * This class generates SIP2MessageResponse objects based on the data
 * received from the ILS SIP server. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ResponseFactory {
    /**
     * Reference to the singleton object.
     */
    private static SIP2ResponseFactory ref;

    /**
     * Constructs and initializes a new SIPResponseFactory object.
     */
    private SIP2ResponseFactory() {
    }

    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static SIP2ResponseFactory getInstance() {
        if (ref == null) {
            ref = new SIP2ResponseFactory();
        }
        return ref;
    }

    /**
     * Creates a new SIP2MessageResponse object based on the given data. 
     * The data contains the command identifier which defines the actual
     * type of the response message.
     * @param data message response data
     * @return SIP2MessageResponse object parsed from the data
     * @throws InvalidSIP2ResponseException
     * @throws InvalidSIP2ResponseValueException 
     */
    public SIP2MessageResponse create(String data) throws InvalidSIP2ResponseException, InvalidSIP2ResponseValueException {  
        // If data is null, throw an exception
        if (data == null) {
            throw new InvalidSIP2ResponseException("Response message is null.");
        }
        // If data length is less than 2, throw an exception
        if (data.length() < 2) {
            throw new InvalidSIP2ResponseException("Response message is too short.");
        }

        SIP2ResponseParser parser = null;
        // Get the command identifier
        String code = data.substring(0, 2);

        if (code.equals("94")) {
            parser = new SIP2LoginResponseParser();
            return parser.parse(data);
        } else if (code.equals("98")) {
            parser = new SIP2ACSStatusResponseParser();
            return parser.parse(data);
        } else if (code.equals("24")) {
            parser = new SIP2PatronStatusResponseParser();
            return parser.parse(data);
        } else if (code.equals("64")) {
            parser = new SIP2PatronInformationResponseParser();
            return parser.parse(data);
        } else if (code.equals("10")) {
            parser = new SIP2CheckinResponseParser();
            return parser.parse(data);
        } else if (code.equals("12")) {
            parser = new SIP2CheckoutResponseParser();
            return parser.parse(data);
        } else if (code.equals("36")) {
            parser = new SIP2EndSessionResponseParser();
            return parser.parse(data);
        } else if (code.equals("38")) {
            parser = new SIP2FeePaidResponseParser();
            return parser.parse(data);
        } else if (code.equals("18")) {
            parser = new SIP2ItemInformationResponseParser();
            return parser.parse(data);
        } else if (code.equals("20")) {
            parser = new SIP2ItemStatusUpdateResponseParser();
            return parser.parse(data);
        } else if (code.equals("26")) {
            parser = new SIP2PatronEnableResponseParser();
            return parser.parse(data);
        } else if (code.equals("16")) {
            parser = new SIP2HoldResponseParser();
            return parser.parse(data);
        } else if (code.equals("30")) {
            parser = new SIP2RenewResponseParser();
            return parser.parse(data);
        } else if (code.equals("66")) {
            parser = new SIP2RenewAllResponseParser();
            return parser.parse(data);
        }
        throw new InvalidSIP2ResponseException("Unsupported response type! Command identifier: " + code);
    }
}
