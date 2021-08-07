# SCStatus Request (99) - ASCStatus Response (98)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## SCStatus Request (99)

| SIP2 Field | ID | JSIP2 variable | Required |
|------------|----|----------------|----------|
| status code | | statusCode | X |
| max print width | | maxPrintWidth | X |
| protocol version | | protocolVersion | X |

## ACSStatus Response (98)

| SIP2 Field | ID | JSIP2 variable | Required |
|------------|----|----------------|----------|
| on-line status | | onLineStatus | X |
| checkin ok | | checkinOk | X |
| checkout ok | | checkoutOk | X |
| ACS renewal policy | | ILSRenewalPolicy | X |
| status update ok | | statusUpdateOk | X |
| off-line ok | | offlineOk | X |
| timeout period | | timeoutPeriod | X |
| retries allowed | | retriesAllowed | X |
| date / time syncs | | dateTimeSync | X |
| protocol version | | protocolVersion | X |
| institution id | AO | institutionId | X |
| library name | AM | libraryName | |
| supported messages | BX | supportedMessages | X |
| terminal location | AN | terminalLocation | |	
| screen message | AF | screenMessage | |
| print line | AG | printLine | |

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;
import com.pkrete.jsip2.messages.requests.SIP2SCStatusRequest;
import com.pkrete.jsip2.messages.responses.SIP2ACSStatusResponse;

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
    SIP2ACSStatusResponse response = (SIP2ACSStatusResponse) connection.send(status);
    .
    .
    .
  } else {
    /* Login failed */
  }
}
```