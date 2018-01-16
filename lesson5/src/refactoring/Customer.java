package refactoring;

import java.util.Enumeration;
import java.util.Vector;

class Customer {
	private String _name;
	private Vector _rentals = new Vector();

	public Customer(String name) {
		_name = name;
	}

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() {
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";

		double totalAmount = getTotalCharge();
        int frequentRenterPoints = getTotalFrequentRentalPoints();
        while(rentals.hasMoreElements()) {
            Rental thisRental = (Rental) rentals.nextElement();
			result += "\t" + thisRental.getMovie().getTitle() + "\t" + String.valueOf(thisRental.getCharge()) + "\n";
        }
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}

	public String htmlStatement(){
		Enumeration rentals = _rentals.elements();
		String result = "<h1>Rentals for <em>" + getName() + "</em></h1>\n";

		while(rentals.hasMoreElements()){
			Rental thisRental = (Rental) rentals.nextElement();

			//show figures for each rental
			result += thisRental.getMovie().getTitle() + ": "
					+ String.valueOf(thisRental.getCharge()) + "<br>\n";
		}
		//add footer lines
		result += "<p>You owe <em>" + String.valueOf(getTotalCharge()) + "</em></p>\n";
		result += "On this rental you earned <em>" + String.valueOf(getTotalFrequentRentalPoints())
				+ "</em> frequent renter points</p>";
		return result;
	}

	private double getTotalCharge(){
		Enumeration rentals = _rentals.elements();
        double result = 0;
	    while(rentals.hasMoreElements()){
            Rental thisRental = (Rental) rentals.nextElement();
            result += thisRental.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRentalPoints(){
		Enumeration rentals = _rentals.elements();
        int result = 0;
        while(rentals.hasMoreElements()){
            Rental thisRental = (Rental) rentals.nextElement();
            result += thisRental.getFrequentRentalPoints();
        }
        return result;
    }
}