# Item Information Request (17) - Item Information Response (18)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Item Information Request (17)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
item identifier   | AB | itemIdentifier   | X
terminal password | AC | terminalPassword | 

## Item Information Response (18)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
circulation status | | circulationStatus | X
security marker | | securityMarker | X
fee type | | feeType | X
transaction date        |    | transactionDate       | X
hold queue length | CF | holdQueueLength |
due date | AH | dueDate |
recall date | CJ | recallDate |
hold pickup date | CM | holdPickupDate |
item identifier   | AB | itemIdentifier   | X
title identifier | AJ | titleIdentifier | X
owner | BG  | owner |
currency type | BH | currencyType |
fee amount | BV | feeAmount |
media type | CK | mediaType |
permanent location | AQ | permanentLocation |
current location | AP | currentLocation |
item properties | CH | itemProperties |
screen message | AF | screenMessage |
print line | AG | printLine |

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;
import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.messages.requests.SIP2ItemInformationRequest;
import com.pkrete.jsip2.messages.responses.SIP2ItemInformationResponse;

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
    String itemIdentifier = "1234567";
    SIP2ItemInformationRequest request = new SIP2ItemInformationRequest(itemIdentifier);
    SIP2ItemInformationResponse response = (SIP2ItemInformationResponse) connection.send(request);
    .
    .
    .
  } else {
    /* Login failed */
  }
}
```