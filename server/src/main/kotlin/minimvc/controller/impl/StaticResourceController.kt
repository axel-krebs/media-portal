package minimvc.controller.impl

import minimvc.controller.ControllerBase
import minimvc.model.ResourceModel
import minimvc.view.ResourceView
import minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.mutiny.core.http.HttpServerRequest

/**
 * Purpose: Deliver static resources in folder 'static'; cannot be Singleton because of parameter.
 * @param resourcePath A file in the resource folder, may reside in subdirectory.
 */
open class StaticResourceController(val resourcePath: String) : ControllerBase() {

    //val staticResources : Map<String,ResourceModel> =
    private val resourceModel: ResourceModel = wrapModel(resourcePath)

    val error500Data: ResourceModel = wrapModel("500.html")

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        val promisedResource: Promise<ViewBase> = Promise.promise<ViewBase>()
        val resourceView: ResourceView = if (resourceModel.isValid()) {
            ResourceView(resourceModel)
        } else {
            ResourceView(error500Data)
        }
        promisedResource.complete(resourceView)
        return promisedResource.future()
    }

    /*
    * Memento: this can already be done with RESTEasy, MVC legacy here..
     */
    private fun wrapModel(resourcePath: String?): ResourceModel {
        val data: ByteArray? =
            this.javaClass.classLoader.getResourceAsStream("static/".plus(resourcePath))?.readAllBytes()
        // If not found? Errors must be checked on startup
        return ResourceModel(data)
    }
}