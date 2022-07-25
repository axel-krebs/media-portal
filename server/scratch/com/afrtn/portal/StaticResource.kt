package com.afrtn.portal.frontend;

import minimvc.controller.impl.StaticResourceController;
import minimvc.view.Format;
import io.quarkus.vertx.web.Route
import minimvc.view.ViewBase
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
open class StaticResource {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(StaticResource::class.java.name)
    }

    val indexController: StaticResourceController = StaticResourceController("index.html");

    @Route(path = "/", methods = [Route.HttpMethod.GET])
    fun index(rc: io.vertx.ext.web.RoutingContext) {
        val muti: io.vertx.mutiny.ext.web.RoutingContext = io.vertx.mutiny.ext.web.RoutingContext(rc);
        indexController.process(muti.request())?.onSuccess { view : ViewBase? -> view?.render(muti.request(), Format.HTML5) }
    }

    /*@Route(path = "/:resource", methods = Route.HttpMethod.GET)
    void getStatic(io.vertx.ext.web.RoutingContext rc, @PathParam("resource") String resourcePath) {
        io.vertx.mutiny.ext.web.RoutingContext mut = new io.vertx.mutiny.ext.web.RoutingContext(rc);
        StaticResourceController srController = new StaticResourceController(resourcePath);
        if (srController.getResourceModel().isValid()) {
            new StaticResourceController(resourcePath).process(mut.request()).onSuccess(
                    view -> view.render(mut.request(), Format.RAW_BYTES)
            ).onFailure(error -> {
                        LOG.info("Resource request handled successfully.");
                    }
            );
        } else {
            // return error
            LOG.warn("Static resource not found in StaticResourceController: {}", resourcePath);
        }
    }*/

}
