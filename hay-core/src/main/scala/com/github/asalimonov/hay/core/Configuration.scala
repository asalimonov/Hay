package com.github.asalimonov.hay.core

import com.github.asalimonov.hay.core.endpoints.http.{HttpEndpoint, HttpEndpointConfig}
import com.github.asalimonov.hay.core.logging._

import scala.collection.mutable.HashMap

sealed class Configuration private[core] (val name: String, val logger: LoggerBase) {
  private var httpEndpointConfig: HttpEndpointConfig = _
  private var httpEndpoint: HttpEndpoint = _

  private val metricsContexts = HashMap[String, MetricsContext]()

  // Setup

  def setHealthCheckEndpoint(hostname: String, port: Int, ssl: Boolean) : Configuration ={
    require(hostname != null && !hostname.isEmpty, "hostname cannot be null or empty")
    require(port >=1 && port <=65534, "port should >= 1 and <=65534")

    httpEndpointConfig = new HttpEndpointConfig(hostname, port, ssl)
    this
  }

  def getMetricsContexts(): scala.collection.Map[String, MetricsContext]  = metricsContexts

  def addMetricsContext(name: String) : MetricsContext  = {
    require(name != null, "Name of metricsContext should not be null")
    val mc = new MetricsContext(name)
    metricsContexts.put(name, mc)
    mc
  }


  // Management

  def start(): Unit ={
    if(httpEndpointConfig != null){
      httpEndpoint = new HttpEndpoint(httpEndpointConfig, this)
      httpEndpoint.start()
    }
  }

  def stop(): Unit ={
    if(httpEndpoint != null) {
      httpEndpoint.stop()
      httpEndpoint = null
    }
  }

  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case c: Configuration => name.equals(c.name)
      case _ => false
    }
  }
}
