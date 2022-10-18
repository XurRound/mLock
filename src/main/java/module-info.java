module me.xurround.mlock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires com.google.gson;

    exports me.xurround.mlock to javafx.fxml, javafx.graphics;
    exports me.xurround.mlock.misc.enums to com.google.gson;
    opens me.xurround.mlock.layout.components to javafx.fxml;
    opens me.xurround.mlock.model to javafx.base, com.google.gson;
    opens me.xurround.mlock.controller to javafx.fxml, javafx.graphics;
}