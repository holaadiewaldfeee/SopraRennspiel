package view;

import javafx.scene.Scene;
import model.GameModel;

public interface View {

    void setupGameWindow();

    Scene getScene();

    void render(GameModel model);
}
