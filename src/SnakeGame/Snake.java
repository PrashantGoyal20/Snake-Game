package SnakeGame;
import javax.swing.*;

public class Snake extends JFrame{
    Snake(){
        super("Snake Game");
        add(new Board());
        pack();
        setVisible(true);
        setResizable(false);
        setSize(325,325);
        setLocationRelativeTo(null);


    }
}
