package com.zooplus.zoobrain.workshop.either

import com.zooplus.zoobrain.workshop.model.{
  CustomerError,
  CustomerWithLocation,
  Item,
  ItemError,
  LocationError,
  NoValidDescription,
  NoValidPrice
}

class CustomerWithEitherUtils {
  def validateCustomer(
      customer: CustomerWithLocation): Either[CustomerError, CustomerWithLocation] =
    if (customer.location.equals("Europe")) {
      Right(customer)
    } else {
      Left(LocationError("This customer has an invalid location"))
    }

  def validateItem(item: Item): Either[ItemError, Item] =
    if (item.description.isEmpty)
      Left(NoValidDescription("The description can not be empty"))
    else if (item.price <= 0.0)
      Left(NoValidPrice("The price can not be less than 0"))
    else Right(item)
}

object ShopWithEither extends App {
  val utils    = new CustomerWithEitherUtils
  val customer = CustomerWithLocation("Emma", Some("Spain"), "Europe", 4, "http://www.zooplus.es")

  val item = Item("X12345Y", "valid article description", 10.50)

  val result = for {
    validCustomer <- utils.validateCustomer(customer)
    validItem     <- utils.validateItem(item)
  } yield println(s"The customer ${validCustomer.name} has spent ${validItem.price} euros")

}
