package com.datastax.example

import com.datastax.oss.driver.api.mapper.annotations.{DaoFactory, Mapper}

@Mapper
trait InventoryMapper {
  @DaoFactory
  def productDao(): ProductDao
}
