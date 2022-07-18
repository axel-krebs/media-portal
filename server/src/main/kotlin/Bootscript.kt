import de.akrebs.web.minimvc.controller.ApiCallController
import de.akrebs.web.minimvc.controller.StaticResourceController
import de.akrebs.web.minimvc.view.Format
import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Read configuration and start framework.
 */
class Bootstrap {

    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            // 1.Start Quarkus
            //Quarkus.run(args);
            // 2. Establish cluster
            val opts = VertxOptions()
            val vertx = Vertx.vertx(opts)
            // 3. create routes, th.i. register 'handlers'
            val rbRouter = Router.router(vertx)
            createRoutes(rbRouter)
            // 4. Deploy verticles, if any..

            // 5. Start server on port 8080
            val server = Vertx.vertx().createHttpServer().requestHandler(rbRouter)
            server.listen(8181)
        }

        fun createRoutes(router: Router) {

            // 1. 'IndexHandler' handles the 'landing page': deliver the 'index.html' static file.
            val indexController = StaticResourceController("index.html")
            val indexHandler = IndexHandler(indexController)
            router.route("/").handler(indexHandler)

            // 2. Any GET request (that is not an API call) shall deliver the requested resource; any other HTTP method
            // is not supported!
            val resourceHandler = ResourceHandler()
            router.get("/:resource").handler(resourceHandler)

            // 3. API calls do have a separate path prefix ('api/v2/domain' for example).
            val apiVersion = "v1"
            val apiHandler = ApiCallHandler(apiVersion)
            router.route("/api/".plus(apiVersion).plus("/**")).handler(apiHandler)
        }
    }
}

class ApiCallHandler(private val version: String) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ApiCallHandler::class.java.name)
    }

    private val apiController: ApiCallController = ApiCallController(version)

    override fun handle(rc: RoutingContext?) {
        val request: HttpServerRequest? = rc?.request()
        val requestedFormat: Format = Format.AJAX_JSON
        apiController.process(request)?.onSuccess { view -> view?.render(request, requestedFormat) }
            ?.onFailure { error -> LOG.error("Request unsuccessfully finished, {}", error) }
    }

}

/**
 * Special case for ResourceHandler: path not given..
 */
class IndexHandler(val controller: StaticResourceController) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(IndexHandler::class.java.name)
    }

    override fun handle(event: RoutingContext?) {
        val request: HttpServerRequest? = event?.request()
        val httpMethod: HttpMethod? = request?.method()
        val defaultFormat: Format = Format.HTML5 // assume browser
        if (httpMethod == HttpMethod.GET) {
            controller.process(request)
                ?.onSuccess { viewAction: ViewBase? -> viewAction?.render(request, defaultFormat) }
                ?.onFailure { error -> LOG.error("Request unsuccessfully processed. {}", error) }
        } else {
            throw Error("HTTP methods other than GET are not supported in IndexHandler.")
        }
    }
}

class ResourceHandler : Handler<RoutingContext> {

    /**
     * Static initializations
     */
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ResourceHandler::class.java.name)
    }

    override fun handle(routingContext: RoutingContext?) {
        val resourcePath: String? = routingContext?.pathParam("resource")
        if (null != resourcePath) {
            StaticResourceController(resourcePath).process(routingContext.request())?.onSuccess { view ->
                view?.render(routingContext.request(), Format.RAW_BYTES)?.onSuccess {
                    LOG.info("Resource request handled successfully.")
                }?.onFailure { error -> LOG.error("Couln't retrieve resource: {}", error) }
            }
        }
    }

}