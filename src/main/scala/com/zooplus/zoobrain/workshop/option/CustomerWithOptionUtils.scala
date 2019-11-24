package com.zooplus.zoobrain.workshop.option

import com.zooplus.zoobrain.workshop.model.{Customer, CustomerWithOptionalCountry}

class CustomerWithOptionUtils {

  def validateCustomer(customer: CustomerWithOptionalCountry): Option[CustomerWithOptionalCountry] =
    if (customer.isAnonymous)
      None
    else Some(customer)

}

object ShopWithOption extends App {
  val utils          = new CustomerWithOptionUtils
  val germanCustomer = CustomerWithOptionalCountry("Peter", Some("Germany"), 30)
  val dummyCustomer  = CustomerWithOptionalCountry("Peter", None, 30)

  utils
    .validateCustomer(germanCustomer)
    .map { loggedInCustomer =>
      println(s"*****LOGGED IN*****")
      println(
        s"${loggedInCustomer.name}, ${loggedInCustomer.age} years, from ${loggedInCustomer.country}")
    }

//  val default = utils
//    .validateCustomer(dummyCustomer)
//    .getOrElse(CustomerWithOptionalCountry("Dummy", Some("DummyLand"), 100))
//  println(s"${default.name}, ${default.age} years, from ${default.country}")

}
