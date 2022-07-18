package de.akrebs.web.minimvc.adapters.vertx

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

/**
 * Adapt Vertx' Handler<RoutingContext>
 */
abstract class VertxRoutingContextHandlerAdapter : Handler<RoutingContext> {

}