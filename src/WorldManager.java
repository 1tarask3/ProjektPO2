public class WorldManager {
    private static final int fill = 20; // wypełnienie planszy w skali od 0 do 100
    private World world = null;

    public WorldManager() {}

    public World getWorld() {
        return world;
    }

    public void GenerateNewWorld(int x, int y) {
        world = new World(x, y);
        int organismsPerSpecies = (int)(world.getMaxNumberOfOrganisms() * fill / 1000);

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
        if (world.getX() > world.getY()) return world.getX();
        else return world.getY();
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

}
