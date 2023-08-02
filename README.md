### Remote call of server with gRPC plus kafka

Simple project to test gRPC protocol in k8s cluster.
Also test DB mount and kafka broker in k8s cluster.

Run all services in k8s:

1. Database
2. Kafka broker
3. Consumer
4. Producer

Expose producer port and sent request for client create and delete
through gRPC and kafka.
Expose consumer and check the logs.
Also check new lines in database.

NOTE: Some times in Open Shift, request between services through gRPC
could converts to HTTP/1.x and fails because of that.
It caused by name of port in k8s service configuration. For proper work, use names which starts from 'grpc-'. Example:

protocol: TCP

port: 35070

name: grpc-35070

targetPort: 35070

