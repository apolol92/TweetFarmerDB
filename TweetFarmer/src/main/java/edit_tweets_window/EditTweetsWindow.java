package edit_tweets_window;

import edit_tweets_window.scenes.EditTweetsScene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Created by gross on 11.05.2016.
 * This Window give the user the possibility to edit his tweets
 */
public class EditTweetsWindow {
    public EditTweetsWindow() {
        Stage stage = new Stage();
        EditTweetsScene editTweetsScene = new EditTweetsScene();
        stage.setScene(editTweetsScene);
        stage.show();
    }
}
