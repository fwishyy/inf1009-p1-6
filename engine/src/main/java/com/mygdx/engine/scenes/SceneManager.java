package com.mygdx.engine.scenes;

import com.mygdx.engine.core.Manager;

import java.util.Stack;

public class SceneManager extends Manager {
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
