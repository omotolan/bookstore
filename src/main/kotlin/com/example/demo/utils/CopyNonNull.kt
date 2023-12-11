package com.example.demo.utils

import org.apache.commons.beanutils.BeanUtilsBean

import java.lang.reflect.InvocationTargetException

object BeanUtilHelper : BeanUtilsBean() {

    @Throws(IllegalAccessException::class, InvocationTargetException::class)
    override fun copyProperty(dest: Any, name: String, value: Any?) {
        if (value == null) return
        super.copyProperty(dest, name, value)
    }
}

fun copyPropertiesIgnoreNull(source: Any, destination: Any) {
    BeanUtilHelper.copyProperties(destination, source)
}