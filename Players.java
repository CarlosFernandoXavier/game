
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * The Class that contains all the variables and stats of the players
 * graphically and mathematically. It also draws the image of the player.
 *
 * @author Don, Trung Hieu (Austin)
 *
 */
public class Players {

    /**
     * x - the int that is used to indicate the x position of the player on
     * screen y - the int that is used to indicate the y position of the player
     * on screen height - the int that is used to store the height value of the
     * image width - the int that is used to store the width value of the image
     * direction - the int that is used to store the direction of the player (0
     * means right, 1 means left) imgBullet - the imageIcon containing the image
     * of the player imgDeadPlayer - the imageIcon containing the image of the
     * dead player isDead - the boolean telling whether the player is dead
     * health - the int showing the health of the player energy - the int
     * showing the energy of the player
     */

    private int x, y, width, height, direction;
    private ImageIcon imgPlayer, imgDeadPlayer;
    private boolean isDead;
    private int health, energy;

    
    public static class Builder {

        private int x;
        private int y;
        private ImageIcon imgPlayer;
        private ImageIcon imgDeadPlayer;
        private boolean isDead;
        private int width;
        private int height;
        private int health;
        private int energy;
        
        public Builder x(int x) {
            this.x = x;
            return this;
        }
        
        public Builder y(int y) {
            this.y = y;
            return this;
        }
        public Builder imgPlayer(ImageIcon imgPlayer) {
            this.imgPlayer = imgPlayer;
            return this;
        }
        public Builder imgDeadPlayer(ImageIcon imgDeadPlayer) {
            this.imgDeadPlayer = imgDeadPlayer;
            return this;
        }
        public Builder isDead(boolean isDead){
            this.isDead = isDead;
            return this;
        }
        public Builder width(int width) {
            this.width= width;
            return this;
        }
        public Builder height(int height){
            this.height = height;
            return this;
        }
        public Builder health(int health) {
            this.health = health;
            return this;
        }
        public Builder energy(int energy) {
            this.energy = energy;
            return this;
        }
        
        
        public Players build() {
            return new Players(this);
        }
    }
    public Players(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.imgPlayer = builder.imgPlayer;
        this.imgDeadPlayer = builder.imgDeadPlayer;
        this.isDead = builder.isDead;
        this.width = builder.width;
        this.height = builder.height;
        this.health = builder.health;
        this.energy = builder.energy;
    }

    /**
     * The setter method of the image of player
     *
     * @param a - the image that is used to set the image of the player
     */
    public void setImage(ImageIcon a) {
        imgPlayer = a;
    }

    /**
     * The method that is used to draw the player depending on whether the
     * player is dead
     *
     * @param g2 - The graphics2D passed from the main class
     */
    public void draw(Graphics2D g2) {
        if (isDead) {
            g2.drawImage(imgDeadPlayer.getImage(), x, y, null);
        } else {
            g2.drawImage(imgPlayer.getImage(), x, y, null);
        }

    }

    public void setDirection(int a) {
        direction = a;
    }

    public int getDirection() {
        return direction;
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
     * @param x - the int used to set the x coordinate of the object on screen
     */
    public void setX(int x) {
        this.x = x;
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
    public void setY(int y) {
        this.y = y;
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
     * The getter method of the variable health
     *
     * @return health - the int showing the current health of player
     */
    public int getHealth() {
        return health;
    }

    /**
     * The setter method of the variable health
     *
     * @param health - the int setting the current health of player
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * The getter method of the variable energy
     *
     * @return energy - the int showing the current energy of player
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * The setter method of the variable energy
     *
     * @param energy - the int setting the current energy of player
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * The getter method of the variable isDead
     *
     * @return isDead - the boolean telling whether the player is dead
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * The setter method of the variable isDead
     *
     * @return isDead - the boolean setting the boolean that states whether the
     * player is dead (isDead)
     */
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    /**
     * The getter method that returns the hit box of the player
     *
     * @return a rectangle that surrounds the player
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, imgPlayer.getIconWidth(), imgPlayer.getIconHeight());
    }

}
