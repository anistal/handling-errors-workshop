package com.zooplus.zoobrain.workshop.model

case class CustomerWithSite(name: String, country: Option[String], age: Int, site: String) {
  def isRegistered: Boolean = country.contains("Germany")
  def isAnonymous: Boolean  = !isRegistered
}
