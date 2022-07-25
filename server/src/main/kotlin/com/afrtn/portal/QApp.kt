package com.afrtn.portal

//import minimvc.adapters.vertx.handlers.IndexHandler
//import minimvc.adapters.vertx.handlers.ResourceHandler
//import minimvc.controller.impl.StaticResourceController
import io.quarkus.runtime.QuarkusApplication
import io.vertx.ext.web.Router
import io.vertx.mutiny.core.Vertx
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.adapters.vertx.handlers.ControllerHandler
import minimvc.adapters.vertx.handlers.ResourceHandler
import minimvc.controller.impl.StaticResourceController
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
        createRoutes(io.vertx.mutiny.ext.web.Router(router))
    }

    open fun init(@Observes router: io.vertx.mutiny.ext.web.Router?) {
        LOG.trace("io.vertx.mutiny.ext.web.Router initialized")
        createRoutes(router!!)
    }

    private var indexController = StaticResourceController("index.html")

    /*
     * Attention: Vertx routes shall not interfere with REST endpoints!
     */
    private fun createRoutes(router: io.vertx.mutiny.ext.web.Router) {
        // 1. Handle the 'landing page': deliver the 'index.html' static file.
        val controllerHandler = ControllerHandler(indexController)
        // map the root context to static index.html
        router.route("/").handler { event: RoutingContext? ->
            controllerHandler.handle(
                event
            )
        }

        // 2. Any GET request (that is not an API call) shall deliver the requested resource; any
        // other HTTP method is not supported!
        val resourceHandler = ResourceHandler()
        router["/:resource"].handler { routingContext: RoutingContext? ->
            resourceHandler.handle(
                routingContext
            )
        }

        // 3. API calls do have a separate path prefix ('api/v2/domain' for example).
        //String apiVersion = "v1";
        //ApiCallHandler apiHandler = new ApiCallHandler(apiVersion);
        //router.route("/api/" + apiVersion + "/*").handler(rc -> apiHandler.handle(rc));
    }
}