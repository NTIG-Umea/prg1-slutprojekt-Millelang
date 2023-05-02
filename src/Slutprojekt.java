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

    private Rectangle spelare1 = new Rectangle(960, 540, 50,50);

    private Rectangle spelare2 = new Rectangle(960, 540, 100,50);

    private Rectangle Pucken = new Rectangle(960,540,20,20);

    private Rectangle planhitbox = new Rectangle(0,0,1224,632);
    private boolean running = false;
    private Thread thread;

    private int Spelare1VX;

    private int Spelare2VX;

    private int Spelare1VY;

    private int Spelare2VY;

    private BufferedImage plan;

    private BufferedImage spelare;

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
        setSize(1224, 634);
        JFrame frame = new JFrame();
        frame.add(this);
        this.addKeyListener(new MyKeyListener());
        this.addMouseMotionListener(new MyMouseMotionListener());
        this.addMouseListener(new MyMouseListener());
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
        g.drawOval(Pucken.x,Pucken.y,20,20);
        g.drawImage(spelare, spelare1.x, spelare1.y,(int)spelare1.getWidth(),(int)spelare1.getHeight(),null);
    }

    private void update() {
        spelare1.x += Spelare1VX;
        spelare1.y += Spelare1VY;
        spelare2.x += Spelare2VX;
        spelare2.y += Spelare2VY;
        System.out.println(Spelare1VY);
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
        double ns = 1000000000.0 / 25.0;
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
            if (e.getKeyChar() == 'a') {
                Spelare1VX = -15;
            }
            if (e.getKeyChar() == 'd') {
                Spelare1VX = 15;
            }
            if (e.getKeyChar() == 'w') {
                Spelare1VY = 15;
            }
            if (e.getKeyChar() == 's') {
                Spelare1VY = -15;
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
                Spelare2VY = -8;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Spelare2VY = 8;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Spelare2VX = 8;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Spelare2VX = -8;
            }
        }
    }

    private class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}