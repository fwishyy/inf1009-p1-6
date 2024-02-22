package com.mygdx.engine.scenes;

import com.mygdx.engine.utils.Event;

public class StateUpdateEvent extends Event {

    private Scene scene;
    private Scene.State newState;

    public StateUpdateEvent(Scene scene, Scene.State newState) {
        scene.setState(newState);
    }
}
