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
import com.pkrete.jsip2.variables.CurrencyType;
import com.pkrete.jsip2.variables.FeeType;
import com.pkrete.jsip2.variables.PaymentType;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static com.pkrete.jsip2.util.TestUtils.CARRIAGE_RETURN;
import static com.pkrete.jsip2.util.TestUtils.SIP2_DATE_TIME;

/**
 * Test cases for SIP2EndPatronSessionRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2FeePaidRequestTest extends TestCase {

    @Test
    public void testFeePaidRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2FeePaidRequest req = new SIP2FeePaidRequest("institutionId", "patronId", "2.00");
            assertEquals("3720210814    0834550100EURBV2.00|AOinstitutionId|AApatronId|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testFeePaidRequestWithErrorDetection1() {
        SIP2FeePaidRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2FeePaidRequest("institutionId", "patronId", "2.00");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20130514    132223");
            req.setFeeType(FeeType.OVERDUE);
            req.setPaymentType(PaymentType.CREDIT_CARD);
            req.setFeeIdentifier("feeIdentifier");
        }
        assertEquals("3720130514    1322230402EURBV2.00|AOinstitutionId|AApatronId|CGfeeIdentifier|AY1AZE672" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testFeePaidRequestWithErrorDetection2() {
        SIP2FeePaidRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2FeePaidRequest("institutionId", "patronId", "9.99");
            req.setErrorDetectionEnabled(true);
            req.setCurrencyType(CurrencyType.US_DOLLAR);
            req.setTerminalPassword("terminalPassword");
            req.setPatronPassword("patronPassword");
            req.setTransactionId("transactionId");
        }
        assertEquals("3720210814    0834550100USDBV9.99|AOinstitutionId|AApatronId|ACterminalPassword|ADpatronPassword|BKtransactionId|AY0AZD797" + CARRIAGE_RETURN, req.getData());
    }
}
