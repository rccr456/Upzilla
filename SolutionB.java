import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param an
     *            int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class SolutionB {

    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();

    // Method to find the most visited site in the queue size endIndex
    public static SiteStats findMostVisited(Queue<SiteStats> sites, int endIndex) {
        // WRITE CODE HERE
        int maxVisits = -1; // Initializing maxVisits as lowest element
        SiteStats mostVisited = null;
        // Looping over array to find the most visited site till endIndex
        for (int i = 0; i < sites.size(); i++) {
            SiteStats head = sites.remove();

            if ((head.getNumVisits() >= maxVisits) && (i < endIndex)) {
                mostVisited = head;
                maxVisits = head.getNumVisits();
            }
            
            sites.add(head);
        }

        return mostVisited;
        // --------------
    }

    
    public static void reorder(Queue<SiteStats> sites, SiteStats mostVisited) {
        // WRITE CODE HERE
        for (int i = 0; i <= sites.size(); i++) {
            SiteStats head = sites.remove();
           
            if (mostVisited != head) {
                sites.add(head);
            }
        }
        
        sites.add(mostVisited);
       
    }

    
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {

        for (int i = 0; i < sites.size(); i++) {
            SiteStats mostVisited = findMostVisited(sites, sites.size() - i);
            reorder(sites, mostVisited);
        }

        System.out.println("Rank | URL | VisitCount");
        for (int i = 0; i < n; i++) {

            if (!sites.isEmpty()) {
                System.out.println(Integer.toString(i + 1) + " | " + sites.remove());
            }
        }
        // -----------------
    }

    
    public static void updateCount(String url) {

        Boolean newSite = true;
        SiteStats updated = null;
        int queueSize = sites.size();
        for (int i = 0; i < queueSize; i++) {

            SiteStats head = sites.remove();


            if (head.getUrl().equals(url)) {

                head.setNumVisits(head.getNumVisits() + 1);

                newSite = false;

                updated = head;
            }

            else {sites.add(head);}
        }


        if (newSite) {
            sites.add(new SiteStats(url, 1));
        }
        else {
            sites.add(updated);
        }
        
    }

    public static void main(String[] args) {


        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

        for (String url : visitedSites) {
            updateCount(url);
        }
        listTopVisitedSites(sites, 5);

    }

}