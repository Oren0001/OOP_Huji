import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{

    private static final int INITIAL_MAX_ENERGY = 210;
    private static final int INITIAL_CURRENT_ENERGY = 190;
    private static final int INITIAL_SHIP_HEALTH = 22;
    private static final int GOT_HIT_HEALTH_REDUCTION = 1;
    private static final int GOT_HIT_ENERGY_REDUCTION = 10;
    private static final int BASH_ENERGY_ADDITION = 18;
    private static final int ENERGY_REGENERATION = 1;
    private static final int GUN_COOLDOWN = 7;
    private static final int FIRE_ENERGY_REDUCTION = 19;
    private static final int TELEPORT_ENERGY_REDUCTION = 140;
    private static final int SHIELD_ENERGY_REDUCTION = 3;
    private static final int SHIP_DISABLED_DURATION = 35;
    private long lastRoundFired = -GUN_COOLDOWN; // the last round in which the ship fired a shot
    private int maxEnergy = INITIAL_MAX_ENERGY;
    private int currentEnergy = INITIAL_CURRENT_ENERGY;
    private int shipHealth = INITIAL_SHIP_HEALTH;
    private long currentRound = 0;
    private long lastRoundShieldOn = 0; // the last round in which the ship activated its shield
    private int deathCount = 0;
    private long roundDisabled = -SHIP_DISABLED_DURATION; // the first round in which the ship was disabled
    private Image shipImage;

    /** Represents the position, direction and velocity of the ship. */
    protected SpaceShipPhysics shipPhysics = new SpaceShipPhysics();

    /** True if the ship is disabled, false otherwise. */
    protected boolean disabled = false;

    
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shipImage = GameGUI.ENEMY_SPACESHIP_IMAGE;
        currentRound++;
        if (roundDisabled + SHIP_DISABLED_DURATION < currentRound)
            disabled = false;
    }


    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip(){
        gotHit();
        if (lastRoundShieldOn == currentRound) {
            maxEnergy += BASH_ENERGY_ADDITION;
            currentEnergy += BASH_ENERGY_ADDITION;
        }
    }


    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        shipPhysics = new SpaceShipPhysics();
        shipHealth = INITIAL_SHIP_HEALTH;
        maxEnergy = INITIAL_MAX_ENERGY;
        currentEnergy = INITIAL_CURRENT_ENERGY;
        lastRoundFired = -GUN_COOLDOWN;
        deathCount += 1;
        disabled = false;
    }


    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return shipHealth == 0;
    }


    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipPhysics;
    }


    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (lastRoundShieldOn != currentRound){
            if (shipHealth > 0)
                shipHealth -= GOT_HIT_HEALTH_REDUCTION;
            if (maxEnergy >= GOT_HIT_ENERGY_REDUCTION) {
                maxEnergy -= GOT_HIT_ENERGY_REDUCTION;
                if (currentEnergy > maxEnergy)
                    currentEnergy = maxEnergy;
            }
        }
    }


    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage() {
        return shipImage;
    }


    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (currentEnergy >= FIRE_ENERGY_REDUCTION && lastRoundFired+GUN_COOLDOWN <= currentRound) {
           currentEnergy -= FIRE_ENERGY_REDUCTION;
           lastRoundFired = currentRound;
           game.addShot(shipPhysics);
       }
    }


    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergy >= SHIELD_ENERGY_REDUCTION) {
            lastRoundShieldOn = currentRound;
            currentEnergy -= SHIELD_ENERGY_REDUCTION;
            if (shipImage == GameGUI.SPACESHIP_IMAGE)
                shipImage = GameGUI.SPACESHIP_IMAGE_SHIELD;
            else
                shipImage = GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
    }


    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergy >= TELEPORT_ENERGY_REDUCTION) {
            currentEnergy -= TELEPORT_ENERGY_REDUCTION;
            shipPhysics = new SpaceShipPhysics();
        }
    }


    /**
     * Increases the current energy by 1 each round.
     */
    protected void regenerateEnergy() {
        if (currentEnergy < maxEnergy)
            currentEnergy += ENERGY_REGENERATION;
    }


    /**
     * Turns towards the closest ship.
     * @param angleToClosest Angle to closest ship.
     */
    protected void turnToClosest (double angleToClosest) {
        if (angleToClosest < 0)
            shipPhysics.move(true,-1);
        else if (angleToClosest > 0)
            shipPhysics.move(true,1);
        else
            shipPhysics.move(true,0);
    }


    /**
     * @return The current round of the game.
     */
    protected long getCurrentRound () {
        return currentRound;
    }


    /**
     * @return The last round in which the ship activated its shield.
     */
    protected long getLastRoundShieldOn () {
        return lastRoundShieldOn;
    }


    /**
     * @return The number of times this ship has died.
     */
    protected int getDeathCount () {
        return deathCount;
    }


    /**
     * Sets the given parameter to the first round in which the ship was disabled.
     * @param specifiedRound A round in the game.
     */
    protected void setRoundDisabled (long specifiedRound) {
        roundDisabled = specifiedRound;
    }


    /**
     * Sets the image of the ship.
     * @param image Image of a ship.
     */
    protected void setImage(Image image) {
        shipImage = image;
    }

}
