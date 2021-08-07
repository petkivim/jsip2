# Checkin Request (09) - Checkin Response (10)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Checkin Request (09)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
no block          |    | noBlock          | X
transaction date  |    | transacationDate | X
return date       |    | returnDate        | X
current location  | AP | currentLocation  | X
institution id    | AO | institutionId    | X
item identifier   | AB | itemIdentifier   | X
terminal password | AC | terminalPassword | X
item properties   | CH | itemProperties   |
cancel            | BI | cancel           |

## Checkin Response (10)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
ok | | ok | X
resensitize | | resensitize | X
magnetic media | | magneticMedia | X
alert | | alert | X
transaction date        |    | transactionDate       | X
institution id          | AO | institutionId         | X
item identifier   | AB | itemIdentifier   | X
permanent location | AQ | permanentLocation | X
title identifier | AJ | titleIdentifier | X
sort bin | CL | sortBin |
patron identifier       | AA | patronIdentifier      |
media type | CK | mediaType |
item properties | CH | itemProperties |
*collection code | CR | collectionCode |
*call number | CS | callNumber |
*destination location | CT | destinationLocation |
*alert type | CV | alertType |
*hold patron id | CY | holdPatronId |
*hold patron name | DA | holdPatronName |
screen message | AF | screenMessage |
print line | AG | printLine |

<b>\* = Extension field that is not included in the original SIP2 definition.</b>

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;
import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.messages.requests.SIP2CheckinRequest;
import com.pkrete.jsip2.messages.responses.SIP2CheckinResponse;

/* Connect to the ILS SIP server */
SIP2SocketConnection connection = new SIP2SocketConnection("mydomain.com", 12345);

if(connection.connect()) {
  /* Login to the ILS */
  /* Create a login request */
  SIP2LoginRequest login = new SIP2LoginRequest("userName", "password", "circulationLocation");
  /* Send the request */
  SIP2LoginResponse loginResponse = (SIP2LoginResponse)connection.send(login);

  /* Check the response*/
  if(loginResponse.isOk()) {
    /* Send SCStatusRequest */
    SIP2SCStatusRequest status = new SIP2SCStatusRequest();
    SIP2ACSStatusResponse statusResponse = (SIP2ACSStatusResponse) connection.send(status); 
    .
    .
    .
    String institutionId = "LIBRARY";
    String itemIdentifier = "1234567";
    SIP2CheckinRequest request = new SIP2CheckinRequest(institutionId, itemIdentifier);
    SIP2CheckinResponse response = (SIP2CheckinResponse) connection.send(request);
    .
    .
    .

  } else {
    /* Login failed */
  }
}
```