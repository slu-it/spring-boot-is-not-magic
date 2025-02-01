package example

import bfdif.Application
import bfdif.runApplication

@Application
class TestApplication

fun main(args: Array<String>) {
    runApplication(TestApplication::class)
}
