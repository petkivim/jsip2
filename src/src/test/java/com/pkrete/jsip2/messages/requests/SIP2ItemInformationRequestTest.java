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
 * Test cases for SIP2ItemInformationRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2ItemInformationRequestTest extends TestCase {

    @Test
    public void testItemInformationRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2ItemInformationRequest req = new SIP2ItemInformationRequest("institutionId", "terminalPassword", "itemId");
            assertEquals("1720210814    083455AOinstitutionId|ABitemId|ACterminalPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testItemInformationRequestWithErrorDetection1() {
        SIP2ItemInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2ItemInformationRequest("itemId");
            req.setSequence(2);
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("1720210814    083455AO|ABitemId|AY2AZF67F" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testItemInformationRequestWithErrorDetection2() {
        SIP2ItemInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2ItemInformationRequest("institutionId", "itemId");
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20000227    161243");
        }
        assertEquals("1720000227    161243AOinstitutionId|ABitemId|AY0AZF117" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testItemInformationRequestWithErrorDetection3() {
        SIP2ItemInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2ItemInformationRequest("institutionId", "terminalPassword", "itemId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("1720210814    083455AOinstitutionId|ABitemId|ACterminalPassword|AY1AZE95A" + CARRIAGE_RETURN, req.getData());
    }
}
