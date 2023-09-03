public class OneWayLink extends Link{

    // construct a One Way Link from firstCity to secondCity with length len
    public OneWayLink(City firstCity, City secondCity, int len, String way){
        super(firstCity,secondCity,len);
            city1 = firstCity;
            city2 = secondCity;
            firstCity.addLink(this);
            oneWay = true;
    }

    public String toString(){
        return city1.toString() + " " + length + " " + city2.toString() + " one";
    }

}
