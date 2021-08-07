# Item Status Update Request (19) - Item Status Update Response (20)

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
To view a copy of this license, visit <https://creativecommons.org/licenses/by-sa/4.0/>

## Item Status Update Request (19)

SIP2 Field        | ID | JSIP2 variable   | Required
------------------|----|------------------|-----------
transaction date  |    | transacationDate | X
institution id    | AO | institutionId    | X
item identifier   | AB | itemIdentifier   | X
terminal password | AC | terminalPassword | 
item properties | CH | itemProperties |

## Item Status Update Response (20)

SIP2 Field              | ID | JSIP2 variable        | Required
------------------------|----|-----------------------|-----------
item properties ok | | itemPropertiesOk | X
transaction date        |    | transactionDate       | X
item identifier   | AB | itemIdentifier   | X
title identifier | AJ | titleIdentifier |
item properties | CH | itemProperties |
screen message | AF | screenMessage |
print line | AG | printLine |
