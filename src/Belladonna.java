import java.awt.*;

public class Belladonna extends Plant {
    public Belladonna(Point position, World world) {
        super(99, 4, position, world);
    }

    @Override
    public void action() { super.action(); }

    @Override
    public void collision(Organism attacker) {
        world.setOrganismOnBoard(attacker.getPosition(), null);
        world.addLog(attacker.getName() + " has eaten " + this.getName() + " at " + this.position.toString());
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Belladonna;
    }

    @Override
    public String getName() {
        return "Belladonna";
    }

    public Color getColor() {
        return new Color(20, 0, 60);
    }
}
