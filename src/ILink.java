public interface ILink extends Comparable<Link> {
    /* return the length of this link */
    int getLength();

    /* get the opposite city from c
     * return city1 if c is city2
     * return city2 if c is city1
     * behaviour is unspecified if c is not city1 or city2
     */
    City getAdj(City c);

    /* return true if this link is on a shortest path (i.e. used == true) and false otherwise */
    boolean isUsed();

    /* set used to u */
    void setUsed(boolean u);

    /* return a string representation of the Link
     * e.g. "City1 3 City2"
     * The city names should be in sorted order, e.g. Halifax comes before Toronto
     */
    String toString();

    /* compare this Link to Link l
     * returns 0 if both links have the same city1 and city2
     * return negative int if this.city1 < l.city1 or the city1 are equal and this.city2 < l.city2
     * else return a positive int
     */
    int compareTo(Link l);
}
