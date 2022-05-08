package pl.po.projekt;

import java.util.ArrayList;

public final class OrganismsArrayList extends ArrayList<Class<? extends Organism>> {
    OrganismsArrayList() {
        this.add(Antelope.class);
        this.add(Belladonna.class);
        this.add(Dandelion.class);
        this.add(Fox.class);
        this.add(Grass.class);
        this.add(Guarana.class);
        this.add(Hogweed.class);
        this.add(Sheep.class);
        this.add(Turtle.class);
        this.add(Wolf.class);
    }
}
