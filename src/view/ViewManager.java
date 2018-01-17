package view;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    public Stage stage;
    //3 scenen abspeichern
    public List<MainView> sceneList = new ArrayList<>();
    //The scene where all is stacked up
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    /**
     * GameView object for setting up the GUI
     *
     * @param stage the primary stage
     */
    public ViewManager(Stage stage) {
        this.stage = stage;
        stage.setTitle("Rennspiel");
        stage.setResizable(true);
        stage.sizeToScene();

        StartView sv = new StartView(this);
        sceneList.add(sv);

        GameView gv = new GameView(this);
        sceneList.add(gv);

        PauseView pv = new PauseView(this);
        sceneList.add(pv);

        changeScene(0);
        // stage.setScene(changeScene(index));
    }

    public void changeScene(int index) {
        stage.setScene(sceneList.get(index).getScene());
        System.out.println(index);
        // return sceneList.get(index).getScene();
    }


}
