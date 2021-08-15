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
 * Test cases for SIP2CheckinRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2CheckinRequestTest extends TestCase {

    @Test
    public void testCheckinRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2CheckinRequest req = new SIP2CheckinRequest("location", "terminalPassword", "institutionId","itemId");
            assertEquals("09N20210814    08345520210814    083455APlocation|AOinstitutionId|ABitemId|ACterminalPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testCheckinRequestWithErrorDetection1() {
        SIP2CheckinRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckinRequest("itemId");
            req.setErrorDetectionEnabled(true);
            req.setNoBlock(true);
        }
        assertEquals("09Y20210814    08345520210814    083455AP|AO|ABitemId|AC|AY0AZF0CF" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testCheckinRequestWithErrorDetection2() {
        SIP2CheckinRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckinRequest("location", "itemId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20210715    124501");
            req.setReturnDate("20210810    180136");
            req.setItemProperties("itemProperties");
        }
        assertEquals("09N20210715    12450120210810    180136APlocation|AO|ABitemId|AC|CHitemProperties|AY1AZE6B3" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testCheckinRequestWithErrorDetection3() {
        SIP2CheckinRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckinRequest("location", "institutionId","itemId");
            req.setErrorDetectionEnabled(true);
            req.setSequence(3);
            req.setReturnDate("20210815    082755");
            req.setUseCancel(true);
        }
        assertEquals("09N20210814    08345520210815    082755APlocation|AOinstitutionId|ABitemId|AC|BIN|AY3AZE6AF" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testCheckinRequestWithErrorDetection4() {
        SIP2CheckinRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2CheckinRequest("location", "terminalPassword", "institutionId","itemId");
            req.setErrorDetectionEnabled(true);
            req.setNoBlock(true);
            req.setTransactionDate("20200103    192346");
            req.setCancel(true);
            req.setUseCancel(true);
        }
        assertEquals("09Y20200103    19234620210814    083455APlocation|AOinstitutionId|ABitemId|ACterminalPassword|BIY|AY0AZDFFA" + CARRIAGE_RETURN, req.getData());
    }
}
