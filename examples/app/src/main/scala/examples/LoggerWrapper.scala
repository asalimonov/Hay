package examples

import com.github.asalimonov.hay.core.logging.LoggerBase
import com.typesafe.scalalogging.Logger

class LoggerWrapper(val logger: Logger) extends LoggerBase{
  def error(message: String): Unit = {
    logger.error(message)
  }
  def error(message: String, cause: Throwable): Unit ={
    logger.error(message, cause)
  }
  def error(message: String, args: Any*): Unit ={
    logger.error(message, args)
  }

  // Warn
  def warn(message: String): Unit ={
    logger.warn(message)
  }
  def warn(message: String, cause: Throwable): Unit ={
    logger.warn(message, cause)
  }
  def warn(message: String, args: Any*): Unit ={
    logger.warn(message, args)
  }

  // Info
  def info(message: String): Unit ={
    logger.error(message)
  }
  def info(message: String, cause: Throwable): Unit ={
    logger.error(message, cause)
  }
  def info(message: String, args: Any*): Unit ={
    logger.error(message, args)
  }

  // Debug
  def debug(message: String): Unit ={
    logger.debug(message)
  }
  def debug(message: String, cause: Throwable): Unit ={
    logger.debug(message, cause)
  }
  def debug(message: String, args: Any*): Unit ={
    logger.debug(message, args)
  }

  // Trace
  def trace(message: String): Unit ={
    logger.trace(message)
  }
  def trace(message: String, cause: Throwable): Unit ={
    logger.trace(message, cause)
  }
  def trace(message: String, args: Any*): Unit ={
    logger.trace(message, args)
  }
}
