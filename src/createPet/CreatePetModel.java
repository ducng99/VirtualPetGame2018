/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createPet;

import java.util.Observable;
import main.GameDB;
import main.Pet;
import main.Utilities;

/**
 *
 * @author Tom
 */
public class CreatePetModel extends Observable {
    private String name;
    private char gender = 'M';
    
    public void create()
    {
        if (Utilities.nameCheck(name))
        {
            Pet pet = new Pet(name, gender);
            GameDB.createPet(pet);
            setChanged();
            notifyObservers("close");
        }
        else
        {
            setChanged();
            notifyObservers("name error");
        }
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setGender(char gender)
    {
        this.gender = gender;
    }
}
