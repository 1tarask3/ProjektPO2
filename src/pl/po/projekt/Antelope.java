package pl.po.projekt;

import java.awt.Color;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope() {super();}
    public Antelope(Point position, World world) {
        super(4, 4, position, world);
    }

    public void action() {
        super.possibleRandomMove(2);
    }

    @Override
    public void collision(Organism attacker) {
        if (sameSpecies(attacker)) {
            procreate(attacker);
        }
        else {
            int rand = new Random().nextInt(2);
            if (rand == 0) {
                if (position.nextNotOccupiedPosition(world) == null) {
                    world.addLog("Antelope at " + position.toString() + " tried to escape from " + attacker.toString() + ", but there is no place to escape");
                    super.collision(attacker);
                }
                else {
                    Point tmp = position;
                    world.setOrganismOnBoard(position, null);
                    position = position.nextNotOccupiedPosition(world);
                    world.placeOnBoard(this);
                    attacker.moveTo(tmp);
                    world.addLog("Antelope at " + tmp.toString() + " escaped from " + attacker.getName() + " to " + position.toString());
                }
            }
            else super.collision(attacker);
        }
    }

    public boolean sameSpecies(Organism organism) {
        return organism instanceof Antelope;
    }

    public final String getName() {
        return "Antelope";
    }

    public final Color getColor() {
        return new Color(180,150,80);
   }
}
