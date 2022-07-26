package minimvc.controller

import io.vertx.core.Future
import io.vertx.mutiny.core.MultiMap
import io.vertx.mutiny.core.http.HttpServerRequest
import minimvc.model.FormDataModel
import minimvc.view.ViewBase

/**
 * Reads the parameters from the request and passes a Map to the internal processor.
 */
open class FormDataController() : ControllerBase() {

    // class parameter would be difficult with CDI.
    lateinit var model: FormDataModel

    constructor(model: FormDataModel) : this() {
        this.model = model
    }

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        val mm: MultiMap? = request?.params()
        val m: Map<String, Any> = HashMap()
        model.apply(m)
        return null;
    }
}