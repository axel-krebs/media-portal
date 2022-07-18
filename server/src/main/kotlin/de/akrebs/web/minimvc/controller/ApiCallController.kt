package de.akrebs.web.minimvc.controller

import de.akrebs.web.minimvc.controller.ControllerBase
import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.core.http.HttpServerRequest

class ApiCallController(val version : String) : ControllerBase() {

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        throw Error("NIH")
    }

}
