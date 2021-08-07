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

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;

/**
 * This class generates CirculationStatus objects based on the circulation 
 * status code. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class CirculationStatusFactory {

    /**
     * Reference to the singleton object.
     */
    private static CirculationStatusFactory ref;

    /**
     * Constructs and initializes a new CirculationStatusFactory object.
     */
    private CirculationStatusFactory() {
    }

    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static CirculationStatusFactory getInstance() {
        if (ref == null) {
            ref = new CirculationStatusFactory();
        }
        return ref;
    }

    /**
     * Returns a circulation status matching the given circulation status code.
     * @param code circulation status code
     * @return circulation status with the given circulation status code
     * @throws InvalidSIP2ResponseValueException 
     */
    public CirculationStatus getCirculationStatus(String code) throws InvalidSIP2ResponseValueException {
        if (code.equals("01")) {
            return CirculationStatus.INVALID_OR_UNKNOWN_ITEM_ID;
        } else if (code.equals("02")) {
            return CirculationStatus.ON_ORDER;
        } else if (code.equals("03")) {
            return CirculationStatus.AVAILABLE;
        } else if (code.equals("04")) {
            return CirculationStatus.CHARGED;
        } else if (code.equals("05")) {
            return CirculationStatus.CHARGED_NOT_TO_BE_RECALLED_UNTIL_EARLIEST_RECALL_DATE;
        } else if (code.equals("06")) {
            return CirculationStatus.IN_PROCESS;
        } else if (code.equals("07")) {
            return CirculationStatus.RECALLED;
        } else if (code.equals("08")) {
            return CirculationStatus.WAITING_ON_HOLD_SHELF;
        } else if (code.equals("09")) {
            return CirculationStatus.WAITING_TO_BE_RESHELVED;
        } else if (code.equals("10")) {
            return CirculationStatus.IN_TRANSIT;
        } else if (code.equals("11")) {
            return CirculationStatus.CLAIMED_RETURNED;
        } else if (code.equals("12")) {
            return CirculationStatus.LOST;
        } else if (code.equals("13")) {
            return CirculationStatus.MISSING;
        } else {
            throw new InvalidSIP2ResponseValueException("Invalid circulation status code! The given code \"" + code + "\" doesn't match with any circulation status!");
        }
    }
}
