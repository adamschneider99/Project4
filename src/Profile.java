import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Adam Schneider
 * @version 2022-11-11
 *          SOURCE: A large portion of this was taken from the lecture
 *
 */
public class Profile {

    private String name;
    private String status;
    private ArrayList<Profile> friendProfiles;

    /**
     * Constructor
     */
    public Profile() {
        this.name = "";
        this.status = "";
        this.friendProfiles = new ArrayList<Profile>();
    }


    /**
     * initializes the attributes with the accepted valued.
     * 
     * @param name
     *            name
     * @param status
     *            the status of the profile
     * @param friendProfiles
     *            list of friends
     */
    public Profile(
        String name,
        String status,
        ArrayList<Profile> friendProfiles) {
        this.name = name;
        this.status = status;
        this.friendProfiles = friendProfiles;
    }


    /**
     * initializes the attributes with the accepted valued and the last
     * attribute with a default arraylist object.
     * 
     * @param name
     *            of person
     * @param status
     *            of the person
     */
    public Profile(String name, String status) {
        this.name = name;
        this.status = status;
    }


    /**
     * - the setter method for the name attribute that accepts the first and
     * last name of the user and set the name attribute with firstName +” “
     * +lastName (Note the space between the two names).
     * O(1)
     * 
     * @param firstName
     * @param lastName
     */
    public void setName(String firstName, String lastName) {
        this.name = firstName + " " + lastName;
    }


    /**
     * - the getter method for the name attribute.
     * O(1)
     */
    public String getName() {
        return name;
    }


    /**
     * setter method
     * O(1)
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * getter method
     * O(1)
     * 
     * @return status
     */
    public String getStatus() {
        return this.status;
    }


    /**
     * – returns the a string that represents the profile of the user. It
     * displays the string in the following format.
     * "Name: " + name + "\n\tStatus: " + status + “\n\tNumber of friend
     * profiles: " + friend’s number + "\n"
     * 
     * O(1)
     */
    public String toString() {
        return "Name: " + this.name + "\n\tStatus: " + this.status
            + "\n\tNumber of friend profiles: " + friendProfiles.size() + "\n";
    }


    /**
     * - displays the profile and the friends profiles. Take a look at the
     * sample run to see the format of display.
     * O(n)
     */
    public void display() {
        System.out.println("Name: " + this.name + "\n\tStatus: " + this.status
            + "\n\tNumber of friend profiles: " + friendProfiles.size());
        System.out.println("Friends:");
        // List all of the current profiles friends
        for (Profile currProfile : friendProfiles) {
            System.out.println("\t" + currProfile.name);
        }
    }


    /**
     * - add a new friend to the friends list.
     * O(n)
     * 
     * @param user
     */
    public void addFriend(Profile user) {
        friendProfiles.add(user);
    }


    /**
     * @return the friendProfiles
     *         O(1)
     */
    public ArrayList<Profile> getFriendProfiles() {
        return friendProfiles;
    }


    /**
     * - the getter method for the friendProfiles attribute.
     * O(1)
     * 
     * @param friendProfiles
     *            the friendProfiles to set
     */
    public void setFriendProfiles(ArrayList<Profile> friendProfiles) {
        this.friendProfiles = friendProfiles;
    }


    /**
     * - removes an existing friend from the list of friends. returns true if
     * the removal of the profile is successful, false otherwise.
     * O(n)
     * 
     * @param user
     * @return bool true if succesful
     */
    public boolean unFriend(Profile user) {
        return friendProfiles.remove(user);
    }

}
