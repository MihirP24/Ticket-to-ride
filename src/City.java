import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/* Class representing a City
 */
public class City implements ICity {
  /* lookup table of all cities by name */
  public static HashMap<String, City> cities = new HashMap<String, City>();
  public String name;
  /* adjacent Links */
  public final HashSet<Link> links = new HashSet<Link>();
  /* shortest path distance */
  public int distance;
  /* shortest path parent */
  public Link parent;

  /* construct a City with name nm
   * add to the HashMap of cities
   */
  public City(String nm) {
    name = nm;
    cities.put(name, this);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Link getParent() {
    return parent;
  }
  @Override
  public void setParent(Link par){
    parent = par;
  }
  @Override
  public int getDistance(){
    return distance;
  }
  @Override
  public void setDistance(int d){
    distance = d;
  }
  @Override
  public HashSet<Link> getLinks(){
    return links;
  }

  /* add a link to links
   */
  @Override
  public void addLink(Link lnk) {
    links.add(lnk);
  }



  /* compare cities by their names
   * return: negative if c1 is alphanumerically less,
   *  0 if names are the same,
   *  positive if c2 is alphanumerically less
   */
  @Override
  public int compareTo(City p) {
    return name.compareTo(p.getName());
  }

  /* return the name of the city
   */
  @Override
  public String toString() {
    return name;
  }




  /* compare cities by their distance from the start of the rail network
   * return: negative if c1 is closer to 0, 0 if equal distance, positive if c2 is closer to 0
   */
  @Override
  public int compare(City c1, City c2) {
    return c1.getDistance() - c2.getDistance();
  }

  /* find a path from this to dest of used links
   * return true if a path of used links is found and false otherwise
   * add the followed Links to routeLinks
   */
  @Override
  public boolean getLinksTo(City dest, Set<Link> routeLinks) {
    for (Link l : links) {
      if (l.isUsed() && (l != parent)) {
        City child = l.getAdj(this);
        if ((dest == child) || child.getLinksTo(dest, routeLinks)) {
          routeLinks.add(l);
          return true;
        }
      }
    }
    return false;
  }

  /* create a shortest path tree starting from this City
   * uses Dijkstra's shortest paths algorithm
   * set the distance of this City to 0 and others to infinity
   * consider each found City closest to the start
   *   update the best known distance to all adjacent cities
   * postcondition: every City.distance is the shortest distance from this to that City
   * postcondition: every City.parent is the Link before that City in the set of shortest paths
   */
  @Override
  public void makeTree() {
    Comparator<City> comparator = new CityComparator();
    PriorityQueue<City> pq = new PriorityQueue<City>(comparator);
    // add cities to the priority queue. Set the distance of this City to 0 and others to Integer.MAX_VALUE
    for (City c : cities.values()) {
      if (c != this) {
        c.setDistance(Integer.MAX_VALUE) ;
      } else {
        c.setDistance(0);
      }
      pq.add(c);
      c.setParent(null);
      for (Link l : c.getLinks()) {
        l.setUsed(false);
      }
    }

    HashSet<City> tree = new HashSet<City>();
    // visit the next closest city and update the distances of its adjacent cities in the priority queue
    while (!pq.isEmpty()) {
      City city = pq.poll();
      if (city.getParent() !=  null) {
        city.getParent().setUsed(true);
      }
      tree.add(city);

      for (Link l : city.getLinks()) {

        City child = l.getAdj(city);
        if (!tree.contains(child)) {
          int length = l.getLength();

          if (child.getDistance() > city.getDistance() + length) {
            pq.remove(child);
            child.setDistance(city.getDistance() + length)  ;
            child.setParent(l);
            pq.add(child);
          }
        }
      }
    }
  }

}
