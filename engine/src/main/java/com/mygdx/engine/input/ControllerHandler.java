package com.mygdx.engine.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;

public class ControllerHandler extends ControllerAdapter {

    @Override
    public boolean buttonDown(Controller controller, int buttonIndex) {

        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonIndex) {

        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisIndex, float value) {

        return true;
    }

    @Override
    public void connected(Controller controller) {

    }
}
