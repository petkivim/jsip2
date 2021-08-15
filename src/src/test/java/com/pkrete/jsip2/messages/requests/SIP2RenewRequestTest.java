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
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static com.pkrete.jsip2.util.TestUtils.CARRIAGE_RETURN;
import static com.pkrete.jsip2.util.TestUtils.SIP2_DATE_TIME;

/**
 * Test cases for SIP2RenewRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2RenewRequestTest extends TestCase {

    @Test
    public void testRenewRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2RenewRequest req = new SIP2RenewRequest("institutionId", "patronId", "itemId", "titleId");
            assertEquals("29NN20210814    08345520210814    083455AOinstitutionId|AApatronId|ABitemId|AJtitleId|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testRenewRequestWithErrorDetection1() {
        SIP2RenewRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewRequest("patronId");
            req.setErrorDetectionEnabled(true);
            req.setThirdPartyAllowed(true);
            req.setNoBlock(true);
        }
        assertEquals("29YY20210814    08345520210814    083455AO|AApatronId|AY0AZF19D" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testRenewRequestWithErrorDetection2() {
        SIP2RenewRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewRequest("patronId",  "itemId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setItemProperties("itemProperties");
            req.setTransactionDate("20171020    225122");
            req.setNbDueDate("20171120    225121");
        }
        assertEquals("29NN20171020    22512220171120    225121AO|AApatronId|ABitemId|CHitemProperties|AY1AZE794" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testRenewRequestWithErrorDetection3() {
        SIP2RenewRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewRequest("patronId", "itemId", "titleId");
            req.setErrorDetectionEnabled(true);
            req.setSequence(3);
            req.setPatronPassword("patronPassword");
            req.setTerminalPassword("terminalPassword");
            req.setUseFeeAcknowledged(true);
        }
        assertEquals("29NN20210814    08345520210814    083455AO|AApatronId|ADpatronPassword|ABitemId|AJtitleId|ACterminalPassword|BON|AY3AZDA8D" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testRenewRequestWithErrorDetection4() {
        SIP2RenewRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewRequest("institutionId", "patronId", "itemId", "titleId");
            req.setErrorDetectionEnabled(true);
            req.setNoBlock(true);
            req.setFeeAcknowledged(true);
            req.setUseFeeAcknowledged(true);
            req.setItemProperties("itemProperties");
        }
        assertEquals("29NY20210814    08345520210814    083455AOinstitutionId|AApatronId|ABitemId|AJtitleId|CHitemProperties|BOY|AY0AZDCB7" + CARRIAGE_RETURN, req.getData());
    }
}
