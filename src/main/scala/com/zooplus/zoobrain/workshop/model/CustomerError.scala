package com.zooplus.zoobrain.workshop.model

sealed trait CustomerError {
  val message: String
}

final case class LocationError(message: String)    extends CustomerError
final case class InvalidNameError(message: String) extends CustomerError
