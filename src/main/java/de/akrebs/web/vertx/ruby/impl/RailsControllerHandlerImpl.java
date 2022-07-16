package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsController;
import de.akrebs.web.vertx.ruby.RailsControllerHandler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

public class RailsControllerHandlerImpl implements RailsControllerHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RailsControllerHandlerImpl.class);
    private final RailsController _rc;

    public RailsControllerHandlerImpl(final RailsController rc) {
        this._rc = rc;
    }

    @Override
    public void handle(@NotNull RoutingContext event) {
        HttpMethod httpMethod = event.request().method();
        if (httpMethod.equals(HttpMethod.GET)){

        }
        this._rc.process(event.request()).onSuccess(railsViewAction -> {
            railsViewAction.render(event.request()).onSuccess(success -> {
                LOG.info("RailsControllerHandlerImpl handled request successful.");
            }).onFailure(error -> {
                LOG.error("Error rendering request!", error);
            });
        }).onFailure(processError -> {
            LOG.error("RailsController ended erroneously..", processError);
        });
    }

}
