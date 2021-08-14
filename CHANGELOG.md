# Changelog

## 1.2.0 - xxxx-xx-xx
- Add basic debug and error logging.
- Make `SIP2MessageRequest.errorDetectionEnabled` an instance variable instead of a class variable.
- Remove auto increase from `SIP2MessageRequest.sequence` variable. From now on, sequence has to be manually maintained by the client.

## 1.1.0 - 2014-06-30
- Response message fieds with certain values were parsed erroneously and caused exceptions.
- When response message parser throws an exception, the whole response message is included in the exception message.

## 1.0.1 - 2014-06-27
- Minor fixes.

## 1.0.0 - 2012-08-17
- Initial release.