package MentalMathFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;


public class Form extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Mental Math");
    GridPane a = FXMLLoader.load(getClass().getResource("Form.fxml"));
    Parent root = a;
    Scene scene = new Scene(root, 400, 400);

    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
