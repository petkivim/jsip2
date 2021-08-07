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
package com.pkrete.jsip2.parser;

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.variables.SupportedMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class parses the data received from the ILS SIP server
 * and generates {@link SIP2ACSStatusResponse SIP2ACSStatusResponse} objects.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2ACSStatusResponseParser extends SIP2ResponseParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIP2ACSStatusResponseParser.class);

    /**
     * Parses a new SIP2ACSStatusResponse from the given data.
     * @param data message response data
     * @return SIP2ACSStatusResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     */
    @Override
    public SIP2MessageResponse parse(String data)
            throws InvalidSIP2ResponseValueException {
        LOGGER.debug("Response: {}", data);

        SIP2ACSStatusResponse response = new SIP2ACSStatusResponse(data);
        try {
            response.setOnLineStatus(charToBool(data.charAt(2)));
            response.setCheckinOk(charToBool(data.charAt(3)));
            response.setCheckoutOk(charToBool(data.charAt(4)));
            response.setILSRenewalPolicy(charToBool(data.charAt(5)));
            response.setStatusUpdateOk(charToBool(data.charAt(6)));
            response.setOfflineOk(charToBool(data.charAt(7)));
            response.setTimeoutPeriod(this.stringToInt(data.substring(8, 11)));
            response.setRetriesAllowed(this.stringToInt(data.substring(11, 14)));
            response.setDateTimeSync(data.substring(14, 32));
            response.setProtocolVersion(data.substring(32, 36));

            response.setInstitutionId(parseVariableWithoutDelimiter("AO", data.substring(36)));
            response.setLibraryName(parseVariable("AM", data.substring(36)));

            // Parse supported messages
            String bx = parseVariable("BX", data.substring(36));
            SupportedMessages messages = new SupportedMessages();

            messages.setPatronStatusRequest(charToBool(bx.charAt(0)));
            messages.setCheckout(charToBool(bx.charAt(1)));
            messages.setCheckin(charToBool(bx.charAt(2)));
            messages.setBlockPatron(charToBool(bx.charAt(3)));
            messages.setSCILSStatus(charToBool(bx.charAt(4)));
            messages.setRequestSCILSResend(charToBool(bx.charAt(5)));
            messages.setLogin(charToBool(bx.charAt(6)));
            messages.setPatronInformation(charToBool(bx.charAt(7)));
            messages.setEndPatronSession(charToBool(bx.charAt(8)));
            messages.setFeePaid(charToBool(bx.charAt(9)));
            messages.setItemInformation(charToBool(bx.charAt(10)));
            messages.setItemStatusUpdate(charToBool(bx.charAt(11)));
            messages.setPatronEnable(charToBool(bx.charAt(12)));
            messages.setHold(charToBool(bx.charAt(13)));
            messages.setRenew(charToBool(bx.charAt(14)));
            messages.setRenewAll(charToBool(bx.charAt(15)));

            response.setSupportedMessages(messages);

            response.setTerminalLocation(parseVariable("AN", data.substring(36)));
            response.setScreenMessage(parseVariableMulti("AF", data.substring(36)));
            response.setPrintLine(parseVariableMulti("AG", data.substring(36)));
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
