package pl.po.projekt;

import java.util.Random;

public abstract class Plant extends Organism {
    private final int spreadProbability; // prawdopodobieństwo rozprzestrzenienia się rośliny w skali od 0 do 100

    public Plant() {
        super();
        spreadProbability = 0;
    }

    public Plant(int strength, int spreadProbability, Point position, World world) {
        super(strength, 0, position, world);
        this.spreadProbability = spreadProbability;
    }

    protected void spread() {
        Point[] possiblePositions = new Point[4];
        int count = 0;

        for (Direction direction : Direction.values()) {
            Point tmp = position.shift(direction, 1);
            if (tmp.isOnTheBoard(world)) {
                if (world.getOrganismFromBoard(tmp) == null) {
                    possiblePositions[count] = tmp;
                    count++;
                }
            }
        }

        if (count > 0) {
            int rand = new Random().nextInt(count);

            world.addLog(this.getName() + " at " + this.getPosition().toString() +  " has spread");

            if (this instanceof Grass) {
                new Grass(possiblePositions[rand], world);
            }
            else if (this instanceof Dandelion) {
                new Dandelion(possiblePositions[rand], world);
            }
            else if (this instanceof Guarana) {
                new Guarana(possiblePositions[rand], world);
            }
            else if (this instanceof Hogweed) {
                new Hogweed(possiblePositions[rand], world);
            }
            else if (this instanceof Belladonna) {
                new Belladonna(possiblePositions[rand], world);
            }
        }
        else {
            world.addLog(this.getName() + " tried to spread at " + this.position.toString() + " but there is no empty place");
        }
    }

    @Override
    public void moveTo(Point newPosition) {}

    @Override
    public void action() {
        int rand = new Random().nextInt(100);
        if (rand < spreadProbability) {
            spread();
        }
    }

    @Override
    public void collision(Organism attacker) {
        world.setOrganismOnBoard(attacker.getPosition(), null);
        attacker.setPosition(position);
        world.placeOnBoard(attacker);
        world.addLog(attacker.getName() + " has eaten " + this.getName() + " at " + this.position.toString());
        position = null;
    }
}
