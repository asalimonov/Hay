package com.github.asalimonov.hay.core.endpoints

trait Endpoint {
  def start(): Unit
  def stop(): Unit
}
