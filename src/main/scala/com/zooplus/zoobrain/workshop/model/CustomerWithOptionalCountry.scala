package com.zooplus.zoobrain.workshop.model

case class CustomerWithOptionalCountry(name: String, country: Option[String], age: Int) {
  def isRegistered: Boolean = country.contains("Germany")
  def isAnonymous: Boolean  = !isRegistered
}
