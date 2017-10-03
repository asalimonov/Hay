package com.github.asalimonov.hay.core

import com.github.asalimonov.hay.core.endpoints.http.{HttpEndpoint, HttpEndpointConfig}
import com.github.asalimonov.hay.core.logging._

import scala.collection.mutable.ListBuffer

sealed class Configuration private[core] (val name: String, val logger: LoggerBase) {
  private var httpEndpointConfig: HttpEndpointConfig = _
  private var httpEndpoint: HttpEndpoint = _

  private var metricsContexts = ListBuffer[MetricsContext]()

  // Setup

  def setHealthCheckEndpoint(hostname: String, port: Int, ssl: Boolean) : Configuration ={
    httpEndpointConfig = new HttpEndpointConfig(hostname, port, ssl)
    this
  }

  def addMetricsContext(mc: MetricsContext) : Configuration = {
    metricsContexts += mc
    this
  }

  // Management

  def start(): Unit ={
    if(httpEndpointConfig != null){
      httpEndpoint = new HttpEndpoint(httpEndpointConfig, this, logger)
      httpEndpoint.start()
    }
  }

  def stop(): Unit ={
    if(httpEndpoint != null) {
      httpEndpoint.stop()
      httpEndpoint = null
    }
  }
}
