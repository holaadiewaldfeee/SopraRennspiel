package view;

import javafx.scene.Scene;
import model.GameModel;

/**
 * View interface to be shared between the subviews
 */
public interface View {

    /**
     * Set up all view components.
     */
    void setupGameWindow();

    /**
     * The associated scene of the view
     * @return the scene of this view
     */
    Scene getScene();

    /**
     * draw and update all components on the screen
     * @param model the current model state
     */
    void render(GameModel model);
}
