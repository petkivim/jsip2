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

package com.pkrete.jsip2.connection;

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseException;
import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import com.pkrete.jsip2.messages.SIP2MessageRequest;
import com.pkrete.jsip2.messages.SIP2MessageResponse;
import com.pkrete.jsip2.messages.SIP2ResponseFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible of the communication between the system
 * and the ILS SIP server. The communication is based on a socket 
 * connection, which means that the data is sent and received by using
 * a socket.
 * 
 * The connection between the system and the ILS is stateless, and its
 * based on message/reponse pairs. Each pair should stand on its own,
 * irrespoective of any previous or future pair. However, in practise there
 * are depencies between the message pairs. For example, in most cases the system
 * must first log in to the ILS SIP server before sending any other messages.
 *  
 * @author Petteri Kivimäki
 */
public class SIP2SocketConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(SIP2SocketConnection.class);

    /**
     * The address of the ILS SIP server.
     */ 
    private String host;
    /**
     * Port number of the ILS SIP server.
     */ 
    private int port;
    /**
     * Timeout in milliseconds.
     */ 
    private int timeout;
    private Socket socket = null;
    private BufferedWriter out = null;
    private BufferedReader in = null;

    /**
     * Constructs and initializes a new SIP2SocketConnection object with
     * the given host and port. The default timeout is 10 seconds.
     * @param host address of the ILS SIP server
     * @param port port number of the ILS SIP server
     */
    public SIP2SocketConnection(String host, int port) {
        this(host, port, 10000);
    }
    
    /**
     * Constructs and initializes a new SIP2SocketConnection object with
     * the given host, port and timeout. A timeout of zero is interpreted 
     * as an infinite timeout.
     * @param host address of the ILS SIP server
     * @param port port number of the ILS SIP server
     * @param timeout timeout value in milliseconds
     */
    public SIP2SocketConnection(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    /**
     * Connects to the ILS SIP server. The connection blocks until established 
     * or an error occurs. 
     * @return true if the socket successfuly connected to a server
     */
    public boolean connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } finally {
            return socket.isConnected();
        }      
    }

    /**
     * Closes the connection to the ILS SIP server.
     * @return true if the socket has been closed
     */
    public boolean close() {
        try {
            in.close();
            out.close();
            socket.close();
        } finally {
            return socket.isClosed();
        }     
    }

    /**
     * Sends the given string to the ILS SIP server.
     * @param data string to be sent
     * @return true if the operation succeeded
     */
    public boolean write(String data) {
        try {
            out.write(data);
            out.flush();
        } catch (java.io.IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return false;
        }
        return true;
    }

    /**
     * Read a line of text. A line is considered to be terminated by 
     * any one of a line feed ('\n'), a carriage return ('\r'), or a 
     * carriage return followed immediately by a linefeed. The connection
     * blocks until there's data available in the socket.
     * @return A String containing the contents of the line, not including 
     * any line-termination characters, or null if the end of the stream 
     * has been reached 
     */
    public String read() {
        try {
            return in.readLine();
        } catch (java.io.IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }
    /**
     * Sends the given SIP2MessageRequest to the ILS SIP server and returns
     * the SIP2MessageResponse received. The connection blocks until 
     * the response is received or an error occurs. 
     * @param request SIP2MessageRequest to be sent
     * @return SIP2MessageResponse received from the ILS SIP server
     * @throws InvalidSIP2ResponseException
     * @throws InvalidSIP2ResponseValueException 
     */
    public SIP2MessageResponse send(SIP2MessageRequest request) throws InvalidSIP2ResponseException, InvalidSIP2ResponseValueException {
        String data = request.getData();
        LOGGER.debug("Request: {}", data);
        if(write(data)) {
            String response = read();
            return SIP2ResponseFactory.getInstance().create(response);
        }
        return null;
    }
    
    /**
     * Returns true if and only if the socket is connected to a server.
     * @return true if the socket is connected to a server, otherwise false
     */
    public boolean connected() {
        return this.socket.isConnected();
    }
    
    /**
     * Returns the socket used for the connection with the ILS SIP server.
     * @return socket used for the connection with the ILS SIP server
     */
    public Socket getSocket() {
        return this.socket;
    }
    
    /**
     * Sets the used for the connection with the ILS SIP server.
     * @param socket new socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
