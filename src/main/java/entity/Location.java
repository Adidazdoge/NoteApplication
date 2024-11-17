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
     * Temperature is crucial for calculating food/water consumption. ideally, closer to core of iceland,
     * Colder it is, as well as desert, specific calculation are for later.
     * @param distance distance of location from its core.
     * @return temperature.
     */
    Double getsettemperature(int distance);

    /**
     * Getter method of temperature.
     * @return Temperature of that location.
     */
    Double gettemperature();

    /**
     * Crucial to the number people joins, in a double represent the number we'll use as scalar for people increase
     * Method.
     * Ideally, closer to core of city (center as more populated), it will be higher.
     * And the longer they stay in same spot, the scalar decrease(people already joined or don't wanna join so decrease)
     * @param distance distance from its core.
     * @return people resource scalar.
     */
    Double getsetpeopleresourceavailable(int distance);

    /**
     * Getter method of people resource scalar.
     * @return people resource scalar of that location.
     */
    Double getpeopleresourceavailable();

    /**
     * Crucial to the number of food gathers act as scalar. Ideally, make it high in city and woods, the scalar decrease
     * as the longer they stayed.
     * @param distance distance from its core.
     * @return food resource scalar
     */
    Double getsetfoodresourceavailable(int distance);

    /**
     * Getter method of food resource scalar.
     * @return food resource scalar of that location.
     */
    Double getfoodresourceavailable();

    /**
     * Crucial to the number of water gathers act as scalar. Ideally, make it high in iceland and wood
     * the scalar decrease as the longer they stayed.
     * @param distance distance from its core.
     * @return water resource scalar
     */
    Double getsetwaterresourceavailable(int distance);

    /**
     * Getter method of water resource scalar.
     * @return water resource scalar of that location.
     */
    Double getwaterresourceavailable();

    /**
     * Crucial to number of firearm gathered. Closer to city center, this scalar will be higher.
     * Same spot, decrease this.
     * @param distance distance from its core.
     * @return firearm resource scalar
     */
    Double getsetweaponresourceavailable(int distance);

    /**
     * Getter method of firearm resource scalar.
     * @return firearm resource scalar of that location.
     */
    Double getweaponresourceavailable();

    /**
     * The method responsible for decrease the scalar of the location if they stayed, by some proportion, determined
     * by player's selected move ex: if player gathered, food weapon and water resource drop
     * This is for food weapon and water drop.
     */
    void decreaseresourceavailable();

    /**
     * Decrease the scalar by some proportion, which is gonna happen if player broadcast, so what's left is less.
     */
    void decreaserepeopleavailable();


    /**
     * The threat level of this location, used for calculating power of the horde, and people decrease.
     * Closer to city core, more threat level, Closer to iceland/desert,(as they are more isolated) lower threat.
     * @param distance distance from its core.
     * @return threat level.
     */
    Double getsetthreatlevel(int distance);

    /**
     * Getter method of threat scalar.
     * @return people threat scalar of that location.
     */
    Double getthreatlevel();
}
