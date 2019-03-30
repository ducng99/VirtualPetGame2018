/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createPet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Tom
 */
public class CreatePetNameController extends KeyAdapter {
    private CreatePetModel model;
    private CreatePetView view;
    
    @Override
    public void keyReleased(KeyEvent ke) {
        model.setName(view.nameInput.getText());
    }
    
    public void addModel(CreatePetModel m)
    {
        this.model = m;
    }
    
    public void addView(CreatePetView v)
    {
        this.view = v;
    }
}
