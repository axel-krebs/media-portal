package de.akrebs.web.vertx.ruby;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Basically a wrapper around the RailsController.
 */
public interface RailsControllerHandler extends Handler<RoutingContext> {

}
