package pl.po.projekt;

import java.util.Random;

public abstract class Animal extends Organism {
    public Animal() {super();}
    public Animal(int strength, int initiative, Point position, World world) {
        super(strength, initiative, position, world);
    }

    public void moveTo(Point newPosition) {
       // world.addLog(this.getName() + " moving from " + getPosition().toString() +  " to " + newPosition.toString());
        if (getWorld().getOrganismFromBoard(newPosition) == null) {
            getWorld().setOrganismOnBoard(position, null);
            this.position = newPosition;
            world.placeOnBoard(this);
        }
        else {
            getWorld().getOrganismFromBoard(newPosition).collision(this);
        }
    }

    protected void possibleRandomMove(int moveRange) {
        Point[] availableMoves = new Point[4];
        int count = 0;
        for (Direction direction : Direction.values()) {
            Point move = position.shift(direction, moveRange);
            if (move.isOnTheBoard(world)) {
                availableMoves[count] = move;
                count++;
            }
        }
        if (count > 0) {
            int rand = new Random().nextInt(count);
            Point newPosition = availableMoves[rand];
            if (newPosition != null) this.moveTo(newPosition);
        }
        else {
            world.addLog(this.getName() + " at " + this.position.toString() + "tried to move, but there is no empty place");
        }
    }

    public void collision(Organism attacker) {
        if (attacker != this) {
            if (sameSpecies(attacker)) {
                procreate(attacker);
            }
            else {
                world.addLog(attacker.getName() + " attacked " + this.getName() + " at position " + this.position.toString());
                if (attacker.getStrength() >= strength) {
                    world.setOrganismOnBoard(attacker.getPosition(), null);
                    attacker.setPosition(position);
                    world.placeOnBoard(attacker);
                    world.addLog(this.getName() + " died at position " + this.position.toString());
                    position = null;
                }
                else {
                    world.setOrganismOnBoard(attacker.getPosition(), null);
                    attacker.setPosition(null);
                    world.addLog(attacker.getName() + " died at position " + this.position.toString());
                }
            }
        }
    }

    public void action() {
        possibleRandomMove(1);
    }

    protected void procreate(Organism organism) {
        int x1 = getPosition().getX();
        int y1 = getPosition().getY();
        int x2 = organism.getPosition().getX();
        int y2 = organism.getPosition().getY();

        if (x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        Point[] possiblePositions = new Point[30];
        int count = 0;

        for (int i = x1 - 1; i <= x2; i++) {
            for (int j = y1 - 1; j <= y2; j++) {
                Point tmp = new Point(i, j);
                if (tmp.isOnTheBoard(world)) {
                    if (world.getOrganismFromBoard(tmp) == null) {
                        possiblePositions[count] = tmp;
                        count++;
                    }
                }
            }
        }

        if (count > 0) {
            world.addLog("Procreation of organisms " + getName() + " at positions " + this.getPosition().toString() + " " + organism.getPosition().toString());

            int rand = new Random().nextInt(count);

            if (this instanceof Wolf) {
                new Wolf(possiblePositions[rand], world);
            }
            else if (this instanceof Sheep) {
                new Sheep(possiblePositions[rand], world);
            }
            else if (this instanceof Fox) {
                new Fox(possiblePositions[rand], world);
            }
            else if (this instanceof Turtle) {
                new Turtle(possiblePositions[rand], world);
            }
            else if (this instanceof Antelope) {
                new Antelope(possiblePositions[rand], world);
            }
        }
        else {
            world.addLog("No place to procreate for " + this.getName() + " at " + this.getPosition().toString() + " and " + organism.getPosition().toString());
        }
    }

    public abstract boolean sameSpecies(Organism organism);
}
