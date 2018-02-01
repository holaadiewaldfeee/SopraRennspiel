package controller;

import view.View;

/**
 * Generic controller interface shared between all controllers
 */
public interface Controller {

    /**
     * Update method of for the controller
     */
    void update();

    /**
     * Interaction methods for the controller and the view. This is used to set up all interaction methods the user can activate.
     */
    void setupInteraction();

    /**
     * returns the view associated with the controller.
     * @return
     */
    View getView();

    /**
     * Continuous key update for user input
     */
    void updateKeys();
}
