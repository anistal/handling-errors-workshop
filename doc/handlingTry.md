# Handling Errors workshop

## *Try[A]*

```scala
import scala.util.Try
import java.net.URL
def parseURL(url: String): Try[URL] = Try(new URL(url))
```

### PROS
- `Try[A]` represents a computation that:
  - may result in a value of type A, that is an instance of `Success[A]`, simply wrapping a value of type A,
  - or may result in an error, that is an instance of `Failure[A]`, wrapping a `Throwable`, if something went wrong.
- Using Try[A] as the return type of our function, **forces clients of our function to deal with the possibility of an error in some way.**
- **AWESOME (AS WELL)!!!** As Option, also provides:
  - pattern matching
  - `getOrElse` to pass in a default value to be returned if the Try is a Failure:
	```scala
  val url = parseURL(Console.readLine("URL: ")) getOrElse new URL("http://zooplus.com")
  ```
  - `foreach` (***WARNING!!*** side-effect)
  - `map` & `flatMap`
  ```scala
  parseURL("http://zooplus.com").map(_.getProtocol)
  // results in Success("http")
  parseURL("garbage").map(_.getProtocol)
  // results in Failure(java.net.MalformedURLException: no protocol: garbage)
  ```
  ```scala
  import java.io.InputStream
  def inputStreamForURL(url: String): Try[Try[Try[InputStream]]] = parseURL(url).map { u =>
      Try(u.openConnection()).map(conn => Try(conn.getInputStream))
  }
  ```
  - `filter`
  - `for-comprehension` (flatMap + map)
  ```scala
  import scala.io.Source
  def getURLContent(url: String): Try[Iterator[String]] =
  for {
    url         <- parseURL(url)
    connection  <- Try(url.openConnection())
    is          <- Try(connection.getInputStream)
    source      = Source.fromInputStream(is)
  } yield source.getLines()
  ```
- use `recover` instead of `getOrElse` for returning a kind of default behaviour in the case of a Failure.
```scala
import java.net.MalformedURLException
import java.io.FileNotFoundException
val content = getURLContent("garbage") recover {
  case e: FileNotFoundException => Iterator("Requested page does not exist")
  case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
  case _ => Iterator("An unexpected error has occurred. We are so sorry!")
}
```
- It's OK for wrapping exceptions coming from *third-party libraries*, but....


### CONS
-  .... for our own code, if you want to provide a Failure, you finally need, AGAIN, to throw an exception which is exactly what we want to avoid, don't we?
- Failure for wrapping any exception, but then, outside the context of that exception:
  - where does that exception come from?
  - Depending on that, maybe we need to handle it in different ways.
  - WARNING!!! After all, we are still handling exceptions, even after
  - Very limited in a descriptive way
