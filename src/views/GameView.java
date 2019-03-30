/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import controllers.GameController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import main.Pet;

/**
 *
 * @author kqn1498
 */
public class GameView implements Observer {
    private static Pet pet;
   
    private Image def = null;
    private Image[] feedImg;
    private Image[] showerImg;
    private Image[] vetImg;
    private Image[] playImg;
    
    private JLabel status = new JLabel();
    private JLabel noti = new JLabel();
    private JButton feedButton = new JButton("Feed");
    private JButton showerButton = new JButton("Shower");
    private JButton playButton = new JButton("Play");
    private JButton vetButton = new JButton("Vet");
    private JLabel screen = new JLabel();
    private JFrame frame = new JFrame("Another Pet Game");
    
    public GameView (Pet p) {
        //Images declaring - start
        try {
            this.feedImg = new Image[] {
                ImageIO.read(new File("images/Feed1.jpg")),
                ImageIO.read(new File("images/Feed2.jpg")),
                ImageIO.read(new File("images/Feed3.jpg")),
                ImageIO.read(new File("images/Feed4.jpg"))
            };
            this.showerImg = new Image[] {
                ImageIO.read(new File("images/Shower1.jpg")),
                ImageIO.read(new File("images/Shower2.jpg")),
                ImageIO.read(new File("images/Shower3.jpg"))
            };
            this.playImg = new Image[] {
                ImageIO.read(new File("images/Play1.jpg")),
                ImageIO.read(new File("images/Play2.jpg")),
                ImageIO.read(new File("images/Play3.jpg")),
                ImageIO.read(new File("images/Play4.jpg")),
                ImageIO.read(new File("images/Play5.jpg")),
            };
            this.vetImg = new Image[] {
                ImageIO.read(new File("images/Vet1.jpg")),
                ImageIO.read(new File("images/Vet2.jpg")),
                ImageIO.read(new File("images/Vet3.jpg")),
                ImageIO.read(new File("images/Vet4.jpg")),
                ImageIO.read(new File("images/Vet5.jpg")),
            };
            this.def = ImageIO.read(new File("images/Default.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Images declaring - end
        pet = p;
        System.out.println("View initialize");
        frame.setLayout(null);
        frame.setSize(700, 500);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        screen.setIcon(new ImageIcon(def));
        screen.setSize(600, 310);
        screen.setLocation(50, 10);
        frame.add(screen);
        
        noti.setLocation(50, 320);
        noti.setSize(600, 30);
        frame.add(noti);
        
        status.setLocation(50, 350);
        status.setSize(600, 30);
        status.setText(pet.toString());
        frame.add(status);
        
        feedButton.setSize(100, 30);
        feedButton.setLocation(50, 400);
        frame.add(feedButton);
        
        showerButton.setSize(100,30);
        showerButton.setLocation(216, 400);
        frame.add(showerButton);

        playButton.setSize(100, 30);
        playButton.setLocation(382, 400);
        frame.add(playButton);
        
        vetButton.setSize(100, 30);
        vetButton.setLocation(548, 400);
        frame.add(vetButton);
        
        frame.addWindowListener(new CloseListener());
        frame.setVisible(true);
    }
    
    @Override
    public void update(Observable obs, Object obj)
    {
        status.setText(pet.toString());
        
        if (((String)obj).contains("noti:"))
        {
            noti.setText(((String)obj).split("noti:")[1]);
        }
        if (((String)obj).contains("screen:feed"))
        {
            System.out.println("Screen update with feeding");
            
            feedButton.setEnabled(false);
            showerButton.setEnabled(false);
            playButton.setEnabled(false);
            vetButton.setEnabled(false);
            
            Timer[] frames = new Timer[feedImg.length+1];
            ActionListener[] task = new ActionListener[feedImg.length+1];
            
            for (int i = 0; i < frames.length; i++)
            {
                if (i == frames.length - 1)
                {
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(def));
            
                        feedButton.setEnabled(true);
                        showerButton.setEnabled(true);
                        playButton.setEnabled(true);
                        vetButton.setEnabled(true);
                    };
                    frames[i] = new Timer(i * 600, task[i]);
                }
                else
                {
                    final int index = i;
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(feedImg[index]));
                    };
                    frames[i] = new Timer(i * 600, task[i]);
                }
                frames[i].setRepeats(false);
                frames[i].start();
            }
        }
        else if (((String)obj).contains("screen:shower"))
        {
            System.out.println("Screen update with showering");
            
            feedButton.setEnabled(false);
            showerButton.setEnabled(false);
            playButton.setEnabled(false);
            vetButton.setEnabled(false);
            
            Timer[] frames = new Timer[showerImg.length+1];
            ActionListener[] task = new ActionListener[showerImg.length+1];
            
            for (int i = 0; i < frames.length; i++)
            {
                if (i == frames.length - 1)
                {
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(def));
            
                        feedButton.setEnabled(true);
                        showerButton.setEnabled(true);
                        playButton.setEnabled(true);
                        vetButton.setEnabled(true);
                    };
                    frames[i] = new Timer(i * 600, task[i]);
                }
                else
                {
                    final int index = i;
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(showerImg[index]));
                    };
                    frames[i] = new Timer(i * 600, task[i]);
                }
                frames[i].setRepeats(false);
                frames[i].start();
            }
        }
        else if (((String)obj).contains("screen:play"))
        {
            System.out.println("Screen update with playing");
            
            feedButton.setEnabled(false);
            showerButton.setEnabled(false);
            playButton.setEnabled(false);
            vetButton.setEnabled(false);
            
            Timer[] frames = new Timer[playImg.length+1];
            ActionListener[] task = new ActionListener[playImg.length+1];
            
            for (int i = 0; i < frames.length; i++)
            {
                if (i == frames.length - 1)
                {
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(def));
            
                        feedButton.setEnabled(true);
                        showerButton.setEnabled(true);
                        playButton.setEnabled(true);
                        vetButton.setEnabled(true);
                    };
                    frames[i] = new Timer(i * 500, task[i]);
                }
                else
                {
                    final int index = i;
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(playImg[index]));
                    };
                    frames[i] = new Timer(i * 500, task[i]);
                }
                frames[i].setRepeats(false);
                frames[i].start();
            }
        }
        else if (((String)obj).contains("screen:vet"))
        {
            System.out.println("Screen update with vetting");
            
            feedButton.setEnabled(false);
            showerButton.setEnabled(false);
            playButton.setEnabled(false);
            vetButton.setEnabled(false);
            
            Timer[] frames = new Timer[vetImg.length+1];
            ActionListener[] task = new ActionListener[vetImg.length+1];
            
            for (int i = 0; i < frames.length; i++)
            {
                if (i == frames.length - 1)
                {
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(def));
            
                        feedButton.setEnabled(true);
                        showerButton.setEnabled(true);
                        playButton.setEnabled(true);
                        vetButton.setEnabled(true);
                    };
                    frames[i] = new Timer(i * 500, task[i]);
                }
                else
                {
                    final int index = i;
                    task[i] = (ActionEvent ae) -> {
                        screen.setIcon(new ImageIcon(vetImg[index]));
                    };
                    frames[i] = new Timer(i * 500, task[i]);
                }
                frames[i].setRepeats(false);
                frames[i].start();
            }
        }
    }
    
    public void addController(GameController c)
    {
        System.out.println("View: adding controller");
        
        if (c.getClass().equals(FeedController.class))
        {
            feedButton.addActionListener(c);
        }
        else if (c.getClass().equals(ShowerController.class))
        {
            showerButton.addActionListener(c);
        }
        else if (c.getClass().equals(PlayController.class))
        {
            playButton.addActionListener(c);
        }
        else if (c.getClass().equals(VetController.class))
        {
            vetButton.addActionListener(c);
        }
    }
    
    private static class CloseListener extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
