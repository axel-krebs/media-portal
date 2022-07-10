package de.akrebs.web;

import de.akrebs.web.vertx.ruby.RailsController;
import de.akrebs.web.vertx.ruby.RailsControllerHandler;
import de.akrebs.web.vertx.ruby.RailsModel;
import de.akrebs.web.vertx.ruby.RailsViewAction;
import de.akrebs.web.vertx.ruby.impl.*;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PortalApp {

    // enable logging
    static {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging" +
                ".Log4jLogDelegateFactory");
    }
    static final Logger LOG = LoggerFactory.getLogger("my-logger");

    public static void main(String[] args) {

        final String podName = System.getenv("HOSTNAME") != null ? System.getenv("HOSTNAME") :
                "localhost";
        VertxOptions opts = new VertxOptions();
        Vertx vertx = Vertx.vertx(opts);
        String myFileName = "deployment.json";
        ConfigRetriever retriever = ConfigRetriever.create(vertx,
                new ConfigRetrieverOptions().addStore(new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", myFileName))));

        retriever.getConfig(json -> {
            JsonObject a = json.result().getJsonObject("a");
            vertx.deployVerticle("test.rb", new DeploymentOptions().setConfig(a));
        });

        Router rbRouter = Router.router(vertx);
        rbRouter.get("/").handler(req -> {
            LOG.info("Processing incoming request");
            req.response().end("Hello World! from host: " + podName + "\n");
        });

        try {
            applyRoR(rbRouter);
            HttpServer server = Vertx.vertx().createHttpServer().requestHandler(rbRouter);
            // start server which automatic closes when jvm terminates
            server.listen(8080);
            LOG.info("vert.x HTTP server running on port 8080");
        } catch (Exception e) {
            LOG.error("Something went wrong..", e);
        }
    }

    private static void applyRoR(Router rbRouter) throws Exception {
        InputStream is = PortalApp.class.getResourceAsStream("test.html.erb");
        InputStreamReader isr = new InputStreamReader(is);
        String testScript = "";
        try (BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            testScript = sb.toString();
        } catch (Exception e) {
            LOG.error("Couldn't read config file");
        }
        RailsModel rm2 = new RailsModelImpl();
        RailsViewAction fwdIndex = new RailsViewActionImpl("test2");
        RailsViewAction[] forwardActionViews = new RailsViewAction[]{fwdIndex};
        RailsController test2 = new RailsControllerImpl(rm2, forwardActionViews);
        RailsViewAction rvaForward = new RailsViewActionForwardImpl(test2);
        RailsControllerHandler rc2Handler = new RailsControllerHandlerImpl(test2);
        rbRouter.get("/test2").handler(rc2Handler);

        RailsViewAction rv = new RailsViewActionImpl(testScript);
        RailsViewAction[] viewActions = new RailsViewAction[]{rvaForward, rv};
        RailsModel rm1 = new RailsModelImpl();
        RailsController rc = new RailsControllerImpl(rm1, viewActions);
        RailsControllerHandler rcHandler = new RailsControllerHandlerImpl(rc);

        rbRouter.get("/test").handler(rcHandler);
    }
}
