package io.github.andronikus.animation4jsample;

import com.andronikus.animation4j.rig.graphics.GraphicsContext;
import io.github.andronikus.animation4jsample.animation.RobotAnimationController;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements ActionListener {

    private final RobotAnimationController controller;
    private final RobotState robotState = new RobotState();
    private final Timer timer;

    public Main() {
        controller = new RobotAnimationController(robotState);

        final JFrame frame = new JFrame("Animation Scenario");
        frame.add(this);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final KeyBoardListener keyBoardListener = new KeyBoardListener();
        this.addKeyListener(keyBoardListener);
        frame.addKeyListener(keyBoardListener);

        timer = new Timer(30, this);
        timer.start();

        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        final GraphicsContext context = new GraphicsContext();
        context.setGraphics2d((Graphics2D)graphics);
        context.setObserver(this);
        context.setComponentHeight(this.getHeight());

        controller.renderNext(context, new Object(), robotState, 800, 400, 0);
    }

    private class KeyBoardListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_H) {
                robotState.setRobotHappy(true);
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                robotState.setRobotSad(true);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_H) {
                robotState.setRobotHappy(false);
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                robotState.setRobotSad(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
}
