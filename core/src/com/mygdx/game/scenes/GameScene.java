package com.mygdx.game.scenes;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.controls.KeyCodes;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.player.Player;

import com.mygdx.engine.entity.*;
import com.mygdx.engine.physics.CollisionManager;

public class GameScene extends Scene {

    private GameContainer container;
    private EntityManager em;
    private SpriteBatch batch;
    private CollisionManager cm;
    private PlayerControlManager pm;
    private Player p1;
    private Player p2;


    public GameScene(GameContainer container) {
        this.container = container;
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();

        em = container.getEntityManager();
        cm = container.getCollisionManager();
        pm = container.getPlayerControlManager();
        em.createEntity(1, Player.class, "badlogic.jpg", 0, 0, "player1");
        em.createEntity(1, Player.class, "badlogic.jpg", 300, 300, "player2");
        p1 = (Player) em.getEntity("player1");
        p2 = (Player) em.getEntity("player2");
        cm.addCollider(p1);
        cm.addCollider(p2);

        ActionMap playerControls = new ActionMap();
        playerControls.addNewBinding(KeyCodes.W, new DirectionalMoveAction(IMoveable.Direction.UP));
        playerControls.addNewBinding(KeyCodes.A, new DirectionalMoveAction(IMoveable.Direction.LEFT));
        playerControls.addNewBinding(KeyCodes.S, new DirectionalMoveAction(IMoveable.Direction.DOWN));
        playerControls.addNewBinding(KeyCodes.D, new DirectionalMoveAction(IMoveable.Direction.RIGHT));
        pm.setActionMap(p1, playerControls);
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        batch.begin();
        em.update();
        em.draw(batch);
        cm.update();
        batch.end();
    }
}
