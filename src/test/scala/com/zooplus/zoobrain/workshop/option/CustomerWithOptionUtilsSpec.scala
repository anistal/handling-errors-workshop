package com.zooplus.zoobrain.workshop.option

import com.zooplus.zoobrain.workshop.model.CustomerWithOptionalCountry
import org.scalatest.{FlatSpec, Matchers, OptionValues}

class CustomerWithOptionUtilsSpec extends FlatSpec with Matchers with OptionValues {
  val germanCustomer = CustomerWithOptionalCountry("Johan", Option("Germany"), 25)
  val dummyCustomer  = CustomerWithOptionalCountry("Dummy", None, 25)
  val utils          = new CustomerWithOptionUtils

  "validateCustomer" should "return the customer when it comes from Germany" in {
    utils.validateCustomer(germanCustomer).value shouldBe germanCustomer
  }

  "validateCustomer" should "return None when the customer is not valid" in {
    utils.validateCustomer(dummyCustomer) shouldBe None

  }
}
