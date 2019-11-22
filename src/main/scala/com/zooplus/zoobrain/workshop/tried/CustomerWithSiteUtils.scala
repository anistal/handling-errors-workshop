package com.zooplus.zoobrain.workshop.tried

import java.net.URL

import com.zooplus.zoobrain.workshop.exception.NotRegisteredException
import com.zooplus.zoobrain.workshop.model.{CustomerWithOptionalCountry, CustomerWithSite}

import scala.util.{Failure, Success, Try}

class CustomerWithSiteUtils {

  def parseURL(site: String): Try[URL] = Try(new URL(site))

  def validateCustomer(customer: CustomerWithSite): Option[CustomerWithSite] =
    if (customer.isAnonymous)
      None
    else Some(customer)

  def validateCustomerWithSite(customer: CustomerWithSite): Try[CustomerWithSite] =
    parseURL(customer.site) match {
      case Success(_) =>
        if (customer.isAnonymous)
          Failure(NotRegisteredException("Customer must be registered to buy any item"))
        else Success(customer)
      case Failure(error) => Failure(error)
    }

}
