import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.studyspot.model.Rating;

public class RatingTest {
    Rating testRating;

    @BeforeEach
    public void setup() {
        testRating = new Rating(1, 2, 3, 4, 5, 3);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, testRating.getNoiseLevel());
        assertEquals(2, testRating.getCleanliness());
        assertEquals(3, testRating.getLighting());
        assertEquals(4, testRating.getSeating());
        assertEquals(5, testRating.getDrinkQuality());
        assertEquals(3, testRating.getFoodQuality());
    }
}
