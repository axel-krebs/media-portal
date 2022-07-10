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
    final RailsViewAction[] viewActions;

    public RailsControllerImpl(final RailsModel rm, RailsViewAction[] viewActions) {
        this._rm = rm;
        this.viewActions = viewActions;
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
        Promise<RailsModel> action = execute(params);
        Promise<RailsViewAction> promisedView = Promise.promise();
        action.future().compose(this::applyModelAndChoseView).onSuccess(rva -> {
            promisedView.complete(rva);
            LOG.info("Completed successfully!");
        }).onFailure(error -> {
            LOG.error("An error occurred.");
            promisedView.complete(errorPage);
        });
        return promisedView.future();
    }

    private Promise<RailsModel> execute(Map<String, Object> params){
        Promise<RailsModel> promise = Promise.promise();
        // execute 'business logic' here ..
        this._rm.apply(params);
        promise.complete(this._rm);
        return promise;
    }

    private Future<RailsViewAction> applyModelAndChoseView(RailsModel model){
        Promise<RailsViewAction> promise = Promise.promise();
        RailsViewAction chosen = this.viewActions[1];
        chosen.setModel(model);
        promise.complete(chosen);
        return promise.future();
    }

    private Map<String, Object> parseHttpRequest(HttpServerRequest request) {
        Map<String, Object> retMap = new HashMap<>();
        HttpMethod httpMethod = request.method();
        retMap.put("action", httpMethod.name());
        return retMap;
    }
}
