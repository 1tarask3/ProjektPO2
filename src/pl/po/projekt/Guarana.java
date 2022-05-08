package pl.po.projekt;

import java.awt.Color;

public class Guarana extends Plant {
    public Guarana() {super();}
    public Guarana(Point position, World world) {
        super(0, 10,position, world);
    }

    @Override
    public void collision(Organism attacker) {
        world.setOrganismOnBoard(attacker.getPosition(), null);
        attacker.setPosition(position);
        attacker.setStrength(attacker.getStrength() + 3);
        world.placeOnBoard(attacker);
        world.addLog(attacker.getName() + " has eaten " + this.getName() + " at " + this.position.toString());
        world.addLog("Guarana has strengthen " + attacker.getName() + " by 3 points, new strength: " + attacker.getStrength());
        this.position = null;
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Guarana;
    }

    @Override
    public String getName() {
        return "Guarana";
    }

    public Color getColor() {
        return new Color(220,20,20);
    }
}
