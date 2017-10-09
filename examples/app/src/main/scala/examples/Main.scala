package examples

import com.github.asalimonov.hay.core._
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


object Main extends App {

  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  def main(): Unit = {
    println("Hello, it is Hay test application!")

    Hay.addConfiguration("test")
        .setHealthCheckEndpoint("localhost", 9876, false)
    Hay.setLogger(new LoggerWrapper(logger))
    Hay.start()

    println("Press any key to exit...")
    scala.io.StdIn.readChar()
  }

  main()
}


