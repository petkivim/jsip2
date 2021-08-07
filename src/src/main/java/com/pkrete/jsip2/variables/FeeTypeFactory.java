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
 * This class generates FeeType objects based on the fee code. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class FeeTypeFactory {

    /**
     * Reference to the singleton object.
     */
    private static FeeTypeFactory ref;

    /**
     * Constructs and initializes a new FeeTypeFactory object.
     */
    private FeeTypeFactory() {
    }

    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static FeeTypeFactory getInstance() {
        if (ref == null) {
            ref = new FeeTypeFactory();
        }
        return ref;
    }

    /**
     * Returns the fee type that matches the given code.
     * @param code fee type code
     * @return fee type that matches the given code
     * @throws InvalidSIP2ResponseValueException
     */
    public FeeType getFeeType(String code) throws InvalidSIP2ResponseValueException {
        if (code.equals("01")) {
            return FeeType.OTHER_UNKNONW;
        } else if (code.equals("02")) {
            return FeeType.ADMINISTRATIVE;
        } else if (code.equals("03")) {
            return FeeType.DAMAGE;
        } else if (code.equals("04")) {
            return FeeType.OVERDUE;
        } else if (code.equals("05")) {
            return FeeType.PROCESSING;
        } else if (code.equals("06")) {
            return FeeType.RENTAL;
        } else if (code.equals("07")) {
            return FeeType.REPLACEMENT;
        } else if (code.equals("08")) {
            return FeeType.COMPUTER_ACCESS_CHARGE;
        } else if (code.equals("09")) {
            return FeeType.HOLD_FEE;
        } else {
            throw new InvalidSIP2ResponseValueException("Invalid fee type code! The given code \"" + code + "\" doesn't match with any fee type!");
        }
    }
}
