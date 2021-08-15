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

import junit.framework.TestCase;
import org.junit.Test;

import static com.pkrete.jsip2.util.TestUtils.CARRIAGE_RETURN;

/**
 * Test cases for SIP2LoginRequestTest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2LoginRequestTest extends TestCase {

    @Test
    public void testLoginRequestWithoutErrorDetection() {
        SIP2LoginRequest req = new SIP2LoginRequest("user", "passwd", "location");
        assertEquals("9300CNuser|COpasswd|CPlocation|" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testLoginRequestWithErrorDetection1() {
        SIP2LoginRequest req = new SIP2LoginRequest("user", "passwd", "location");
        req.setErrorDetectionEnabled(true);
        assertEquals("9300CNuser|COpasswd|CPlocation|AY0AZF2FB" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testLoginRequestWithErrorDetection2() {
        SIP2LoginRequest req = new SIP2LoginRequest("user", "passwd", "location");
        req.setSequence(1);
        req.setErrorDetectionEnabled(true);
        req.setCirculationLocation("circulationLocation");
        assertEquals("9300CNuser|COpasswd|CPcirculationLocation|AY1AZEE7D" + CARRIAGE_RETURN, req.getData());
    }
}
