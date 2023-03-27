//sidhant malik & ap cs a resources cite later

import java.awt.Color;

public class Blossom extends Flower
{
	//from original flower class, modified for a blossom
	private static final Color DEFAULT_COLOR = Color.GREEN;
	private static final double DARKENING_FACTOR = 0.05;
	
	private int steps;
	private int lifeSpan;
	
	public Blossom()
	{
		setColor(DEFAULT_COLOR);
		lifeSpan = 10;
	}
	
	public Blossom(int lifeIn)
	{
		setColor(DEFAULT_COLOR);
		lifeSpan = lifeIn;
	}
	
	public void act()
	{
		if (steps < lifeSpan)
		{
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
			steps++;
		}
		else if (steps == lifeSpan)
		{
			removeSelfFromGrid();
			steps++;
			return;
		}
	}
}
