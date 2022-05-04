import java.awt.*;

public class Grass extends Plant {
    public Grass(Point position, World world) {
        super(0, 20, position, world);
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
    }

    @Override
    public void action() {
        super.action();
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Grass;
    }

    @Override
    public String getName() {
        return "Grass";
    }

    public Color getColor() {
        return new Color(0, 250, 0);
    }
}
