package io.github.andronikus.animation4jsample.stopmotion;

import io.github.andronikus.animation4jsample.RobotState;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class HeadStopMotionScenarioRunner extends JPanel implements ActionListener {

    private final HeadStopMotionController headController = new HeadStopMotionController();
    private final RobotState robotState = new RobotState();
    private final Timer timer;

    public HeadStopMotionScenarioRunner() {
        final JFrame frame = new JFrame("Head Stop Motion Scenario");
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
        final BufferedImage sprite = headController.nextSprite(new Object(), robotState);
        int magnitude = 10;
        graphics.drawImage(sprite, 400, 150, 48 * magnitude, 32 * magnitude, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
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

    public static void main(String[] args) {
        new HeadStopMotionScenarioRunner();
    }
}
