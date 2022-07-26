package minimvc.adapters.vertx.handlers

import io.vertx.core.Handler
import io.vertx.mutiny.core.http.HttpServerRequest
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.controller.ApiCallController
import minimvc.view.ViewBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ApiCallHandler(private val version: String) : Handler<RoutingContext> {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ApiCallHandler::class.java.name)
    }

    private val apiController: ApiCallController = ApiCallController(version)

    override fun handle(rc: RoutingContext?) {
        val request: HttpServerRequest? = rc?.request()
        apiController.process(request)?.onSuccess { view: ViewBase? -> view?.render(request) }
            ?.onFailure { error -> LOG.error("Request unsuccessfully finished, {}", error) }
    }

}