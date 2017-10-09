package com.github.asalimonov.hay.core.metrics

object TimeUnit extends Enumeration {
  type TimeUnit = Value
  val Nanoseconds = Value(0)
  val Microseconds = Value(1)
  val Milliseconds = Value(2)
  val Seconds = Value(3)
  val Minutes = Value(4)
  val Hours = Value(5)
  val Days = Value(6)
}
