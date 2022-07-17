package de.akrebs.web.vertx.ruby;

import de.akrebs.web.vertx.ruby.impl.RailsViewActionImpl;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;

public interface RailsController {

    default RailsViewAction getErrorPage(int httpError){
        return new RailsViewActionImpl("500.html");
    }

    public Future<RailsViewAction> process(HttpServerRequest request);
}
