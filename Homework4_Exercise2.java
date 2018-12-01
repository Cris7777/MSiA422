package com.msi.sample;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JComponent;

@SuppressWarnings("serial")

public class Homework4_Exercise2 {

	public static void draw(Graphics g)
	{  
		Scanner in = new Scanner(System.in);
		try {
		System.out.println("Enter Grid Dimension:");
		String GridDimension1 = in.next();
		int GridDimension = Integer.valueOf(GridDimension1).intValue();
	    if (GridDimension % 2 != 0) {
	    	System.out.println("Grid Dimension must be EVEN");
	    	System.exit(0);;
	    }
		for (int row = 0;row<GridDimension;row++)
		{
			for (int column = 0;column<GridDimension;column++)
		    {
				if (row < GridDimension/2 && column < GridDimension/2) 
				{
					g.setColor(Color.GREEN);
					g.fillOval(row*60 + 50,column*60 + 50, 50,50);	
				}
				else if(row >= GridDimension/2 && column >= GridDimension/2) {
					g.setColor(Color.RED);
					g.fillOval(row*60 + 50,column*60 + 50, 50,50);	
				}
				else {
					g.setColor(Color.BLACK);
					g.fillOval(row*60 + 50,column*60 + 50, 50,50);
				}
		    }
		}
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter an integer");
			draw(g);
		}
		
		finally {
			if (in != null) {
				in.close();
			}
	}
		}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		final int FRAME_WIDTH = 800;
		final int FRAME_HEIGHT = 800;

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent component = new JComponent()
		{
			public void paintComponent(Graphics graph)
			{
				draw(graph);
			}
		};     
		frame.add(component);
		frame.setVisible(true);
   }   

	}

