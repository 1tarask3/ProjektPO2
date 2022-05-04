import java.awt.*;

public class Wolf extends Animal {
    public Wolf(Point position, World world) {
        super(9, 5, position, world);
    }

    public boolean sameSpecies(Organism organism) {
        return organism instanceof Wolf;
    }

    public String getName() {
        return "Wolf";
    }

    public void action() {
        super.action();
    }

    public void collision(Organism attacker){
        super.collision(attacker);
    }

    public Color getColor() {
        return new Color(100,100,100);
    }
}
