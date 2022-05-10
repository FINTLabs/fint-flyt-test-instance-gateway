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
JSON serialized ``Instance`` model.

##### Example
```
curl --request POST \
  --url 'http://localhost:8091/api/test/instance-gateway/send-incoming-instance-event?orgId=orgIdHeaderValue&service=serviceHeaderValue&sourceApplication=sourceApplicationHeaderValue&sourceApplicationInstanceId=sourceApplicationInstanceIdHeaderValue&sourceApplicationIntegrationId=sourceApplicationIntegrationIdHeaderValue&correlationId=correlationIdHeaderValue' \
  --header 'Content-Type: application/json' \
  --data '{
    "formId": "instanceFormId",
    "documents": [
      {
        "format": "documentFormat",
        "uri": "documentUri"
      }
    ],
    "fields": {
      "field1Name": {
        "name": "field1Name",
        "value": "field1Value"
        },
        "field2Name": {
          "name": "field2Name",
          "value": "field2Value"
        }
    },
    "uri": "instanceUri"
  }'
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

##### Example
```
curl --request POST \
  --url 'http://localhost:8091/api/test/instance-gateway/send-error-event?orgId=orgIdHeaderValue&service=serviceHeaderValue&sourceApplication=sourceApplicationHeaderValue&sourceApplicationInstanceId=sourceApplicationInstanceIdHeaderValue&sourceApplicationIntegrationId=sourceApplicationIntegrationIdHeaderValue&correlationId=correlationIdHeaderValue' \
  --header 'Content-Type: application/json' \
  --data '{
  "errors": [
    {
      "errorCode": "errorCode1",
      "args": {
        "arg1" : "arg1Value",
        "arg2" : "arg2Value"
      }
    },
    {
      "errorCode": "errorCode2",
      "args": {
        "arg1" : "arg1Value"
      }
    }
  ]
}'
```
