package io.gigiperih.cityx.utils.extensions

fun String?.getValue(): String {
    return this ?: ""
}