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
import com.pkrete.jsip2.messages.responses.SIP2RenewResponse;
import com.pkrete.jsip2.variables.CurrencyTypeFactory;
import com.pkrete.jsip2.variables.FeeTypeFactory;
import com.pkrete.jsip2.variables.MediaTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2RenewResponse SIP2RenewResponse} 
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2RenewResponseParser extends SIP2ResponseParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIP2RenewResponseParser.class);

    /**
     * Parses a new SIP2RenewResponse from the given data.
     * @param data message response data
     * @return SIP2RenewResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     */
    @Override
    public SIP2RenewResponse parse(String data)
            throws InvalidSIP2ResponseValueException {
        LOGGER.debug("Response: {}", data);

        SIP2RenewResponse response = new SIP2RenewResponse(data);
        try {
            response.setOk(this.intToBool(data.charAt(2)));
            response.setRenewalOk(this.charToBool(data.charAt(3)));
            if (data.charAt(4) == 'U') {
                response.setMagneticMediaSupported(false);
                response.setMagneticMedia(false);
            } else {
                response.setMagneticMediaSupported(true);
                response.setMagneticMedia(this.charToBool(data.charAt(4)));
            }
            if (data.charAt(5) == 'U') {
                response.setDesensitizeSupported(false);
                response.setDesensitize(false);
            } else {
                response.setDesensitizeSupported(true);
                response.setDesensitize(this.charToBool(data.charAt(5)));
            }
            response.setTransactionDate(data.substring(6, 24));

            response.setInstitutionId(parseVariableWithoutDelimiter("AO", data.substring(24)));
            response.setPatronIdentifier(parseVariable("AA", data.substring(24)));
            response.setItemIdentifier(parseVariable("AB", data.substring(24)));
            response.setTitleIdentifier(parseVariable("AJ", data.substring(24)));
            response.setDueDate(parseVariable("AH", data.substring(24)));

            if (existsAndNotEmpty("BT", data.substring(24))) {
                response.setFeeType(FeeTypeFactory.getInstance().getFeeType(parseVariable("BT", data.substring(24))));
            }
            if (existsAndNotEmpty("CI", data.substring(24))) {
                response.setSecurityInhibitUsed(true);
                response.setSecurityInhibit(this.charToBool(parseVariable("CI", data.substring(24)).charAt(0)));
            }
            if (existsAndNotEmpty("BH", data.substring(24))) {
                response.setCurrencyType(CurrencyTypeFactory.getInstance().getCurrencyType(parseVariable("BH", data.substring(24))));
            }

            response.setFeeAmount(parseVariable("BV", data.substring(24), false));

            if (existsAndNotEmpty("CK", data.substring(24))) {
                response.setMediaType(MediaTypeFactory.getInstance().getMediaType(parseVariable("CK", data.substring(24))));
            }

            response.setItemProperties(parseVariable("CH", data.substring(24), false));
            response.setTransactionId(parseVariable("BK", data.substring(24), false));

            response.setScreenMessage(parseVariableMulti("AF", data.substring(24)));
            response.setPrintLine(parseVariableMulti("AG", data.substring(24)));

            if (!parseSequence(data).isEmpty()) {
                response.setSequence(Integer.parseInt(parseSequence(data)));
            }
            response.setCheckSum(parseChecksum(data));
        } catch (InvalidSIP2ResponseValueException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InvalidSIP2ResponseValueException(e.getMessage() + " Response message string: \"" + data + "\"");
        }
        return response;
    }
}
