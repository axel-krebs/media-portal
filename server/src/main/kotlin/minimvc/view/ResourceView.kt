package minimvc.view

import minimvc.model.ModelBase
import minimvc.model.ResourceModel
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.mutiny.core.buffer.Buffer
import io.vertx.mutiny.core.http.HttpServerRequest

class ResourceView(private val resource: ResourceModel) : ViewBase(resource) {


    override fun render(request: HttpServerRequest?, format: Format): Future<Boolean?>? {
        val p: Promise<Boolean> = Promise.promise<Boolean>()
        // requested resource assumed valid or not must be evaluated prior to rendering!
        request?.response()?.putHeader("Content-Type", format.contentType)
        val buffer: Buffer = Buffer.buffer(resource.getAsByteArray())
        request?.response()?.send(buffer)
        request?.response()?.end()
        p.complete()
        return p.future()
    }

}