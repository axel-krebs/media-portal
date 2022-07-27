package com.afrtn.portal

import com.afrtn.portal.frontend.LoginController
import io.quarkus.runtime.QuarkusApplication
import io.vertx.ext.web.Router
import io.vertx.mutiny.core.Vertx
import minimvc.adapters.vertx.handlers.FormDataControllerHandler
import minimvc.adapters.vertx.handlers.Routes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

@ApplicationScoped
open class QApp : QuarkusApplication {

    companion object {
        var LOG: Logger = LoggerFactory.getLogger(QApp::class.java)
    }

    @Throws(Exception::class)
    override fun run(vararg args: String?): Int {
        System.out.println("QApp started..")
        return 0
    }

    open fun init(@Observes vertx: Vertx?) {
        LOG.trace("Vertx initialized.")
        // deploy verticles
    }

    @Inject
    lateinit var loginController: LoginController

    open fun init(@Observes router: Router?) {
        LOG.trace("io.vertx.ext.web.Router initialized.")
        val routes = Routes(io.vertx.mutiny.ext.web.Router.newInstance(router))

        routes.addFormHandler("/login", FormDataControllerHandler(loginController))
    }

    open fun init(@Observes router: io.vertx.mutiny.ext.web.Router?) {
        LOG.trace("io.vertx.mutiny.ext.web.Router initialized.")
        Routes(router!!)
    }
}