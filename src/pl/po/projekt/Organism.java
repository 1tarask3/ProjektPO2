package pl.po.projekt;

import java.awt.Color;

public abstract class Organism {
    protected int strength, initiative, age;
    protected Point position;
    protected World world;

    public Organism() {}

    public Organism(int strength, int initiative, Point position, World world) {
        this.strength = strength;
        this.initiative = initiative;
        this.position = position;
        this.world = world;
        this.world.placeOnBoard(this);
        world.addLog("Organism " + this.getName() + " created on position " + this.getPosition().toString());
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void incrementAge() {
        age++;
    }

    public int getAge() {
        return age;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public final boolean isAnimal() {
        return this instanceof Animal;
    }

    public abstract void moveTo(Point newPosition);
    public abstract void action();
    public abstract void collision(Organism attacker);
    public abstract boolean sameSpecies(Organism organism);
    public abstract Color getColor();
    public abstract String getName();

    public String print() {
        return this.getName() + " " + this.getPosition().getX() + " " + this.getPosition().getY() + " " + this.getStrength();
    }
}
