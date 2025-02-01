package example.foo

import bfdif.Component
import bfdif.PostConstruct

@Component
class SomeOtherService {

    @PostConstruct
    fun init() {
        println("SomeOtherService was initialized")
    }
}
