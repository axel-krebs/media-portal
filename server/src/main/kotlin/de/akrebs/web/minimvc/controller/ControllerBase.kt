package de.akrebs.web.minimvc.controller

import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest

/**
 * Provide a default view object ("index.html").
 */
abstract class ControllerBase {
    abstract fun process(request: HttpServerRequest?): Future<ViewBase?>?
}