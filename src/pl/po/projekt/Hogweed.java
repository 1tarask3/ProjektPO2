package pl.po.projekt;

import java.awt.Color;

public class Hogweed extends Plant {
    public Hogweed() {super();}
    public Hogweed(Point position, World world) {
        super(0, 3, position, world);
    }

    @Override
    public void action() {
        for (Direction direction : Direction.values()) {
            Point tmp = position.shift(direction, 1);
            if (tmp.isOnTheBoard(world)) {
                if (world.getOrganismFromBoard(tmp) != null && world.getOrganismFromBoard(tmp).isAnimal()) {
                    world.addLog("Hogweed has killed " + world.getOrganismFromBoard(tmp).getName() + " at " + tmp);
                    world.setOrganismOnBoard(tmp, null);
                }
            }
        }
        super.action();
    }

    @Override
    public void collision(Organism attacker) {
        world.setOrganismOnBoard(attacker.getPosition(), null);
        attacker.setPosition(null);
        world.setOrganismOnBoard(position, null);
        world.addLog(attacker.getName() + " has eaten Hogweed at " + position.toString() + " and died");
        position = null;
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Hogweed;
    }

    @Override
    public String getName() {
        return "Hogweed";
    }

    public Color getColor() {
        return new Color(0,80,0);
    }
}
