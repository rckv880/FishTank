package com.fishtank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author avleendhanjal
 */


public class Fish implements Runnable {
	// Declaring the variables as per Java Coding standards for improved readability.
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double velX;
	private double velY;
	private double worldWidth;  				// Java uses camelCase for variable names instead of underscore ("_") like C++
	private double worldHeight; 				// camelCase;
	private double size;
	private Color[] color;
	private int MIN = 0; 						// Constants should be Uppercase.
	private int MAX = 50;
	private double speed;
	private boolean isAlive;
	private Random generator;
	private FishShoal shoal;

	// Constructor
    public Fish(FishShoal shoal, int worldWidth, int worldHeight) {
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
		this.color = new Color[3]; 				// three colours of the lines of the fish.
        generator = new Random();
        this.size = generator.nextInt(50) + 1;
        this.shoal = shoal;
        this.dx = 1;
        this.dy = 1;

/*
        Test Values.
        speed = 1;
        this.velY = 30;
        this.velX = 30;
*/
        speed = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        this.velX = (this.size * this.dx ) / 2 * speed;
        this.velY = (this.size * this.dy ) / 2 * speed;
    }

	// Move logic in the thread run method for parallel execution.
    @Override
    public void run() {
        isAlive = true;
        while (isAlive) {
            move();
        }
    }

    // get methods.
	public double getX() {
        return this.x;							// this is used to return the current object's x value.
    }

    public double getY() {
        return this.y;
    }

    public double getSize() {
        return size;
    }

    // kills the fish. :(
    public void kill() {
        isAlive = false;
    }

    private void move() {
        x = x + dx;
        y = y + dy;

        // think about checking world boundary
        if (x <= 0 || (x + size) >= worldWidth) {
            dx =- dx;
        }

        if (y <= 0 || (y + size) >= worldHeight) {
            dy =- dy;
        }

        System.out.format("INFO: (%f, %f)--Speed: %f, velX: %f, velX: %f \n", x, y, speed, velX, velY);
    }

    // use semicolon only for for loops.. from the UML diagram. (Did not understand. semicolon for For loops? meaning?)
    public void eat(Fish target) {
        // the eat method is used to feed this fish with the target parameter fish.
        // in which case it kills the target fish and stops its thread from running... and increases its size upto a maximum.
        // if a fish dies it should remove itself from the shoal.
		target.isAlive = false;
		shoal.remove(target);
    }

    public void draw(Graphics g) {
        // drawing the fish using the drawLine method.
        // orange.
        g.drawLine((int) Math.round(x - velX - velY),  (int) Math.round(y + velX - velY),  (int) Math.round(x),  (int) Math.round(y));
        // blue line.
        g.drawLine((int) Math.round(x - 2 * velX), (int) Math.round(y - 2 * velY), (int) Math.round(x), (int) Math.round(y));
        // grey line.
        g.drawLine((int) Math.round(x - velX + velY), (int) Math.round(y - velX - velY), (int) Math.round(x), (int) Math.round(y));
    }
}
