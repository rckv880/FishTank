package com.fishtank;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
/**
 * 
 * @author avleendhanjal
 *
 */



public class FishShoal {
	private List<Fish> fishList =   new ArrayList<Fish>();

    public FishShoal() {
		fishList = new ArrayList<Fish>();
	}

	public void add (Fish fish) {
		if (fishList.size () >= 0) {
			fishList.add(fish);
		}
	}
	
	public void remove (Fish fish) {
		if(fishList.contains(fish)) {
			fishList.remove(fish);
		}
	}
	
	public void drawShoal(Graphics g) {
		for(Fish fish: fishList) {
			fish.draw(g);
		}
	}

	public int size () {
		return fishList.size();
	}

	// fish checks if it can eat another fish by passing itself to the method.
	public Fish canEat (Fish targetFish) {
    	// Iterate over the shoal collection and find if any fish matches the size and range of the target fish;
		for (Fish parameterFish:fishList) {
			boolean allowedSize = parameterFish.getSize() >= (targetFish.getSize() * 40 / 100); 		// 1. Check for allowed size.
			double avgSize = (parameterFish.getSize() + targetFish.getSize()) / 2;						// 2. Calculate avg size and check for range.

			boolean axisXinRange = false;
			if((parameterFish.getX() - targetFish.getX()) > 0) {	// check if the fish's x value is within the range of + or - of avg size of retrieved fish.
				axisXinRange = (targetFish.getX() > parameterFish.getX() - avgSize) && (targetFish.getX() < parameterFish.getX() + avgSize);
			} else {
				axisXinRange = (parameterFish.getX() > targetFish.getX() - avgSize) && (parameterFish.getX() < targetFish.getX() + avgSize);
			}

			boolean axisYinRange = false;
			if ((parameterFish.getY() - targetFish.getY()) > 0) {
				axisYinRange = (targetFish.getY() > parameterFish.getY() - avgSize) && (targetFish.getY() < parameterFish.getY() + avgSize);
			} else {
				axisYinRange = (parameterFish.getY() > targetFish.getY() - avgSize) && (parameterFish.getY() < targetFish.getY() + avgSize);
			}

			if (allowedSize && axisXinRange && axisYinRange) { 						// If all the validations succeed then
				return targetFish;													// Select that fish and return
			}
		}

		return null;																// else return null;
	}
}
