import java.awt.*;

public class Human extends Animal {
    private int currentStateRounds = 5;
    private boolean abilityActivated = false;
    private Direction direction = null;

    public Human(Point position, World world) {
        super(5, 4, position, world);
    }

    public void setCurrentStateRounds(int currentStateRounds) {
        this.currentStateRounds = currentStateRounds;
    }

    public void incrementStateRounds() {
        currentStateRounds++;
    }

    public int getCurrentStateRounds() {
        return currentStateRounds;
    }

    public void setAbilityActivated(boolean abilityActivated) {
        this.abilityActivated = abilityActivated;
    }

    public boolean isAbilityActivated() {
        return abilityActivated;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void action() {
        int additionalMove = abilityActivated ? 1 : 0;
        if (position.shift(direction, 1 + additionalMove).isOnTheBoard(world)) {
            moveTo(position.shift(direction, 1 + additionalMove));
        }
    }

    @Override
    public boolean sameSpecies(Organism organism) {
        return organism instanceof Human;
    }

    @Override
    public String getName() {
        return "Human";
    }

    public Color getColor() {
        return new Color(255,0,200);
    }
}
