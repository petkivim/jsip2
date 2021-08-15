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
 * Test cases for SIP2BlockPatronRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2BlockPatronRequestTest extends TestCase {

    @Test
    public void testBlockPatronRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2BlockPatronRequest req = new SIP2BlockPatronRequest("institutionId", "terminalPassword", "patronId","message");
            assertEquals("01N20210814    083455AOinstitutionId|ALmessage|AApatronId|ACterminalPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testBlockPatronRequestWithErrorDetection1() {
        SIP2BlockPatronRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2BlockPatronRequest("patronId");
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("01N20210814    083455AO|AL|AApatronId|AC|AY0AZF34D" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testBlockPatronRequestWithErrorDetection2() {
        SIP2BlockPatronRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2BlockPatronRequest("institutionId", "patronId");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20210715    124501");
            req.setCardRetained(true);
        }
        assertEquals("01Y20210715    124501AOinstitutionId|AL|AApatronId|AC|AY1AZEDD6" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testBlockPatronRequestWithErrorDetection3() {
        SIP2BlockPatronRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2BlockPatronRequest("institutionId", "patronId","message");
            req.setErrorDetectionEnabled(true);
            req.setSequence(3);
        }
        assertEquals("01N20210814    083455AOinstitutionId|ALmessage|AApatronId|AC|AY3AZEAEE" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testBlockPatronRequestWithErrorDetection4() {
        SIP2BlockPatronRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2BlockPatronRequest("institutionId", "terminalPassword", "patronId","message");
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20200103    192346");
        }
        assertEquals("01N20200103    192346AOinstitutionId|ALmessage|AApatronId|ACterminalPassword|AY0AZE44C" + CARRIAGE_RETURN, req.getData());
    }
}
