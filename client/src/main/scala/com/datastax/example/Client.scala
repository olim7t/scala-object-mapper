package com.datastax.example

import java.time.Duration
import java.util.UUID

import com.datastax.oss.driver.api.core.cql.SimpleStatement
import com.datastax.oss.driver.api.core.{CqlIdentifier, CqlSession}

import scala.util.Using

object Client {

  val KeyspaceId: CqlIdentifier = CqlIdentifier.fromCql("scala_mapper")

  def main(args: Array[String]): Unit = {
    Using.resource(CqlSession.builder().build()) { session =>
      createSchema(session)

      val mapper = new InventoryMapperBuilder(session).build()
      val dao = mapper.productDao(KeyspaceId)

      val initialProduct = Product(UUID.randomUUID(), "mock description")
      println(s"Saving mapped entity to the database: $initialProduct")
      dao.save(initialProduct)
      val retrievedProduct = dao.find(initialProduct.id)
      println(s"Retrieved mapped entity from the database: $retrievedProduct")
    }
  }

  def createSchema(session: CqlSession): Unit = {
    val queries = List(
      s"CREATE KEYSPACE IF NOT EXISTS $KeyspaceId WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}",
      s"CREATE TABLE IF NOT EXISTS $KeyspaceId.product(id uuid PRIMARY KEY, description text)")
    val timeout = Duration.ofSeconds(10)
    queries.foreach(query =>
      session.execute(SimpleStatement.newInstance(query).setTimeout(timeout)))
  }
}
