import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import static java.lang.System.exit;

public class RouteCost {


  public static void main(String [] args) {
    Scanner kb = new Scanner(System.in);
    TreeSet<Link> links = new TreeSet<Link>();


    // for loop to take city names and length between the two cities and then create link
    for (String name = kb.nextLine(); !name.equals("done"); name = kb.nextLine()) {
      String[] line1 =name.split(" ");
      String way=null;

      if(line1.length == 3 || line1.length == 4) {
        City city1 = ICity.find(line1[0]);
        City city2 = ICity.find(line1[2]);
        Integer length = null;
        try {
          length = Integer.parseInt(line1[1]);
        } catch (NumberFormatException e) {
          System.out.println("Invalid line: " + name);
          exit(1);
        }

        if (line1.length == 3) {
          Link l = new TwoWayLink(city1, city2, length,way);
        } else if (line1.length == 4) {
          way = line1[3];
          if ((way.equals("one")) ){
            Link l = new OneWayLink(city1,city2,length,way);
          }else if((way.equals("two"))){
            Link l = new TwoWayLink(city1,city2,length,way);
          }
          else {
            System.out.println("Invalid line: " + name);
            exit(1);
          }
        }
      }
      else {
        System.out.println("Invalid line: " + name);
        exit(1);
      }
    }

    // for loop to know the shortest distance between two cities
    for (String name = kb.nextLine(); !name.equals("done"); name = kb.nextLine()) {
      String[] line2= name.split(" ");

      if(line2.length!=2){
        System.out.println("Invalid line: " + name);
        exit(1);
      }
      else {
        City city1 = ICity.find(line2[0]);
        city1.makeTree();

        if (!city1.getLinksTo(ICity.find(line2[1]), links)) {
          System.out.println("Error: Route not found! " + line2[0]);
          return;
        }
        else if (line2.length == 2) {
          if (!city1.getLinksTo(ICity.find(line2[1]), links)) {
            System.out.println("Error: Route not found! " + line2[0]);
            return;
          }
        }
      }

    }


    int totalCost = 0;

    // printing the shortest link between the cities
    System.out.println("The rail network consists of:");
    for (Link link : links) {
      System.out.println("  " + link);
      totalCost += link.getLength();
    }
    System.out.println("The total cost is: " + totalCost);
  }
}
