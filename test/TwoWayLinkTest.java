import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoWayLinkTest {
    public static final String city1Name = "Halifax";
    public static final String city2Name = "Toronto";
    public static final int cityDistance = 6;

    @Test
    void constructor_TwoWayDirected() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new TwoWayLink(city1, city2,cityDistance, "two");
        City.cities.clear();
        assertTrue(city1.links.contains(link));
        assertTrue(city2.links.contains(link));
    }

    @Test
    void constructor_TwoWayRegular() {
        City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new TwoWayLink(city1, city2,cityDistance, null);
        City.cities.clear();
        assertTrue(city1.links.contains(link));
        assertTrue(city2.links.contains(link));
    }

    @Test
    void testToString_TwoWay() { City city1 = new City(city1Name);
        City city2 = new City(city2Name);
        Link link = new TwoWayLink(city1, city2,cityDistance, null);
        String expected = city1Name + " " + cityDistance + " " + city2Name ;
        City.cities.clear();
        assertEquals(expected, link.toString(), "toString returns wrong string");
    }

}