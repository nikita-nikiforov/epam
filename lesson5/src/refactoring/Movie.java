package refactoring;

public class Movie {
	private String _title;
	private Price _price;

	public Movie(String title, Price price) {
		_title = title;
		_price = price;
	}

	public String getTitle() {
		return _title;
	}

	public double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}

	public int getFrequentRenterPoints(int daysRented){
		return _price.getFrequentRenterPoints(daysRented);
	}
}