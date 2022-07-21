package de.akrebs.web.minimvc.view

import de.akrebs.web.minimvc.model.ModelBase
import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest

/**
 * Apply model data to template (HTML, XML, JSON et al.)
 */
abstract class ViewBase(val model : ModelBase) {
    abstract fun render(request: HttpServerRequest?, format : Format): Future<Boolean?>?
}