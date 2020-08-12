# Driver 4 object mapper + Scala demo

Demonstrates how to use the [Datastax driver 4 Object
Mapper](https://docs.datastax.com/en/developer/java-driver/4.8/manual/mapper/) in a Scala
application.

## Usage

### Prerequisites

At the time of writing, this depends on a snapshot build of the driver, see
`project/Dependencies.scala`. You need to check out the [driver pull
request](https://github.com/datastax/java-driver/pull/1487) and install it in your local Maven
repository.

The client expects that a Cassandra instance is running locally and accessible at 127.0.0.1:9042
(this can be customized in `client/src/main/scala/com/datastax/example/Client.scala`).

### Running

```
sbt "clean ; client/run"
```

The program creates a simple keyspace and schema, inserts an entity and then retrieves it.

## How it works

Scala does not support annotation processing natively, so the mapper processor cannot operate on the
Scala sources directly. However, what it can do is process the compiled class files output by the
Scala compiler.

So the compilation of model types happens in 3 phases:

1. Compile the Scala sources with the regular sbt task. This puts the class files in
  `model/target/scala-2.13/classes`.
2. Execute a custom task that runs the annotation processor (`javac -proc:only ...`) on the compiled
   class files. This generates a set of Java classes in
   `model/target/scala-2.13/src_managed/main/mapper`.
3. Execute another custom task that compiles those Java classes back into
   `model/target/scala-2.13/classes`.

Because the application code depends on some of the generated types (namely
`InventoryMapperBuilder`), it **must** reside in a separate subproject, otherwise phase 1 would fail
with a `ClassNotFoundError`.

Note: the author is not proficient in sbt. There are probably better ways to implement the custom
tasks, see the TODOs in `build.sbt`.
