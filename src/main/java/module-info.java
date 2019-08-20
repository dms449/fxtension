module dms.fxtensions {
  requires transitive javafx.controls;
  requires javafx.graphics;
  requires javafx.fxml;
  requires java.xml.bind;
  requires java.desktop;

  exports dms.fxtensions.config;
//  exports dms.fxtensions.controls;
  exports dms.fxtensions.panes;
  exports dms.fxtensions.settings;
}
