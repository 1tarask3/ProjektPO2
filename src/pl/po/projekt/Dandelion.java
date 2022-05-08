package pl.po.projekt;

import java.awt.Color;

public class Dandelion extends Plant {
    public Dandelion() {super();}
    public Dandelion(Point position, World world) {
        super(0, 8, position, world);
    }

    @Override
    public void action() {
        for (int i = 0; i < 3; i++) {
            super.action();
        }
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Dandelion;
    }

    @Override
    public String getName() {
        return "Dandelion";
    }

    public Color getColor() {
        return new Color(200, 200, 0);
    }
}
