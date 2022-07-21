package de.akrebs.web.minimvc.controller.impl

import de.akrebs.web.minimvc.controller.ControllerBase
import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest

class ApiCallController(val version : String) : ControllerBase() {

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        throw Error("NIH")
    }

}
