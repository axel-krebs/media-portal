package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import de.akrebs.web.vertx.ruby.ViewActionType;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class RailsViewActionRedirectImpl implements RailsViewAction {

    RailsModel model;

    @Override
    public ViewActionType getType() {
        return ViewActionType.REDIRECT;
    }

    @Override
    public void setModel(RailsModel model) {
        this.model = model;
    }

    @Override
    public Promise<Boolean> render(HttpServerRequest request) {
        HttpServerResponse response = request.response();
        response.send("Redirect to -");
        return null;
    }
}
