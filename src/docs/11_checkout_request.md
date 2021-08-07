# Checkout Request (11) - Checkout Response (12)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Checkout Request (11)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
SC renewal policy |    | scRenewalPolicy  | X
no block          |    | noBlock          | X
transaction date  |    | transacationDate | X
nb due date       |    | nbDueDate        | X
institution id    | AO | institutionId    | X
patron identifier | AA | patronIdentifier | X
item identifier   | AB | itemIdentifier   | X
terminal password | AC | terminalPassword | X
item properties   | CH | itemProperties   |
patron password   | AD | patronPassword   |
fee acknowledged  | BO | feeAcknowledged  |
cancel            | BI | cancel           |

## Checkout Response (12)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
ok | | ok | X
renewal ok | | renewalOk | X
magnetic media | | magneticMedia | X
desensitize | | desensitize | X
transaction date        |    | transactionDate       | X
institution id          | AO | institutionId         | X
patron identifier       | AA | patronIdentifier      | X
item identifier   | AB | itemIdentifier   | X
title identifier | AJ | titleIdentifier | X
due date | AH | dueDate | X
fee type | BT | feeType |
security inhibit | CI | securityInhibit |
currency type | BH | currencyType |
fee amount | BV | feeAmount |
media type | CK | mediaType |
item properties | CH | itemProperties |
transaction id | BK | transactionId |
screen message | AF | screenMessage |
print line | AG | printLine |

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;
import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;
import com.pkrete.jsip2.messages.requests.SIP2PatronInformationRequest;
import com.pkrete.jsip2.messages.responses.SIP2PatronInformationResponse;
import com.pkrete.jsip2.messages.requests.SIP2CheckoutRequest;
import com.pkrete.jsip2.messages.responses.SIP2CheckoutResponse;

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
    /* The patron must be validated before checking out an item */
    String institutionId = "LIBRARY";
    String patronIdentifier = "1234567890";
    String patronPassword = "12345";
    SIP2PatronInformationRequest request = new SIP2PatronInformationRequest(institutionId, patronIdentifier, patronPassword);
    SIP2PatronInformationResponse response = (SIP2PatronInformationResponse) connection.send(request);

    /* Check if the patron and patron password are valid */
    if(response.isValidPatron() && response.isValidPatronPassword()) {
      String itemIdentifier = "1234567";
      SIP2CheckoutRequest co = new SIP2CheckoutRequest(patronIdentifier, itemIdentifier);
      SIP2CheckoutResponse coResponse = (SIP2CheckoutResponse) connection.send(co);

      /* Check if the item was succesfully charged to the patron */
      if(coResponse.isOk()) {
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