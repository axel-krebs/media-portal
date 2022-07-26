package minimvc.controller.impl

import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.mutiny.core.http.HttpServerRequest
import minimvc.controller.ControllerBase
import minimvc.model.ResourceModel
import minimvc.view.Format
import minimvc.view.ResourceView
import minimvc.view.ViewBase

/**
 * Purpose: Deliver static resources in folder 'static'; cannot be Singleton because of parameter.
 * @param resourcePath A file in the resource folder, may reside in subdirectory.
 */
open class StaticResourceController(private val resourcePath: String?) : ControllerBase() {

    //val staticResources : Map<String,ResourceModel> =
    private val resourceModel: ResourceModel = wrapModel(resourcePath)

    /**
     * The resource has not been found (invalid request URI)
     */
    private val error404Data: ResourceModel = wrapModel("404.html")

    private var responseContentType: Format = adviseContentType(resourcePath)

    // not so nice: the function 'adviceContentType'  will be called first. Workaround?
    constructor(resourcePath: String, preDefinedFormat: Format) : this(resourcePath) {
        responseContentType = preDefinedFormat
    }

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        val promisedResource: Promise<ViewBase> = Promise.promise<ViewBase>()
        val resourceView: ResourceView = if (resourceModel.isValid()) {
            ResourceView(resourceModel, responseContentType)
        } else {
            ResourceView(error404Data, Format.HTML5)
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
        // If not found? Errors must be checked on startup!
        return ResourceModel(data)
    }

    private fun adviseContentType(path: String?): Format {
        return Format.getFormatForPath(path)
    }
}