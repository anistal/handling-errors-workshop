package com.zooplus.zoobrain.workshop.model

sealed trait ItemError {
  val message: String
}

final case class NoValidDescription(message: String) extends ItemError
final case class NoValidPrice(message: String)       extends ItemError
