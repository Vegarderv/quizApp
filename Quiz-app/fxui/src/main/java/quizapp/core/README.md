# Core

This is logic in the FXUI part of the application

## UserAccess

This part contains the interface UserAccess and the two java classes DirectUserAccess and RemoteUserAccess, and is for accessing the .json file containing the users

Encryption and Decryption happens he

DirectUserAccess is for testing and installing, and is only used then. It accesses the .json files witout use of the restapi

RemoteUserAccess is for normal usage fo the app. It accesses the .json file using the restapi

The reason UserAccess is not tested is because #1, it is indirectly tested through the mockRemoteAccess in the integration test in UserController¨
and #2 we are not excpected to be able to make a functioning test for these kind of classes

## QuizAccess

Same as UserAccess, only with Quiz instead

## UsernameCheck

Class for confirming that Username and Password is correct