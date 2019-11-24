package com.zooplus.zoobrain.workshop.model

case class CustomerWithLocation(
    name: String,
    country: Option[String],
    location: String,
    age: Int,
    site: String) {
  def isRegistered: Boolean = country.contains("Germany")
  def isAnonymous: Boolean  = !isRegistered
}
