package de.akrebs.web.vertx.ruby;

import java.util.Map;

public interface RailsModel {
    RailsModel apply(Map<String, Object> params);
}
