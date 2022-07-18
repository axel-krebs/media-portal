package de.akrebs.web.minimvc.controller

import de.akrebs.web.minimvc.model.ResourceModel
import de.akrebs.web.minimvc.view.ResourceView
import de.akrebs.web.minimvc.view.ViewBase
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.http.HttpServerRequest

/**
 * Purpose: Deliver static resources in folder 'static'
 */
open class StaticResourceController(val resourcePath : String) : ControllerBase() {

    //val staticResources : Map<String,ResourceModel> =
    var data: ByteArray? = this.javaClass.classLoader.getResourceAsStream("static/".plus(resourcePath)).readAllBytes()

    override fun process(request: HttpServerRequest?): Future<ViewBase?>? {
        val promisedResource : Promise<ViewBase> = Promise.promise<ViewBase>()
        val resourcePath : String? = request?.path()
        val resourceView : ResourceView = ResourceView(getResourceModel(resourcePath))
        promisedResource.complete(resourceView)
        return promisedResource.future()
    }

    fun getResourceModel(resourcePath: String?) : ResourceModel {
        return ResourceModel(data)
    }
}