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
import com.pkrete.jsip2.messages.responses.SIP2PatronInformationResponse;
import com.pkrete.jsip2.variables.CurrencyTypeFactory;
import com.pkrete.jsip2.variables.ItemType;
import com.pkrete.jsip2.variables.ItemTypeFactory;
import com.pkrete.jsip2.variables.LanguageFactory;
import com.pkrete.jsip2.variables.PatronStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2PatronInformationResponse SIP2PatronInformationResponse} 
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2PatronInformationResponseParser extends SIP2ResponseParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIP2PatronInformationResponseParser.class);

    /**
     * Parses a new SIP2PatronInformationResponse from the given data.
     * @param data message response data
     * @return SIP2PatronInformationResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     */
    @Override
    public SIP2MessageResponse parse(String data)
            throws InvalidSIP2ResponseValueException {
        LOGGER.debug("Response: {}", data);

        SIP2PatronInformationResponse response = new SIP2PatronInformationResponse(data);
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

            response.setHoldItemsCount(stringToInt(data.substring(37, 41)));
            response.setOverdueItemsCount(stringToInt(data.substring(41, 45)));
            response.setChargedItemsCount(stringToInt(data.substring(45, 49)));
            response.setFineItemsCount(stringToInt(data.substring(49, 53)));
            response.setRecallItemsCount(stringToInt(data.substring(53, 57)));
            response.setUnavailableHoldsCount(stringToInt(data.substring(57, 61)));

            response.setInstitutionId(parseVariableWithoutDelimiter("AO", data.substring(61)));
            response.setPatronIdentifier(parseVariable("AA", data.substring(61)));
            response.setPersonalName(parseVariable("AE", data.substring(61)));

            if (exists("BZ", data.substring(61))) {
                String temp = parseVariable("BZ", data.substring(61));
                response.setHoldItemsLimit(stringToInt(temp));
            }
            if (exists("CA", data.substring(61))) {
                String temp = parseVariable("CA", data.substring(61));
                response.setOverdueItemsLimit(stringToInt(temp));
            }
            if (exists("CB", data.substring(61))) {
                String temp = parseVariable("CB", data.substring(61));
                response.setChargedItemsLimit(stringToInt(temp));
            }
            if (existsAndNotEmpty("BL", data.substring(61))) {
                String temp = parseVariable("BL", data.substring(61));
                response.setValidPatron(charToBool(temp.charAt(0)));
                response.setValidPatronUsed(true);
            }
            if (existsAndNotEmpty("CQ", data.substring(61))) {
                String temp = parseVariable("CQ", data.substring(61));
                response.setValidPatronPassword(charToBool(temp.charAt(0)));
                response.setValidPatronPasswordUsed(true);
            }
            if (existsAndNotEmpty("BH", data.substring(61))) {
                String temp = parseVariable("BH", data.substring(61));
                response.setCurrencyType(CurrencyTypeFactory.getInstance().getCurrencyType(temp));
            }

            response.setFeeAmount(parseVariable("BV", data.substring(61), false));
            response.setFeeLimit(parseVariable("CC", data.substring(61), false));

            for (ItemType type : ItemTypeFactory.getInstance().getAllItemTypes()) {
                List<String> temp = parseVariableMulti(type.toString(), data.substring(61));
                if (!temp.isEmpty()) {
                    response.setItems(temp);
                    response.setItemType(type);
                    break;
                }
            }

            response.setHomeAddress(parseVariable("BD", data.substring(61), false));
            response.setEmail(parseVariable("BE", data.substring(61), false));
            response.setPhone(parseVariable("BF", data.substring(61), false));

            /* SIP2 Extensions - Begin */
            response.setBirthDate(parseVariable("PB", data.substring(61), false));
            response.setPacAccessType(parseVariable("PA", data.substring(61), false));
            response.setPatronType(parseVariable("ZY", data.substring(61), false));
            /* SIP2 Extensions - End */

            /* Voyager ESIP extensions - Begin */
            response.setPatronGroup(parseVariable("PT", data.substring(61), false));
            /* Voyager ESIP extensions - End */

            response.setScreenMessage(parseVariableMulti("AF", data.substring(61)));
            response.setPrintLine(parseVariableMulti("AG", data.substring(61)));

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
