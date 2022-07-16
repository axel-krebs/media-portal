package de.akrebs.web.minimvc.view

import io.vertx.core.Future
import io.vertx.core.http.HttpServerRequest

abstract class ViewBase {

    abstract fun render(request: HttpServerRequest?): Future<Boolean?>?
}