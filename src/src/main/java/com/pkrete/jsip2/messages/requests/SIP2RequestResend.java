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
package com.pkrete.jsip2.messages.requests;

import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.util.MessageUtil;

/**
 * This class represent the message that is used to request the ILS to
 * re-transmit its last message. It is sent by the system to the ILS when
 * the checksum in a received message does not match the value calculated
 * by the system. The ILS shoud respond by re-transmitting its last message.
 *
 * This message should never inclide a sequence number field, even when error
 * detection is enabled, but would include a checksum field since checksums are
 * in use.
 * 
 * @author Petteri Kivimäki
 */
public class SIP2RequestResend extends SIP2MessageRequest {

    /**
     * Constructs and initializes a new SIP2RequestResend object.
     */
    public SIP2RequestResend() {
        super("97");
    }

    @Override
    /**
     * Returns a String presentation of this message, that is sent
     * to the ILS SIP server. The message contains all the variables
     * plus a checksum value when error detection is enabled. This message
     * should never include a sequence number field.
     */
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        
        if (errorDetectionEnabled) {
            builder.append("AZ");
            this.checkSum = MessageUtil.computeChecksum(builder.toString());
            builder.append(checkSum);
        }
        return builder.toString() + '\r';
    }
}
