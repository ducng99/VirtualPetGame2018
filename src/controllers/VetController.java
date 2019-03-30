/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;

/**
 *
 * @author Tom
 */
public class VetController extends GameController {

    @Override
    public void actionPerformed(ActionEvent e) {
        model.execute();
    }
    
}
