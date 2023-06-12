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

