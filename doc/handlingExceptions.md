# Handling Errors workshop

## Exception

```scala
case class NotRegisteredException(message: String) extends Exception(message)
case class Customer(name: String, country: String, age: Int) {
  def isRegistered: Boolean = country == "Germany"
  def isAnonymous: Boolean  = !isRegistered
}

def validateCustomer(customer: Customer): Customer =
  if (customer.isAnonymous)
    throw NotRegisteredException(s"Customer must be registered to buy any item")
  else customer


```
```scala
val customer = Customer("Pedro", "Spain",30)

 try {
   validateCustomer(customer)
 }catch {
   case NotRegisteredException(msg) => println(s"****ERROR*** $msg")
}
```
### PROS
- Very similarly to Java, although using a partial function to specify the exceptions we want to deal with

### CONS
- having this kind of exception handling code all over your code base can become ugly very quickly and doesn't really go well with functional programming.
- It's also a rather bad solution for applications with a lot of concurrency.
