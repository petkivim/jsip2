# Hold Request (15) - Hold Response (16)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Hold Request (15)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
hold mode | | holdMode | X
transaction date  |    | transacationDate | X
expiration date | BW | expirationDate |
pickup location | BS | pickupLocation |
hold type | BY | holdType | 
institution id    | AO | institutionId    | X
patron identifier       | AA | patronIdentifier      | X
patron password | AD | patronPassword |
item identifier   | AB | itemIdentifier   | 
title identifier | AJ | titleIdentifier | 
terminal password | AC | terminalPassword | 
fee acknowledged | BO | feeAcknowledged |
\*\*bib id | MA | bibId |

<b>\*\* = Voyager ESIP extension.</b>

## Hold Response (16)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
ok | | ok | X
available | | available | X
transaction date        |    | transactionDate       | X
expiration date | BW | expirationDate |
queue position | BR | queuePosition |
pickup location | BS | pickupLocation |
institution id          | AO | institutionId         | X
patron identifier       | AA | patronIdentifier      | X
item identifier   | AB | itemIdentifier   |
title identifier | AJ | titleIdentifier |
\*\*bib id | MA | bibId |
\*\*ISBN | MB | isbn |
\*\*LCCN | MC | lccb |
screen message | AF | screenMessage |
print line | AG | printLine |

<b>\*\* = Voyager ESIP extension.</b>

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;
import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.messages.requests.SIP2PatronInformationRequest;
import com.pkrete.jsip2.messages.responses.SIP2PatronInformationResponse;
import com.pkrete.jsip2.messages.requests.SIP2HoldRequest;
import com.pkrete.jsip2.messages.responses.SIP2HoldResponse;

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
    /* The patron must be validated before placing a hold */
    String institutionId = "LIBRARY";
    String patronIdentifier = "1234567890";
    String patronPassword = "12345";
    SIP2PatronInformationRequest request = new SIP2PatronInformationRequest(institutionId, patronIdentifier, patronPassword);
    SIP2PatronInformationResponse response = (SIP2PatronInformationResponse) connection.send(request);

    /* Check if the patron and patron password are valid */
    if(response.isValidPatron() && response.isValidPatronPassword()) {
      String itemIdentifier = "1234567";
      SIP2HoldRequest holdRequest = new SIP2HoldRequest(patronIdentifier, itemIdentifier);
      SIP2HoldResponse holdResponse = (SIP2HoldResponse) connection.send(holdRequest);

      /* Check that the hold was placed succesfully */
      if(holdResponse.isOk()) {
        .
        .
        .
      }
    }
  } else {
    /* Login failed */
  }
}
```