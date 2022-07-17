package de.akrebs.web.vertx.ruby;

import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;

public interface RailsViewAction {
    public ViewActionType getType();
    public void setModel(RailsModel model);
    public Future<Boolean> render(HttpServerRequest request);
}
