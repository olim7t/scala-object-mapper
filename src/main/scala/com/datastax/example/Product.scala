package com.datastax.example

import java.util.UUID

import com.datastax.oss.driver.api.mapper.annotations.{Entity, PartitionKey}

import scala.annotation.meta.field

@Entity
case class Product(@(PartitionKey@field) id: UUID, description: String)
