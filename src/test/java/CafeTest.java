import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.studyspot.model.Cafe;
import com.studyspot.model.Rating;

public class CafeTest {
    private Cafe testCafe;

    @BeforeEach
    public void setup() {
        testCafe = new Cafe("Starbucks", "123 W 10th St", "Vancouver");
    }

    @Test
    public void testConstructor() {
        assertEquals("Starbucks", testCafe.getName());
        assertEquals("123 W 10th St", testCafe.getAddress());
        assertEquals("Vancouver", testCafe.getCity());

        // to ensure it has been constructed with no ratings
        assertNull(testCafe.getAvgNoiseLevel());
        assertNull(testCafe.getAvgCleanliness());
        assertNull(testCafe.getAvgLighting());
        assertNull(testCafe.getAvgSeating());
        assertNull(testCafe.getAvgDrinkQuality());
        assertNull(testCafe.getAvgFoodQuality());
    }

    @Test
    public void testGetAveragesOneRating() {
        testCafe.addRating(new Rating(1, 3, 4, 4, 5, 2));
        assertEquals(1, testCafe.getAvgNoiseLevel());
        assertEquals(3, testCafe.getAvgCleanliness());
        assertEquals(4, testCafe.getAvgLighting());
        assertEquals(4, testCafe.getAvgSeating());
        assertEquals(5, testCafe.getAvgDrinkQuality());
        assertEquals(2, testCafe.getAvgFoodQuality());
    }

    @Test
    public void testGetAveragesMultipleRatings() {
        testCafe.addRating(new Rating(1, 3, 4, 4, 5, 2));
        testCafe.addRating(new Rating(3, 5, 4, 2, 1, 3));
        assertEquals(2, testCafe.getAvgNoiseLevel());
        assertEquals(4, testCafe.getAvgCleanliness());
        assertEquals(4, testCafe.getAvgLighting());
        assertEquals(3, testCafe.getAvgSeating());
        assertEquals(3, testCafe.getAvgDrinkQuality());
        assertEquals(2.5, testCafe.getAvgFoodQuality());
    }
}
