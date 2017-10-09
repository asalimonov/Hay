package com.github.asalimonov.hay.core.metrics

trait Histogram extends ResetableMetric {

  def update(value: Long, userValue: String) : Unit
}
