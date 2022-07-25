package com.afrtn.portal

import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * The sole purpose of this resource controller is to handle calls to the root context.
 */
open class IndexResource {

    @Inject
    lateinit var userSession: UserSession

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun index(): TemplateInstance
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public fun get(): TemplateInstance? {

        return Templates.index()
    }
}