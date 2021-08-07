/*
 * The MIT License
 *
 * Copyright 2012- Petteri Kivimäki
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.pkrete.jsip2.variables;

/**
 * This enum defines a set of circulation statuses that can be used in SIP2
 * request and response messages. Each status has a code that is
 * used in the communication between the system and the ILS.
 * 
 * @author Petteri Kivimäki
 */
public enum CirculationStatus {
    INVALID_OR_UNKNOWN_ITEM_ID("01"), ON_ORDER("02"), AVAILABLE("03"), 
    CHARGED("04"), CHARGED_NOT_TO_BE_RECALLED_UNTIL_EARLIEST_RECALL_DATE("05"), 
    IN_PROCESS("06"), RECALLED("07"), WAITING_ON_HOLD_SHELF("08"), 
    WAITING_TO_BE_RESHELVED("09"),IN_TRANSIT("10"), CLAIMED_RETURNED("11"), 
    LOST("12"), MISSING("13");
    
    private String value;
    
    private CirculationStatus(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
