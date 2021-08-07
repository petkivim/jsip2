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
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.messages.responses.SIP2PatronStatusResponse;
import com.pkrete.jsip2.variables.CurrencyTypeFactory;
import com.pkrete.jsip2.variables.LanguageFactory;
import com.pkrete.jsip2.variables.PatronStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2PatronStatusResponse SIP2PatronStatusResponse} 
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2PatronStatusResponseParser extends SIP2ResponseParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIP2PatronStatusResponseParser.class);

    /**
     * Parses a new SIP2PatronStatusResponse from the given data.
     * @param data message response data
     * @return SIP2PatronStatusResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     */
    @Override
    public SIP2MessageResponse parse(String data)
            throws InvalidSIP2ResponseValueException {
        LOGGER.debug("Response: {}", data);

        SIP2PatronStatusResponse response = new SIP2PatronStatusResponse(data);
        try {
            PatronStatus status = new PatronStatus();
            status.setChargePrivilegesDenied(charEmptyToBool(data.charAt(2)));
            status.setRenewalPrivilegesDenied(charEmptyToBool(data.charAt(3)));
            status.setRecallPrivilegesDenied(charEmptyToBool(data.charAt(4)));
            status.setHoldPrivilegesDenied(charEmptyToBool(data.charAt(5)));
            status.setCardReportedLost(charEmptyToBool(data.charAt(6)));
            status.setTooManyItemsCharged(charEmptyToBool(data.charAt(7)));
            status.setTooManyItemsOverdue(charEmptyToBool(data.charAt(8)));
            status.setTooManyRenewals(charEmptyToBool(data.charAt(9)));
            status.setTooManyClaimsOfItemsReturned(charEmptyToBool(data.charAt(10)));
            status.setTooManyItemsLost(charEmptyToBool(data.charAt(11)));
            status.setExcessiveOutstandingFines(charEmptyToBool(data.charAt(12)));
            status.setExcessiveOutstandingFees(charEmptyToBool(data.charAt(13)));
            status.setRecallOverdue(charEmptyToBool(data.charAt(14)));
            status.setTooManyItemsBilled(charEmptyToBool(data.charAt(15)));
            response.setStatus(status);

            response.setLanguage(LanguageFactory.getInstance().getLanguage(data.substring(16, 19)));

            response.setTransactionDate(data.substring(19, 37));

            response.setInstitutionId(parseVariableWithoutDelimiter("AO", data.substring(37)));
            response.setPatronIdentifier(parseVariable("AA", data.substring(37)));
            response.setPersonalName(parseVariable("AE", data.substring(37)));
            if (existsAndNotEmpty("BL", data.substring(37))) {
                String temp = parseVariable("BL", data.substring(37));
                response.setValidPatron(charToBool(temp.charAt(0)));
                response.setValidPatronUsed(true);
            }
            if (existsAndNotEmpty("CQ", data.substring(37))) {
                String temp = parseVariable("CQ", data.substring(37));
                response.setValidPatronPassword(charToBool(temp.charAt(0)));
                response.setValidPatronPasswordUsed(true);
            }
            if (existsAndNotEmpty("BH", data.substring(37))) {
                String temp = parseVariable("BH", data.substring(37));
                response.setCurrencyType(CurrencyTypeFactory.getInstance().getCurrencyType(temp));
            }

            response.setFeeAmount(parseVariable("BV", data.substring(37), false));

            response.setScreenMessage(parseVariableMulti("AF", data.substring(37)));
            response.setPrintLine(parseVariableMulti("AG", data.substring(37)));

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
