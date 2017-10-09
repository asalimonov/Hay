package com.github.asalimonov.hay.core

class MetricsContext (val name: String) {

  private var enabled : Boolean = true

  def isEnabled() : Boolean = enabled

  def enable() : Unit = {
    enabled = true
  }

  def disable() : Unit = {
    enabled = false
  }

}
