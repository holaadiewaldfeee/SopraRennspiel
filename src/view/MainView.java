package view;

import javafx.scene.Scene;
import model.Car;

public interface MainView {

    void setUpGameWindow();

    Scene getScene();

    void render(Car car);
}
