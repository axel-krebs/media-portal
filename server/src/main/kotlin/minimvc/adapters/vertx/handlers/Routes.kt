package minimvc.adapters.vertx.handlers

import io.vertx.mutiny.ext.web.Router
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.controller.StaticResourceController
import minimvc.view.Format
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Create routes using the Mutiny Router.
 */
open class Routes(private val router: Router) {

    private var indexController = StaticResourceController("index.html", Format.HTML5)

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(Routes::class.java)
    }

    init {
        createRoutes(router)
    }

    /**
     * Add a route to a static resource. Only GET requests are possible!
     */
    public fun addStatic(route: String, handler: StaticResourceControllerHandler) {
        router.get(route).handler { event ->
            handler.handle(event)
        };
    }

    /**
     * Add a route to a form data processor.
     */
    public fun addFormHandler(route: String, handler: FormDataControllerHandler) {
        router.post(route).handler { event -> handler.handle(event) }
    }

    /*
    * Attention: Vertx routes shall not interfere with REST endpoints which are defined in application resources (REST)!
    */
    private fun createRoutes(router: Router) {

        // 1. Handle the 'landing page': deliver the 'index.html' static file.
        val staticResourceControllerHandler = StaticResourceControllerHandler(indexController)
        // map the root context to static index.html controller
        router.route("/").handler { event: RoutingContext? ->
            staticResourceControllerHandler.handle(
                event
            )
        }

        // 2. Any GET request (that is not an API call) shall deliver the requested resource; other HTTP methods are not
        // supported, that is the handler will throw an error if a POST, PUT, DELETE etc. is sent to a resource. For now,
        // it is assumed that the requested path corresponds to the path under the 'static' resource folder
        router.routeWithRegex("(?<somePath>[^;]*?)\\.(?<someExtension>[a-z]+)$")
            .handler { routingContext: RoutingContext? ->
                val somePath: String? = routingContext?.pathParam("somePath")
                val someExtension: String? = routingContext?.pathParam("someExtension")
                // empty plus something is something - it's not mathematics!!
                val staticPath = somePath.plus(".").plus(someExtension)
                val resourceController = StaticResourceController(staticPath)
                StaticResourceControllerHandler(resourceController).handle(
                    routingContext
                )
            }

        // 666. HELL I didn't know how (above) to make this first path element optional !!! Now gets 'favicon.ico* ..
//        router.routeWithRegex("\\/(?<someResource>[^\\/]+)\\.(?<someExtension>[^\\.]+)$")
//            .handler { routingContext: RoutingContext? ->
//                val someResource: String? = routingContext?.pathParam("someResource")
//                val someExtension: String? = routingContext?.pathParam("someExtension")
//                // empty plus something is something - it's not mathematics!!
//                val staticPath = someResource.plus(".").plus(someExtension)
//                val resourceController = StaticResourceController(staticPath)
//                StaticResourceControllerHandler(resourceController).handle(
//                    routingContext
//                )
//            }
    }
}