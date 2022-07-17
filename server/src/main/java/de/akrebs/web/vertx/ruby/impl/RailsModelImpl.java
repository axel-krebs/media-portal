package de.akrebs.web.vertx.ruby.impl;

import de.akrebs.web.vertx.ruby.RailsModel;

import java.util.Map;

public class RailsModelImpl implements RailsModel {
    @Override
    public RailsModelImpl apply(Map<String, Object> params) {
        return this;
    }
}
