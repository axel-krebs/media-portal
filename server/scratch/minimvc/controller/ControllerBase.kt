package minimvc.controller

import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest
import minimvc.view.ViewBase

/**
 * Provide a default view object ("index.html").
 */
abstract class ControllerBase {
    abstract fun process(request: HttpServerRequest?): Future<ViewBase?>?
}