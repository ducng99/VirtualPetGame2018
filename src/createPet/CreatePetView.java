/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createPet;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Tom
 */
public class CreatePetView implements Observer{
    public JTextField nameInput = new JTextField();
    public JRadioButton genderInputMale = new JRadioButton("Male", true);
    public JRadioButton genderInputFemale = new JRadioButton("Female", false);
    public JButton submitButton = new JButton("Start");
    
    private JFrame frame = new JFrame("Create Pet - Another Virtual Pet Game");
    
    public CreatePetView()
    {
        frame.setLayout(null);
        frame.setSize(500, 200);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setSize(100, 30);
        nameLabel.setLocation(10, 10);
        frame.add(nameLabel);
        
        nameInput.setSize(200, 30);
        nameInput.setLocation(10, 40);
        frame.add(nameInput);
        
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setSize(100, 30);
        genderLabel.setLocation(220, 10);
        frame.add(genderLabel);
        
        genderInputMale.setLocation(220, 40);
        genderInputMale.setSize(100, 30);
        genderInputFemale.setLocation(320, 40);
        genderInputFemale.setSize(100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(genderInputMale);
        bg.add(genderInputFemale);
        frame.add(genderInputMale);
        frame.add(genderInputFemale);
        
        submitButton.setSize(100, 30);
        submitButton.setLocation(10, 70);
        frame.add(submitButton);
        
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object oj) {
        if (((String)oj).equals("name error"))
        {
            JOptionPane.showMessageDialog(null, "Name error: Please choose other name");
        }
        else if (((String)oj).equals("close"))
            frame.setVisible(false);
    }
    
    public void addNameController(CreatePetNameController c)
    {
        nameInput.addKeyListener(c);
    }
    public void addGenderController(CreatePetGenderController c)
    {
        genderInputMale.addActionListener(c);
        genderInputFemale.addActionListener(c);
    }
    public void addSubmitController(CreatePetSubmitController c)
    {
        submitButton.addActionListener(c);
    }
}
