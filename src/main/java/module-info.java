module Calculatrice {
    requires javafx.controls;
    requires javafx.fxml;

    opens Calculatrice to javafx.fxml;
    exports Calculatrice;
}
