package com.datastax.example

import com.datastax.oss.driver.api.core.CqlIdentifier
import com.datastax.oss.driver.api.mapper.annotations.{DaoFactory, DaoKeyspace, Mapper}

@Mapper
trait InventoryMapper {
  @DaoFactory
  def productDao(@DaoKeyspace keyspace: CqlIdentifier): ProductDao
}
