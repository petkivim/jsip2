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
package com.pkrete.jsip2.parser;

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseException;
import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import com.pkrete.jsip2.messages.responses.SIP2ItemStatusUpdateResponse;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2ItemStatusUpdateResponse SIP2ItemStatusUpdateResponse} 
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ItemStatusUpdateResponseParser extends SIP2ResponseParser {

    /**
     * Parses a new SIP2ItemStatusUpdateResponse from the given data.
     * @param data message response data
     * @return SIP2ItemStatusUpdateResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     * @throws InvalidSIP2ResponseException 
     */
    @Override
    public SIP2ItemStatusUpdateResponse parse(String data)
            throws InvalidSIP2ResponseValueException,
            InvalidSIP2ResponseException {
        SIP2ItemStatusUpdateResponse response = new SIP2ItemStatusUpdateResponse(data);
        try {
            response.setItemPropertiesOk(this.intToBool(data.charAt(2)));
            response.setTransactionDate(data.substring(3, 21));

            response.setItemIdentifier(parseVariableWithoutDelimiter("AB", data.substring(21)));

            response.setTitleIdentifier(parseVariable("AJ", data.substring(21), false));
            response.setItemProperties(parseVariable("CH", data.substring(21), false));

            response.setScreenMessage(parseVariableMulti("AF", data.substring(21)));
            response.setPrintLine(parseVariableMulti("AG", data.substring(21)));

            if (!parseSequence(data).isEmpty()) {
                response.setSequence(Integer.parseInt(parseSequence(data)));
            }
            response.setCheckSum(parseChecksum(data));
        } catch (InvalidSIP2ResponseValueException e) {
            throw new InvalidSIP2ResponseValueException(e.getMessage() + " Response message string: \"" + data + "\"");
        }
        return response;
    }
}
