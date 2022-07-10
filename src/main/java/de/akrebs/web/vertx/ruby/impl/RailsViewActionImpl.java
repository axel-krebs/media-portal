package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.ErbTemplateEngine;
import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import de.akrebs.web.vertx.ruby.ViewActionType;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class RailsViewActionImpl implements RailsViewAction {

    private static final Logger LOG = LoggerFactory.getLogger(RailsViewActionImpl.class);

    @Override
    public ViewActionType getType() {
        return ViewActionType.TEMPLATE;
    }

    private RailsModel _rm;

    private String _erbTemplate;

    ErbTemplateEngine _engine;

    public RailsViewActionImpl(String rubyScript) {
        this._erbTemplate = rubyScript;
        this._engine = new ErbTemplateEngineImpl();
    }

    @Override
    public void setModel(RailsModel model) {
        this._rm = model;
    }

    @Override
    public Future<Boolean> render(HttpServerRequest request) {
        HttpServerResponse response = request.response();
        Promise<Boolean> promise = Promise.promise();
        // TODO take the model and complete the erb template, write the result to output stream
        try {
            //this._engine.render()
            response.setChunked(true);
            response.write(_erbTemplate);
            response.end();
            promise.complete(true);
        } catch (Exception e) {
            LOG.error("An error has occurred, {}", e);
            promise.complete(true);
        }
        return promise.future();
    }
}
