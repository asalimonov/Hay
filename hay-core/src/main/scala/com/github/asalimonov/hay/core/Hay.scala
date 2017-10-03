package com.github.asalimonov.hay.core

import com.github.asalimonov.hay.core.logging.{InternalLogger, LoggerBase}

import scala.collection.mutable.ListBuffer

object Hay {

  private val Configurations = ListBuffer[Configuration]()
  private val Logger = new InternalLogger()

  def setLogger(logger: LoggerBase): Unit = {
    require(logger != null, "Logger cannot be null")

    Logger.setLogger(logger)
  }

  def getConfiurations() : List[Configuration] = {
    Configurations.toList
  }

  def addConfiguration(configurationName : String) : Configuration =  {
    require(!configurationName.isEmpty, "Name of configuration shouldn't be empty")

    val cfg = new Configuration(configurationName, Logger)
    Configurations += cfg
    cfg
  }

  def remove(configuration: Configuration): Unit ={
    require(configuration != null, "Configuration cannot be null")

    Configurations -= configuration
  }

  def start() : Unit = {
    Configurations.foreach(c => c.start())
  }

  def stop(): Unit = {
    Configurations.foreach(c => c.stop())
  }

}
