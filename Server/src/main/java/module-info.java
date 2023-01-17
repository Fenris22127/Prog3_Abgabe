module de.medieninformatik.server {
    requires java.logging;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.ws.rs;
    requires jersey.server;
    requires jersey.container.grizzly2.http;
    requires grizzly.http.server;

    opens de.medieninformatik.server to javafx.fxml;
    opens de.medieninformatik.server.rest to javafx.fxml;
    exports de.medieninformatik.server;
    exports de.medieninformatik.server.objects;
}