package entity;

/**
 * Interface for location (each specific xy coordinate), any environment should implement these.
 * Notice, these are just basic and essential functions satisfying Interface segregation(no unnecessary implement)
 * Notice as I said scalar closer to something increases is only for map construction step where each location
 * starts with a higher or lower scalar. The only time resource scalar change is when player stay in same spot, decrease
 * each day by some proportion.
 */
public interface Location {

    /**
     * temperature is crucial for calculating food/water consumption. ideally, closer to core of iceland,
     * Colder it is, as well as desert, specific calculation are for later.
     * @param distance distance of location from its core.
     * @return temperature.
     */
    Double gettemperature(int distance);

    /**
     * Crucial to the number people joins, in a double represent the number we'll use as scalar for people increase
     * Method.
     * Ideally, closer to core of city (center as more populated), it will be higher.
     * And the longer they stay in same spot, the scalar decrease(people already joined or don't wanna join so decrease)
     * @param distance distance from its core.
     * @return people resource scalar.
     */
    Double getpeopleresourceavailable(int distance);

    /**
     * Crucial to the number of food gathers act as scalar. Ideally, make it high in city and woods, the scalar decrease
     * as the longer they stayed.
     * @param distance distance from its core.
     * @return food resource scalar
     */
    Double getfoodresourceavailable(int distance);

    /**
     * Crucial to the number of water gathers act as scalar. Ideally, make it high in iceland and wood
     * the scalar decrease as the longer they stayed.
     * @param distance distance from its core.
     * @return water resource scalar
     */
    Double getwaterresourceavailable(int distance);

    /**
     * Crucial to number of firearm gathered. Closer to city center, this scalar will be higher.
     * Same spot, decrease this.
     * @param distance distance from its core.
     * @return firearm resource scalar
     */
    Double getfirearmresourceavailable(int distance);

    /**
     * Crucial to number of nonfirearm gathered. Closer to city center, this scalar will be higher.
     * Same spot, decrease this.
     * @param distance distance from its core.
     * @return nonfirearm resource scalar.
     */
    Double getnonfirearmresourceavailable(int distance);

    /**
     * The method responsible for decrease the scalar of the location if they stayed, by some proportion
     * Notice, it is for all 5 resource scalar.
     */
    void decreaseresourceavailable();

    /**
     * The threat level of this location, used for calculating power of the horde, and people decrease.
     * Closer to city core, more threat level, Closer to iceland/desert,(as they are more isolated) lower threat.
     * @param distance distance from its core.
     * @return threat level.
     */
    Double getthreatlevel(int distance);
}
