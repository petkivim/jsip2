# Block Patron Request (01) - Patron Status Response (24)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Block Patron Request (01)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
card retaiden | | cardRetained | X
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
blocked car msg | AL | blockedCardMsg | X
patron identifier | AA | patronIdentifier | X
terminal password | AC | terminalPassword | X

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
