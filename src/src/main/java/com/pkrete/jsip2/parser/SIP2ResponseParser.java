/*
 *  The MIT License
 * 
 *  Copyright 2012- Petteri Kivimäki
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
package com.pkrete.jsip2.parser;

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseException;
import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is an abstract base class for all the response message
 * parser classes, which task is to parse the response messages
 * received from the ILS SIP server and create the corresponding
 * objects.
 * 
 * @author Petteri Kivimäki
 */
public abstract class SIP2ResponseParser {

    /**
     * Parses a SIP2MessageResponse from the given string.
     * @param data message response data
     * @return SIP2MessageResponse object parsed from the data
     * @throws InvalidSIP2ResponseValueException
     * @throws InvalidSIP2ResponseException 
     */
    public abstract SIP2MessageResponse parse(String data) throws InvalidSIP2ResponseValueException, InvalidSIP2ResponseException;

    /**
     * Converts the given character to a boolean value. Character
     * must be 0 or 1, 0 = false, 1 = true. Otherwise an exception is
     * thrown.
     * @param character char to be converted
     * @return boolean value
     * @throws InvalidSIP2ResponseValueException 
     */
    protected boolean intToBool(char character) throws InvalidSIP2ResponseValueException {
        if (character == '0') {
            return false;
        } else if (character == '1') {
            return true;
        } else {
            throw new InvalidSIP2ResponseValueException("Response message contains an invalid value. Allowed values are: 0 and 1.");
        }
    }

    /**
     * Converts the given character to a boolean value. Character
     * must be Y or N. Otherwise an exception is
     * thrown.
     * @param character char to be converted
     * @return boolean value
     * @throws InvalidSIP2ResponseValueException 
     */
    protected boolean charToBool(char character) throws InvalidSIP2ResponseValueException {
        if (character == 'N') {
            return false;
        } else if (character == 'Y') {
            return true;
        } else {
            throw new InvalidSIP2ResponseValueException("Response message contains an invalid value. Allowed values are: Y and N.");
        }
    }

    /**
     * Converts the given string to an integer.
     * @param value string to be converted
     * @return integer value
     * @throws InvalidSIP2ResponseValueException 
     */
    protected int stringToInt(String value) throws InvalidSIP2ResponseValueException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new InvalidSIP2ResponseValueException("Response message contains an invalid value. Unable to parse an integer from the given string: \"" + value + "\".");
        }
    }

    /**
     * Converts the given character to a boolean value. Character
     * must be 'Y' or ' '. Otherwise an exception is
     * thrown.
     * @param character char to be converted
     * @return boolean value
     * @throws InvalidSIP2ResponseValueException 
     */
    protected boolean charEmptyToBool(char character) throws InvalidSIP2ResponseValueException {
        if (character == ' ') {
            return false;
        } else if (character == 'Y') {
            return true;
        } else {
            throw new InvalidSIP2ResponseValueException("Response message contains an invalid value. Allowed values are: 'Y' and ' '.");
        }
    }

    /**
     * Parses the value of the given variable from the given data string. A
     * field delimiter must be found before the variable code. Returns
     * an empty string if the variable is required, but it's not present in the 
     * data. If the variable is optional and not present in the data,
     * null is returned instead.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @param required true if the variable is required, false if optional
     * @return variable value, an empty string or null depending if the
     * variable is found and if it's optional or required
     */
    protected String parseVariable(String variable, String data, boolean required) {
        if (required) {
            return parseVariable(variable, data);
        }
        String result = parseVariable(variable, data);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    /**
     * Parses the value of the given variable from the given data string. A
     * field delimiter must be found before the variable code. Returns
     * an empty string if the given variable isn't present in the data.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @return variable value
     */
    protected String parseVariable(String variable, String data) {
        Pattern regex = Pattern.compile("\\|" + variable + "(.*?)\\|");
        Matcher m = regex.matcher(data);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * Parses the value of the given variable from the given data string. Returns
     * an empty string if the variable is required, but it's not present in the 
     * data. If the variable is optional and not present in the data,
     * null is returned instead.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @param required true if the variable is required, false if optional
     * @return variable value, an empty string or null depending if the
     * variable is found and if it's optional or required
     */
    protected String parseVariableWithoutDelimiter(String variable, String data, boolean required) {
        if (required) {
            return parseVariableWithoutDelimiter(variable, data);
        }
        String result = parseVariableWithoutDelimiter(variable, data);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    /**
     * Parses the value of the given variable from the given data string. Returns
     * an empty string if the given variable isn't present in the data.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @return variable value
     */
    protected String parseVariableWithoutDelimiter(String variable, String data) {
        Pattern regex = Pattern.compile(variable + "(.*?)\\|");
        Matcher m = regex.matcher(data);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * Parses the value of the given variable from the given data string. The
     * variable can be repeated one or more times, or it may not be used at
     * all. Returns a list containing all the found values, or an empty
     * list if no mathes were found.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @return list of found variable values
     */
    protected List<String> parseVariableMulti(String variable, String data) {
        List<String> results = new ArrayList<String>();
        Pattern regex = Pattern.compile("\\|(((" + variable + ".*?)\\|)+)");
        Matcher m = regex.matcher(data);
        if (m.find()) {
            String[] arr = m.group(1).split("\\|");
            for (int i = 0; i < arr.length; i++) {
                results.add(arr[i].substring(2));
            }
        }
        return results;
    }

    /**
     * Parses the sequence from the given string.
     * @param data data string
     * @return sequence number or an empty string if the data doesn't contain
     * sequence
     */
    protected String parseSequence(String data) {
        Pattern regex = Pattern.compile("\\|AY(\\d{1})");
        Matcher m = regex.matcher(data);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * Parses the checksum from the given string.
     * @param data data string
     * @return chekcsum or an empty string if the data doesn't contain
     * checksum
     */
    protected String parseChecksum(String data) {
        Pattern regex = Pattern.compile("\\|(AY\\d{1}|)AZ(\\w{4})");
        Matcher m = regex.matcher(data);
        if (m.find()) {
            return m.group(2);
        }
        return "";
    }

    /**
     * Returns true if and only if the given variable is present in
     * the given string.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @return true if the given field is present in the given string,
     * otherwise false
     */
    protected boolean exists(String variable, String data) {
        Pattern regex = Pattern.compile("\\|" + variable + "(.*?)\\|");
        Matcher m = regex.matcher(data);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if and only if the given variable and a value are present in
     * the given string. If only code is present, false is returned.
     * @param variable code of the variable, 2 characters
     * @param data data string
     * @return true if the given variable and value are present in the given string,
     * otherwise false
     */
    protected boolean existsAndNotEmpty(String variable, String data) {
        Pattern regex = Pattern.compile("\\|" + variable + "(.*?)\\|");
        Matcher m = regex.matcher(data);
        if (m.find() && !m.group(1).isEmpty()) {
            return true;
        }
        return false;
    }
}
