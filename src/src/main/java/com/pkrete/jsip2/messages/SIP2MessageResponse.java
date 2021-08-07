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

package com.pkrete.jsip2.messages;

import com.pkrete.jsip2.variables.MediaType;
import java.util.List;

/**
 * This abstract class is a base class of the SIP2 response messages
 * received from the ILS SIP server.
 * 
 * @author Petteri Kivimäki
 */
public abstract class SIP2MessageResponse extends SIP2Message {

    /**
     * Tells that the requested action was allowable and completed succesfully.
     */
    protected boolean ok;
    /**
     * Message data received from the ILS SIP server
     */
    protected String data;
    /**
     * Variable-length field. This field provides a way for the ILS to display
     * messages on the system's screen. They are never required. When used,
     * there can be one or more of those fields, which are then displayed on
     * consecutive lines of the screen.
     */
    protected List<String> screenMessage;
    /**
     * Variable-length field. This field provides a way for the ILS to print
     * messages on the system's printer. They are never required. When used,
     * there can be one or more of these fields, which are then pronted on
     * consecutive lines of the printer.
     */
    protected List<String> printLine;
    /**
     * Due date for the item. The ILS can send this information in any format
     * it wishes.
     */
    protected String dueDate;
    /**
     * The location where the item is normally stored.
     */
    protected String permanentLocation;
    /**
     * Media type of the item.
     */
    protected MediaType mediaType;

    /**
     * Returns the checksum of the message.
     * @return checksum of the message
     */
    public abstract String countChecksum();

    /**
     * Constructs and initializes a new SIP2MessageResponse object.
     * @param code command identifier
     * @param data message data
     */
    protected SIP2MessageResponse(String code, String data) {
        super(code);
        this.data = data;
    }

    /**
     * Returns true if and only if the requested action was allowable 
     * and completed succesfully. Otherwise returns false.
     * @return true if the requested action was allowable 
     * and completed succesfully, otherwise returns false
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Sets the value that tells if the requested action was allowable 
     * and completed succesfully.
     * @param ok new value
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /**
     * Returns the due date for this item. The ILS can send this information 
     * in any format it wishes.
     * @return due date for the item
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for the item.
     * @param dueDate new due date
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the location where the item is normally stored.
     * @return location where the item is normally stored
     */
    public String getPermanentLocation() {
        return permanentLocation;
    }

    /**
     * Changes the location where the item is normally stored.
     * @param permanentLocation new location 
     */
    public void setPermanentLocation(String permanentLocation) {
        this.permanentLocation = permanentLocation;
    }

    /**
     * Returns the media type of the item.
     * @return media type of the item
     */
    public MediaType getMediaType() {
        return mediaType;
    }

    /**
     * Sets the media type of the item
     * @param mediaType new value
     */
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Returns a list of messages received from the ILS to display on the
     * system's screen.
     * @return the screenMessage
     */
    public List<String> getScreenMessage() {
        return screenMessage;
    }

    /**
     * Sets the screenMessage value, that's a list of messages received 
     * from the ILS to display on the system's screen.
     * @param screenMessages list of messages received 
     * from the ILS to display on the system's screen
     */
    public void setScreenMessage(List<String> screenMessages) {
        this.screenMessage = screenMessages;
    }

    /**
     * Returns a list of messages received from the ILS to print on the
     * system's printer.
     * @return the printLine
     */
    public List<String> getPrintLine() {
        return printLine;
    }

    /**
     * Sets the printLine value, that's a list of messages received from the 
     * ILS to print on the system's printer.
     * @param printLines list of messages received from the 
     * ILS to print on the system's printer.
     */
    public void setPrintLine(List<String> printLines) {
        this.printLine = printLines;
    }

    /**
     * Returns the message data received from the ILS SIP server.
     * @return data message data received from the ILS SIP server
     */
    @Override
    public String getData() {
        return data;
    }

    /**
     * Changes the message data.
     * @param data new value
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns true, if and only if, the response message is valid. The 
     * response is validated by comparing the checksum parsed from the 
     * response message and the checksum calculated by the system. If the
     * two checksums match, this reponse is valid. If the response
     * doesn't include a checksum, false is returned, because in this case
     * the validity of the response cannot be guaranteed.
     * @return true if the response is valid, otherwise false
     */
    public boolean isValid() {
        if (!isChecksum()) {
            return false;
        }
        if (!checkSum.equals(countChecksum())) {
            return false;
        }
        return true;
    }
}
