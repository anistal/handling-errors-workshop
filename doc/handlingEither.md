# Handling Errors workshop

## *Either[L, R]*


```scala
import scala.io.Source
import java.net.URL
def getContent(url: URL): Either[String, Source] =
  if (url.getHost.contains("google"))
    Left("Requested URL is blocked for the good of the people!")
  else
    Right(Source.fromURL(url))
```

### PROS
- Either is a good approach to avoid these. It returns Left or Right.
	- **Left** contains, **by convention**, the error message that we pass in our code.
	- **Right** contains the value itself.
- IMHO, Either is the more complicated but better way to handle errors; we can create a custom **Algebraic Data Type (ADT)** to structure and maintain the exceptions.
```scala
sealed trait CustomerError {
  val message: String
}
  final case class LocationError(message: String) extends CustomerError
	final case class InvalidNameError(message: String) extends CustomerError
	...
}
case class Customer(name: String, location: String)

def validateCustomer(customer: Customer): Either[CustomerError, Customer] =

  if(customer.location.equals("Europe")) {
    Right(customer)
  }
  else {
    Left(LocationError("This customer has an invalid location"))
  }

}
```
