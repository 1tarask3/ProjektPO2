package pl.po.projekt;

import java.io.*;

public class WorldManager {
    private static final int fill = 20; // wypełnienie planszy w skali od 0 do 100
    private World world = null;

    public WorldManager() {}

    public World getWorld() {
        return world;
    }

    public void GenerateNewWorld(int x, int y) {
        world = new World(x, y);
        int organismsPerSpecies = world.getMaxNumberOfOrganisms() * fill / 1000;

        Human human = new Human(world.getRandomEmptyPosition(), world);

        for (int i = 0; i < organismsPerSpecies; i++) {
            Antelope antelope = new Antelope(world.getRandomEmptyPosition(), world);
            Belladonna belladonna = new Belladonna(world.getRandomEmptyPosition(), world);
            Dandelion dandelion = new Dandelion(world.getRandomEmptyPosition(), world);
            Fox fox = new Fox(world.getRandomEmptyPosition(), world);
            Grass grass = new Grass(world.getRandomEmptyPosition(), world);
            Guarana guarana = new Guarana(world.getRandomEmptyPosition(), world);
            Hogweed hogweed = new Hogweed(world.getRandomEmptyPosition(), world);
            Sheep sheep = new Sheep(world.getRandomEmptyPosition(), world);
            Turtle turtle = new Turtle(world.getRandomEmptyPosition(), world);
            Wolf wolf = new Wolf(world.getRandomEmptyPosition(), world);
        }
    }

    public void setHumanNextDirection(Direction direction){
        if (world.getHuman() != null) {
            world.getHuman().setDirection(direction);
        }
    }

    public void doTour(Direction humansDirecton) {
        world.clearLogs();
        setHumanNextDirection(humansDirecton);
        if (world != null) world.allOrganismsAction();
    }

    public int biggerDimension() {
        return Math.max(world.getX(), world.getY());
    }

    public boolean noMoreMovement () {
        for (int i = 0; i < world.getX(); i++) {
            for (int j = 0; i < world.getY(); j++) {
                Organism tmp = world.getOrganismFromBoard(new Point(i, j));
                if (tmp == null || tmp.isAnimal()) return false;
            }
        }
        return true;
    }

    public void saveGame(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(world.getX() + " " + world.getY() + "\n");
            for (int i = 0; i < getWorld().getX(); i++) {
                for (int j = 0; j < getWorld().getY(); j++) {
                    if (getWorld().getOrganismFromBoard(i, j) != null) {
                        writer.write(getWorld().getOrganismFromBoard(i, j).print() + "\n");
                    }
                }
            }
            writer.close();
        }
        catch (Exception ex) {
            System.out.println("Nie udało się zapisać gry");
            ex.printStackTrace();
        }

    }

    public void readGame(File file) {
        try {
            int x, y;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String[] args = line.split(" ");
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            world = new World(x, y);
            while ((line = reader.readLine()) != null) {
                args = line.split(" ");
                if (args[0].equals("Human")) {
                    int xPos = Integer.parseInt(args[1]);
                    int yPos = Integer.parseInt(args[2]);
                    int strength = Integer.parseInt(args[3]);
                    boolean abilityActivated = !args[4].equals("0");
                    int currentStateRounds = Integer.parseInt(args[5]);
                    Human human = new Human(new Point(xPos,yPos), world);
                    human.setStrength(strength);
                    human.setAbilityActivated(abilityActivated);
                    human.setCurrentStateRounds(currentStateRounds);
                }
                else {
                    Point position = new Point(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                    int strength = Integer.parseInt(args[3]);
                    Organism organism;

                    switch (args[0]) {
                        case "Antelope":
                            organism = new Antelope(position, world);
                            break;
                        case "Belladonna":
                            organism = new Belladonna(position, world);
                            break;
                        case "Dandelion":
                            organism = new Dandelion(position, world);
                            break;
                        case "Fox":
                            organism = new Fox(position, world);
                            break;
                        case "Grass":
                            organism = new Grass(position, world);
                            break;
                        case "Guarana":
                            organism = new Guarana(position, world);
                            break;
                        case "Hogweed":
                            organism = new Hogweed(position, world);
                            break;
                        case "Sheep":
                            organism = new Sheep(position, world);
                            break;
                        case "Turtle":
                            organism = new Turtle(position, world);
                            break;
                        case "Wolf":
                            organism = new Wolf(position, world);
                            break;
                        default:
                            throw new Exception("Błąd podczas wczytywanie organizmu");
                    }
                    organism.setStrength(strength);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Nie udało się wczytać gry");
            ex.printStackTrace();
        }
    }

}
