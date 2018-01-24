package controller;

import model.GameModel;
import view.View;

public interface Controller {

    void update();

    void render();

    void setupInteraction();

    View getView();
}
