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
public class PlayModel extends GameModel {

    public PlayModel(Pet pet) {
        super(pet);
    }

    @Override
    public void execute() {
        String noti;
        boolean played = false;
        
        if (pet.getHealth() <= 10) {
            noti = pet.getName() + " is weak! Let " + (pet.getGender() == 'M' ? "him" : "her") + " rest or feed " + (pet.getGender() == 'M' ? "him" : "her") + "!";
        } else {
            int x = Utilities.getRand(5, 10);
            pet.setHapiness(pet.getHapiness() + x);
            pet.setHealth(pet.getHealth() - x);

            if (Utilities.getRand(0, 100) < 60) //60% chance it will be dirty
            {
                pet.setDirty(true);
            }
            
            if (pet.getHapiness() != 100)
                noti = pet.getName() + " is playing in the garden! " + (pet.getGender() == 'M' ? "His" : "Her") + " hapiness has been increased by " + x + "!";
            else
                noti = pet.getName() + " is hyped! Keep playing will only make " + (pet.getGender() == 'M' ? "him" : "her") + " weaker";
            played = true;
        }
        
        System.out.println(noti);
        
        setChanged();
        notifyObservers((played ? "screen:play" : "") + "noti:" + noti);
    }
}
