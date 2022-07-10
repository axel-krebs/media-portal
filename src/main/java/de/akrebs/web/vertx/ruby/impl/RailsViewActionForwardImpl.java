package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsController;
import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import de.akrebs.web.vertx.ruby.ViewActionType;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;

public class RailsViewActionForwardImpl implements RailsViewAction {

    RailsController controller;

    RailsModel model;

    public RailsViewActionForwardImpl(RailsController controller) {
        this.controller = controller;
    }

    @Override
    public ViewActionType getType() {
        return ViewActionType.FORWARD;
    }

    @Override
    public void setModel(RailsModel model) {
        this.model = model;
    }

    @Override
    public Promise<Boolean> render(HttpServerRequest request) {
        Promise<Boolean> promise = Promise.promise();
        this.controller.process(request);
        promise.complete(true);
        return promise;
    }
}
