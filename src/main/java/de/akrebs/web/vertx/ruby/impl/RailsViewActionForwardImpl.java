package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsController;
import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import de.akrebs.web.vertx.ruby.ViewActionType;
import io.vertx.core.Future;
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
    public Future<Boolean> render(HttpServerRequest request) {
        Promise<Boolean> promise = Promise.promise();
        this.controller.process(request).onSuccess(rvs -> {
            // since we're intercepting 'normal' rendering, it must be done manually here..
            rvs.render(request);
            promise.complete(true);
        }).onFailure(error -> {
            promise.complete(true);
        });
        return promise.future();
    }
}
