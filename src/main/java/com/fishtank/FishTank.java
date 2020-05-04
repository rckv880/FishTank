package com.fishtank;
/**
 * @author Avleen Dhanjal
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FishTank extends JPanel implements ActionListener {
    private FishShoal fishShoal;
    private DrawPanel drawPanel;
    private String BUTTON_ADD_FISH = "Add Fish";

    public FishTank() {
        super(new BorderLayout());
        fishShoal = new FishShoal();
        drawPanel = new DrawPanel(fishShoal);
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addFish = new JButton("Add Fish");        // Add Fish Button.
        addFish.addActionListener(this);                    // Add action to the button.

        buttonPanel.add(addFish);                              // Add button to the panel.
        add(buttonPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, this);      // call actionPerformed method every 1000ms using Swing timer
        timer.start();
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Fish fish = null;

        if (source instanceof JButton && ((JButton) source).getText().equalsIgnoreCase("Add Fish")) {
            System.out.println("Adding a Fish to the tank");
            fish = new Fish(fishShoal, drawPanel.getWidth(), drawPanel.getHeight());
            fishShoal.add(fish);
            Thread thread = new Thread(fish);                // Start the thread
            thread.start();
        }


        drawPanel.repaint();
    }


    public static void main(String[] args) {
        // Initialize the window Frame.
        JFrame frame = new JFrame("Fish Bowl");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // kill all threads when frame closes
        frame.getContentPane().add(new FishTank());
        frame.pack();

        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();

        // Set location and visibility.
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
    }
}

class DrawPanel extends JPanel {
    private FishShoal shoal;

    public DrawPanel(FishShoal shoal) {
        super();
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        this.shoal = shoal;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawLine(0, 0, 100, 100);
        shoal.drawShoal(g);
    }
}