package com.mygdx.engine.scenes;

import java.util.Stack;

public class SceneManager {
    private Scene currentScene;
    private Stack<Scene> scenes;

    public SceneManager() {
        // predictable first scene
        currentScene = null;
        scenes = new Stack<>();
    }

    public void setScene(Scene scene) {

        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scene;
        currentScene.show();
    }

    public void addScene(Scene scene) {
        scenes.push(scene);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
