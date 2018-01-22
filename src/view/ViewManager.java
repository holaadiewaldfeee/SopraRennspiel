package view;

import controller.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for view-management (GUI elements)
 */
public class ViewManager {

    //new stage
    public Stage stage;
    //3 scenes for switching between start/game/pause
    public List<MainView> sceneList = new ArrayList<>();
    //The scene where all is stacked up
    private Scene scene;


    public Scene getScene() {

        return scene;
    }


    public ViewManager(Stage stage) {

        this.stage = stage;
        stage.setTitle("Rennspiel_Sabrina_Boehm");
        stage.setResizable(false);
        stage.sizeToScene();

        StartView sv = new StartView(this);
        sceneList.add(sv);

        GameView gv = new GameView(this);
        sceneList.add(gv);

        PauseView pv = new PauseView(this);
        sceneList.add(pv);

        changeScene(0);
    }

    //method for scene-changing
    public void changeScene(int index) {

        stage.setScene(sceneList.get(index).getScene());
        System.out.println(index);
    }

    public void render(Car car){
        for (MainView bar : sceneList
             ) {
            bar.render(car);
        }
    }
}
