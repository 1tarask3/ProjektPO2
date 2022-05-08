package pl.po.projekt;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private final Organism[][] board;
    private final int x, y;
    private int round = 0;
    public ArrayList<String> logs = new ArrayList<>();

    public World (int x, int y) {
        this.x = x;
        this.y = y;
        this.board = new Organism[x][y];
    }

    public void placeOnBoard(Organism organism) {
        if (organism != null) this.board[organism.getPosition().getX()][organism.getPosition().getY()] = organism;
    }

    public void setOrganismOnBoard(Point position, Organism organism) {
        if (position.isOnTheBoard(this)) board[position.getX()][position.getY()] = organism;
    }

    public Organism getOrganismFromBoard(Point position) {
        if (position.isOnTheBoard(this)) return board[position.getX()][position.getY()];
        return null;
    }

    public Organism getOrganismFromBoard(int x, int y) {
        if (x >= 0 && x <= this.x && y >= 0 && y <= this.y) return board[x][y];
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMaxNumberOfOrganisms() {
        return x * y;
    }

    public Point getRandomEmptyPosition() {
        int randomX, randomY;
        do {
            randomX = new Random().nextInt(x);
            randomY = new Random().nextInt(y);
        } while (board[randomX][randomY] != null);
        return new Point(randomX, randomY);
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void incrementRound() {
        round++;
    }

    public Human getHuman() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] instanceof Human) return (Human) board[i][j];
            }
        }
        return null;
    }

    public void allOrganismsAction() {
        ArrayList<Organism> organisms = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] != null) {
                    organisms.add(board[i][j]);
                }
            }
        }

        organisms.sort((o1, o2) -> {
            if (o1.getInitiative() > o2.getInitiative()) return -1;
            else if (o1.getInitiative() < o2.getInitiative()) return 1;
            else {
                if (o1.getAge() > o2.getAge()) return -1;
                else if (o1.getAge() < o2.getAge()) return 1;
                else return 0;
            }
        });

        for(Organism organism : organisms) {
            organism.incrementAge();
        }

        for (Organism organism : organisms) {
            if (organism != null && organism.getPosition() != null) organism.action();
        }
    }

    public void addLog(String log) {
        logs.add(log);
    }

    public void clearLogs() {
        logs.clear();
    }

    public String getLogs() {
        StringBuilder result = new StringBuilder();
        for (String log : logs) {
            result.append(log).append("\n");
        }
        return result.toString();
    }
}
