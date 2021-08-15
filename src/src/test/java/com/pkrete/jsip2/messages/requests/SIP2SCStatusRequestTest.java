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

import com.pkrete.jsip2.variables.StatusCode;
import junit.framework.TestCase;
import org.junit.Test;

import static com.pkrete.jsip2.util.TestUtils.CARRIAGE_RETURN;

/**
 * Test cases for SIP2SCStatusRequest class.
 *
 * @author Petteri Kivimäki
 */
public class SIP2SCStatusRequestTest extends TestCase {

    @Test
    public void testSIP2SCStatusRequestWithoutErrorDetection() {
        SIP2SCStatusRequest req = new SIP2SCStatusRequest();
        assertEquals("9900002.00|" + CARRIAGE_RETURN, req.getData());

        req = new SIP2SCStatusRequest(StatusCode.PRINTER_OUT_OF_PAPER, "100");
        assertEquals("9911002.00|" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testSIP2SCStatusRequestWithErrorDetection1() {
        SIP2SCStatusRequest req = new SIP2SCStatusRequest();
        req.setErrorDetectionEnabled(true);
        assertEquals("9900002.00|AY0AZFC2D" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testSIP2SCStatusRequestWithErrorDetection2() {
        SIP2SCStatusRequest req = new SIP2SCStatusRequest(StatusCode.PRINTER_OUT_OF_PAPER);
        req.setErrorDetectionEnabled(true);
        assertEquals("9910002.00|AY0AZFC2C" + CARRIAGE_RETURN, req.getData());
    }

    @Test
    public void testSIP2SCStatusRequestWithErrorDetection3() {
        SIP2SCStatusRequest req = new SIP2SCStatusRequest(StatusCode.SHUT_DOWN, "010");
        req.setSequence(1);
        req.setErrorDetectionEnabled(true);
        assertEquals("9920102.00|AY1AZFC29" + CARRIAGE_RETURN, req.getData());
    }
}
