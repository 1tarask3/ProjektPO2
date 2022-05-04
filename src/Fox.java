import java.awt.*;
import java.util.Random;

public class Fox extends Animal {
    public Fox(Point position, World world) {
        super(3, 7, position, world);
    }

    @Override
    protected final void possibleRandomMove(int moveRange) {
        Point[] availableMoves = new Point[4];
        Direction[] possibleDirections = new Direction[4];
        int count = 0;
        for (Direction direction : Direction.values()) {
            Point move = position.shift(direction, moveRange);
            if (move.isOnTheBoard(world)) {
                if (world.getOrganismFromBoard(move) == null || world.getOrganismFromBoard(move).getStrength() <= strength) {
                    availableMoves[count] = move;
                    possibleDirections[count] = direction;
                    count++;
                }
            }
        }
        if (count > 0) {
            int rand = new Random().nextInt(count);
            Point newPosition = availableMoves[rand];
            Direction newDirection = possibleDirections[rand];
            moveTo(newPosition);
        }
    }

    @Override
    public void action() {
        possibleRandomMove(1);
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
    }

    public boolean sameSpecies(Organism organism) {
        return organism instanceof Fox;
    }

    public String getName() {
        return "Fox";
    }

    public Color getColor() {
        return new Color(240,120,0);
    }
}
