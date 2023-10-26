# Spring Cloud Data Flow Example with Kafka Binder.

This repository provides an example of setting up Spring Cloud Data Flow with Kafka Binder. Follow the steps below to get started.

## Prerequisites

- [Download Apache Kafka Binary Distribution](https://kafka.apache.org/downloads).
- [Apache kafka Installation Tutorial](https://www.youtube.com/watch?v=tFlYoEJsT2k&t=4s)

## Step 1: Start Zookeeper Server.

Use the following command to start the Zookeeper server:

```bash
zookeeper-server-start.sh Path\To\zookeeper.properties
```

## Step 2: Start Kafka Server.

```bash
kafka-server-start.sh Path\To\server.properties
```

## Step 3: Start Spring Cloud Data Flow Server.

- Download the Spring Cloud Data Flow Server JAR from [here](https://dataflow.spring.io/docs/installation/local/manual). Then, start the Spring Cloud Data Flow Server with the following command:

```bash
java -jar spring-cloud-dataflow-server-2.11.1.jar
```

## Step 4: Start Spring Cloud Data Flow Shell.

- Download the Spring Cloud Data Flow Shell JAR from [here](https://dataflow.spring.io/docs/installation/local/manual). Then, start the Spring Cloud Data Flow Shell with the following command:

```bash
java -jar spring-cloud-dataflow-shell-2.11.1.jar
```

## Step 5: Start Spring Cloud Data Flow Skipper Server.

- Download the Spring Cloud Data Flow Skipper Server JAR from [here](https://dataflow.spring.io/docs/installation/local/manual). Then, start the Spring Cloud Data Flow Skipper Server with the following command:

```bash
java -jar spring-cloud-skipper-server-2.11.1.jar
```

## Step 6: Build Source, Processor and Sink.
 - Build Source, Processor and Sink applications using following command.

```bash
 mvn install -DskipTests
```

## Step 7: Register Services to Spring Cloud Data Flow Server.
- Register Source, Processor and Sink applications to Spring Cloud Data Flow Server using Spring Cloud Data Flow Shell.

```bash
app register --name test-source --type source --uri maven://com.spring.cloud.dataflow:source:jar:0.0.1-SNAPSHOT

app register --name test-processor --type processor --uri maven://com.spring.cloud.dataflow:processor:jar:0.0.1-SNAPSHOT

app register --name test-sink --type sink --uri maven://com.spring.cloud.dataflow:sink:jar:0.0.1-SNAPSHOT
```

## Step 8: Create Cloud Stream.
- Create Cloud Stream to connect between all microservices registered in Spring Cloud Data Flow server using Spring Cloud Data Flow Shell.

```bash
create --name Test-Stream --definition 'test-source | test-processor | test-sink'
```

## Step 9: Start & Deploy Stream.
- Start and deploy created stream using Spring Cloud Data Flow Shell.

```bash
stream deploy --name Test-Stream
```