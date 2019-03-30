package main;

import createPet.*;
import controllers.*;
import views.GameView;
import models.*;
import createPet.CreatePetView;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class GameMain {
	private static Pet pet;

	private GameMain() {
            //View
            GameView view = new GameView(pet);
            //Models
            FeedModel feedModel = new FeedModel(pet);
            ShowerModel showerModel = new ShowerModel(pet);
            PlayModel playModel = new PlayModel(pet);
            VetModel vetModel = new VetModel(pet);
            //Controllers
            FeedController feedController = new FeedController();
            ShowerController showerController = new ShowerController();
            PlayController playController = new PlayController();
            VetController vetController = new VetController();
            
            feedModel.addObserver(view);
            showerModel.addObserver(view);
            playModel.addObserver(view);
            vetModel.addObserver(view);
            
            feedController.addModel(feedModel);
            showerController.addModel(showerModel);
            playController.addModel(playModel);
            vetController.addModel(vetModel);
            
            view.addController(feedController);
            view.addController(showerController);
            view.addController(playController);
            view.addController(vetController);
	}

	public static void main(String[] args) {
            // Database Initialization
            GameDB.establishConnection();
            
            //GameDB.removePet();       //uncomment to test create pet function
            if (GameDB.getPet() == null)
            {
                GameDB.initializeDB();
                createPet();
                System.out.println("Pet in DB check loop:");
                do
                {
                    try {
                        Thread.sleep(1000);     //wait for pet to be created
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while (GameDB.getPet() == null);
            }
            pet = GameDB.getPet();

            System.out.println(pet);
            
            //Initializing MVC
            GameMain main = new GameMain();
            
            //Game loop
            Timer loop = new Timer(600000, (java.awt.event.ActionEvent ae) -> {
                pet.setHealth(pet.getHealth() - Utilities.getRand(1 + (pet.isSick() || pet.isDirty() ? 2 : 0), 3 + (pet.isSick() || pet.isDirty() ? 2 : 0)));         //Every 10 minutes, pet's health and hapiness will be decreased
                pet.setHapiness(pet.getHapiness() - Utilities.getRand(1 + (pet.isSick() || pet.isDirty() ? 2 : 0), 3 + (pet.isSick() || pet.isDirty() ? 2 : 0)));     //If pet is sick or dirty, they will be decreased even more
                
                if (pet.getHealth() < 15 && !pet.isSick() && Utilities.getRand(0, 99) < 30)
                {
                    pet.setSick(true);      //If pet's health is below 15, it has 30% chance of getting sick
                }
                
                if (pet.getHealth() == 0 || pet.getHapiness() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Because you don't care about " + pet.getName() + ", " + (pet.getGender() == 'M' ? "he" : "she") + " ran away...");
                    GameDB.removePet();
                    System.exit(0);
                    //If pet's health or hapiness reaches 0, pet will run away and pet will be removed from DB
                }
            });
            loop.start();
	}

	private static void createPet()
	{
            CreatePetView createPetView = new CreatePetView();
            CreatePetModel createPetModel = new CreatePetModel();
            CreatePetSubmitController createPetSubmitController = new CreatePetSubmitController();
            CreatePetNameController createPetNameController = new CreatePetNameController();
            CreatePetGenderController createPetGenderController = new CreatePetGenderController();
            
            createPetSubmitController.addModel(createPetModel);
            createPetNameController.addModel(createPetModel);
            createPetNameController.addView(createPetView);
            createPetGenderController.addModel(createPetModel);
            createPetGenderController.addView(createPetView);
            
            createPetView.addGenderController(createPetGenderController);
            createPetView.addNameController(createPetNameController);
            createPetView.addSubmitController(createPetSubmitController);
            
            createPetModel.addObserver(createPetView);
	}
}
