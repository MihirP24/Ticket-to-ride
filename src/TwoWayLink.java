public class TwoWayLink extends Link{

    /* construct a Two Way Link between firstCity and secondCity with length len
     * The City alphanumerically smaller is stored as city1 and the other will be city2
     * add the link to both cities
     */
    public TwoWayLink (City firstCity, City secondCity, int len, String way){
        super(firstCity,secondCity,len);
        if (firstCity.compareTo(secondCity) < 0) {
            city1 = firstCity;
            city2 = secondCity;
        } else {
            city1 = secondCity;
            city2 = firstCity;
        }
        firstCity.addLink(this);
        secondCity.addLink(this);
    }

    public String toString(){
        return city1.toString() + " " + length + " " + city2.toString();
    }
}
