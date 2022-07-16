import de.akrebs.web.minimvc.controller.ControllerBase
import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.http.HttpServerRequest

open class IndexController : ControllerBase() {
    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        val promise : Promise<ViewBase> = Promise.promise()
        promise.complete(default())
        return promise.future()
    }
}

open class ResourceController : ControllerBase() {
    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        TODO("Not yet implemented")
    }

}