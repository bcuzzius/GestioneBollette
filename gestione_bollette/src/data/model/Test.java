package data.model;

import controller.DAO;

public class Test {

	public static void main(String[] args) {
		DAO.getBollette().forEach(System.out::println);
	}

}
