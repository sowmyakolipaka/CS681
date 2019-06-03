package edu.umb.cs.cs681.hw03;

public class Car 
{
	private int price;
	private int year;
	private String name;
	private int mileage;
	private int count;
	
public Car(String name, int mil, int pr,int yr)
{
		this.name = name;
		this.mileage = mil;
		this.price = pr;	
		this.year = yr;	
		count++;
}
	
	public int get_Mileage()
	{
		return this.mileage;
	}
	public int get_count()
	{
		return this.count;
	}
	public int get_Year()
	{
		return this.year;
	}
	public String toString()
	{
		return this.name;
	}
	public int get_Price()
	{
		return this.price;
	}	
	
}
