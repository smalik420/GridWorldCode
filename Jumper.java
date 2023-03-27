//Sidhant Malik

package Activity3;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import java.awt.Color;
import info.gridworld.actor.Flower;

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
		
		Location loc = getLocation();
		Location nextLoc = loc.getAdjacentLocation(getDirection());
		
		if (!grid.isValid(nextLoc)) 
		{
			return false;
		}
		
		Location jumpLanding = nextLoc.getAdjacentLocation(getDirection());
		if (!grid.isValid(jumpLanding)) 
		{
			return false;
		}
		
		Actor neighbor = grid.get(jumpLoc);
		return (neighbor == null || neighbor instanceof Flower); //used api for instance of but basically checks if its a flower obj
	}

	public void jump() 
	{
		Grid<Actor> grid = getGrid();
		if (grid == null) //should never be called with null but just in case
		{
			return;
		}
		
		Location loc = getLocation();
		Location nextLoc = loc.getAdjacentLocation(getDirection());
		Location jumpLanding = nextLoc.getAdjacentLocation(getDirection());
		moveTo(jumpLanding);
		
		Flower flower = new Flower();
		flower.putSelfInGrid(grid, loc);
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
		} else 
		{
			turn();
			jumpCount++;
		}
	}
}
