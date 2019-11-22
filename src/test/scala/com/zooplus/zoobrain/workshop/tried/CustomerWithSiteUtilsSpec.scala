package com.zooplus.zoobrain.workshop.tried

import java.net.MalformedURLException

import com.zooplus.zoobrain.workshop.model.CustomerWithSite
import org.scalatest.{FlatSpec, Matchers, TryValues}

class CustomerWithSiteUtilsSpec extends FlatSpec with Matchers with TryValues {
  val validCustomer = CustomerWithSite("Thomas", Option("Germany"), 25, "http://www.zooplus.de")
  val validCustomerWithInvalidSite =
    CustomerWithSite("Thomas", Option("Germany"), 25, "invalidSite")
  val utils = new CustomerWithSiteUtils

  "validateCustomer" should "return the customer when it comes from Germany and the site is valid" in {
    utils
      .validateCustomerWithSite(validCustomerWithInvalidSite)
      .failure
      .exception
      .getClass shouldBe classOf[MalformedURLException]
  }
}
