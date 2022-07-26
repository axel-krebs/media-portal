package com.afrtn.portal

import io.quarkus.arc.DefaultBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.Dependent
import javax.enterprise.context.SessionScoped

@SessionScoped
@Dependent
open class UserSession {
    val userAgent: String = ""

    @DefaultBean
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(UserSession.Companion::class.java)
    }
}