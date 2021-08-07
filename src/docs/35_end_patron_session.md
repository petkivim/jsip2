# End Patron Session Request (35) - End Session Response (36)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## End Patron Session Request (35)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
patron identifier | AA | patronIdentifier | X
terminal password | AC | terminalPassword |
patron password   | AD | patronPassword   |

## End Session Response (36)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
end session | | endSession | X
transaction date        |    | transactionDate       | X
institution id          | AO | institutionId         | X
patron identifier       | AA | patronIdentifier      | X
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
import com.pkrete.jsip2.messages.requests.SIP2EndPatronSessionRequest;
import com.pkrete.jsip2.messages.responses.SIP2EndPatronSessionResponse;

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
    /* Validate patron - starts a patron session */
    String institutionId = "LIBRARY";
    String patronIdentifier = "1234567890";
    String patronPassword = "12345";
    SIP2PatronInformationRequest request = new SIP2PatronInformationRequest(institutionId, patronIdentifier, patronPassword);
    SIP2PatronInformationResponse response = (SIP2PatronInformationResponse) connection.send(request);
    .
    .
    .
    SIP2EndPatronSessionRequest end = new SIP2EndPatronSessionRequest(institutionId, patronIdentifier);   
    SIP2EndSessionResponse endResponse = (SIP2EndSessionResponse) connection.send(end);

    /* Check that the session is closed */
    if(!endResponse.isEndSession()) {
      /* Patron's session was not ended, error condition */
      .
      .
      .
    }
  } else {
    /* Login failed */
  }
}
```