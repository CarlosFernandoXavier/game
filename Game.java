package game;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * The class Game used the Bullet class and Ground Class and the Players Class
 * to demonstrate a 2D shooting game at each other with angle and power. When
 * bullets hit the ground, it will create an explosion on the ground. Game ends
 * when either of the player die by losing all health or fall off the screen.
 *
 * @author Don, Trung Hieu (Austin)
 *
 */
public class Game extends JPanel implements ActionListener, KeyListener {

    /**
     * imgBackground - an imageIcon of the background
     *
     * ground - a ground used to make explosions and check for solid/transparent
     * pixels
     *
     * player1 - a player represents one of the two players
     *
     * player2 - a player represents one of the two players
     *
     * player - a player that is used for all the methods to control one of the
     * two players in each turn by implementing player = player1 or player =
     * player2
     *
     * bullet1 - the bullet of the first player
     *
     * bullet2 - the bullet of the second player
     *
     * bullet - the bullet that is used for all methods and algorithms to
     * control one of the two bullets. It is the same idea as
     * player/player1/player2
     *
     * end - a boolean to escape the loop
     *
     * hit1 - a boolean to check if bullet hits player1
     *
     * hit2 - a boolean to check if bullet hits player2
     *
     * power - the int represents the power that the player shoots the bullet
     *
     * angle - the int shows angle by which the player shoots
     *
     * sec - the int that represents seconds
     *
     * centerX - the int for the x - coordinate of where the bullet hits the
     * ground
     *
     * centerY - the int for the y - coordinate of where the bullet hits the
     * ground
     *
     * projextX - the int shows x coordinate of the bullet when shot
     *
     * projextY - the int shows y coordinate of the bullet when shot
     *
     * bulletTimer - a timer starts when bullet is shot to check for collisions
     *
     * everySec - a timer used to count seconds in order to display messages
     *
     * time - a double used of the time at a certain point when the bullet is
     * shot
     *
     * imgInfo - an imageIcon for health, energy and angle at the top
     *
     * line - an imageIcon of a line
     *
     * line1 - an imageIcon of a dotted line
     *
     */
    private ImageIcon imgBackground;
    private Ground ground;
    private Players player1, player2, player;
    private Bullet bullet, bullet1, bullet2;
    private boolean end, hit1, hit2;
    private int power, angle, sec;
    private int centerX;
    private int centerY;
    private int projectX, projectY;
    private int points;
    private int count;
    private String[] options;
    private Timer bulletTimer, everySec;
    private double time;
    private ImageIcon imgInfo, line, line1;
    private JLabel lblAngle, lblHealth, lblEnergy, lblMessage, lblAttack, lblDefense, lblEnergy1, lblExplode,
            lblMessage1, lblPoints, lblInstructions;
    private JButton btn1, btn2, btn3;
    private JComboBox<String> cb, cb1, cb2, cb3;
    private JPanel weapon, panel, panel1;
    private JFrame frame;
    private ImageIconTarget image;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Game();
    }

    /**
     * The constructor used to initialize all necessary objects and variables.
     * The constructor will initialize JLabels and customizes those. It will
     * then add the components to the panel in the right order, place and set
     * the panel to the frame.
     */
    public Game() {
        // customize the font and format the message
        lblInstructions = new JLabel();
        lblInstructions.setFont(new Font("Arial Black", Font.BOLD, 21));
        lblInstructions.setText("<html>Welcome to the Game! There will be two characters and each player will <br>take their turn to shoot at each other! One will die if their health gets to 0 or they fall off the screen!<br><br>    CONTROLS:<br>You will be using left and right arrow keys to move your character!<br>Up and Down to set the angle of your shot<br>Hold spacebar to set the power of your shot, the longer the more powerful<br>Esc to exit the game<br><br>     But first you will need to tune your weapons:<br>Attack will inrease your damage<br>Defense will decrease the incoming damage<br>Energy will let you move more<br>Explode will make your weapon more explosive</html>");
        // show message instructions to the player at first
        JOptionPane.showMessageDialog(null,
                lblInstructions,
                "Welcome!", JOptionPane.INFORMATION_MESSAGE);

        // initialize variables
        count = 0;
        points = 15;
        options = new String[]{"0", "1", "2", "3", "4", "5"};
        end = false;
        sec = 0;
        angle = 45;
        hit1 = false;
        hit2 = false;
        time = 0;
        centerX = 0;
        centerY = 0;

        // initialize panels and customize panels
        weapon = new JPanel(new GridLayout(4, 2, 10, 10));
        weapon.setBackground(new Color(176, 196, 222));

        panel = new JPanel(new GridLayout(4, 1, 0, 20));
        panel.setBackground(new Color(176, 196, 222));

        panel1 = new JPanel();
        panel1.setBackground(new Color(176, 196, 222));

        this.setLayout(null);

        // Initialize the ground object, players and bullets
        ground = new Ground();
        
        image = new OpenIconAdapter();
        BulletFactory bulletFactory = new WinchesterFactory();
        
        bullet1 = bulletFactory.createBullet();
        bullet2 = bullet1.clonar();

        ImageIcon imgPlayer = image.carregarIcon("images/emoji1.png");
        ImageIcon imgDeadPlayer = image.carregarIcon("images/rip.png");

        player1 = new Players.Builder()
                .x(0)
                .y(0)
                .imgPlayer(imgPlayer)
                .imgDeadPlayer(imgDeadPlayer)
                .isDead(false)
                .width(imgPlayer.getIconWidth())
                .height(imgPlayer.getIconHeight())
                .health(1000)
                .energy(250)
                .build();

        

        ImageIcon imgPlayer2 = image.carregarIcon("images/emoji2.png");
        player2 = new Players.Builder()
                .x(1200)
                .y(0)
                .imgPlayer(imgPlayer2)
                .imgDeadPlayer(imgDeadPlayer)
                .isDead(false)
                .width(imgPlayer2.getIconWidth())
                .height(imgPlayer2.getIconHeight())
                .health(1000)
                .energy(250)
                .build();

        // call the gravity method so that players stay close to the ground
        // as the gravity only gravitates the player object, set the player1 and player2 to the player object and call method
        player = player2;
        gravity();
        // set the player that plays the first turn
        bullet = bullet1;
        player = player1;
        gravity();

        // initialize timers
        bulletTimer = new Timer(50, this);
        everySec = new Timer(1000, this);

        // initialize ImageIcons
        imgBackground = image.carregarIcon("images/backgroundart_grassland.jpg");
        imgInfo = image.carregarIcon("images/image.png");
        line = image.carregarIcon("images/line.png");
        line1 = image.carregarIcon("images/characterline.png");

        // Initialize lblHealth, set bounds, font, font size and text, also add to the panel
        lblHealth = new JLabel();
        lblHealth.setBounds(320, 25, 100, 10);
        lblHealth.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblHealth.setText("1000/1000");
        this.add(lblHealth);

        // Initialize lblEnergy, set bounds, font, font size and text, also add to the panel
        lblEnergy = new JLabel();
        lblEnergy.setBounds(320, 55, 100, 10);
        lblEnergy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblEnergy.setText("1000/1000");
        this.add(lblEnergy);

        setSize(256, 256);

        // Initialize lblAngle, set bounds, font, font size and text, also add to the panel
        lblAngle = new JLabel();
        lblAngle.setForeground(Color.RED);
        lblAngle.setBounds(2, 2, 20, 10);
        //lblAngle.setBounds(0, 0, 5, 10);
        lblAngle.setFont(new Font("Arial Black", Font.BOLD, 14));
        lblAngle.setHorizontalAlignment(SwingConstants.CENTER);
        lblAngle.setText(45 + "");
        this.add(lblAngle);

        // Initialize lblMessage, set bounds, font, font size and text, also add to the panel
        lblMessage = new JLabel();
        lblMessage.setForeground(Color.RED);
        //lblMessage.setBounds(400, 250, 1000, 100);
        lblMessage.setBounds(20, 250, 50, 100);
        lblMessage.setFont(new Font("Britannic Bold", Font.BOLD, 70));
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setText("");
        this.add(lblMessage);

        // Initialize lblEnergy1 font, font size and text, also align it to the center
        lblEnergy1 = new JLabel();
        lblEnergy1.setFont(new Font("Britannic Bold", Font.BOLD, 48));
        lblEnergy1.setText("Energy");
        lblEnergy1.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize lblAttack font, font size and text, also align it to the center
        lblAttack = new JLabel();
        lblAttack.setFont(new Font("Britannic Bold", Font.BOLD, 48));
        lblAttack.setText("Attack");
        lblAttack.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize lblDefense font, font size and text, also align it to the center
        lblDefense = new JLabel();
        lblDefense.setFont(new Font("Britannic Bold", Font.BOLD, 48));
        lblDefense.setText("Defense");
        lblDefense.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize lblExplode font, font size and text, also align it to the center
        lblExplode = new JLabel();
        lblExplode.setFont(new Font("Britannic Bold", Font.BOLD, 48));
        lblExplode.setText("Explode");
        lblExplode.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize lblMessage1 font, font size and text, also align it to the center
        lblMessage1 = new JLabel();
        lblMessage1.setFont(new Font("Britannic Bold", Font.BOLD, 72));
        lblMessage1.setText("Player 1! Please tune your weapon");
        lblMessage1.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize lblPoints font, font size and text, also align it to the center
        lblPoints = new JLabel();
        lblPoints.setFont(new Font("Britannic Bold", Font.BOLD, 48));
        lblPoints.setText("Points: " + points);
        lblPoints.setHorizontalAlignment(SwingConstants.CENTER);

        // intialize all the comboBox with the array options and add actionlistener to update the points
        cb = new JComboBox<String>(options);
        cb.addActionListener(this);
        cb1 = new JComboBox<String>(options);
        cb1.addActionListener(this);
        cb2 = new JComboBox<String>(options);
        cb2.addActionListener(this);
        cb3 = new JComboBox<String>(options);
        cb3.addActionListener(this);

        // Initialize btn1, set font, font size, dimension, text and add actionlistener
        btn1 = new JButton();
        btn1.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        btn1.setText("Craft Player1's Weapon");
        btn1.setSize(new Dimension(40, 30));
        btn1.addActionListener(this);

        // Initialize btn2, set font, font size, dimension, text and add actionlistener
        btn2 = new JButton();
        btn2.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        btn2.setText("Craft Player2's Weapon");
        btn2.setSize(new Dimension(40, 30));
        btn2.addActionListener(this);
        btn2.setEnabled(false);

        // Initialize btn3, set font, font size, dimension, text and add actionlistener
        btn3 = new JButton();
        btn3.setFont(new Font("Britannic Bold", Font.BOLD, 30));
        btn3.setText("Play");
        btn3.setSize(new Dimension(40, 30));
        btn3.addActionListener(this);
        btn3.setEnabled(false);

        // add all the needed components to the panel weapon
        weapon.add(lblAttack);
        weapon.add(cb);
        weapon.add(lblDefense);
        weapon.add(cb1);
        weapon.add(lblEnergy1);
        weapon.add(cb2);
        weapon.add(lblExplode);
        weapon.add(cb3);

        // add all the components to the panel panel
        panel.add(lblMessage1);
        panel.add(weapon);
        panel.add(lblPoints);
        // add all the components to the panel1
        panel1.add(btn1);
        panel1.add(btn2);
        panel1.add(btn3);

        panel.add(panel1);

        // initialize frame, set contentpane as panel, set title, let the frame be maximized with no borders, 
        frame = new JFrame();
        frame.setContentPane(panel);
        setFocusable(true);
        frame.setTitle("Gunny");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // add keylistener
        addKeyListener(this);

    }

    /**
     * This is the method used to receive the keyboard input. Move angle using
     * up and down arrow keys, move player using left and right and the spacebar
     * is used to set the power. User can also exit the game by pressing Esc
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (power < 150) {
                power += 5;
            } else {
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setDirection(1);
            move();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setDirection(0);
            move();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (angle < 90) {
                angle++;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (angle > 0) {
                angle--;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            player = player2;
        }
        repaint();
    }

    @Override
    /**
     * The method to receive when the player release the key. When released the
     * spacebar, it will set the bullet to the position of the dotted line, set
     * isFired boolean to true and start the bullet timer so that it shoots.
     */

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (bullet.isFired() == false) {
                bullet.setFired(true);
                if (player.getDirection() == 0) {
                    projectX = player.getX() + player.getWidth();
                    projectY = player.getY() - bullet.getHeight();

                } else {
                    projectX = player.getX() - player.getWidth();
                    projectY = player.getY() - bullet.getHeight();

                }
                bullet.setX(projectX);
                bullet.setY(projectY);
                bulletTimer.start();

            }
        }
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * <p>
     * There is the button listener and the combobox listener that is used to
     * tune the weapons in the first panel and calculate points left
     * <p>
     * For in game, the method used to receive timers updates. The method will
     * call a specific method for each type of timer to perform the correct
     * task. The bulletTimer will call the methods to check for collision and
     * change the player turns.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // when bullet is shot
        if (e.getSource() == bulletTimer) {
            // disable the keyboard
            removeKeyListener(this);

            // keep adding time to update the x,y position of the bulelt
            time += 0.40;

            // update the bullet by calling the method
            updateBullet(player, bullet);

            // call the method to check if any player is hit
            bulletAndPlayerCollision();

            // do calculations and make changes, change player's turn
            endTurn();

            // update screen
            repaint();
        }

        // timer starts when the one of the player dies
        if (e.getSource() == everySec) {
            // count up seconds
            sec++;
            // after 3 seconds show message ask if the player wants to play again
            if (sec == 3) {
                // enable keyboard in case player exits
                addKeyListener(this);
                int result = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Gunny",
                        JOptionPane.YES_NO_OPTION);
                // exit if says no
                if (result == 1) {
                    System.exit(0);
                } else {
                    // if yes, starts over the game
                    new Game();
                }
            }
        }

        // when the btn1 is pressed (tune player 1 weapons) for confirming
        if (e.getSource() == btn1) {

            // set the weapon attack stats to the choice chosen
            if (cb.getSelectedIndex() == 0) {
                bullet1.setAttack(0);
            } else if (cb.getSelectedIndex() == 1) {
                bullet1.setAttack(50);
            } else if (cb.getSelectedIndex() == 2) {
                bullet1.setAttack(100);
            } else if (cb.getSelectedIndex() == 3) {
                bullet1.setAttack(150);
            } else if (cb.getSelectedIndex() == 4) {
                bullet1.setAttack(200);
            } else if (cb.getSelectedIndex() == 5) {
                bullet1.setAttack(250);
            }

            // set the weapon defense stats to the choice chosen
            if (cb1.getSelectedIndex() == 0) {
                bullet1.setDefense(0);
            } else if (cb1.getSelectedIndex() == 1) {
                bullet1.setDefense(30);
            } else if (cb1.getSelectedIndex() == 2) {
                bullet1.setDefense(60);
            } else if (cb1.getSelectedIndex() == 3) {
                bullet1.setDefense(90);
            } else if (cb1.getSelectedIndex() == 4) {
                bullet1.setDefense(120);
            } else if (cb1.getSelectedIndex() == 5) {
                bullet1.setDefense(150);

            }

            // set the weapon energy stats to the choice chosen
            if (cb2.getSelectedIndex() == 0) {
                bullet1.setEnergy(0);
            } else if (cb2.getSelectedIndex() == 1) {
                bullet1.setEnergy(50);
            } else if (cb2.getSelectedIndex() == 2) {
                bullet1.setEnergy(100);
            } else if (cb2.getSelectedIndex() == 3) {
                bullet1.setEnergy(150);
            } else if (cb2.getSelectedIndex() == 4) {
                bullet1.setEnergy(200);
            } else if (cb2.getSelectedIndex() == 5) {
                bullet1.setEnergy(250);
            }

            // set the weapon explosion stats to the choice chosen
            if (cb3.getSelectedIndex() == 0) {
                bullet1.setExplode(0);
            } else if (cb3.getSelectedIndex() == 1) {
                bullet1.setExplode(20);
            } else if (cb3.getSelectedIndex() == 2) {
                bullet1.setExplode(40);
            } else if (cb3.getSelectedIndex() == 3) {
                bullet1.setExplode(60);
            } else if (cb3.getSelectedIndex() == 4) {
                bullet1.setExplode(80);
            } else if (cb3.getSelectedIndex() == 5) {
                bullet1.setExplode(100);
            }

            // disable the other 2 button and enable the btn2 (Tune player2's weapon)
            btn2.setEnabled(true);
            btn1.setEnabled(false);
            // reset comboBox
            cb.setSelectedIndex(0);
            cb1.setSelectedIndex(0);
            cb2.setSelectedIndex(0);
            cb3.setSelectedIndex(0);

            // edit the message 
            lblMessage1.setText("Player 2! Please tune your weapon");

        }

        // When btn2 is pressed (tune player2's weapon)
        if (e.getSource() == btn2) {

            // set the weapon attack stats to the choice chosen
            if (cb.getSelectedIndex() == 0) {
                bullet2.setAttack(0);
            } else if (cb.getSelectedIndex() == 1) {
                bullet2.setAttack(50);
            } else if (cb.getSelectedIndex() == 2) {
                bullet2.setAttack(100);
            } else if (cb.getSelectedIndex() == 3) {
                bullet2.setAttack(150);
            } else if (cb.getSelectedIndex() == 4) {
                bullet2.setAttack(200);
            } else if (cb.getSelectedIndex() == 5) {
                bullet2.setAttack(250);
            }

            // set the weapon defense stats to the choice chosen
            if (cb1.getSelectedIndex() == 0) {
                bullet2.setDefense(0);
            } else if (cb1.getSelectedIndex() == 1) {
                bullet2.setDefense(30);
            } else if (cb1.getSelectedIndex() == 2) {
                bullet2.setDefense(60);
            } else if (cb1.getSelectedIndex() == 3) {
                bullet2.setDefense(90);
            } else if (cb1.getSelectedIndex() == 4) {
                bullet2.setDefense(120);
            } else if (cb1.getSelectedIndex() == 5) {
                bullet2.setDefense(150);
            }

            // set the weapon energy stats to the choice chosen
            if (cb2.getSelectedIndex() == 0) {
                bullet2.setEnergy(0);
            } else if (cb2.getSelectedIndex() == 1) {
                bullet2.setEnergy(50);
            } else if (cb2.getSelectedIndex() == 2) {
                bullet2.setEnergy(100);
            } else if (cb2.getSelectedIndex() == 3) {
                bullet2.setEnergy(150);
            } else if (cb2.getSelectedIndex() == 4) {
                bullet2.setEnergy(200);
            } else if (cb2.getSelectedIndex() == 5) {
                bullet2.setEnergy(250);
            }

            // set the weapon explode stats to the choice chosen
            if (cb3.getSelectedIndex() == 0) {
                bullet2.setExplode(0);
            } else if (cb3.getSelectedIndex() == 1) {
                bullet2.setExplode(20);
            } else if (cb3.getSelectedIndex() == 2) {
                bullet2.setExplode(40);
            } else if (cb3.getSelectedIndex() == 3) {
                bullet2.setExplode(60);
            } else if (cb3.getSelectedIndex() == 4) {
                bullet2.setExplode(80);
            } else if (cb3.getSelectedIndex() == 5) {
                bullet2.setExplode(100);
            }

            // disable the first 2 buttons and enable the third button 
            btn2.setEnabled(false);
            btn3.setEnabled(true);
        }

        // when the btn3 is pressed to start the game
        if (e.getSource() == btn3) {
            // make the changes that the player just make when tuning weapons
            player1.setEnergy(bullet1.getEnergy());
            player2.setEnergy(bullet2.getEnergy());

            // replace the panel with the in-game panel
            frame.getContentPane().removeAll();
            frame.setContentPane(this);
            frame.invalidate();
            frame.validate();
            this.requestFocus();
        }

        // the points are calculated depending on what level of stats is chosen. The level value matches the comboBox Index
        if (e.getSource() == cb || e.getSource() == cb1 || e.getSource() == cb2 || e.getSource() == cb3) {
            points = 15 - cb.getSelectedIndex() - cb1.getSelectedIndex() - cb2.getSelectedIndex()
                    - cb3.getSelectedIndex();
            lblPoints.setText("Points: " + points);
            if (points < 0) {
                // If the levels is worth more than 15, show message and reset comboBox
                JOptionPane.showMessageDialog(null, "Points Exceeded", "Error!", JOptionPane.INFORMATION_MESSAGE);
                cb.setSelectedIndex(0);
                cb1.setSelectedIndex(0);
                cb2.setSelectedIndex(0);
                cb3.setSelectedIndex(0);
            }
        }
    }

    /**
     * The method used to draw everything using Graphics2D. The method contain
     * methods called from other classes which requires Graphics2D and also draw
     * shapes images taken from this class. It also set Text to the variables as
     * their are connections between JLabels and images/shape.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        Graphics2D rotate = (Graphics2D) g.create();

        // draw the background
        g2.drawImage(imgBackground.getImage(), 0, 0, null);

        // draw the image for health, energy and angles
        g2.drawImage(imgInfo.getImage(), 0, 0, null);

        // set new color and draw the light power bar
        g2.setColor(new Color(219, 78, 78, 50));
        g2.fillRoundRect(450, 30, 900, 50, 10, 10);

        //set new color and draw the power bar that increases when the player holds space
        g2.setColor(new Color(216, 31, 42, 255));
        g2.fillRoundRect(450, 30, (int) (power / 150.0 * 900), 50, 10, 10);

        // set new color and draw the health bar
        g2.setColor(new Color(216, 31, 42, 255));
        g2.fillRoundRect(115, 20, (int) (player.getHealth() / 1000.0 * 200), 20, 7, 7);
        lblHealth.setText(player.getHealth() + "/" + 1000);

        // set new color and draw the energy bar
        g2.setColor(new Color(153, 153, 0, 255));
        if (player.getEnergy() > 0) {
            g2.fillRoundRect(115, 50, (int) (player.getEnergy() / (double) bullet.getEnergy() * 188), 18, 7, 7);
        }
        lblEnergy.setText(player.getEnergy() + "/" + bullet.getEnergy());

        // draw the ground
        ground.draw(g2);

        // set bullets position to each player and draw bullet
        drawBullet(bullet1, player1);
        bullet1.draw(g2);

        drawBullet(bullet2, player2);
        bullet2.draw(g2);

        // draw player 1
        player1.draw(g2);

        // set new color and draw the base of the health bar that sticks to the player
        g2.setColor(Color.GRAY);
        g2.fillRect(player1.getX() + player1.getWidth() / 2 - 35, player1.getY() - 20, 70, 10);
        g2.fillRect(player2.getX() + player2.getWidth() / 2 - 35, player2.getY() - 20, 70, 10);

        // set new color 
        g2.setColor(Color.GREEN);

        // if health is below 400, set color to red so that health bar appears low
        if (player1.getHealth() <= 400) {
            g2.setColor(Color.red);
        }

        // Draw the real health bar that sticks to the player1
        g2.fillRect(player1.getX() + player1.getWidth() / 2 - 35, player1.getY() - 20,
                (int) (player1.getHealth() / 1000.0 * 70), 10);
        g2.setColor(Color.GREEN);

        // if health is below 400, set color to red so that health bar appears low
        if (player2.getHealth() <= 400) {
            g2.setColor(Color.red);
        }

        // draw the real health bar that sticks to the player
        g2.fillRect(player2.getX() + player2.getWidth() / 2 - 35, player2.getY() - 20,
                (int) (player2.getHealth() / 1000.0 * 70), 10);
        // draw player2
        player2.draw(g2);

        // set rotations and origion of rotation 
        g2.translate(58, 60);
        // depending on player's direction, set rotate angle and the origin of rotation to the 2 graphics2D
        if (player.getDirection() == 0) {
            g2.rotate(Math.toRadians(-angle));
            rotate.translate(player.getX() + player.getWidth(), player.getY());
            rotate.rotate(Math.toRadians(-angle));
        } else {
            g2.rotate(Math.toRadians(angle + 180));
            rotate.translate(player.getX(), player.getY());
            rotate.rotate(Math.toRadians(angle + 180));
        }
        // set the exact angle value to the lblAngle
        lblAngle.setText(angle + "");

        // Having the rotation angle, draw the lines
        // dotted line nearby players and solid line in the circle
        g2.drawImage(line.getImage(), 0, 0, this);
        rotate.drawImage(line1.getImage(), 0, 0, this);

        // if the players are dead
        // show message
        // disable keyboards
        // start timer to show the message dialogue
        if (player1.isDead() == true) {
            lblMessage.setText("WASTED! PLAYER 2 WINS");
            removeKeyListener(this);
            everySec.start();

        } else if (player2.isDead() == true) {
            lblMessage.setText("WASTED! PLAYER 1 WINS");
            removeKeyListener(this);
            everySec.start();
        }

    }

    /**
     * The method used to update the bullet for each player using physics
     * formulas and a little modification due to how x and y coordinate works in
     * java (y positive going down)
     *
     * @param player the player that shoots the bullet
     * @param bullet the bullet which the player shoots
     */
    public void updateBullet(Players player, Bullet bullet) {
        // using physics formula to mimic the trajectory
        // in java, y increases when going down, therefore signs are the opposite to the real life formula
        bullet.setY(projectY + 0.5 * 9.8 * time * time - power * Math.sin(Math.toRadians(angle)) * time);
        // depending on the direction that the bullet goes left or right
        if (player.getDirection() == 0) {
            bullet.setX(projectX + Math.cos(Math.toRadians(angle)) * power * time);
        } else {
            bullet.setX(projectX - Math.cos(Math.toRadians(angle)) * power * time);
        }

    }

    /**
     * This method use the 2D array bitmap to check for solid pixels and check
     * where the bullet hits on the ground. The centerX and centerY is then set
     *
     * @return boolean to check if the bullet hits the ground.
     */
    private boolean bulletAndGroundCollision() {
        // using nested for loop to check for every pixel
        for (int x = 0; x < ground.getImage().getWidth(); x++) {
            for (int y = 0; y < ground.getImage().getHeight(); y++) {
                // if pixel is solid and the bullet hits it
                if (ground.getBitmap()[x][y] == 0 && bullet.getRectangle().contains(x, y)) {
                    // set the centerX and centerY to that position to make explosions later
                    centerX = x;
                    centerY = y;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method get the rectangle of the player1/player2 and bullet to check
     * if those intersects. If they intersect, the boolean hit1/hit2 is set to
     * true
     */
    public void bulletAndPlayerCollision() {
        // check if the bullet hits any of the 2 players by checking intersecting rectangles
        // hit box idea
        // if hit, damage will be calculated later
        if (bullet.getRectangle().intersects(player1.getRectangle())) {
            hit1 = true;
        } else if (bullet.getRectangle().intersects(player2.getRectangle())) {
            hit2 = true;
        }
    }

    /**
     * This method is used to move the players, it only moves if the energy is
     * more than 0 and the player is within the screen. It uses bitmap to check
     * if the player hit and solid pixels if it were to move one left or right
     * and proceed based on that.
     */
    public void move() {
        // if energy ran out, do nothing
        if (player.getEnergy() == 0) {

            // if players are at borders of the screen, bounce back, energy still decreases
        } else if (player.getX() == 0) {
            player.setX(player.getX() + 1);
        } else if (player.getX() + player.getWidth() >= 1919) {
            player.setX(player.getX() - 1);

        } else {
            // call gravity method to stick the players to the ground 
            gravity();
            // initialize the local variable a by accessing the bitmap of the ground
            int[][] a = ground.getBitmap();
            // if it player goes east
            if (player.getDirection() == 0) {
                // move 1 right
                player.setX(player.getX() + 1);
                // decrease the energy
                player.setEnergy(player.getEnergy() - 1);
                // check for terrain ahead by checking the 2 sides of the top right corner of the player's hit box 
                for (int x = 0; x < player.getWidth(); x++) {
                    for (int y = 0; y < player.getHeight(); y++) {
                        // if the player after moving hits the terrain, goes backwards and give back energy
                        if (a[player.getX() + x][player.getY() + y] == 0) {
                            player.setX(player.getX() - 1);
                            player.setEnergy(player.getEnergy() + 1);
                            // escape loop for efficiency
                            x = player.getWidth();
                            y = player.getHeight();
                        }
                    }
                }
                // if moving west
            } else {
                // move 1 left
                player.setX(player.getX() - 1);
                // decrease the energy
                player.setEnergy(player.getEnergy() - 1);
                // check for terrain ahead by checking the 2 sides of the top left corner of the player's hit box 
                for (int x = 0; x < player.getWidth(); x++) {
                    for (int y = 0; y < player.getHeight(); y++) {
                        // if the player after moving hits the terrain, goes backwards and give back energy
                        if (a[player.getX() + x][player.getY() + y] == 0) {
                            player.setEnergy(player.getEnergy() + 1);
                            player.setX(player.getX() + 1);
                            // escape loop for efficiency
                            x = player.getWidth();
                            y = player.getHeight();
                        }
                    }
                }

            }
            // sticks the player to the ground in case it was a downhill
            gravity();
        }
    }

    /**
     * This method is used to keep the player close to the ground all the time.
     * A loop is used to search for the closest solid pixels below. If the
     * player sit on the solid pixel, it moves up by three, this is how the
     * character climbs up and down.
     */
    public void gravity() {
        // initialize the boolean
        end = false;
        // initialize the local int[][] a by accessing the bitmap from the ground class
        int[][] a = ground.getBitmap();
        // get the bottom of the player
        int num = player.getY() + player.getHeight();
        do {
            // if the bottom hits the end of the fram vertically, the player dies, escape loop
            if (num == 1080) {
                end = true;
                player.setDead(true);
            }
            // loop through all the points of the bottom side of the player's hit box
            for (int x = 0; x <= player.getWidth(); x++) {
                // keeps increasing the y value (going down) until it hits the terrain
                // if hit terrain, bounce back by 3 and escape loop

                // by bouncing back by 3 gives the ability to climb terrain
                // (cases where player sits right on top of the terrain, it will
                // always maintain a distance of 3, if the terrain rises, the
                // player rises)
                if (a[player.getX() + x][num] == 0) {
                    end = true;
                    num -= 3;
                }
            }
            num++;
        } while (!end);
        // set y coordinate to the player
        player.setY(num - player.getHeight());
    }

    /**
     * The bullet when it is not shot must stay close to the players. Therefore
     * x and y coordinates need to be taken.
     *
     * @param bullet the bullet of the player.
     * @param player the player that carries the bullet
     */
    public void drawBullet(Bullet bullet, Players player) {
        // if the bullet is not fired, set it attached to the players depending on the direction
        if (bullet.isFired() == false) {
            if (player.getDirection() == 0) {
                bullet.setX(player.getX() - 25);
                bullet.setY(player.getY() - 15);
            } else {
                bullet.setX(player.getX());
                bullet.setY(player.getY() - 15);
            }
        }
    }

    /**
     * This method happens when the bullet goes beyond the frame and if the
     * bullet hits the ground. It will then reset variables, call the explosion
     * method. It will also make edits to the health at the end if the players
     * are hit.
     */
    public void endTurn() {
        // The turn ends whenever the bullet goes beyond screen or when bullet hits the ground
        if (bulletAndGroundCollision() || bullet.getX() < 0 || bullet.getX() > 1920 || bullet.getY() > 1080) {
            // if bullet hits the ground
            if (bulletAndGroundCollision()) {
                // make explosions by passing the point where bullet hit and explosion radius
                ground.explode(centerX, centerY, bullet.getExplode());
                // reset bitmap to change the values of the pixels that just exploded
                ground.setupBitmap();
            }
            // stop the bullet timer to stop the bullet
            bulletTimer.stop();
            // set fired to false so that the bullet gets attached to the player again
            bullet.setFired(false);
            // reset other values for new turn
            time = 0;
            power = 0;
            angle = 45;

            // gravitate the player in case the ground which the player is standing on is exploded
            gravity();

            // if one of the player is hit
            if (hit1) {
                // calculate damage taken by taking the current bullet attack's damage minus the defense stats of the weapon of the player being hit
                int damage = bullet.getAttack() - bullet1.getDefense();
                // catch error, in case defense is greater than attack which results healing
                if (damage > 0) {
                    player1.setHealth(player1.getHealth() - (damage));
                }
                // reset boolean and check if the player is dead after calculations
                hit1 = false;
                // if dead set dead to true
                if (player1.getHealth() <= 0) {
                    player1.setDead(true);
                }
            }

            if (hit2) {
                // calculate damage taken by taking the current bullet attack's damage minus the defense stats of the weapon of the player being hit
                int damage = bullet.getAttack() - bullet2.getDefense();
                // catch error, in case defense is greater than attack which results healing
                if (damage > 0) {
                    player2.setHealth(player2.getHealth() - (damage));
                }
                // reset boolean and check if the player is dead after calculations
                hit2 = false;
                // if dead set dead to true
                if (player2.getHealth() <= 0) {
                    player2.setDead(true);
                }

            }
            // switch turns by setting the opposite player to the player object
            if (player == player1) {
                bullet = bullet2;
                player = player2;
                addKeyListener(this);

            } else if (player == player2) {
                bullet = bullet1;
                player = player1;
                addKeyListener(this);
            }

            // gravitates the player for this turn in case the terrain below the player has jsut exploded
            gravity();
            // reset energy
            player.setEnergy(bullet.getEnergy());
        }
    }
}
