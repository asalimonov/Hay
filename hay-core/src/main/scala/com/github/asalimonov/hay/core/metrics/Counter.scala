package com.github.asalimonov.hay.core.metrics

trait Counter extends ResetableMetric {
  def increment (): Unit
  def increment (name: String): Unit
  def increment (amount: Long): Unit
  def increment (name: String, amount: Long): Unit
  def decrement (): Unit
  def decrement (name: String): Unit
  def decrement (amount: Long): Unit
  def decrement (name: String, amount: Long): Unit

}
