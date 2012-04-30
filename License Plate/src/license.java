import java.io.*;
import java.util.*;
import java.math.*;

public class license
{
	public static void main(String argv[])
	{
		/*
		 * This creates an interactive CLI for the user.
		 * The IO module provided here was not created by me and was
		 * imported for ease of inputting and outputting values and detecting
		 * errors from the user input.
		 */
		while(true)
		{
			System.out.println("1.Create us some license plates");
			System.out.println("2.Quit");
			
			int choice = IO.readInt();
			if(choice == 1)
			{
				System.out.println("Enter your population");
				
				int pop = IO.readInt();
				if(pop < 0)
					System.out.println("Please enter a positive number");
				else
				{
					numLicense(pop);
					continue;
				}
				
			}
			else if(choice == 2)
			{
				return;
			}
			else
				IO.reportBadInput();
		}
	}
	
	private static void numLicense(int population)
	{
		/*
		 * The code begins by finding the max number of spaces by dividing by "ten" b/c it is our base
		 * case. Then it begins to iterate through all possible combinations of either the space containing 
		 * the pattern (num,letter, or both). It decides the best combination by keeping track of it's current
		 * best(min excess) case. 
		 */
		int excess = 0;
		int people = population; 
		int curr_excess = 999999999; // simulates infinity
		int num = people;
		
		int spaces = 0;
		int total = 0;
		int ten = 0;
		int twenty = 0;
		int thirty = 0;
		while(num > 0)
		{
			num = num/10;
			spaces++;
		}
		
		for(int a = 1; a <= spaces; a++)
		{
			for(int b = 0; b <= a; b++)
			{
				for(int c = (a-b); c >= 0; c--)
				{
					for(int d = (a - (b+c)); d >=0; d--)
					{
						//System.out.println("tens:" + b + "|twenty:" + c + "|thirty:" + d);
						//Calculating how many plates our combination will create
						total = (int) Math.pow(10, b)*(int) Math.pow(26, c)*(int) Math.pow(36, d);
						//System.out.println("total:" + total);
						// Then seeing how many excess plates come from that combination.
						excess = total - people;
						//System.out.println("~excess:" + excess);
				
						if(excess < 0) // if its smaller than zero, we didn't fill the population
							continue;
						
						if(excess > curr_excess) // we didn't hit better than previously
							continue;
						
						//System.out.println("Got here");
						// Storing our best case if it got this far.
						curr_excess = excess;
						ten = b;
						twenty = c;
						thirty = d;
					}
				}
			}
		}
		System.out.println("Population: " + people);
		System.out.println("Spaces contain only num (10):" + ten);
		System.out.println("Spaces contain only letter (26):" + twenty);
		System.out.println("Spaces contain both (36):" + thirty);
		System.out.println("Total Spaces in Pattern: " + (ten+twenty+thirty));
		System.out.println("Total Plates: " + (people+curr_excess));
		System.out.println("Excess Plates: " + curr_excess);
	}
}