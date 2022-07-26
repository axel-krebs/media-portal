package com.afrtn.portal

import io.quarkus.runtime.QuarkusApplication
import io.vertx.ext.web.Router
import io.vertx.mutiny.core.Vertx
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.adapters.vertx.handlers.Routes
import minimvc.adapters.vertx.handlers.StaticResourceControllerHandler
import minimvc.controller.impl.StaticResourceController
import minimvc.view.Format
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

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
    }

    open fun init(@Observes router: Router?) {
        LOG.trace("io.vertx.ext.web.Router initialized.")
        Routes(io.vertx.mutiny.ext.web.Router.newInstance(router))
    }

    open fun init(@Observes router: io.vertx.mutiny.ext.web.Router?) {
        LOG.trace("io.vertx.mutiny.ext.web.Router initialized.")
        Routes(router!!)
    }
}