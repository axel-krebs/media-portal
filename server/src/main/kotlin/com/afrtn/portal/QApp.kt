package com.afrtn.portal

//import minimvc.adapters.vertx.handlers.IndexHandler
//import minimvc.adapters.vertx.handlers.ResourceHandler
//import minimvc.controller.impl.StaticResourceController
import io.quarkus.runtime.QuarkusApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
open class QApp : QuarkusApplication {

    companion object {
        var LOG : Logger = LoggerFactory.getLogger(QApp::class.java)
    }

    @Throws(Exception::class)
    override fun run(vararg args: String?): Int {
        LOG.info("PortalApp - Logger is working..")
        return 0
    }

//    fun init(@Observes vertx: Vertx?) {
//        LOG.trace("Vertx initialized.")
//    }

//    fun init(@Observes router: Router?) {
//        LOG.trace("io.vertx.ext.web.Router initialized.")
//        createRoutes(io.vertx.mutiny.ext.web.Router(router))
//    }

//    fun init(@Observes router: io.vertx.mutiny.ext.web.Router?) {
//        LOG.trace("io.vertx.mutiny.ext.web.Router initialized")
//        createRoutes(router!!)
//    }

    //private var indexController = StaticResourceController("index.html")

    //private fun createRoutes(router: io.vertx.mutiny.ext.web.Router) {
        // 1. Handle the 'landing page': deliver the 'index.html' static file.
//        val indexHandler = IndexHandler(indexController)
//        router.route("/").handler { event: RoutingContext? ->
//            indexHandler.handle(
//                event
//            )
//        }

        // 2. Any GET request (that is not an API call) shall deliver the requested resource; any
        // other HTTP method is not supported!
//        val resourceHandler = ResourceHandler()
//        router["/:resource"].handler { routingContext: RoutingContext? ->
//            resourceHandler.handle(
//                routingContext
//            )
//        }

        // 3. API calls do have a separate path prefix ('api/v2/domain' for example).
        //String apiVersion = "v1";
        //ApiCallHandler apiHandler = new ApiCallHandler(apiVersion);
        //router.route("/api/" + apiVersion + "/*").handler(rc -> apiHandler.handle(rc));
   // }
}