import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneWayLinkTest {
    public static final String city1Name = "Halifax";
    public static final String city2Name = "Toronto";
    public static final int cityDistance = 6;

    @Test
    void constructor_OneWay() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new OneWayLink(city1, city2,cityDistance, "one");
        City.cities.clear();
        assertTrue(city1.links.contains(link));
        assertFalse(city2.links.contains(link));
    }

    @Test
    void testToString_oneWay() { City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new OneWayLink(city1, city2,cityDistance, "one");
        String expected = city1Name + " " + cityDistance + " " + city2Name + " one";
        City.cities.clear();
        assertEquals(expected, link.toString(), "toString returns wrong string");
    }

}