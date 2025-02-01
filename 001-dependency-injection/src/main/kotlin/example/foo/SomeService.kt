package example.foo

import bfdif.Component
import bfdif.PostConstruct

@Component
class SomeService(
    private val otherService: SomeOtherService
) {

    @PostConstruct
    fun init() {
        println("SomeService was initialized")
    }
}
