package com.datastax.example

import java.util.UUID

import com.datastax.oss.driver.api.mapper.annotations.{Dao, Insert, Select}

@Dao
trait ProductDao {
  @Select
  def find(id: UUID): Product

  @Insert
  def save(product: Product)
}
