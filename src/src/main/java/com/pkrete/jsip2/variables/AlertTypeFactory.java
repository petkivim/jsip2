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
 * This class generates AlertType objects based on the alert code. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class AlertTypeFactory {

    /**
     * Reference to the singleton object.
     */
    private static AlertTypeFactory ref;

    /**
     * Constructs and initializes a new AlertTypeFactory object.
     */
    private AlertTypeFactory() {
    }

    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static AlertTypeFactory getInstance() {
        if (ref == null) {
            ref = new AlertTypeFactory();
        }
        return ref;
    }

    /**
     * Returns the alert type that matches the given code.
     * @param code alert type code
     * @return alert type that matches the given code
     * @throws InvalidSIP2ResponseValueException
     */
    public AlertType getAlertType(String code) throws InvalidSIP2ResponseValueException {
        if (code.equals("00")) {
            return AlertType.UNKNONW;
        } else if (code.equals("01")) {
            return AlertType.HOLD_FOR_THIS_LIBRARY;
        } else if (code.equals("02")) {
            return AlertType.HOLD_FOR_OTHER_BRANCH;
        } else if (code.equals("03")) {
            return AlertType.HOLD_FOR_ILL;
        } else if (code.equals("04")) {
            return AlertType.SENT_TO_OTHER_BRANCH;
        } else if (code.equals("99")) {
            return AlertType.OTHER;
        } else {
            throw new InvalidSIP2ResponseValueException("Invalid alert type code! The given code \"" + code + "\" doesn't match with any alert type!");
        }
    }
}
