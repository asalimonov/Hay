package com.github.asalimonov.hay.core.logging

private[core] sealed class InternalLogger extends LoggerBase {

  private var l : LoggerBase = _

  def setLogger(logger: LoggerBase): Unit ={
    l = logger
  }

  override def error(message: String): Unit = {
    if(l != null){
      l.error(message)
    }
  }

  override def error(message: String, cause: Throwable): Unit = {
    if(l != null){
      l.error(message, cause)
    }
  }

  override def error(message: String, args: Any*): Unit = {
    if(l != null){
      l.error(message, args)
    }
  }

  override def warn(message: String): Unit = {
    if(l != null){
      l.warn(message)
    }
  }

  override def warn(message: String, cause: Throwable): Unit = {
    if(l != null){
      l.warn(message, cause)
    }
  }

  override def warn(message: String, args: Any*): Unit = {
    if(l != null){
      l.warn(message, args)
    }
  }

  override def info(message: String): Unit = {
    if(l != null){
      l.info(message)
    }
  }

  override def info(message: String, cause: Throwable): Unit = {
    if(l != null){
      l.info(message, cause)
    }
  }

  override def info(message: String, args: Any*): Unit = {
    if(l != null){
      l.info(message, args)
    }
  }

  override def debug(message: String): Unit = {
    if(l != null){
      l.debug(message)
    }
  }

  override def debug(message: String, cause: Throwable): Unit = {
    if(l != null){
      l.debug(message, cause)
    }
  }

  override def debug(message: String, args: Any*): Unit = {
    if(l != null){
      l.debug(message, args)
    }
  }

  override def trace(message: String): Unit = {
    if(l != null){
      l.trace(message)
    }
  }

  override def trace(message: String, cause: Throwable): Unit = {
    if(l != null){
      l.trace(message, cause)
    }
  }

  override def trace(message: String, args: Any*): Unit = {
    if(l != null){
      l.trace(message, args)
    }
  }
}
