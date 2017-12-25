# wash-machine-api
This is an example of a REST service, based on Spring Boot and Mongo DB.  The REST implementation is not pure-REST with various server error codes, but it's still nice.
# build

Launch `mvn clean package` or `mvn clean install` from command line to build the `jar` file.
The resulting jar is called `wash-machine-api-<version>.jar`, where `version` is the version
number.
To launch the application, do the following steps:

1. Start MongoDB instance on specified `host` and `port`. The `host` and `port` can be changed in the
`application.properties` file in the `src/main/resources` folder. By default the `host` is `127.0.0.1` and 
`port` is `27017`. 

2. Start `wash-machine-api` jar from command line by the command 

`java -jar wash-machine-api-<version>.jar` 

from the 
`target` directory or specify the full path to the jar file. The service will be available on the 
`http://localhost:8080` by default. The port can be specified in the launching command:

`java -jar wash-machine-api-<version>.jar --server.port=7788`

where `7788` is the port number. Any other port number can be specified.

# API
The service has the following API. All values are returned in the JSON format.

`/` - root path, returns `Welcome to the Wash Machine API` string.

`/machine/all` - returns a list of all wash machines, available in the database.

`/machine/{modelName}/create` - creates new machine with default parameters and specified `modelName`.
Example: `/machine/model1/create` - the machine with name `model1` will be created. Returns error,
if the machine with `modelName` is already exists in the database.

`/machine/{modelName}/remove` - removes the machine with specified `modelName`. Returns error, if the 
machine with `modelName` is absent from the database.

`/machine/{modelName}/get` - gets the machine info with specified `modelName`. Returns error, if the 
machine with `modelName` is absent from the database.

`/turn/{modelName}/on` - sets the flag `turnedOn` of the machine with specified `modelName` to true.

`/turn/{modelName}/off` - sets the flag `turnedOn` of the machine with specified `modelName` to false.

`/turn/{modelName}/isTurnedOn` - returns the flag `turnedOn` of the machine with specified `modelName`.

`/state/{modelName}/getState` - returns the current 