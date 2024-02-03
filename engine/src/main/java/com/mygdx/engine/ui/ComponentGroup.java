package com.mygdx.engine.ui;

import java.util.ArrayList;

// for managing groups of components
public class ComponentGroup<T extends Component> {
    private ArrayList<T> members;
    private int memberCount;

    public ComponentGroup() {

    }

    public void addComponents(T newComponent) {
        members.add(newComponent);
        memberCount++;
    }

    public void removeComponent(T delComponent) {
        members.remove(delComponent);
        memberCount--;
    }


    public void destroy() {
        members.clear();
    }

    public void layout() {
    }
}