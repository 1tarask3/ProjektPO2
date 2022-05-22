package pl.po.projekt;

import java.awt.*;
import java.util.Random;

public class Human extends Animal {
    private int abilityCooldown = 10;
    private boolean abilityActivated = false;
    private Direction direction = null;

    public Human(Point position, World world) {
        super(5, 4, position, world);
    }

    public void setAbilityActivated(boolean abilityActivated) {
        this.abilityActivated = abilityActivated;
    }

    public boolean isAbilityActivated() {
        return abilityActivated;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public int getAbilityCooldown() {
        return this.abilityCooldown;
    }

    @Override
    public void action() {
        int additionalMove = 0;
        if (abilityActivated) {
            if (abilityCooldown < 3) {
                additionalMove = 1;
            }
            else {
                int rand = new Random().nextInt(2);
                if (rand == 0) {
                    additionalMove = 1;
                }
            }
        }
        if (position.shift(direction, 1 + additionalMove).isOnTheBoard(world)) {
            moveTo(position.shift(direction, 1 + additionalMove));
        } else if (position.shift(direction, 1).isOnTheBoard(world)) {
            moveTo(position.shift(direction, 1));
        }
        abilityCooldown++;

        if (abilityCooldown == 5) abilityActivated = false;
    }

    public void activateAbility() {
        abilityActivated = true;
        abilityCooldown = 0;
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Human;
    }

    @Override
    public String getName() {
        return "Human";
    }

    public Color getColor() {
        return new Color(255,0,200);
    }

    @Override
    public String print() {
        int abilityActivated = this.abilityActivated ? 1 : 0;
        return this.getName() + " " + this.getPosition().getX() + " " + this.getPosition().getY() + " " + this.getStrength() + " " + abilityActivated + " " + abilityCooldown;
    }
}
