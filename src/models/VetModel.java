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
 * @author Tom
 */
public class VetModel extends GameModel {

    public VetModel(Pet pet) {
        super(pet);
    }

    @Override
    public void execute() {
        String noti;
        boolean vetted = false;
        
        if (pet.isSick())
        {
            vetted = true;
            pet.setSick(false);
            if (pet.getHealth() < 15) pet.setHealth(15);
            noti = pet.getName() + " has been cured. It's hurt and " + (pet.getGender() == 'M' ? "he" : "she") + " needs a rest";
        }
        else
        {
            noti = pet.getName() + " isn't sick, " + (pet.getGender() == 'M' ? "he" : "she") + " doesn't need to go to the vet";
        }
        
        setChanged();
        notifyObservers((vetted ? "screen:vet" : "") + "noti:" + noti);
    }
    
}
