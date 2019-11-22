package com.zooplus.zoobrain.workshop.exception

import com.zooplus.zoobrain.workshop.model.Customer
import org.scalatest.{FlatSpec, Matchers}

class CustomerUtilsSpec extends FlatSpec with Matchers {
  val germanCustomer  = Customer("Johan", "Germany", 25)
  val spanishCustomer = Customer("Francisco", "Spain", 25)
  val utils           = new CustomerUtils

  "validateCustomer" should "return the customer when it comes from Germany" in {
    utils.validateCustomer(germanCustomer) shouldBe germanCustomer
  }

  "validateCustomer" should "return an exception when the customer is not German" in {
    intercept[NotRegisteredException] {
      utils.validateCustomer(spanishCustomer)
    }
  }
}
