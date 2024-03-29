import java.util.Collections;

import kata.harrypotter.BookingSystem;
import kata.harrypotter.BookingSystemImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HarryPotterTest {

	private BookingSystem bookingSystem = new BookingSystemImpl();

	@Test
	void test0Books() {
		Assertions.assertEquals(0, bookingSystem.getPrice(null));
		Assertions.assertEquals(0, bookingSystem.getPrice(new int[]{}));
		Assertions.assertEquals(0, bookingSystem.getPrice());
	}

	@Test
	void test1Book1() {
		Assertions.assertEquals(8, bookingSystem.getPrice(1));
		Assertions.assertEquals(8, bookingSystem.getPrice(2));
		Assertions.assertEquals(8, bookingSystem.getPrice(3));
		Assertions.assertEquals(8, bookingSystem.getPrice(4));
		Assertions.assertEquals(8, bookingSystem.getPrice(5));
	}

	@Test
	void testDiscounts() {
		Assertions.assertEquals(8d * 2d * 0.95, bookingSystem.getPrice(1, 2));
		Assertions.assertEquals(8d * 3d * 0.9, bookingSystem.getPrice(1, 3, 5));
		Assertions.assertEquals(8d * 4d * 0.8, bookingSystem.getPrice(1, 2, 3, 4));
		Assertions.assertEquals(8d * 5d * 0.75, bookingSystem.getPrice(1, 2, 3, 4, 5));
	}

	@Test
	void testSetWithDuplicate() {
		Assertions.assertEquals(8d + (8d * 2d * 0.95), bookingSystem.getPrice(1, 1, 2));
	}


	@Test
	void testSeveralDiscounts() {
		Assertions.assertEquals(8d + (8d * 2d * 0.95), bookingSystem.getPrice(1, 1, 2));
		Assertions.assertEquals(2d * (8d * 2d * 0.95), bookingSystem.getPrice(1, 1, 2, 2));
		Assertions.assertEquals((8d * 4d * 0.8d) + (8d * 2d * 0.95), bookingSystem.getPrice(1, 1, 2, 3, 3, 4));
		Assertions.assertEquals(8d + (8d * 5d * 0.75), bookingSystem.getPrice(1, 2, 2, 3, 4, 5));
	}

	@Test
	void testEdgeCases1() {
		Assertions.assertEquals(2d * (8d * 4d * 0.8), bookingSystem.getPrice(1,1,2,2,3,3,4,5));
	}

	@Test
	void testEdgeCases11() {
		Assertions.assertEquals(2d * (8d * 5d * 0.75), bookingSystem.getPrice(1,2,3,4,5,1,2,3,4,5));
	}

	@Test
	void testEdgeCases2() {
		Assertions.assertEquals(3d * (8d * 5d * 0.75) + 2d * (8d * 4d * 0.8), bookingSystem.getPrice(
				1,1,1,1,1,
				2,2,2,2,2,
				3,3,3,3,
				4,4,4,4,4,
				5,5,5,5));
	}

}
