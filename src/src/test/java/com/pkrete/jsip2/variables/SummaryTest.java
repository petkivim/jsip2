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
package com.pkrete.jsip2.variables;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Test cases for Summary class.
 *
 * @author Petteri Kivimäki
 */
public class SummaryTest extends TestCase {

    @Test
    public void testSummaryNone() {
        Summary summary = new Summary();
        assertEquals("          ", summary.toString());
    }

    public void testSummaryHoldItems() {
        Summary summary = new Summary();
        summary.setHoldItems(true);
        assertEquals("Y         ", summary.toString());
    }

    public void testSummaryOverdueItems() {
        Summary summary = new Summary();
        summary.setOverdueItems(true);
        assertEquals(" Y        ", summary.toString());
    }

    public void testSummaryChargedItems() {
        Summary summary = new Summary();
        summary.setChargedItems(true);
        assertEquals("  Y       ", summary.toString());
    }

    public void testSummaryFineItems() {
        Summary summary = new Summary();
        summary.setFineItems(true);
        assertEquals("   Y      ", summary.toString());
    }

    public void testSummaryRecallItems() {
        Summary summary = new Summary();
        summary.setRecallItems(true);
        assertEquals("    Y     ", summary.toString());
    }

    public void testSummaryUnavailableHolds() {
        Summary summary = new Summary();
        summary.setUnavailableHolds(true);
        assertEquals("     Y    ", summary.toString());
    }
}
