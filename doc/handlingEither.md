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
- Self documented code.
- You know where to lookup the errors.
- Compiler helps when new errors are added to your ADT.
```scala
sealed trait CustomerError {
    val message: String
}
final case class LocationError(message: String) extends CustomerError
final case class InvalidNameError(message: String) extends CustomerError
```
```scala
def validateCustomer(customer: Customer): Either[CustomerError, Customer] =
    if(customer.location.equals("Europe")) {
      Right(customer)
    }
    else {
      Left(LocationError("This customer has an invalid location"))
    }
}
```
- Curiously..... **AWESOME** (again)!!, you can:
  - (Since 2.12) Either is right-based, which means that Right is assumed to be the default case to operate on. If it is Left, operations like **map, flatMap**, ... return the Left value unchanged:
```scala
Right(12).map(_ * 2) // Right(24)
Left(23).map(_ * 2)  // Left(23)
```
  - pattern matching
  - `getOrElse` to pass in a default value to be returned if the Either is a Left.
  - `foreach` (***WARNING!!*** side-effect)
  - `filter`
  - `for-comprehension` (flatMap + map)
  ```scala
  for {
    validCustomer <- utils.validateCustomer(customer)
    validItem     <- utils.validateItem(item)
  } yield println(s"The customer ${validCustomer.name} has spent ${validItem.price} euros")
  ```

### CONS
- The boilerplate you need for defining your own ADT.
- For handling third-party libraries exceptions, you know, unexpected exceptions, it's not recommended. For those cases, Try does that better.
