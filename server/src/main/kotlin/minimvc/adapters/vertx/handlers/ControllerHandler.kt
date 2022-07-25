package minimvc.adapters.vertx.handlers

import minimvc.view.Format
import minimvc.view.ViewBase
import io.vertx.core.Handler
import io.vertx.core.http.HttpMethod
import io.vertx.mutiny.core.http.HttpServerRequest
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.controller.ControllerBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Special case for de.akrebs.web.minimvc.adapters.vertx.handlers.ResourceHandler: path not given..
 */
open class ControllerHandler(val controller: ControllerBase) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ControllerHandler::class.java.name)
    }

    override fun handle(event: RoutingContext?) {
        val request: HttpServerRequest? = event?.request()
        val httpMethod: HttpMethod? = request?.method()
        val defaultFormat: Format = Format.HTML5 // assume browser
        if (httpMethod == HttpMethod.GET) {
            controller.process(request)
                // Memento: If resource is not found, controller replies with error view already.
                ?.onSuccess { viewAction: ViewBase? ->
                    viewAction?.render(request, defaultFormat)
                }
                // This error happens while rendering:
                ?.onFailure { error ->
                    LOG.error("Request unsuccessfully rendered. {}", error)
                }
        } else {
            throw Error("HTTP methods other than GET are not supported in IndexHandler.")
        }
    }
}