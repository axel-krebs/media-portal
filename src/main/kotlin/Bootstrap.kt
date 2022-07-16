import de.akrebs.web.minimvc.controller.ControllerBase
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
import java.io.File

/**
 * Read configuration and start framework.
 */
class Bootstrap {

    companion object {
        @JvmStatic fun main(vararg args: String) {
            // 1. Read configuration
            val scriptFile = File(args[0])
            println("Reading script $scriptFile")
            // 2. Establish cluster
            val opts = VertxOptions()
            val vertx = Vertx.vertx(opts)
            // 3. createRoutes
            val rbRouter = Router.router(vertx)
            createRoutes(rbRouter)
            // 4. Start server on port 8080

            //applyRoR(rbRouter);
            //WebApp.start(rbRouter);
            val server = Vertx.vertx().createHttpServer().requestHandler(rbRouter)
            server.listen(8080)
        }

        fun createRoutes(router: Router) {
            val indexController = IndexController()
            val indexHandler = IndexHandler(indexController)
            router.route("/").handler(indexHandler)
        }
    }
}

class IndexHandler(val controller: ControllerBase) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(IndexHandler::class.java.name)
    }

    override fun handle(event: RoutingContext?) {
        val request: HttpServerRequest? = event?.request()
        val httpMethod: HttpMethod? = request?.method()
        if (httpMethod == HttpMethod.GET) {
            controller.process(request)?.onSuccess { viewAction: ViewBase? -> viewAction?.render(request) }
                ?.onFailure { error -> LOG.error("Request unsuccessfully processed. {}", error) }
        } else {
            throw Error("HTTP methods other than GET are not supported in IndexHandler.")
        }
    }

}