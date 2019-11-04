/**
 * Create a class called Passenger that will store the information for each passenger 
 * from the file. A passenger should have instance variables for passenger:
 *  - id
 *  - name
 *  - gender
 *  - age
 *  - class
 *  - ticket number
 *  - fare 
 *  - whether the passenger is still alive or not
 */
public class Passenger {
	private int id, age, pClass;
	private boolean isAlive;
	private String name, gender, ticketNumber;
	private double fare;
	
	//PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked
	public Passenger(int id, int survived, int pclass, String name, String sex, int age, String ticketNumber, double fare) {
		this.id = id;
		this.isAlive = survived == 1;
		this.pClass = pclass;
		this.name = name;
		this.gender = sex;
		this.age = age;
		this.ticketNumber = ticketNumber;
		this.fare = fare;
	}
	
	@Override
	public String toString() {
		return "Name\t" + name + 
			   "\tID\t" + id + 
			   "\tAlive\t" + isAlive +
			   "\tClass\t" + pClass +
			   "\tGender\t" + gender +
			   "\tTicket Number\t" + ticketNumber +
			   "\tAge\t" + age +
			   "\tFare\t" + fare;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the pClass
	 */
	public int getpClass() {
		return pClass;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the ticketNumber
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @return the fare
	 */
	public double getFare() {
		return fare;
	}
}
