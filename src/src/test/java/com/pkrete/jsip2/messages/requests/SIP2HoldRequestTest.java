/*
 *  The MIT License
 *
 *  Copyright 2021- Petteri Kivimäki
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

import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.variables.HoldMode;
import com.pkrete.jsip2.variables.HoldType;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static com.pkrete.jsip2.util.TestUtils.CARRIAGE_RETURN;
import static com.pkrete.jsip2.util.TestUtils.SIP2_DATE_TIME;

/**
 * Test cases for SIP2HoldRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2HoldRequestTest extends TestCase {
    
    @Test
    public void testHoldRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2HoldRequest req = new SIP2HoldRequest("institutionId", "itemId", "patronId", "titleId");
            assertEquals("15+20210814    083455AOinstitutionId|AAitemId|ABpatronId|AJtitleId|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testHoldRequestWithErrorDetection1() {
        SIP2HoldRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2HoldRequest("patronId");
            req.setErrorDetectionEnabled(true);
            req.setHoldMode(HoldMode.DELETE);
            req.setExpirationDate("20140914    185459");
        }
        assertEquals("15-20210814    083455BW20140914    185459|AO|AApatronId|AY0AZF108" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testHoldRequestWithErrorDetection2() {
        SIP2HoldRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2HoldRequest("patronId",  "itemId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setPickupLocation("pickupLocation");
            req.setHoldType(HoldType.SPECIFIC_COPY);
            req.setTransactionDate("20130915    1854553");
        }
        assertEquals("15+20130915    1854553BSpickupLocation|BY3|AO|AApatronId|ABitemId|AY1AZE9BF" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testHoldRequestWithErrorDetection3() {
        SIP2HoldRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2HoldRequest("patronId","itemId", "titleId");
            req.setErrorDetectionEnabled(true);
            req.setSequence(3);
            req.setUseFeeAcknowledged(true);
            req.setPatronPassword("patronPassword");
        }
        assertEquals("15+20210814    083455AO|AApatronId|ADpatronPassword|ABitemId|AJtitleId|BON|AY3AZE5FD" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testHoldRequestWithErrorDetection4() {
        SIP2HoldRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2HoldRequest("institutionId", "patronId", "itemId", "titleId");
            req.setErrorDetectionEnabled(true);
            req.setFeeAcknowledged(true);
            req.setUseFeeAcknowledged(true);
            req.setTerminalPassword("terminalPassword");
            req.setBibId("bibId");
        }
        assertEquals("15+20210814    083455AOinstitutionId|AApatronId|ABitemId|AJtitleId|ACterminalPassword|BOY|MAbibId|AY0AZDCD3" + CARRIAGE_RETURN, req.getData());
    }
}
