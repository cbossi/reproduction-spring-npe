package org.sample.demo

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("test")
internal class SampleController(greetingService: GreetingService) : BaseController(greetingService) {

  @RequestMapping("bar")
  fun bar(): String {
    return "Bar"
  }

}

internal abstract class BaseController(private val greetingService: GreetingService) {

  @RequestMapping("foo")
  fun foo(): String {
    return greetingService.greet("Foo")
  }

}

@Service
internal class GreetingService {

  fun greet(input: String): String {
    return "Hello $input"
  }

}
