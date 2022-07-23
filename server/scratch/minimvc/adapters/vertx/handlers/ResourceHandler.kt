package minimvc.adapters.vertx.handlers

import minimvc.controller.impl.StaticResourceController
import minimvc.view.Format
import io.vertx.core.Handler
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.view.ViewBase
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
            StaticResourceController(resourcePath).process(routingContext.request())?.onSuccess { view : ViewBase? ->
                view?.render(routingContext.request(), Format.RAW_BYTES)?.onSuccess {
                    LOG.info("Resource request handled successfully.")
                }?.onFailure { error -> LOG.error("Couln't retrieve resource: {}", error) }
            }
        }
    }
}