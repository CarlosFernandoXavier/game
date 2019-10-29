


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * This class is for the weapons of the players, it includes drawing and storing
 * all the variables and information of the weapon both graphically and
 * mathematically.
 *
 * @author Don, Trung Hieu (Austin)
 *
 */
public class Bullet {

    /**
     * x - the int that is used to indicate the x position of the weapon on
     * screen y - the int that is used to indicate the y position of the weapon
     * on screen height - the int that is used to store the height value of the
     * image width - the int that is used to store the width value of the image
     * attack - the int that is used to store the attack damage of the weapon
     * defense - the int that is used to store the value of the defense of the
     * weapon energy - the int used to store the maximum energy of the player
     * explode - the int used to store the explosion radius of the explosion
     * imgBullet - the imageIcon containing the image of the bullet shot - the
     * boolean telling whether the bullet is projected
     */
    private int x, y, width, height, attack, defense, energy, explode;
    private ImageIcon imgBullet;
    private boolean shot;
    
    public Bullet(){
        
    }

    protected Bullet(Bullet bullet){
        this.x = bullet.x;
        this.y = bullet.y;
        this.imgBullet = bullet.imgBullet;
        this.width = bullet.width;
        this.height = bullet.height;
        this.shot = bullet.shot;
    }
    
    public Bullet clonar(){
        return new Bullet(this);
    }

    /**
     * The getter method of the variable attack
     *
     * @return attack - the int showing the attack stats of the weapon
     */
    public int getAttack() {
        return attack;
    }

    /**
     * The setter method of the variable attack
     *
     * @param attack - the int that is used to set the attack stats of the
     * weapon
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * The getter method of the variable defense
     *
     * @return defense - the int showing the defense stats of the weapon
     */
    public int getDefense() {
        return defense;
    }

    /**
     * The setter method of the variable defense
     *
     * @param defense - the int that is used to set the defense stats of the
     * weapon
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * The getter method of the variable explode
     *
     * @return explode - the int that shows the explosion radius that the weapon
     * will create
     */
    public int getExplode() {
        return explode;
    }

    /**
     * The setter method of the variable explode
     *
     * @param explode - the int that is used to set the explosion radius that
     * the weapon will create
     */
    public void setExplode(int explode) {
        this.explode = explode;
    }

    /**
     * The getter method of the variable energy
     *
     * @return energy - the int that is used to get the maximum energy of the
     * player
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * The setter method of the variable energy
     *
     * @param energy - the int that is used to set the maximum energy of the
     * player
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * The getter method of the variable width
     *
     * @return width - the int that shows the width value of the image
     */
    public int getWidth() {
        return width;
    }

    /**
     * The setter method of the variable width
     *
     * @param width - the int that is used to set the width of the image
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * The getter method of the variable height
     *
     * @return height - the int that shows the height of the image
     */
    public int getHeight() {
        return height;
    }

    /**
     * The setter method of the variable height
     *
     * @param height - the int that sets the height of the image
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * The getter method that returns the hit box of the bullet
     *
     * @return a rectangle that surrounds the bullet
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, imgBullet.getIconWidth(), imgBullet.getIconHeight());
    }

    /**
     * The method that is used to draw the bullet
     *
     * @param g2 The {@link Graphics2D} object passed from the main class
     */
    public void draw(Graphics2D g2) {

        g2.drawImage(imgBullet.getImage(), x, y, null);

    }

    /**
     * The getter method of the x position of the object
     *
     * @return x - the int shows the x coordinate of the object on screen
     */
    public int getX() {
        return x;
    }

    /**
     * The setter method of the x position of the object
     *
     * @param d - the int used to set the x coordinate of the object on screen
     */
    public void setX(double d) {
        this.x = (int) d;
    }

    /**
     * The getter method of the y position of the object
     *
     * @return y - the int shows the y coordinate of the object on screen
     */
    public int getY() {
        return y;
    }

    /**
     * The setter method of the y position of the object
     *
     * @param d - the int used to set the y coordinate of the object on screen
     */
    public void setY(double d) {
        this.y = (int) d;
    }

    /**
     * The getter method of the image of the bullet
     *
     * @return imgBullet - the image of the Bullet
     */
    public ImageIcon getBullet() {
        return imgBullet;
    }

    /**
     * The setter method of the image of Bullet
     *
     * @param imgbullet - the image that is used to set the image of the bullet
     */
    public void setImgBullet(ImageIcon imgbullet) {
        this.imgBullet = imgbullet;
    }

    /**
     * The getter method of the boolean shot
     *
     * @return shot - the boolean showing whether weapon is projected
     */
    public boolean isFired() {
        // TODO Auto-generated method stub
        return shot;
    }

    /**
     * The setter method of the boolean shot
     *
     * @param b - the boolean that is used to set the boolean shot
     */
    public void setFired(boolean b) {
        // TODO Auto-generated method stub
        shot = b;
    }

}
