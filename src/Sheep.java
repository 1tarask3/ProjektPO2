import java.awt.*;

public class Sheep extends Animal {
    public Sheep(Point position, World world) {
        super(4, 4, position, world);
    }

    public boolean sameSpecies(Organism organism) {
        return organism instanceof Sheep;
    }

    public String getName() {
        return "Sheep";
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
    }

    @Override
    public void action() {
        super.action();
    }

    public Color getColor() {
        return new Color(200,200,200);
    }
}
