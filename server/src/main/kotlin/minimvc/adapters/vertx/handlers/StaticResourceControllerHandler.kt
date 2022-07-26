package minimvc.adapters.vertx.handlers

import io.vertx.core.Handler
import io.vertx.core.http.HttpMethod
import io.vertx.mutiny.core.http.HttpServerRequest
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.controller.impl.StaticResourceController
import minimvc.view.ViewBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Special case for de.akrebs.web.minimvc.adapters.vertx.handlers.ResourceHandler: path not given..
 */
open class StaticResourceControllerHandler(val controller: StaticResourceController) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(StaticResourceControllerHandler::class.java.name)
    }

    override fun handle(event: RoutingContext?) {
        val request: HttpServerRequest? = event?.request()
        val httpMethod: HttpMethod? = request?.method()
        if (httpMethod == HttpMethod.GET) {
            controller.process(request)
                // Memento: If resource is not found, controller replies with error view already.
                ?.onSuccess { viewAction: ViewBase? ->
                    viewAction?.render(request)?.onSuccess { success ->
                        if (success!!) {
                            LOG.info("Successfully rendered resource.")
                            //event?.next()
                        } else {
                            LOG.info("The renderer returned FALSE!")
                            //event?.next() stop routing events?
                        }
                    }?.onFailure { error ->
                        LOG.error("Request unsuccessfully rendered. {}", error)
                        //event?.next() stop routing events?
                    }
                }
                ?.onFailure { error ->
                    LOG.error("Controller could not process request. {}", error)
                    //event?.next() stop routing events?
                }
        } else {
            throw Error("HTTP methods other than GET are not supported in IndexHandler.")
        }
    }
}