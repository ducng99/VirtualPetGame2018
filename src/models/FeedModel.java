/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import main.Pet;
import main.Utilities;

/**
 *
 * @author kqn1498
 */
public class FeedModel extends GameModel {

    public FeedModel(Pet pet) {
        super(pet);
    }

    @Override
    public void execute() {
        String noti;
        boolean ate = false;
        
        if (pet.getHealth() >= pet.getMaxHealth()) {
            noti = pet.getName() + " is full!";
        } else if (Utilities.getTime() - pet.getLastFeedTime() < 300) {
            noti = pet.getName() + " can only eat once every 5 minutes!";
        } else {
            int x = Utilities.getRand(5, 10);
            pet.setHealth(pet.getHealth() + x);
            pet.setLastFeedTime(Utilities.getTime());
            noti = pet.getName() + " is eating some food... Her health has been increased by " + x + "!";
            ate = true;
        }
        
        System.out.println(noti);
        
        setChanged();
        notifyObservers((ate ? "screen:feed" : "") + " noti:" + noti);
    }
}
