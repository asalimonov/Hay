package com.github.asalimonov.hay.core.metrics

import com.github.asalimonov.hay.core.metrics.TimeUnit.TimeUnit

trait Timer extends ResetableMetric{
  def newContext(userValue: String = null): Unit
  def time(action: () => Unit, userValue: String = null): Unit
  def time[T](action: ()=> T, userValue: String): T
  def startRecording(): Unit
  def endRecording(): Long
  def getCurrentTime(): Long
  def record(time: Long, unit: TimeUnit, userValue: String = null): Unit

}
