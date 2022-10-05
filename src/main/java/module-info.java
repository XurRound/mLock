module me.xurround.mlock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;

    exports me.xurround.mlock to javafx.fxml, javafx.graphics;
    opens me.xurround.mlock.model to javafx.base;
    opens me.xurround.mlock.controller to javafx.fxml, javafx.graphics;
}