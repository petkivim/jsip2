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

import com.pkrete.jsip2.messages.responses.SIP2PatronInformationResponse;
import com.pkrete.jsip2.util.MessageUtil;
import com.pkrete.jsip2.variables.Summary;

/**
 * This class represents the message that is used to request detailedd patron 
 * information from the ILS SIP server, and this class extends
 * the {@link SIP2PatronRequest SIP2PatronRequest} message.
 * The ILS SIP server should respond to this message with the 
 * {@link SIP2PatronInformationResponse SIP2PatronInformationResponse} message,
 * 
 * @author Petteri Kivimäki
 */
public class SIP2PatronInformationRequest extends SIP2PatronRequest {

    /**
     * Defines what detailed and summary information of selected category of 
     * items that are be requested as a part of this request. For example a list
     * of items that the patron has borrowed.
     */
    private Summary summary;
    /**
     * Specifies the number of the first item to be sent in the list defined
     * by the summary. Optional field.
     */
    private String startItem;
    /**
     * Specifies the number of the last item to be sent in the list defined
     * by the summary. Optional field.
     */
    private String endItem;

    /**
     * Constructs and initializes a new PatronInformationRequest object. 
     */
    private SIP2PatronInformationRequest() {
        super("63");
        this.institutionId = "";
        this.patronPassword = "";
        this.terminalPassword = "";
        this.summary = new Summary();
    }

    /**
     * Constructs and initializes a new PatronInformationRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * No summary information is requested by default.
     * @param patronIdentifier an identifying value for the patron
     */
    public SIP2PatronInformationRequest(String patronIdentifier) {
        this();
        this.patronIdentifier = patronIdentifier;
    }

    /**
     * Constructs and initializes a new PatronInformationRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * No summary information is requested by default.
     * @param institutionId library's institution id
     * @param patronIdentifier an identifying value for the patron
     */
    public SIP2PatronInformationRequest(String institutionId, String patronIdentifier) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronIdentifier;
    }

    /**
     * Constructs and initializes a new PatronInformationRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * No summary information is requested by default.
     * @param institutionId library's institution id
     * @param patronIdentifier an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     */
    public SIP2PatronInformationRequest(String institutionId, String patronIdentifier, String patronPassword) {
        this();
        this.institutionId = institutionId;
        this.patronIdentifier = patronIdentifier;
        this.patronPassword = patronPassword;
    }

    /**
     * Constructs and initializes a new PatronInformationRequest message
     * with the given parameters. The language is set to UNKNOWN by default.
     * No summary information is requested by default.
     * @param institutionId library's institution id
     * @param terminalPassword password to login the ILS
     * @param patronIdentifier an identifying value for the patron
     * @param patronPassword password (PIN) of the patron
     */
    public SIP2PatronInformationRequest(String institutionId, String terminalPassword, String patronIdentifier, String patronPassword) {
        this();
        this.institutionId = institutionId;
        this.terminalPassword = terminalPassword;
        this.patronIdentifier = patronIdentifier;
        this.patronPassword = patronPassword;
    }

    /**
     * Returns what detailed and summary information of selected category of 
     * items that are be requested as a part of this request. For example a list
     * of items that the patron has borrowed.
     * @return summary object
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     * Sets what detailed and summary information of selected category of 
     * items that are be requested as a part of this request. For example a list
     * of items that the patron has borrowed.
     * @param summary new value
     */
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    /**
     * Returns the number of the first item to be sent in the list defined
     * by the summary.
     * @return the number of the first item to be sent in the list defined
     * by the summary
     */
    public String getStartItem() {
        return startItem;
    }

    /**
     * Sets the number of the first item to be sent in the list defined
     * by the summary.
     * @param startItem new value, must contain only numbers
     */
    public void setStartItem(String startItem) {
        this.startItem = startItem;
    }

    /**
     * Returns the number of the last item to be sent in the list defined
     * by the summary.
     * @return the number of the last item to be sent in the list defined
     * by the summary
     */
    public String getEndItem() {
        return endItem;
    }

    /**
     * Sets the number of the last item to be sent in the list defined
     * by the summary.
     * @param endItem new value, must contain only numbers
     */
    public void setEndItem(String endItem) {
        this.endItem = endItem;
    }

    @Override
    /**
     * Returns a String presentation of this message, that is sent
     * to the ILS SIP server. The message contains all the variables
     * plus sequence and checksum values when error detection is enabled.
     */
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append(code);
        builder.append(language);
        builder.append(transactionDate);
        builder.append(summary);
        builder.append("AO");
        builder.append(institutionId);
        builder.append("|AA");
        builder.append(patronIdentifier);
        builder.append("|AC");
        builder.append(terminalPassword);
        builder.append("|AD");
        builder.append(patronPassword);
        if (startItem != null) {
            builder.append("|BP");
            builder.append(startItem);
        }
        if (endItem != null) {
            builder.append("|BQ");
            builder.append(endItem);
        }
        builder.append("|");

        if (errorDetectionEnabled) {
            builder.append("AY");
            builder.append(getSequence());
            builder.append("AZ");
            this.checkSum = MessageUtil.computeChecksum(builder.toString());
            builder.append(checkSum);
        }
        return builder.toString() + '\r';
    }
}
