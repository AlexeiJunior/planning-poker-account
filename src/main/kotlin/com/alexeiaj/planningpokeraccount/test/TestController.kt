package com.alexeiaj.planningpokeraccount.test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun test() = "HELLO WORLD"
}