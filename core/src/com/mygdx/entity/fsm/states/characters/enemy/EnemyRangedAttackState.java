package com.mygdx.entity.fsm.states.characters.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.entity.Character;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;
import com.mygdx.entity.projectiles.Arrow;

public class EnemyRangedAttackState extends CharacterState {
    float animationDuration;
    float attackTimer;

    public EnemyRangedAttackState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("attack");
        animationDuration = character.getAnimationDuration();
    }

    @Override
    public void update() {
        Enemy enemy = (Enemy) character;
        Character target = enemy.getTarget();
        Vector2 targetPos = target.getVector2();
        Vector2 origin = enemy.getVector2().cpy();
        origin.x += enemy.getCollider().getWidth() / 2f;
        origin.y += enemy.getCollider().getWidth() / 2f;

        attackTimer += Gdx.graphics.getDeltaTime();
        if (attackTimer >= animationDuration) {
            if (target != null) {
                Vector2 direction = targetPos.cpy().sub(origin);
                Arrow arrow = new Arrow(enemy, "projectiles/arrow.png", origin.x, origin.y, "arrow", 1, 1, 0.05f);
                arrow.setDirection(direction.nor());
                EntityAddedEvent.addEvent(new EntityAddedEvent(arrow));
                attackTimer = 0;
            }
        }
    }
}
