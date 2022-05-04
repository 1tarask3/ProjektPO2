import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {
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
                Point tmp = position;
                possibleRandomMove(1);
                attacker.moveTo(position);
            }
            else {
                super.collision(attacker);
            }
        }
    }

    public boolean sameSpecies(Organism organism) {
        return organism instanceof Antelope;
    }

    public String getName() {
        return "Antelope";
    }

    public Color getColor() {
        return new Color(180,150,80);
    }
}
