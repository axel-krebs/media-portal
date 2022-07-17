package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsController;
import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RailsControllerImpl implements RailsController {

    private static final Logger LOG = LoggerFactory.getLogger(RailsControllerImpl.class);
    final RailsModel _rm;
    final RailsViewAction[] _viewActions;

    public RailsControllerImpl(final RailsModel rm, RailsViewAction[] viewActions) {
        this._rm = rm;
        this._viewActions = viewActions;
    }

    @Override
    public Future<RailsViewAction> process(HttpServerRequest request) {
        // prepare model for view
        Map<String, Object> params = parseHttpRequest(request);
        // just a sample
        String referrer = request.getHeader("Referrer");
        if (null != referrer && referrer.equalsIgnoreCase("X-Mozilla")) {
            LOG.info("Request from Firefox");
        }
        // Execute 'control' logic.. async!
        return execute(params).future();
    }

    private Promise<RailsViewAction> execute(Map<String, Object> params) {
        Promise<RailsViewAction> promise = Promise.promise();
        // execute 'business logic' here ..
        RailsModel m = this._rm.apply(params);
        // which view??
        RailsViewAction retVal = this._viewActions[0];
        retVal.setModel(m);
        promise.complete(retVal);
        return promise;
    }

    private Map<String, Object> parseHttpRequest(HttpServerRequest request) {
        Map<String, Object> retMap = new HashMap<>();
        HttpMethod httpMethod = request.method();
        retMap.put("action", httpMethod.name());
        return retMap;
    }
}
