package models;

import java.util.Observable;
import main.Pet;

public abstract class GameModel extends Observable{
    protected Pet pet;

    public GameModel(Pet pet) {
        System.out.println("Model Initialize");
        this.pet = pet;
    }

    public abstract void execute();
}
