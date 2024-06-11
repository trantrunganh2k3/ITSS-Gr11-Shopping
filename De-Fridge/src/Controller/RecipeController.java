package Controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeController implements Initializable {


    public Label lblTitle;
    public Label lblDes;
    public Button btnDetail;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener () {
        btnDetail.setOnAction(event -> {
            try {
                onDetail();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onDetail () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/fxml/favoriteRecipes/recipeDetail.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
