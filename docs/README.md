# Introduction

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## About JSIP2

3M Standard Interchange Protocol (SIP) is an industry standard protocol by 3M to allow automate check out terminals communicate with library systems (ILS).

JSIP2 is an Open Source (MIT) library that implements the 3M SIP version 2 protocol including the 3M SIP2 Extensions for SIP2 clients. The library contains the necessary functionality for SIP2 client socket based implementation supporting all the SIP2 messages and fields, including also the fields defined in the the 3M SIP2 Extensions.

The SIP2 messages are listed below.

- Login (93) - Login response (94) ([info](93_login.md))
- SCStatus Request(99) - ACS Status Response (98) ([info](99_scstatus_request.md))
- Patron Status Request (23) - Patron Status Response (24) ([info](23_patron_status_request.md))
- Patron Information Request (63) - Patron Information Response (64) ([info](63_patron_information_request.md))
- Checkout Request (11) - Checout Response (12) ([info](11_checkout_request.md))
- Checkin Request (09) - Checkin Response (10) ([info](09_checkin_request.md))
- Block Patron Request (01) - Patron Status Response (24) ([info](01_block_patron_request.md))
- End Patron Session (35) - End Session Response (36) ([info](35_end_patron_session.md))
- Fee Paid Request (37) - Fee Paid Response (38) ([info](37_fee_paid_request.md))
- Item Information Request (17) - Item Information Response (18) ([info](17_item_information_request.md))
- Item Status Update Request (19) - Item Status Update Response (20) ([info](19_item_status_update_request.md))
- Patron Enable Request (25) - Patron Enable Response (26) ([info](25_patron_enable_request.md))
- Hold Request (15) - Hold Response (16) ([info](15_hold_request.md))
- Renew Request (29) - Renew Response (30) ([info](29_renew_request.md))
- Renew All Request (65) - Renew All Response (66) ([info](65_renew_all_request.md))
- Request resend (97) ([info](97_request_resend.md))

In addition to the official SIP2 messages listed above, each ILS may have their own extensions that are not included in the official documentation, but are described in the documentation of the ILS in question only. By default JSIP2 does not include these messages.

## About SIP2

All communication between the system using the JSIP2 library and the ILS is initiated by the system. The ILS only responds to the messages sent by the system, it does not initiate messages. For each message sent from the system, there is a required response message from the ILS.

Each request/response pair should stand on its own, irrespective of any previous or future request/response pair. However, there are some exceptions to this rule, because usually it's not possible to complete any patron or item related actions without authentication and patron validation.

Authentication happens by sending the Login Request message containing a valid username and password. Some additional information, like circulation location for example, may also be required, as the parameters needed in the Login Request message vary between different library systems.

After a succesful login, the SC Status request should be sent to the library system, that responds with the ACS Status message, which contains information about the operating parameters and supported messages. After receiving the ACS Status response the system is ready for normal operation.

Validating the patron happens by sending the Patron Status Request or the Patron Information Request to the ILS, which response contains the information about the validity of the patron. This information should be checked programmatically before any circulation transactions take place.

Please see the complete [SIP2 Developers Guide by 3M](https://usermanual.wiki/Document/sip2developersguide.980251042) for further information.

## Good to Know

### Error Detection and Sequence Number

When error detection is enabled, a sequence number field, followed by a checksum number field, is appended to every message. By default, error detection is `disabled` and it must be enabled for each request message separately.

By default, the sequence number is zero. When error detection is `enabled`, the client application is responsible for maintaining the sequence number and setting it for each message separately.

## Example

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.messages.responses.SIP2LoginResponse;

/* Connect to the ILS SIP server */
SIP2SocketConnection connection = new SIP2SocketConnection("mydomain.com", 12345);

/* If connection fails, exit program */
if (!connection.connect()) {
  System.exit(0);
}

/* Login to the ILS */
/* Create a login request */
SIP2LoginRequest login = new SIP2LoginRequest("userName", "password", "circulationLocation");

/* Send the request */
SIP2LoginResponse loginResponse = (SIP2LoginResponse) connection.send(login);

/* Check the response and exit, if it failed */
if (!loginResponse.isOk()) {
  System.exit(0);
}

/* Get the status of the library system's SIP server */
SIP2ACSStatusResponse statusResponse = (SIP2ACSStatusResponse) connection.send(new SIP2SCStatusRequest());

/* Check the status and start processing... */
.
.
.
/* ...processing done... */

/* Close connection */
connection.close();
```