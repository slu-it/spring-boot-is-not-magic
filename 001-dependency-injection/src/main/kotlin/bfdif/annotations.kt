package bfdif

import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention
@Target(CLASS)
annotation class Application

@Retention
@Target(CLASS)
annotation class Component

@Retention
@Target(FUNCTION)
annotation class PostConstruct
