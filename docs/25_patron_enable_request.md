# Patron Enable Request (25) - Patron Enable Response (26)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Patron Enable Request (25)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
patron identifier | AA | patronIdentifier | X
terminal password | AC | terminalPassword |
patron password   | AD | patronPassword   |

## Patron Enable Response (26)

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
screen message        | AF | screenMessage       |
print lint            | AF | printLine           |
