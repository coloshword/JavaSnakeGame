import javax.swing.JFrame;

public class GUI extends JFrame{
    // create the constructor for the Game's GUI
    public GUI() {
        // in the constructor for the GUI, you create an instance of the GamePanel Class
        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // this.pack() makes it so that the size of the JFrame window is based on the size
        // and contents inside of the Frame, rather than a set size
        this.pack();
        this.setVisible(true);
        // this makes it so that the window always appears at the middle
        this.setLocationRelativeTo(null);
    }
}
