package de.akrebs.web.minimvc.controller

import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.core.http.HttpServerRequest

/**
 * Provide a default view object ("index.html").
 */
 abstract class ControllerBase {

    /**
     * The 'default' view - same as calling 'index'
     */
    companion object DefaultView : ViewBase() {
        override fun render(request: HttpServerRequest?): Future<Boolean?>? {
            request?.response()?.end("index.html")
            return Future.succeededFuture()
        }

    }

    fun default(): ViewBase {
        return DefaultView
    }

    abstract fun process(request: HttpServerRequest?): Future<ViewBase?>?
}