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
 * Test cases for SIP2ItemStatusUpdateRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2ItemStatusUpdateRequestTest extends TestCase {

    @Test
    public void testItemStatusUpdateRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2ItemStatusUpdateRequest req = new SIP2ItemStatusUpdateRequest("institutionId", "terminalPassword", "itemId", "itemProperties");
            assertEquals("1920210814    083455AOinstitutionId|ABitemId|ACterminalPassword|CHitemProperties|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testItemStatusUpdateRequestWithErrorDetection1() {
        SIP2ItemStatusUpdateRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2ItemStatusUpdateRequest("itemId", "itemProperties");
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20091119    195612");
        }
        assertEquals("1920091119    195612AO|ABitemId|CHitemProperties|AY0AZEF98" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testItemStatusUpdateRequestWithErrorDetection2() {
        SIP2ItemStatusUpdateRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2ItemStatusUpdateRequest("institutionId","itemId", "itemProperties");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("1920210814    083455AOinstitutionId|ABitemId|CHitemProperties|AY1AZEA24" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testItemStatusUpdateRequestWithErrorDetection3() {
        SIP2ItemStatusUpdateRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2ItemStatusUpdateRequest("institutionId", "terminalPassword", "itemId", "itemProperties");
            req.setSequence(3);
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("1920210814    083455AOinstitutionId|ABitemId|ACterminalPassword|CHitemProperties|AY3AZE273" + CARRIAGE_RETURN, req.getData());
    }
}
