/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.GameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kqn1498
 */
public abstract class GameController implements ActionListener {
    protected GameModel model;
    
    public void addModel(GameModel m)
    {
        model = m;
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
