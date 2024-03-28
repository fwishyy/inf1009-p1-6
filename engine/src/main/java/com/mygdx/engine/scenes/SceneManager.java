package com.mygdx.engine.scenes;

import com.mygdx.engine.core.Manager;

import java.util.Stack;

public class SceneManager extends Manager {
    private Stack<Scene> scenes;

    public SceneManager() {
        scenes = new Stack<>();
    }

    public void setScene(Scene newScene) {
        for (Scene scene : scenes) {
            scene.dispose();
        }
        scenes.clear();
        newScene.show();
        scenes.push(newScene);
    }

    public void pushScene(Scene scene) {
        scenes.push(scene);
        scene.show();
    }

    public void popScene(Scene scene) {
        scenes.peek().dispose();
        scenes.pop();
    }

    public void render(float deltaTime) {
        scenes.peek().render(deltaTime);
    }
}
