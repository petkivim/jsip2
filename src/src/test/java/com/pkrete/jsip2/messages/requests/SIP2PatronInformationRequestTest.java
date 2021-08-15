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
 * Test cases for SIP2PatronInformationRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2PatronInformationRequestTest extends TestCase {

    @Test
    public void testPatronInformationRequestWithoutErrorDetection() {
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);

            SIP2PatronInformationRequest req = new SIP2PatronInformationRequest("institutionId", "terminalPassword", "patronIdentifier", "patronPassword");
            assertEquals("6300020210814    083455          AOinstitutionId|AApatronIdentifier|ACterminalPassword|ADpatronPassword|" + CARRIAGE_RETURN, req.getData());
        }
    }

    @Test
    public void testPatronInformationRequestWithErrorDetection1() {
        SIP2PatronInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronInformationRequest("patronIdentifier");
            req.setSequence(2);
            req.setErrorDetectionEnabled(true);
        }
        assertEquals("6300020210814    083455          AO|AApatronIdentifier|AC|AD|AY2AZEE73" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testPatronInformationRequestWithErrorDetection2() {
        SIP2PatronInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronInformationRequest("institutionId", "patronIdentifier");
            req.setErrorDetectionEnabled(true);
            req.getSummary().setChargedItems(true);
            req.setTransactionDate("20150416    064702");
        }
        assertEquals("6300020150416    064702  Y       AOinstitutionId|AApatronIdentifier|AC|AD|AY0AZE8CA" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testPatronInformationRequestWithErrorDetection3() {
        SIP2PatronInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronInformationRequest("institutionId", "patronIdentifier", "patronPassword");
            req.setSequence(1);
            req.setErrorDetectionEnabled(true);
            req.setLanguage(Language.FINNISH);
        }
        assertEquals("6300720210814    083455          AOinstitutionId|AApatronIdentifier|AC|ADpatronPassword|AY1AZE30F" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testPatronInformationRequestWithErrorDetection4() {
        SIP2PatronInformationRequest req = null;
        try (MockedStatic<MessageUtil> mocked = Mockito.mockStatic(MessageUtil.class)) {
            mocked.when(MessageUtil::getSipDateTime).thenReturn(SIP2_DATE_TIME);
            req = new SIP2PatronInformationRequest("institutionId", "terminalPassword", "patronIdentifier", "patronPassword");
            req.setErrorDetectionEnabled(true);
            req.setStartItem("2");
            req.setEndItem("5");
        }
        assertEquals("6300020210814    083455          AOinstitutionId|AApatronIdentifier|ACterminalPassword|ADpatronPassword|BP2|BQ5|AY0AZD9E4" + CARRIAGE_RETURN, req.getData());
    }
}
