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
 * Test cases for SIP2CheckoutRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2CheckoutRequestTest extends TestCase {

    @Test
    public void testCheckoutRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2CheckoutRequest req = new SIP2CheckoutRequest("institutionId", "terminalPassword","patronId", "patronPassword", "itemId");
            assertEquals("11NN20210814    08345520210814    083455AOinstitutionId|AApatronId|ABitemId|ACterminalPassword|ADpatronPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testCheckoutRequestWithErrorDetection1() {
        SIP2CheckoutRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckoutRequest("patronId", "itemId");
            req.setErrorDetectionEnabled(true);
            req.setScRenewalPolicy(true);
            req.setNoBlock(true);
        }
        assertEquals("11YY20210814    08345520210814    083455AO|AApatronId|ABitemId|AC|AY0AZED4B" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testCheckoutRequestWithErrorDetection2() {
        SIP2CheckoutRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckoutRequest("institutionId", "patronId",  "itemId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setItemProperties("itemProperties");
            req.setTransactionDate("20190304    173152");
        }
        assertEquals("11NN20190304    17315220210814    083455AOinstitutionId|AApatronId|ABitemId|AC|CHitemProperties|AY1AZE10B" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testCheckoutRequestWithErrorDetection3() {
        SIP2CheckoutRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckoutRequest("institutionId", "patronId", "patronPassword", "itemId");
            req.setErrorDetectionEnabled(true);
            req.setSequence(3);
            req.setScRenewalPolicy(true);
            req.setUseFeeAcknowledged(true);
            req.setUseCancel(true);
        }
        assertEquals("11YN20210814    08345520210814    083455AOinstitutionId|AApatronId|ABitemId|AC|ADpatronPassword|BON|BIN|AY3AZDE44" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testCheckoutRequestWithErrorDetection4() {
        SIP2CheckoutRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckoutRequest("institutionId", "terminalPassword","patronId", "patronPassword", "itemId");
            req.setErrorDetectionEnabled(true);
            req.setNoBlock(true);
            req.setFeeAcknowledged(true);
            req.setUseFeeAcknowledged(true);
            req.setCancel(true);
            req.setUseCancel(true);
        }
        assertEquals("11NY20210814    08345520210814    083455AOinstitutionId|AApatronId|ABitemId|ACterminalPassword|ADpatronPassword|BOY|BIY|AY0AZD782" + CARRIAGE_RETURN, req.getData());
    }
}
