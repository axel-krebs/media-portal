package minimvc.view

import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.mutiny.core.buffer.Buffer
import io.vertx.mutiny.core.http.HttpServerRequest
import minimvc.model.ResourceModel

class ResourceView(private val resource: ResourceModel, private val format: Format) : ViewBase(resource, format) {

    override fun render(request: HttpServerRequest?): Future<Boolean?>? {
        val p: Promise<Boolean> = Promise.promise<Boolean>()
        // requested resource assumed valid or not must be evaluated prior to rendering!
        request?.response()?.putHeader("Content-Type", format.contentType)
        val byteArray: ByteArray? = resource.getAsByteArray()
        val buffer: Buffer = Buffer.buffer(resource.getAsByteArray())
        // TODO: It's not very reactive to send the file in one rush..
        request?.response()?.putHeader("Content-Length", byteArray?.size.toString())
        request?.response()?.sendAndForget(buffer)
        request?.response()?.end()
        p.complete(true)
        return p.future()
    }

}