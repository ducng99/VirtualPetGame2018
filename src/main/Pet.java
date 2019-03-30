package main;

public class Pet {

    private String name;
    private char gender;
    private int health;
    private int hapiness;
    private int age;
    private boolean isSick;
    private boolean isDirty;
    private int bornTime;
    private int lastFeedTime;

    public Pet(String name, char gender) {
        this.setName(name);
        this.setGender(gender);
        this.setHapiness(100);
        this.setSick(false);
        this.setDirty(false);
        this.setBornTime(Utilities.getTime());
        this.setHealth(getMaxHealth());
        this.setLastFeedTime(0);
    }

    public Pet(String name, char gender, int health, int hapiness, boolean isSick, boolean isDirty, int bornTime, int lastFeedTime) {
        this.setName(name);
        this.setGender(gender);
        this.setHealth(health);
        this.setHapiness(hapiness);
        this.setSick(isSick);
        this.setDirty(isDirty);
        this.setBornTime(bornTime);
        this.setLastFeedTime(lastFeedTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getMaxHealth() {
        return 30 + getAge() * 10;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health >= 0 && health <= getMaxHealth()) {
            this.health = health;
        } else if (health < 0) {
            this.health = 0;
        } else if (health > getMaxHealth()) {
            this.health = getMaxHealth();
        }

        updateInfo();
    }

    public int getHapiness() {
        return hapiness;
    }

    public void setHapiness(int hapiness) {
        if (hapiness >= 0 && hapiness <= 100) {
            this.hapiness = hapiness;
        } else if (health < 0) {
            this.hapiness = 0;
        } else if (health > 100) {
            this.hapiness = 100;
        }

        updateInfo();
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean isSick) {
        this.isSick = isSick;

        updateInfo();
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;

        updateInfo();
    }

    public int getBornTime() {
        return bornTime;
    }

    public void setBornTime(int bornTime) {
        this.bornTime = bornTime;

        updateInfo();
    }

    public int getLastFeedTime() {
        return lastFeedTime;
    }

    public void setLastFeedTime(int lastFeedTime) {
        this.lastFeedTime = lastFeedTime;

        updateInfo();
    }

    public int getAge() {
        return (Utilities.getTime() - getBornTime()) / 604800;	//Pet's age will increase every week - 604800 seconds = 7 days
    }

    public void updateInfo() {
        GameDB.updatePet(this);
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + " | Gender: " + this.getGender() + " | Health: " + this.getHealth() + " | Hapiness: " + this.getHapiness() + "% | Age: " + this.getAge() + " | Sick: " + (this.isSick() ? "Yes" : "No") + " | Dirty: " + (this.isDirty() ? "Yes" : "No");
    }
}
