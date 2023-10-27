package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
    public int dots;

    public boolean gameOver=false;
    public final int area=900;
    public final int dot_size=20;
    public final int[] y_axis=new int[area];
    public final int[] x_axis=new int[area];
    private Image head;
    private Image body;
    private Image apple;
    private int RANDOM_POSITION=15;
    private int apple_x;
    private int apple_y;

    private  Timer timer;

    private boolean right=true;
    private boolean left=false;
    private boolean up=false;
    private boolean down=false;

    Board(){
        addKeyListener(new TAdapter());
setBackground(new Color(0,0,0));
setFocusable(true);
loadImage();
initGame();

timer=new Timer(140,this);
timer.start();
    }

    public void loadImage(){
        ImageIcon image1=new ImageIcon(ClassLoader.getSystemResource("SnakeGame/Icons/R.png"));
        Image head_=image1.getImage();
        head = head_.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
//        image1 = new ImageIcon(head);

        ImageIcon image2=new ImageIcon(ClassLoader.getSystemResource("SnakeGame/Icons/R (1).png"));
        Image body_=image2.getImage();
        body = body_.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
//        image2 = new ImageIcon(body);

        ImageIcon image3=new ImageIcon(ClassLoader.getSystemResource("SnakeGame/Icons/R (2).png"));
        Image apple_=image3.getImage();
        apple = apple_.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
//        image3 = new ImageIcon(apple);
    }



    public void initGame(){
        dots=3;
        for(int i=0;i<dots;i++){
            y_axis[i]=80;
            x_axis[i]=80-i*dot_size;

        }
      locateApple();
    }

    public void locateApple(){
        int x= (int)(Math.random()*RANDOM_POSITION);
        apple_x=x*dot_size;
        x= (int)(Math.random()*RANDOM_POSITION);
        apple_y=x*dot_size;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        drawings(g);
    }

    public void drawings(Graphics g){

        if(gameOver==false){
        g.drawImage(apple,apple_x,apple_y,this);

        for(int i=0;i<dots;i++){
            if(i==0){
                g.drawImage(head,x_axis[i],y_axis[i],this);
            }
            else{
                g.drawImage(body,x_axis[i],y_axis[i],this);
            }
        }
        Toolkit.getDefaultToolkit().sync();}
        else{
            String overMsg="GAME OVER !!!!";
            Font font =new Font("Algerian Regular", Font.BOLD,27);

            g.setColor(Color.MAGENTA);
            g.setFont(font);
            FontMetrics matrix=getFontMetrics(font);
            g.drawString(overMsg, (300-matrix.stringWidth(overMsg))/2, 150);
        }
    }
public void move(){
        for(int i=dots;i>0;i--){
           x_axis[i]=x_axis[i-1];
           y_axis[i]=y_axis[i-1];
        }

    if(right)
    {x_axis[0]+=dot_size;}
    if(left)
    {x_axis[0]-=dot_size;}
    if(up)
    {y_axis[0]-=dot_size;}
    if(down)
    {y_axis[0]+=dot_size;}

}
public  void gameOverDetection(){

        for(int i=dots;i>0;i--){
            if(i>4 && x_axis[0]==x_axis[i] &&y_axis[0]==y_axis[i]){
                gameOver=true;
            }
            if(x_axis[0]>=300){
                gameOver=true;
            }
            if(x_axis[0]<0){
                gameOver=true;
            }
            if(y_axis[0]<0){
                gameOver=true;
            }
            if(y_axis[0]>=300){
                gameOver=true;
            }

            if(gameOver){
                timer.stop();
            }
        }
}
public void appleLocation(){
        if(x_axis[0]==apple_x  &&  y_axis[0]==apple_y){
            dots++;
            locateApple();
        }
}
public void actionPerformed(ActionEvent e){
        if(!gameOver){
        appleLocation();
        gameOverDetection();
        move();
        repaint();}

}

public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
             if(key==KeyEvent.VK_LEFT && (!right)){
                 left=true;
                 up=false;
                 down=false;
             }
            if(key==KeyEvent.VK_RIGHT && (!left)){
                right=true;
                up=false;
                down=false;
            }
            if(key==KeyEvent.VK_DOWN && (!up)){
                down=true;
                left=false;
                right=false;
            }
            if(key==KeyEvent.VK_UP && (!down)){
                up=true;
                right=false;
                left=false;
            }
        }

    }

}
