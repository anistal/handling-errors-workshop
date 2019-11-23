package com.zooplus.zoobrain.workshop.tried

import java.net.MalformedURLException

import com.zooplus.zoobrain.workshop.exception.NotRegisteredException
import com.zooplus.zoobrain.workshop.model.CustomerWithSite
import org.scalatest.{FlatSpec, Matchers, TryValues}

class CustomerWithTryUtilsSpec extends FlatSpec with Matchers with TryValues {
  val validCustomer = CustomerWithSite("Thomas", Option("Germany"), 25, "http://www.zooplus.de")
  val invalidCustomer = CustomerWithSite("Francisco", Option("Spain"), 25, "http://www.zooplus.es")
  val validCustomerWithInvalidSite =
    CustomerWithSite("Thomas", Option("Germany"), 25, "invalidSite")
  val utils = new CustomerWithTryUtils

  "validateCustomer" should "return Failure when the customer comes from Germany but the site is valid" in {
    utils
      .validateCustomerWithSite(validCustomerWithInvalidSite)
      .failure
      .exception
      .getClass shouldBe classOf[MalformedURLException]
  }

  "validateCustomer" should "return Failure when the customer does not come from Germany" in {
    utils
      .validateCustomerWithSite(invalidCustomer)
      .failure
      .exception
      .getClass shouldBe classOf[NotRegisteredException]
  }

  "validateCustomer" should "return Success when the customer comes from Germany and the site is well-formed" in {
    utils
      .validateCustomerWithSite(validCustomer)
      .success
      .value shouldBe validCustomer
  }
}
