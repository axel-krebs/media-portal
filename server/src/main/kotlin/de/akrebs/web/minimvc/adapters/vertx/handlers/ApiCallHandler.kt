package de.akrebs.web.minimvc.adapters.vertx.handlers

import de.akrebs.web.minimvc.controller.impl.ApiCallController
import de.akrebs.web.minimvc.view.Format
import io.vertx.core.Handler
import io.vertx.mutiny.core.http.HttpServerRequest
import io.vertx.mutiny.ext.web.RoutingContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ApiCallHandler(private val version: String) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ApiCallHandler::class.java.name)
    }

    private val apiController: ApiCallController = ApiCallController(version)

    override fun handle(rc: RoutingContext?) {
        val request: HttpServerRequest? = rc?.request()
        val requestedFormat: Format = Format.AJAX_JSON
        apiController.process(request)?.onSuccess { view -> view?.render(request, requestedFormat) }
            ?.onFailure { error -> LOG.error("Request unsuccessfully finished, {}", error) }
    }

}