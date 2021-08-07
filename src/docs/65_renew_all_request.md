# Renew All Request (65) - Renew All Response (66)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Renew All Request (65)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
patron identifier   | AA | patronIdentifier   | X
patron password | AD | patronPassword |
terminal password | AC | terminalPassword | 
fee acknowledged | BO | feeAcknowledged |

## Renew All Response (66)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
ok | | ok | X
renewed count | | renewedCount | X
unrenewed count | | unrenewedCount | X
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
renewed items | BM | renewedItems |
unrenewed items | BN | unrenewedItems |
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
import com.pkrete.jsip2.messages.requests.SIP2RenewAllRequest;
import com.pkrete.jsip2.messages.responses.SIP2RenewAllResponse;

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
    /* The patron must be validated before the renewal */
    String institutionId = "LIBRARY";
    String patronIdentifier = "1234567890";
    String patronPassword = "12345";
    SIP2PatronInformationRequest request = new SIP2PatronInformationRequest(institutionId, patronIdentifier, patronPassword);
    SIP2PatronInformationResponse response = (SIP2PatronInformationResponse) connection.send(request);

    /* Check if the patron and patron password are valid */
    if(response.isValidPatron() && response.isValidPatronPassword()) {
      SIP2RenewAllRequest renewRequest = new SIP2RenewAllRequest(institutionId, patronIdentifier);
      SIP2RenewAllResponse renewResponse = (SIP2RenewAllResponse) connection.send(renewRequest);

      /* Check that the renewal was ok */
      if(renewResponse.isOk()) {
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