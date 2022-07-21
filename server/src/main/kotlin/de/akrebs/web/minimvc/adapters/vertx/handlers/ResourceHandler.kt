package de.akrebs.web.minimvc.adapters.vertx.handlers

import de.akrebs.web.minimvc.controller.impl.StaticResourceController
import de.akrebs.web.minimvc.view.Format
import io.vertx.core.Handler
import io.vertx.mutiny.ext.web.RoutingContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ResourceHandler : Handler<RoutingContext> {

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