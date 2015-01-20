package MentalMathFX;

import MentalMath.*;

import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.input.*;


public class FormController implements Initializable {

  @FXML
  private TextField qField, aField, lField;
  @FXML
  private Text countText;

  private int count;
  private Question q;
  private Answer an;
  private int ansLength;

  public FormController() {
    count = 0;
  }

  @FXML
  public void aFieldHandle(KeyEvent ev) {
    // Why not work for -?
    if (aField.getCharacters().length() == ansLength) {
      // Exception handling here
      String input = aField.getCharacters().toString();
      aField.setText("");
      if (input.equals(an.getAnswer())) {
        countText.setText(Integer.toString(++count));
      } else {
        countText.setText("Wrong!");
      }
      setQuestion();
    }
  }

  @FXML
  public void lengthHandle(KeyEvent ev) {
    if (ev.getCode() == KeyCode.ENTER)
      setQuestion();
  }

  private void setQuestion() {
    try {
      int n = Integer.parseInt(lField.getCharacters().toString());
      q = new Question(n);
      an = new Answer(q);
      qField.setText(q.toString());
      ansLength = an.getAnswer().length();
    } catch (Exception e) {
      // Error
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    qField.setEditable(false);
    lField.setText("4");
    setQuestion();
  }

}
