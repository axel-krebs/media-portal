package minimvc.controller.impl

import minimvc.controller.ControllerBase
import minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.mutiny.core.http.HttpServerRequest

class ApiCallController(val version : String) : ControllerBase() {

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        throw Error("NIH")
    }

}
