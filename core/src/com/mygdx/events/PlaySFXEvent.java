package com.mygdx.events;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.utils.Event;

public class PlaySFXEvent extends Event {
	private String fxName = "";
	
	public PlaySFXEvent(String fxName) {
		this.fxName = fxName;
	}
	
	public String getFxName() {
		return fxName;
	}
}
