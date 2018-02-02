package ua.nure.bei.SummaryTask4.comparators;

import java.util.Comparator;

import ua.nure.bei.SummaryTask4.models.Car;

public class CarsComparatorDesc {
	public static CarComparatorPrice PRICE = new CarComparatorPrice();
	public static CarComparatorName NAME = new CarComparatorName();

	public static class CarComparatorPrice implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			return !(o1.getPrice() > o2.getPrice()) ? 1 : (o1.getPrice() == o2.getPrice()) ? 0 : -1;
		}
	}

	public static class CarComparatorName implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			return !(o1.getMark().charAt(0) > o2.getMark().charAt(0)) ? 1
					: (o1.getMark().charAt(0) == o2.getMark().charAt(0))
							? ((o1.getModel().charAt(0) > o2.getModel().charAt(0)) ? 1
									: (o1.getModel().charAt(0) == o2.getModel().charAt(0)) ? 0 : -1)
							: -1;
		}

	}

}
