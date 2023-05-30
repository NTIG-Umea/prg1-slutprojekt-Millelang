import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

public class Slutprojekt extends Canvas implements Runnable {
    private BufferStrategy bs;

    private int tid = 0;



    private Rectangle spelare1 = new Rectangle(444, 320, 50,50);

    private Rectangle spelare2 = new Rectangle(780, 320, 50,50);

    private Rectangle Pucken = new Rectangle(960,540,20,20);

    private Rectangle planhitbox = new Rectangle(0,0,1224,632);
    private boolean running = false;
    private Thread thread;

    private int Spelare1VX;

    private boolean puck1ivag = false;
    private int Spelare2VX;
    private boolean puckWithSpelare1 = false;

    private boolean puck2ivag = false;
    private boolean puckWithSpelare2 = false;
    private int Spelare1VY;

    private int Spelare2VY;

    private int tid2;
    private BufferedImage plan;

    private int puckvx;

    private int puckvy;
    private BufferedImage spelare;
    private BufferedImage spelare22;

    int WIDTH = 1400;
    int HEIGHT = 800;

    public Slutprojekt() {
        try {
            plan = ImageIO.read(getClass().getResource("istockphoto-118877822-612x612.jpg"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            spelare = ImageIO.read(getClass().getResource("spelare.jpg"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            spelare22 = ImageIO.read(getClass().getResource("spelare23.jpg"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(1224, 634);
        JFrame frame = new JFrame();
        frame.add(this);
        this.addKeyListener(new MyKeyListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // Rita ut den nya bilden
        draw(g);

        g.dispose();
        bs.show();
    }

    public void draw(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(plan,planhitbox.x,planhitbox.y,(int)planhitbox.getWidth(),(int)planhitbox.getHeight(),null);
        g.setColor(Color.black);
        g.fillOval(Pucken.x,Pucken.y,20,20);
        g.drawImage(spelare, spelare1.x, spelare1.y,(int)spelare1.getWidth(),(int)spelare1.getHeight(),null);
        g.drawImage(spelare22, spelare2.x, spelare2.y,(int)spelare2.getWidth(),(int)spelare2.getHeight(),null);
    }

    private void update() {

        if ( spelare1.intersects(Pucken) && !puckWithSpelare1 && !puck1ivag) {
            puckWithSpelare1 = true;
        }
        if ( spelare2.intersects(Pucken) && !puckWithSpelare2 && !puck2ivag) {
            puckWithSpelare2 = true;
        }

        if ((spelare2.intersects(spelare1) && puckWithSpelare1) == true)  {
            puckWithSpelare1 = false;
            puckWithSpelare2 = true;
        }

        if ((spelare1.intersects(spelare2) && puckWithSpelare2) == true)  {
            puckWithSpelare2 = false;
            puckWithSpelare1 = true;
        }

        if (puckWithSpelare1) {
            puckvx = Spelare1VX;
            puckvy = Spelare1VY;
        }
        if (puckWithSpelare2) {
            puckvx = Spelare2VX;
            puckvy = Spelare2VY;
        }

        if (spelare1.y < 20) {
            Spelare1VY = Spelare1VY * (Spelare1VY+2);
        }
        if (spelare1.y > 620) {
            Spelare1VY = Spelare1VY * -(Spelare1VY+2);
        }
        if (spelare2.y < 20) {
            Spelare2VY = Spelare2VY * (Spelare2VY+2);
        }
        if (spelare2.y > 620) {
            Spelare2VY = Spelare2VY * -(Spelare2VY+2);
        }


        if (Pucken.x < 105) {
            puckvx = 0;
            puckvy = 0;
            Pucken.x = 610;
            Pucken.y = 320;
            spelare2.x = 780;
            spelare2.y = 320;
            spelare1.x = 444;
            spelare1.y = 320;
            puckWithSpelare2 = false;
            puckWithSpelare1 = false;

        }
        if (Pucken.x > 1110) {
            puckvx = 0;
            puckvy = 0;
            Pucken.x = 610;
            Pucken.y = 320;
            spelare2.x = 1000;
            spelare2.y = 320;
            spelare1.x = 444;
            spelare1.y = 320;
            puckWithSpelare2 = false;
            puckWithSpelare1 = false;

        }

        if (Pucken.y > 634) {
            puckvy= -puckvy;
        }
        if (Pucken.y < 0) {
            puckvy= -puckvy;
        }
        if (Pucken.x < 0) {
            puckvx= -20;
        }
        if (Pucken.x > 1224) {
            puckvx= -puckvx;
        }
        tid2++;
       tid++;
        if (tid == 120) {
            puckvx = puckvx/2;
            puckvy = puckvy/2;
            tid = 0;
            puck1ivag = false;
            puck2ivag = false;
        }
        if (tid2 == 10) {

            tid2 = 0;
            puck1ivag = false;
            puck2ivag = false;
        }



        System.out.println(puckvx);
        spelare1.x += Spelare1VX;
        spelare1.y += Spelare1VY;
        spelare2.x += Spelare2VX;
        spelare2.y += Spelare2VY;
        Pucken.x += puckvx;
        Pucken.y += puckvy;

    }

    public static void main(String[] args) {
        Slutprojekt minGrafik = new Slutprojekt();
        minGrafik.start();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        double ns = 1000000000.0 / 60.0;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                // Uppdatera koordinaterna
                update();
                // Rita ut bilden med updaterad data
                render();
                delta--;
            }
        }
        stop();
    }

    public class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyChar() == ' ' && puckWithSpelare1) {
                puckvx += Spelare1VX;
                puckvy += Spelare1VY;
                puckWithSpelare1 = false;
                puck1ivag = true;


            }
            if (e.getKeyCode() == KeyEvent.VK_SHIFT && puckWithSpelare2) {
                puckvx += Spelare2VX;
                puckvy += Spelare2VY;
                puckWithSpelare2 = false;
                puck2ivag = true;


            }

            if (e.getKeyChar() == 'a') {
                Spelare1VX = -5;
            }
            if (e.getKeyChar() == 'd') {
                Spelare1VX = 5;
            }
            if (e.getKeyChar() == 'w') {
                Spelare1VY = -5;
            }
            if (e.getKeyChar() == 's') {
                Spelare1VY = 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                Spelare2VY = -5;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Spelare2VY = 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Spelare2VX = -5;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Spelare2VX = 5;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == 'a') {
                Spelare1VX = 0;
            }
            if (e.getKeyChar() == 'd') {
                Spelare1VX = 0;
            }
            if (e.getKeyChar() == 'w') {
                Spelare1VY = 0;
            }  if (e.getKeyChar() == 's') {
                Spelare1VY = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                Spelare2VY = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Spelare2VY = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Spelare2VX = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Spelare2VX = 0;
            }
        }
    }

    }