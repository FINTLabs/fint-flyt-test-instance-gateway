# FINT flyt test instance gateway
This implementation of instance gateway is intended to be used for testing the instance flow process. It includes a REST API, with endpoints for sending new instances and error events.

## REST API
### Sending instances
Sending instances is done by sending a post request to ``<service-address>/api/test/instance-gateway/send-incoming-instance-event``. In case a param or the body is not included in the request, a randomly generated value will be created and included in the instance.

#### Request params
| Param                            | Type     | Description                       |
|:---------------------------------|:---------|:----------------------------------|
| `seed`                           | `Long`   | Seed for generating random values |
| `orgId`                          | `String` | Instance flow header property     |
| `service`                        | `String` | Instance flow header property     |
| `sourceApplication`              | `String` | Instance flow header property     |
| `sourceApplicationInstanceId`    | `String` | Instance flow header property     |
| `sourceApplicationIntegrationId` | `String` | Instance flow header property     |
| `correlationId`                  | `String` | Instance flow header property     |

#### Body
JSON serialized ``Instance`` model:

```
class Instance {
    String formId;
    List<Document> documents;
    Map<String, InstanceField> fields;
    String uri;
}
```
```
class Document {
    String format;
    String uri;
}
```
```
class InstanceField {
    String name;
    String value;
}
```

### Sending error events
Sending instances is done by sending a post request to ``<service-address>/api/test/instance-gateway/send-error-event``. In case a param or the body is not included in the request, a randomly generated value will be created and included in the instance.

#### Request params
| Param                            | Type     | Description                       |
|:---------------------------------|:---------|:----------------------------------|
| `seed`                           | `Long`   | Seed for generating random values |
| `orgId`                          | `String` | Instance flow header property     |
| `service`                        | `String` | Instance flow header property     |
| `sourceApplication`              | `String` | Instance flow header property     |
| `sourceApplicationInstanceId`    | `String` | Instance flow header property     |
| `sourceApplicationIntegrationId` | `String` | Instance flow header property     |
| `correlationId`                  | `String` | Instance flow header property     |

#### Body
JSON serialized ``ErrorCollection`` model:

```
class ErrorCollection {
    Collection<Error> errors;
}
```
```
class Error {
    String errorCode;
    Map<String, String> args;
}
```
