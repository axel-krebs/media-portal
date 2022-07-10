package de.akrebs.web.vertx.ruby;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;

public interface RailsViewAction {

    public ViewActionType getType();

    public void setModel(RailsModel model);
    public Promise<Boolean> render(HttpServerRequest request);
}
