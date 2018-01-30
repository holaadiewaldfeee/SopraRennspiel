package controller;

import view.View;

public interface Controller {

    void update();

    void setupInteraction();

    View getView();

    void updateKeys();
}
