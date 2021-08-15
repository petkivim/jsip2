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
 * Test cases for SIP2RenewAllRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2RenewAllRequestTest extends TestCase {

    @Test
    public void testRenewAllRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2RenewAllRequest req = new SIP2RenewAllRequest("institutionId", "terminalPassword", "patronId", "patronPassword");
            assertEquals("6520210814    083455AOinstitutionId|AApatronId|ADpatronPassword|ACterminalPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testRenewAllRequestWithErrorDetection1() {
        SIP2RenewAllRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewAllRequest("patronId");
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("6520210814    083455AO|AApatronId|AY0AZF59A" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testRenewAllRequestWithErrorDetection2() {
        SIP2RenewAllRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewAllRequest("institutionId","patronId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20111111    112233");
        }
        assertEquals("6520111111    112233AOinstitutionId|AApatronId|AY1AZF039" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testRenewAllRequestWithErrorDetection3() {
        SIP2RenewAllRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewAllRequest("institutionId",  "patronId", "patronPassword");
            req.setErrorDetectionEnabled(true);
            req.setSequence(3);
            req.setUseFeeAcknowledged(true);
        }
        assertEquals("6520210814    083455AOinstitutionId|AApatronId|ADpatronPassword|BON|AY3AZE7DD" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testRenewAllRequestWithErrorDetection4() {
        SIP2RenewAllRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2RenewAllRequest("institutionId", "terminalPassword", "patronId", "patronPassword");
            req.setErrorDetectionEnabled(true);
            req.setFeeAcknowledged(true);
            req.setUseFeeAcknowledged(true);
        }
        assertEquals("6520210814    083455AOinstitutionId|AApatronId|ADpatronPassword|ACterminalPassword|BOY|AY0AZE026" + CARRIAGE_RETURN, req.getData());
    }
}
