package com.mygdx.sfx;

import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.events.PlaySFXEvent;

public class SoundFX {
	
	private AudioManager am = null;
	
	private EventListener<PlaySFXEvent> hurtSoundListener = new EventListener<PlaySFXEvent>() {
        @Override
        public void onSignal(Event e) {
            playFx((PlaySFXEvent) e);
        }
    };

	public SoundFX(AudioManager am) {
		PlaySFXEvent.addListener(PlaySFXEvent.class, hurtSoundListener);
		this.am = am;
		init();
	}
	
	public void update() {
		EventBus.processEvents(PlaySFXEvent.class);
	}
	
	private void playFx(PlaySFXEvent e) {
		am.play(e.getFxName());
	}
	
	private void init() {
		// add your soundFX here
		am.addSound("SkeletonHurtFX", "audio/fx/skeleton-hurt.mp3", 0.2f, false);
		am.addSound("PlayerHurtFX", "audio/fx/player-hurt.mp3", 0.2f, false);
		am.addSound("PotionPickUpFX", "audio/fx/health-pickup.mp3", 0.5f, false);
		am.addSound("FireBallFX", "audio/fx/fireball-whoosh.mp3", 0.2f, false);
		am.addSound("DeathFX", "audio/fx/death.mp3", 0.2f, false);
	}
}
