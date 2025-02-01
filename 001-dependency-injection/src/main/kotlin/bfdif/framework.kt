package bfdif

import kotlin.reflect.KClass

fun runApplication(applicationClass: KClass<*>) =
    runApplication(applicationClass.java)

fun runApplication(applicationClass: Class<*>) {
    val classLoader = applicationClass.classLoader
    val rootPackage = applicationClass.`package`

    val classes = getClasses(classLoader, rootPackage)
        .onEach { println("loaded: $it") }
}

