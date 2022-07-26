package minimvc.view

import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest
import minimvc.model.ModelBase

/**
 * Apply model data to template (HTML, XML, JSON et al.)
 */
abstract class ViewBase(val model : ModelBase, val outputFormat : Format) {
    abstract fun render(request: HttpServerRequest?): Future<Boolean?>?
}