// the extension of the JPanel class specific to our snake game
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener{
    // create the constructor for the JPanel class specific to our Snake Game
    
    // create screen dimensions
    static final int ScreenWidth = 600;
    static final int ScreenHeight = 600;
    static final int unitSize = 25;
    // the number of items that you can put in the game
    static final int gameUnits = (ScreenWidth * ScreenHeight) / unitSize ;
    static final int delay = 75;
    

    public GamePanel() {

    }
    
    // inside the GamePanel class will be MOST of our game methods
    // since these are instance methods, they are not static
    public void startGame() {

    }
    public void paintComponent(Graphics g) {

    }

    public void draw(Graphics g) {

    }

    public void move() {

    }

    public void checkApple() {

    }

    public void checkCollision() {

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    // an inner class
    public class MyKeyAdapter extends KeyAdapter {
        //@Override
        public void keyPressed(KeyEvent e) {

        }
    }
    
}
