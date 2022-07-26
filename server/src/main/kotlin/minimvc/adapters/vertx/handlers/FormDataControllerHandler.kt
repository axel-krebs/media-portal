package minimvc.adapters.vertx.handlers

import io.vertx.core.Handler
import io.vertx.mutiny.ext.web.RoutingContext
import minimvc.controller.FormDataController
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Wrapper around a form handling controller. Does most of the error logging.
 */
open class FormDataControllerHandler(private val formDataController: FormDataController) : Handler<RoutingContext> {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(FormDataControllerHandler::class.java)
    }

    override fun handle(p0: RoutingContext?) {
        formDataController.process(p0?.request())?.onSuccess { view ->
            view?.render(p0?.request())?.onSuccess {
                LOG.info("Form data request successfully rendered.")
            }?.onFailure { error ->
                LOG.error("Error rendering the form data request. {}", error)
            }
        }?.onFailure { error ->
            LOG.error("Form data request could not be processed successfully. {}", error)
        }
    }

}
