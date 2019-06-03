package edu.umb.cs.cs681.hw03;

import java.util.ArrayList;

public class ClientCode {

	public static void main(String[] args) {
		
ArrayList<Car> usedCars = new ArrayList<Car>();
		
       System.out.println("HW3-1 min max count with reduce");
       
		usedCars.add(new Car("Chevy",2000,1000,400000));
		usedCars.add(new Car("BMW",2010,6000,50000));
		usedCars.add(new Car("Toyato",2007,3000,20000));
		usedCars.add(new Car("Toyato",2001,7500,100000));
		usedCars.add(new Car("Benz",1990,1500,10000));
		usedCars.add(new Car("Honda",2015,10000,30000));
		usedCars.add(new Car("Honda",2018,30000,4000));
		
		System.out.println("\n Inserted Car Price: \n");
		
		usedCars.forEach( (Car car)->System.out.println(car.get_Price()));
		
		Integer min = usedCars.stream().map( (Car car)-> car.get_Price() )
												. reduce(0, (result, carPrice)->{
															if(result==0) return carPrice;
															else if(carPrice < result) return carPrice;
															else return result;} );
		System.out.println("\n Minimum car price " +min);
		
		Integer max = usedCars.stream().map( (Car car)-> car.get_Price() )
				. reduce(0, (result, carPrice)->{
							if(result==0) return carPrice;
							else if(carPrice < result) return result;
							else return carPrice;} );
		System.out.println("\n Maximum car price is: " +max);
				
		Integer Count = usedCars.stream().map( (Car car)-> car.get_Price() )
						. reduce(0, (result, carPrice)->{return ++result;} );
		System.out.println("\n Total car are: " +Count);
				
				System.out.println("\n \n HW3-2 Avg with reduce 3rd version \n");
				
				int[] avg = usedCars.stream().map((Car car)-> car.get_Price() )
						. reduce(new int[3],
								(arr, price)->{arr[0] = 1+arr[0];
												arr[1]=price+ arr[1];
												if(arr[0]!=0)
													arr[2] = arr[1]/arr[0];
									return arr; },
									(arr, intermediateArr)->{ return arr; });
				System.out.println("\n Sum of prices  "+avg[1]);
				System.out.println("\n No of Cars  "+ avg[0]);
				System.out.println("\n Average price of car  " +avg[2]);
				
	}
			

	}
	

