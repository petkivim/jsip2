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

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import com.pkrete.jsip2.messages.responses.SIP2CheckinResponse;
import com.pkrete.jsip2.variables.AlertTypeFactory;
import com.pkrete.jsip2.variables.MediaTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2CheckinResponse SIP2CheckinResponse} 
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2CheckinResponseParser extends SIP2ResponseParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIP2CheckinResponseParser.class);

    /**
     * Parses a new SIP2CheckinResponse from the given data.
     * @param data message response data
     * @return SIP2CheckinResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     */
    @Override
    public SIP2CheckinResponse parse(String data)
            throws InvalidSIP2ResponseValueException {
        LOGGER.debug("Response: {}", data);

        SIP2CheckinResponse response = new SIP2CheckinResponse(data);
        try {
            response.setOk(this.intToBool(data.charAt(2)));
            response.setResensitize(this.charToBool(data.charAt(3)));
            if (data.charAt(4) == 'U') {
                response.setMagneticMediaSupported(false);
                response.setMagneticMedia(false);
            } else {
                response.setMagneticMediaSupported(true);
                response.setMagneticMedia(this.charToBool(data.charAt(4)));
            }
            response.setAlert(this.charToBool(data.charAt(5)));
            response.setTransactionDate(data.substring(6, 24));

            response.setInstitutionId(parseVariableWithoutDelimiter("AO", data.substring(24)));
            response.setItemIdentifier(parseVariable("AB", data.substring(24)));
            response.setPermanentLocation(parseVariable("AQ", data.substring(24)));
            response.setTitleIdentifier(parseVariable("AJ", data.substring(24)));

            response.setSortBin(parseVariable("CL", data.substring(24), false));
            response.setPatronIdentifier(parseVariable("AA", data.substring(24), false));

            if (existsAndNotEmpty("CK", data.substring(24))) {
                response.setMediaType(MediaTypeFactory.getInstance().getMediaType(parseVariable("CK", data.substring(24))));
            }

            response.setItemProperties(parseVariable("CH", data.substring(24), false));

            /* SIP2 Extensions - Begin */
            response.setCollectionCode(parseVariable("CR", data.substring(24), false));
            response.setCallNumber(parseVariable("CS", data.substring(24), false));
            response.setDestinationLocation(parseVariable("CT", data.substring(24), false));
            if (existsAndNotEmpty("CV", data.substring(24))) {
                response.setAlertType(AlertTypeFactory.getInstance().getAlertType(parseVariable("CV", data.substring(24))));
            }
            response.setHoldPatronId(parseVariable("CY", data.substring(24), false));
            response.setHoldPatronName(parseVariable("DA", data.substring(24), false));
            /* SIP2 Extensions - End */

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
