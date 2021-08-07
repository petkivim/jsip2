# Patron Information Request (63) - Patron Information Response (64)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Patron Information Request (63)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
language          |    | language         | X
transaction date  |    | transacationDate | X
summary           |    | summary          | X
institution id    | AO | institutionId    | X
patron identifier | AA | patronIdentifier | X
terminal password | AC | terminalPassword |
patron password   | AD | patronPassword   |
start item        | BP | startItem        |
end item          | BQ | endItem          |

## Patron Information Response (64)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
patron status           |    | patronStatus          | X
language                |    | language              | X
transaction date        |    | transactionDate       | X
hold item count         |    | holdItemsCount        | X
overdue items count     |    | overdueItemsCount     | X
charged items count     |    | chargedItemsCout      | X
fine items count        |    | fineItemsCount        | X
recall items count      |    | recallItemsCount      | X
unavailable holds count |    | unavailableHoldsCount | X
institution id          | AO | institutionId         | X
patron identifier       | AA | patronIdentifier      | X
personal name           | AE | personalName          | X
hold items limit        | BZ | holdItemsLimit        |
overdue items limit     | CA | overdueItemsLimit     |
charged items limit     | CB | chargedItemsLimit     |
valid patron            | BL | validPatron           |
valid patron password   | CQ | validPatronPassword   |
currency type           | BH | cunnrecyType          |
fee amount              | BV | feeAmount             |
hold items              | AS | items, itemType       |
overdue items           | AT | items, itemType       |
charged items           | AU | items, itemType       |
fine items              | AV | items, itemType       |
recall items            | BU | items, itemType       |
unavailable hold items  | CD | items, itemType       |
home address            | BD | homeAddress           |
e-mail address          | BE | email                 |
home phone number       | BF | phone                 |
<b>*</b>birth date      | PB | birthDate             |
<b>*</b>PAC access type | PA | pacAccessType         |
<b>*</b>patron type     | ZY | patronType            |
<b>**</b>patron group    | PT | patronGroup           |
screen message          | AF | screenMessage         |
print lint              | AF | printLine             |

<b>\* = Extension field that is not included in the original SIP2 definition.</b><br />
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
    String patronIdentifier = "1234567890";
    String patronPassword = "12345";
    SIP2PatronInformationRequest request = new SIP2PatronInformationRequest(institutionId, patronIdentifier, patronPassword);
    SIP2PatronInformationResponse response = (SIP2PatronInformationResponse) connection.send(request);

    /* Check if the patron and patron password are valid */
    if(response.isValidPatron() && response.isValidPatronPassword()) {
      .
      .
      .
    }
  } else {
    /* Login failed */
  }
}
```