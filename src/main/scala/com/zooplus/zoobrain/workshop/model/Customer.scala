package com.zooplus.zoobrain.workshop.model

case class Customer(name: String, country: String, age: Int) {
  def isRegistered: Boolean = country == "Germany"
  def isAnonymous: Boolean  = !isRegistered
}
