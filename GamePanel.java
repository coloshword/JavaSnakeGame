import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;



public class GamePanel extends JPanel implements ActionListener{
    
    // static variables related to the game
    static final int screenWidth = 600;
    static final int screenHeight = 600;
    static final int unitSize = 25;
    static final int gameUnits = (screenWidth * screenHeight) / unitSize;
    static final int delay = 75;
    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];
    // number of bodies the snake starts with
    int numBody = 6;
    // number of apples eaten
    int applesEaten = 0;
    // the xcor of the apple
    int appleXcor;
    // the ycor of the apple
    int appleYcor;
    // the direction the snake is going, begins right
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public GamePanel() {
        random = new Random();
        // create a new gamePanel, which will be added to the JFrame and be a specific size
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // only draw if running
        if(running) {
            for(int i = 0; i<screenHeight/ unitSize; i++) {
                g.setColor(Color.darkGray);
                g.drawLine(i * unitSize, 0, i*unitSize, screenHeight);
                g.drawLine(0, i * unitSize, screenWidth, i * unitSize);
            }
            // now draw apple
            g.setColor(Color.red);
            g.fillOval(appleXcor, appleYcor, unitSize, unitSize);

            // now draw the snake 
            for(int i = 0; i < numBody; i++) {
                // this is the head of snake 
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                }
                else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                }
            }
            g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten,(screenWidth - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }

    public void newApple() {
        // generate the coordinates of a new apple
        appleXcor = random.nextInt((int)(screenWidth/unitSize)) * unitSize;
        appleYcor = random.nextInt((int)(screenHeight/unitSize)) * unitSize;

    }

    public void move() {
        // first lets create the snake 
        for(int i = numBody; i > 0; i--) {
            // the way you move the coordinates of the snake. For each time, the coordinates
            // are shifted upward
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        // now create a switch statement to analyze the direction
        switch(direction) {
            case 'U': 
            // since the top y coordinate is 0, you should subtract to make it go up
                y[0] = y[0] - unitSize;
                break;
            case 'D':
                y[0] = y[0] + unitSize;
                break;
            case 'R':
                x[0] = x[0] + unitSize;
                break;
            case 'L':
                x[0] = x[0] - unitSize;
                break;
        }
    }

    public void checkApple() {
        // check to see if you landed an apple
        if((x[0] == appleXcor) && y[0] == appleYcor) {
            // then you have eaten an apple
            applesEaten++;
            // and the snake gets bigger
            numBody++;
            // respawn an apple 
            newApple();
        } 
    }

    public void checkCollisions() {
        // check to see if the head of the snake hits the body
        for(int i = numBody; i > 0; i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }

        }
        // check if the head touches with the border
        // left border
        if(x[0] < 0) {
            running = false;
        }
        //right border
        if(x[0] > screenWidth) {
            running = false; 
        }
        // top border
        if(y[0] < 0) {
            running = false;
        }
        // bottom border
        if(y[0] > screenHeight) {
            running = false;
        }

        if(!running) {
            repaint();
            timer.stop();
            JButton button = new JButton();
            button.setBackground(Color.red);
            button.setOpaque(true);
            button.setBorderPainted(false);
        }
    }

    public void gameOver(Graphics g) {
        // make Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 70));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over",(screenWidth - metrics.stringWidth("Game Over")) / 2, screenHeight / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // now you need to call the relevant methods related to actionPerformed, including move
        if(running) {
            // if the game is running then do these actionPerformed
            // check for these events (basically your while loop)
            move();
            checkApple();
            checkCollisions();
        }
        // call repaint if the game is over
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{ 
        @Override
        public void keyPressed(KeyEvent e) {
            // examine keyEvent and call the corresponding move 
            switch(e.getKeyCode()) {
                case KeyEvent.VK_A:
                    // Cannot make a full 180 degree turn right off the bat
                    // basically if you're not already going right, then you can go left
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    // Cannot make a full 180 degree turn right off the bat
                    // basically if you're not already going right, then you can go left
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    // Cannot make a full 180 degree turn right off the bat
                    // basically if you're not already going right, then you can go left
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break; 
                case KeyEvent.VK_S:
                    // Cannot make a full 180 degree turn right off the bat
                    // basically if you're not already going right, then you can go left
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}
