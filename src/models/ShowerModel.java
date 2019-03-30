/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import main.Pet;

/**
 *
 * @author kqn1498
 */
public class ShowerModel extends GameModel {

    public ShowerModel(Pet pet) {
        super(pet);
    }

    @Override
    public void execute() {
        String noti;
        boolean showered = false;
        
        if (pet.isDirty()) {
            pet.setDirty(false);
            noti = pet.getName() + " is clean now!";
            showered = true;
        } else {
            noti = pet.getName() + " looks shiny already.";
        }
        
        System.out.println(noti);
        
        setChanged();
        notifyObservers((showered ? "screen:shower" : "") + "noti:" + noti);
    }
}
