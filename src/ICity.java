import java.util.HashSet;
import java.util.Set;

public interface ICity {
    /* find a city with name nm in cities
     * return the city if it exists
     * otherwise return a new city with that name
     */
    static City find(String nm) {
        City p = City.cities.get(nm);
        if (p == null) {
            p = new City(nm);
            return p;
        }
        return p;
    }

    String getName();

    Link getParent();

    void setParent(Link par);

    int getDistance();

    void setDistance(int d);

    HashSet<Link> getLinks();

    /* add a link to links
     */
    void addLink(Link lnk);

    /* compare cities by their names
     * return: negative if c1 is alphanumerically less,
     *  0 if names are the same,
     *  positive if c2 is alphanumerically less
     */
    int compareTo(City p);

    /* return the name of the city
     */
    String toString();

    /* compare cities by their distance from the start of the rail network
     * return: negative if c1 is closer to 0, 0 if equal distance, positive if c2 is closer to 0
     */
    int compare(City c1, City c2);

    /* find a path from this to dest of used links
     * return true if a path of used links is found and false otherwise
     * add the followed Links to routeLinks
     */
    boolean getLinksTo(City dest, Set<Link> routeLinks);

    /* create a shortest path tree starting from this City
     * uses Dijkstra's shortest paths algorithm
     * set the distance of this City to 0 and others to infinity
     * consider each found City closest to the start
     *   update the best known distance to all adjacent cities
     * postcondition: every City.distance is the shortest distance from this to that City
     * postcondition: every City.parent is the Link before that City in the set of shortest paths
     */
    void makeTree();
}
