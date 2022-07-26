package minimvc.controller

import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest
import minimvc.view.ViewBase

class ApiCallController(val version: String) : ControllerBase() {

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        throw Error("NIH")
    }

}
