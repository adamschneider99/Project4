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
 *          SOURCE: A large portion of this was taken from the lecture
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
     * O(1)
     * 
     * @param p
     */
    public void addUser(Profile p) {
        graph.addVertex(p);
    }


    /**
     * - Removes an existing user from the social network. If the user does not
     * exist, it returns null.
     * O(n^2)
     * 
     * @param p
     * @return profile of removed user
     */
    public Profile removeUser(Profile p) {
        if (!exists(p)) {
            return null;
        }
        ArrayList<Profile> list = p.getFriendProfiles();
        for (int i = p.getFriendProfiles().size() - 1; i >= 0; i--) {
            Profile p2 = list.get(i);
            removeFriendship(p, p2);
        }
        return graph.removeVertex(p).getLabel();
    }


    /**
     * - Creates a friendship between two users on VTConnect. If the friendship
     * is created successfully, it returns true, false otherwise.
     * O(n)
     * 
     * @param a
     * @param b
     * @return boolean true if the friendship is created successfully
     */
    public boolean createFriendship(Profile a, Profile b) {
        if (!exists(a) || !exists(b)) {
            return false;
        }
        if (hasFriendship(a, b)) {
            return false;
        }
        a.addFriend(b);
        b.addFriend(a);
        graph.addEdge(a, b);
        graph.addEdge(b, a);

        return true;

    }


    /**
     * removes a friendship between two users on VTConnect. If the friendship is
     * discontinued successfully, it returns true, false otherwise.
     * O(n)
     * 
     * @param a
     * @param b
     * @return boolean true if friendship was successfully removed
     */
    public boolean removeFriendship(Profile a, Profile b) {
        if (!exists(a) || !exists(b)) {
            return false;
        }
        
        if (hasFriendship(a, b)) {
            a.unFriend(b);
            b.unFriend(a);
            graph.removeEdge(a, b);
            graph.removeEdge(b, a);
            return true;
        }

        return false;

    }


    /**
     * - Returns true if there is friendship between Profiles a and b, false
     * otherwise.
     * O(n)
     * 
     * @param a
     * @param b
     * @return boolean true if there is friendship between Profiles a and b
     */
    public boolean hasFriendship(Profile a, Profile b) {
        if (!exists(a) || !exists(b)) {
            return false;
        }
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
     * O(n+E)
     * 
     * @param startPoint
     */
    public void traverse(Profile startPoint) {
        Queue<Profile> queue = graph.getBreadthFirstTraversal(startPoint);
        while (!queue.isEmpty()) {
            queue.poll().display();
            System.out.println("");
        }
    }


    /**
     * - this returns true if a user with the given profile exists in VTConnect,
     * false otherwise.
     * O(1)
     * 
     * @param user
     * @return boolean true if the user with the given profile exists in
     *         VTConnect
     */
    public boolean exists(Profile user) {
        List<VertexInterface<Profile>> allProfiles = graph.getVertices();

        for (VertexInterface<Profile> profile : allProfiles) {
            if (profile.getLabel().equals(user)) {
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
     * O(n^2)
     * 
     * @param user
     * @return the list of profiles of friend suggestions
     */
    public List<Profile> friendSuggestion(Profile user) {
        if (user == null || !exists(user)) {
            return null;
        }
        List<Profile> res = new ArrayList<Profile>();
        Queue<Profile> queue = graph.getBreadthFirstTraversal(user);
        while (!queue.isEmpty()) {
            Profile currProfile = queue.poll();
            if (friendshipDistance(user, currProfile) == 2) {
                res.add(currProfile);
            }
        }
        if (res.size() == 0) {
            return null;
        }
        return res;
    }


    /**
     * - Returns the friendship distance between two profiles. A friendship
     * distance is simply how many profiles away the two profiles are. For
     * example, if a and b are friends their friendship distance is 1. If they
     * have a common friend but they are not friends, their friendship distance
     * is 2. If either of the profiles are not in the social networking app, the
     * method returns -1.
     * O(n+E)
     * 
     * @param a
     * @param b
     * @return distance between friends
     */
    public int friendshipDistance(Profile a, Profile b) {
        if (!exists(a) || !exists(b)) {
            return -1;
        }
        Stack<Profile> path = new Stack<Profile>();
        int res = graph.getShortestPath(a, b, path);
        if (res == 0) {
            return -1;
        }
        return res;

    }

}
