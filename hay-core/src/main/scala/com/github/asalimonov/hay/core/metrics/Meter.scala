package com.github.asalimonov.hay.core.metrics

trait Meter extends ResetableMetric {
  def mark(): Unit
  def mark(item: String): Unit
  def mark(count: Long): Unit
  def mark(item: String, count: Long): Unit

}
