package com.mygdx.entity.fsm.states.characters.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Character;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;

public class EnemyRunState extends CharacterState {
    private Character target;

    public EnemyRunState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("run");
    }

    @Override
    public void update() {
        Enemy enemy = (Enemy) character;
        target = enemy.getTarget();
        if (target != null) {
            Vector2 targetPos = target.getVector2();
            Vector2 dir = new Vector2();
            dir.x = (enemy.getX() - targetPos.x <= 0) ? 1 : -1;
            dir.y = (enemy.getY() - targetPos.y <= 0) ? 1 : -1;

            if (dir.x == -1) {
                enemy.setFlip(true);
            }
            if (dir.x == 1) {
                enemy.setFlip(false);
            }

            float newX = enemy.getX() + dir.x * enemy.getSpeed() * Gdx.graphics.getDeltaTime();
            float newY = enemy.getY() + dir.y * enemy.getSpeed() * Gdx.graphics.getDeltaTime();

            enemy.setPosition(newX, newY);
        }
    }
}
