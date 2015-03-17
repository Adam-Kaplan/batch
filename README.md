# POC - Spring + Maven Batching #
Create a batch processing that can be automatically tested by a Maven build.

## Cassandra ##

`mvn cassandra:run`

Will startup a Cassandra instance.  When you do this, the maven execution 
will halt and wait for input.

## Maven Exec ##
This is a hack that uses the cassandra cqlsh tool to run CQL files against a 
started database.  The configuration for *executable* will need to be changed 
to point to a local version of the cqlsh tool.  The *argument* may need to 
be adjusted as well to provide an OS specific path to the execution file.

This is done because the cassandra maven plugin *exec-cql* does not work 
correctly.

## Run the Build ##

`mvn clean install`

1. Start up all dependencies in the *pre-integration-test* phase.
  * Mock Server
    * Uses `MockServerInit` to mock the server request/responses.
  * Cassandra
2. Will run `ApplicationIT` to validate the tests.
  * The test depends on a clean before the test so that the cassandra database files get deleted.
3. Shutdown dependencies.
4. Report on test status.
