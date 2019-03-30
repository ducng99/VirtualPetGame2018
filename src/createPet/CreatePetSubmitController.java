/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createPet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Tom
 */
public class CreatePetSubmitController implements ActionListener {
    private CreatePetModel model;

    @Override
    public void actionPerformed(ActionEvent ae) {
        model.create();
    }
    
    public void addModel(CreatePetModel m)
    {
        this.model = m;
    }
}
