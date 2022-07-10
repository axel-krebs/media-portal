package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsController;
import de.akrebs.web.vertx.ruby.RailsControllerHandler;
import io.vertx.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

public class RailsControllerHandlerImpl implements RailsControllerHandler {
    private final RailsController _rc;
    public RailsControllerHandlerImpl(final RailsController rc) {
        this._rc = rc;
    }

    @Override
    public void handle(@NotNull RoutingContext event) {
        this._rc.process(event.request()).onSuccess(railsViewAction -> {
            railsViewAction.render(event.request()).complete();
        }).onFailure(error -> {
            event.response().send("A failure occurred.");
        });
    }


}
