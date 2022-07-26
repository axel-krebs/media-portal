package minimvc.adapters.vertx.handlers

import io.vertx.mutiny.ext.web.Router
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.controller.impl.StaticResourceController
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
        private const val CSS_PATH: String = "css/"
        private const val JS_PATH: String = "js/"
        private const val FONTS_PATH: String = "fonts/"
    }

    init {
        createRoutes(router)
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
        // supported, that is the handler will throw an error if a POST, PUT, DELETE etc. is sent to a resource.
        router["/:resource"].handler { routingContext: RoutingContext? ->
            val resourcePath: String? = routingContext?.pathParam("resource")
            val resourceController = StaticResourceController(resourcePath)
            StaticResourceControllerHandler(resourceController).handle(
                routingContext
            )
        }

        // 3. Request to css files
        router["/css/:resource"].handler { routingContext: RoutingContext? ->
            val resourcePath: String? = routingContext?.pathParam("resource")
            val resourceController = StaticResourceController(CSS_PATH.plus(resourcePath), Format.CSS3)
            StaticResourceControllerHandler(resourceController).handle(
                routingContext
            )
        }

        // 4. Request to JavaScript files
        router["/js/:resource"].handler { routingContext: RoutingContext? ->
            val resourcePath: String? = routingContext?.pathParam("resource")
            val resourceController = StaticResourceController(JS_PATH.plus(resourcePath), Format.JAVASCRIPT)
            StaticResourceControllerHandler(resourceController).handle(
                routingContext
            )
        }

        // 5. Request to fonts files
        router["/fonts/:resource"].handler { routingContext: RoutingContext? ->
            val resourcePath: String? = routingContext?.pathParam("resource")
            val resourceController =
                StaticResourceController(FONTS_PATH.plus(resourcePath)) // leave format decision to controller
            StaticResourceControllerHandler(resourceController).handle(
                routingContext
            )
        }

        // 3. API calls do have a separate path prefix ('api/v2/domain' for example).
        //String apiVersion = "v1";
        //ApiCallHandler apiHandler = new ApiCallHandler(apiVersion);
        //router.route("/api/" + apiVersion + "/*").handler(rc -> apiHandler.handle(rc));
    }
}