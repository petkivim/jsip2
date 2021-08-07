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
import com.pkrete.jsip2.messages.responses.SIP2ItemInformationResponse;
import com.pkrete.jsip2.variables.CirculationStatusFactory;
import com.pkrete.jsip2.variables.CurrencyTypeFactory;
import com.pkrete.jsip2.variables.FeeTypeFactory;
import com.pkrete.jsip2.variables.MediaTypeFactory;
import com.pkrete.jsip2.variables.SecurityMarkerFactory;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2ItemInformationResponse SIP2ItemInformationResponse} 
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ItemInformationResponseParser extends SIP2ResponseParser {

    /**
     * Parses a new SIP2ItemInformationResponse from the given data.
     * @param data message response data
     * @return SIP2ItemInformationResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     * @throws InvalidSIP2ResponseException 
     */
    @Override
    public SIP2ItemInformationResponse parse(String data)
            throws InvalidSIP2ResponseValueException,
            InvalidSIP2ResponseException {
        SIP2ItemInformationResponse response = new SIP2ItemInformationResponse(data);
        try {
            response.setCirculationStatus(CirculationStatusFactory.getInstance().getCirculationStatus(data.substring(2, 4)));
            response.setSecurityMarker(SecurityMarkerFactory.getInstance().getSecurityMarker(data.substring(4, 6)));
            response.setFeeType(FeeTypeFactory.getInstance().getFeeType(data.substring(6, 8)));
            response.setTransactionDate(data.substring(8, 26));

            response.setHoldQueueLength(parseVariableWithoutDelimiter("CF", data.substring(26), false));
            response.setDueDate(parseVariableWithoutDelimiter("AH", data.substring(26), false));
            response.setRecallDate(parseVariableWithoutDelimiter("CJ", data.substring(26), false));
            response.setHoldPickupDate(parseVariableWithoutDelimiter("CM", data.substring(26), false));

            response.setItemIdentifier(parseVariableWithoutDelimiter("AB", data.substring(26)));
            response.setTitleIdentifier(parseVariable("AJ", data.substring(26)));

            response.setOwner(parseVariable("BG", data.substring(26), false));
            if (existsAndNotEmpty("BH", data.substring(26))) {
                response.setCurrencyType(CurrencyTypeFactory.getInstance().getCurrencyType(parseVariable("BH", data.substring(26))));
            }
            response.setFeeAmount(parseVariable("BV", data.substring(26), false));
            if (existsAndNotEmpty("CK", data.substring(26))) {
                response.setMediaType(MediaTypeFactory.getInstance().getMediaType(parseVariable("CK", data.substring(26))));
            }
            response.setPermanentLocation(parseVariable("AQ", data.substring(26), false));
            response.setCurrentLocation(parseVariable("AP", data.substring(26), false));
            response.setItemProperties(parseVariable("CH", data.substring(26), false));

            response.setScreenMessage(parseVariableMulti("AF", data.substring(26)));
            response.setPrintLine(parseVariableMulti("AG", data.substring(26)));

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
