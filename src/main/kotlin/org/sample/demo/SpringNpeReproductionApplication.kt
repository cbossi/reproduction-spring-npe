package org.sample.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringNpeReproductionApplication

fun main(args: Array<String>) {
  runApplication<SpringNpeReproductionApplication>(*args)
}
