# Patron Status Request (23) - Patron Status Response (24)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Patron Status Request (23)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
language          |    | language         | X
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
patron identifier | AA | patronIdentifier | X
terminal password | AC | terminalPassword | X
patron password   | AD | patronPassword   | X

## Patron Status Response (24)

SIP2 Field            | ID | JSIP2 variable      | Required
----------------------|----|---------------------|-----------
patron status         |    | patronStatus        | X
language              |    | language            | X
transaction date      |    | transactionDate     | X
institution id        | AO | institutionId       | X
patron identifier     | AA | patronIdentifier    | X
personal name         | AE | personalName        | X
valid patron          | BL | validPatron         |
valid patron password | CQ | validPatronPassword |
currency type         | BH | cunnrecyType        |
fee amount            | BV | feeAmount           |
screen message        | AF | screenMessage       |
print lint            | AF | printLine           |

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;
import com.pkrete.jsip2.messages.requests.SIP2PatronStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2PatronStatusResponse;

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
    String institutionId = "LIBRARY";
    String patronIdentifier = "1234567890";
    SIP2PatronStatusRequest request = new SIP2PatronStatusRequest(institutionId, patronIdentifier);
    SIP2PatronStatusResponse response = (SIP2PatronStatusResponse) connection.send(request);
    .
    .
    .
  } else {
    /* Login failed */
  }
}
```