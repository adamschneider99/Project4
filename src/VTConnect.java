import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Adam Schneider
 * @version 2022-11-11
 *
 */
public class VTConnect {

    private Graph<Profile> graph;

    /**
     * - initializes the social networking app.
     */
    public VTConnect() {
        this.graph = new Graph<Profile>();
    }


    /**
     * - Adds a new user to the social network.
     * 
     * @param p
     */
    public void addUser(Profile p) {
        graph.addVertex(p);
    }


    /**
     * - Removes an existing user from the social network. If the user does not
     * exist, it returns null.
     * 
     * @param p
     * @return
     */
    public Profile removeUser(Profile p) {
        return graph.removeVertex(p).getLabel();
    }


    /**
     * - Creates a friendship between two users on VTConnect. If the friendship
     * is created successfully, it returns true, false otherwise.
     * 
     * @param a
     * @param b
     * @return
     */
    public boolean createFriendship(Profile a, Profile b) {
        if (hasFriendship(a, b)) {
            return false;
        }
        a.addFriend(b);
        b.addFriend(a);
        // CHANGE
        return true;

    }


    /**
     * removes a friendship between two users on VTConnect. If the friendship is
     * discontinued successfully, it returns true, false otherwise.
     * 
     * @param a
     * @param b
     * @return
     */
    public boolean removeFriendship(Profile a, Profile b) {
        if (hasFriendship(a, b)) {
            a.unFriend(b);
            b.unFriend(a);
            return true;
        }

        return false;

    }


    /**
     * - Returns true if there is friendship between Profiles a and b, false
     * otherwise.
     * 
     * @param a
     * @param b
     * @return
     */
    public boolean hasFriendship(Profile a, Profile b) {
        ArrayList<Profile> list = a.getFriendProfiles();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(b)) {
                return true;
            }
        }
        return false;
    }


    /**
     * - this method displays each profile's information and friends, starting
     * from the startPoint profile. See the sample run on the format of the
     * display.
     * 
     * @param startPoint
     */
    public void traverse(Profile startPoint) {
        Queue<Profile> queue = graph.getBreadthFirstTraversal(startPoint);
        while (!queue.isEmpty()) {
            queue.poll().display();
        }
    }


    /**
     * - this returns true if a user with the given profile exists in VTConnect,
     * false otherwise.
     * 
     * @param user
     * @return
     */
    public boolean exists(Profile user) {
        Queue<Profile> queue = graph.getBreadthFirstTraversal(user);
        while (!queue.isEmpty()) {
            if (queue.poll().equals(user)) {
                return true;
            }
        }
        return false;
    }


    /**
     * - Returns a list of Profiles, who are friends with one or more of the
     * profile's friends (but not currently the profile's friend). It returns
     * null, if the user does not exist or if it does not have any friend
     * suggestions. Take a look at the sample run for an example.
     * 
     * @param user
     * @return
     */
    public List<Profile> friendSuggestion(Profile user) {
        if (user == null || !exists(user)) {
            return null;
        }
        
        Queue<Profile> queue = graph.getBreadthFirstTraversal(user);
        while (!queue.isEmpty()) {
            Profile temp = queue.poll();
            if (friendshipDistance(user, temp > );
            
                
            }
        }
        
        
        

    }


    /**
     * - Returns the friendship distance between two profiles. A friendship
     * distance is simply how many profiles away the two profiles are. For
     * example, if a and b are friends their friendship distance is 1. If they
     * have a common friend but they are not friends, their friendship distance
     * is 2. If either of the profiles are not in the social networking app, the
     * method returns -1.
     * 
     * @param a
     * @param b
     * @return
     */
    public int friendshipDistance(Profile a, Profile b) {
        Stack<Profile> path = new Stack<Profile>();
        return graph.getShortestPath(a, b, path);

    }

}
