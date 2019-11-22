package com.zooplus.zoobrain.workshop.exception

import com.zooplus.zoobrain.workshop.model.Customer

case class NotRegisteredException(message: String) extends Exception(message)

class CustomerUtils {
  def validateCustomer(customer: Customer): Customer =
    if (customer.isAnonymous)
      throw NotRegisteredException("Customer must be registered to buy any item")
    else customer
}

object Shop extends App {
  val utils = new CustomerUtils
  val customer = Customer("Pedro", "Spain",30)

  try {
    utils.validateCustomer(customer)
  }catch {
    case NotRegisteredException(msg) => println(s"****ERROR*** $msg")
  }


}
