package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import de.akrebs.web.vertx.ruby.ViewActionType;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;

public class RailsViewActionDownloadFile implements RailsViewAction {
    @Override
    public ViewActionType getType() {
        return ViewActionType.DOWNLOAD;
    }

    @Override
    public void setModel(RailsModel model) {

    }

    @Override
    public Future<Boolean> render(HttpServerRequest request) {
        Promise<Boolean> promise = Promise.promise();
        // TODO
        return promise.future();
    }
}
