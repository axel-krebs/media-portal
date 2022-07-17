package de.akrebs.web.minimvc.view

import de.akrebs.web.minimvc.model.ModelBase
import de.akrebs.web.minimvc.model.ResourceModel
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpServerRequest

class ResourceView(private val resource: ResourceModel) : ViewBase(resource) {

    override fun render(request: HttpServerRequest?, format: Format): Future<Boolean?>? {
        val p : Promise<Boolean> = Promise.promise<Boolean>()
        val buffer : Buffer = Buffer.buffer(resource.getAsByteArray())
        request?.response()?.send(buffer)
        request?.response()?.end()
        p.complete()
        return p.future()
    }

}