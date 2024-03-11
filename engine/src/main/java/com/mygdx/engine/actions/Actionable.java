package com.mygdx.engine.actions;

import com.mygdx.engine.actions.GameAction;

public interface Actionable {
    void addAction(GameAction action);
    void update();
}
