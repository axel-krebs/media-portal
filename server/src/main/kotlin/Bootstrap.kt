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
            // 3. createRoutes
            val rbRouter = Router.router(vertx)
            createRoutes(rbRouter)
            // 4. Start server on port 8080
            val server = Vertx.vertx().createHttpServer().requestHandler(rbRouter)
            server.listen(8181)
        }

        fun createRoutes(router: Router) {
            val indexController = StaticResourceController("index.html")
            val indexHandler = IndexHandler(indexController)
            router.route("/").handler(indexHandler)

            val resourceHandler = ResourceHandler()
            router.get("/**").handler(resourceHandler)
        }
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

    //val resourceController : StaticResourceController
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ResourceHandler::class.java.name)
    }

    override fun handle(routeingContext: RoutingContext?) {
        val resourcePath: String? = routeingContext?.request()?.path()
        if (null != resourcePath) {
            StaticResourceController(resourcePath).process(routeingContext.request())?.onSuccess { view ->
                view?.render(routeingContext.request(), Format.RAW_BYTES)?.onSuccess {
                    LOG.info("Resource request handled successfully.")
                }
            }
        }
    }

}