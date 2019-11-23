# Handling Errors workshop

## *Option[A]*

```scala
case class Customer(name: String, country: String, age: Int) {
  def isRegistered: Boolean = country == "Germany"
  def isAnonymous: Boolean  = !isRegistered
}

def validateCustomer(customer: Customer): Option[Customer] =
    if (customer.isAnonymous)
      None
    else Some(customer)
```

```scala
validateCustomer(customer)
    .map { loggedInCustomer =>
      println(s"*****LOGGED IN*****")
      println(
        s"${loggedInCustomer.name}, ${loggedInCustomer.age} years, from ${loggedInCustomer.country}")
    }
```

### PROS
- **AVOID NULLs**. Scala tries to solve the problem by **getting rid of null values** altogether and providing its own type for representing optional values, i.e. values that may be present or not: the Option[A] trait.
- Provides a default value with `getOrElse`
```scala
val user = User(2, "Johanna", "Doe", 30, None)
println("Gender: " + user.gender.getOrElse("not specified")) // will print "not specified"
```
There is no need to worry if creating the default value is costly for some reason or another – this will only happen if the default value is actually required (***lazy evaluation***).
- **AWESOME!!!** Also provides:
  - pattern matching
  - `foreach` (***WARNING!!*** side-effect)
  - `map`
  - `flatMap`
  - `filter`
  - `for-comprehension` (flatMap + map)
  - `orElse` (Chaining options like `someOption orElse otherOption orElse ....`) Vs `getOrElse`

### CONS
- **Missing error cause**. It does not provide the error message in case of failures, it just returns `None` for the failures, so if you need it, you are missing the cause that threw the error.
- ***WARNING using `.get`***. Using `.get`  you might forget about checking with `isDefined` before, leading to a **NullPointException** at runtime, so you haven't gained a lot over using null.
