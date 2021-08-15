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
import com.pkrete.jsip2.variables.Language;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static com.pkrete.jsip2.util.TestUtils.CARRIAGE_RETURN;
import static com.pkrete.jsip2.util.TestUtils.SIP2_DATE_TIME;

/**
 * Test cases for SIP2PatronStatusRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2PatronStatusRequestTest extends TestCase {

    @Test
    public void testPatronStatusRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2PatronStatusRequest req = new SIP2PatronStatusRequest("institutionId", "terminalPassword", "patronIdentifier", "patronPassword");
            assertEquals("2300020210814    083455AOinstitutionId|AApatronIdentifier|ACterminalPassword|ADpatronPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testPatronStatusRequestWithErrorDetection1() {
        SIP2PatronStatusRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronStatusRequest("patronIdentifier");
            req.setSequence(2);
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("2300020210814    083455AO|AApatronIdentifier|AC|AD|AY2AZEFB7" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testPatronStatusRequestWithErrorDetection2() {
        SIP2PatronStatusRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronStatusRequest("institutionId", "patronIdentifier");
            req.setErrorDetectionEnabled(true);
            req.setLanguage(Language.ITALIAN);
        }
        assertEquals("2300420210814    083455AOinstitutionId|AApatronIdentifier|AC|AD|AY0AZEA3E" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testPatronStatusRequestWithErrorDetection3() {
        SIP2PatronStatusRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronStatusRequest("institutionId", "patronIdentifier", "patronPassword");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setTransactionDate("20070102    080109");
        }
        assertEquals("2300020070102    080109AOinstitutionId|AApatronIdentifier|AC|ADpatronPassword|AY1AZE467" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testPatronStatusRequestWithErrorDetection4() {
        SIP2PatronStatusRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronStatusRequest("institutionId", "terminalPassword", "patronIdentifier", "patronPassword");
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("2300020210814    083455AOinstitutionId|AApatronIdentifier|ACterminalPassword|ADpatronPassword|AY0AZDDAC" + CARRIAGE_RETURN, req.getData());
    }
}
