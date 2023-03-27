//Sidhant Malik

import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class Jumper extends Bug 
{
	private int jumpCount;

	public Jumper() 
	{
		setColor(Color.BLUE);
		jumpCount = 0;
	}

	public boolean canJump() 
	{
		Grid<Actor> grid = getGrid();
		if (grid == null) 
		{
			return false;
		}
		
		Location current = getLocation();
		Location adjacent = current.getAdjacentLocation(getDirection());
		
		if (!grid.isValid(adjacent)) 
		{
			return false;
		}
		
		Location jumpLanding = nextLoc.getAdjacentLocation(getDirection());
		if (!grid.isValid(jumpLanding)) 
		{
			return false;
		}
		
		Actor neighbor = grid.get(jumpLanding);
		//this is the one that can eventually be true
		return (neighbor == null || neighbor instanceof Flower); //check if flower seperately
	}

	public void jump() 
	{
		Grid<Actor> grid = getGrid();
		if (grid == null) //should never be called with null but just in case
		{
			return;
		}
		
		Location current = getLocation();
		Location nextLocation = current.getAdjacentLocation(getDirection());
		Location jumpLanding = nextLocation.getAdjacentLocation(getDirection());
		moveTo(jumpLanding);
		
		Flower flower = new Flower();
		flower.putSelfInGrid(grid, current);
	}

	public void act() 
	{
		if (jumpCount == 8) 
		{
			removeSelfFromGrid();
			return;
		}
		if (canJump()) 
		{
			jump();
			jumpCount = 0;
		} 
		else 
		{
			turn();
			jumpCount++;
		}
	}
}
