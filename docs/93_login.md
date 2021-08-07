# Login (93) - Login response (94)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Login Request (93)

| SIP2 Field     | ID | JSIP2 variable      |
|----------------|----|---------------------|
| login user id  | CN | userName            |
| login password | CO |	password            |
| location code  | CP | circulationLocation |

## Login Response (94)

| SIP2 Field | ID | JSIP2 variable |
|------------|----|----------------|
| ok |	 | 	ok |

## Usage

```
import com.pkrete.jsip2.connection.SIP2SocketConnection;
import com.pkrete.jsip2.messages.requests.SIP2LoginRequest;
import com.pkrete.jsip2.messages.requests.SIP2LoginResponse;

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
    /* Logged in succesfully */
  } else {
    /* Login failed */
  }
}
``` 