# Fee Paid Request (37) - Fee Paid Response (38)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Fee Paid Request (37)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
transaction date  |    | transacationDate | X
fee type | | feeType | X
payment type | | paymentType | X
currency type | | currencyType | X
fee amount | BV | feeAmount | X
institution id    | AO | institutionId    | X
patron identifier | AA | patronIdentifier | X
terminal password | AC | terminalPassword | 
patron password | AD | patronPassword |
fee identifier | CG | feeIdentifier |
transaction id | BK | transactionId |

## Fee Paid Response (38)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
payment accepted | | paymentAccepted | X
transaction date        |    | transactionDate       | X
institution id          | AO | institutionId         | X
patron identifier       | AA | patronIdentifier      | X
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
import com.pkrete.jsip2.messages.requests.SIP2FeePaidRequest;
import com.pkrete.jsip2.messages.responses.SIP2FeePaidResponse;

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
    /* The patron must be validated before paying a fee */
    String institutionId = "LIBRARY";
    String patronIdentifier = "1234567890";
    String patronPassword = "12345";
    SIP2PatronInformationRequest request = new SIP2PatronInformationRequest(institutionId, patronIdentifier, patronPassword);
    SIP2PatronInformationResponse response = (SIP2PatronInformationResponse) connection.send(request);

    /* Check if the patron and patron password are valid */
    if(response.isValidPatron() && response.isValidPatronPassword()) {
      String fee = "3,50";
      SIP2FeePaidRequest feeRequest = new SIP2FeePaidRequest(patronIdentifier, fee);
      SIP2FeePaidResponse feeResponse = (SIP2FeePaidResponse) connection.send(feeRequest);

      /* Check that the payment was accepted */
      if(feeResponse.isPaymentAccepted()) {
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