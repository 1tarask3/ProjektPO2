package pl.po.projekt;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {
    public Turtle() {super();}
    public Turtle(Point position, World world) {
        super(2, 1, position, world);
    }

    @Override
    public void action() {
        int rand = new Random().nextInt(4);
        if (rand == 0) {
            super.action();
        }
    }

    @Override
    public void collision(Organism attacker) {
        if (sameSpecies(attacker)) {
            procreate(attacker);
        }
        else if (attacker.getStrength() >= 5 || attacker.getStrength() < strength) {
            super.collision(attacker);
        }
    }

    public boolean sameSpecies(Organism organism) {
        return organism instanceof Turtle;
    }

    public String getName() {
        return "Turtle";
    }

    public Color getColor() {
        return new Color(100,200,100);
    }
}
