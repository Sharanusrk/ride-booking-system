class Ride {
    int rideId;
    String pickup;
    String drop;
    double fare;

    Ride(int rideId, String pickup, String drop, double fare) {
        this.rideId = rideId;
        this.pickup = pickup;
        this.drop = drop;
        this.fare = fare;
    }
}

class Node {
    Ride data;
    Node next;

    Node(Ride data) {
        this.data = data;
        this.next = null;
    }
}

class RideHistory {
    Node head;

    // Add ride at end
    void addRide(Ride r) {
        Node newNode = new Node(r);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Delete last ride
    void deleteLastRide() {
        if (head == null) return;

        if (head.next == null) {
            head = null;
            return;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
    }

    // Display all rides
    void displayRides() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data.rideId + " | " +
                    temp.data.pickup + " -> " +
                    temp.data.drop + " | ₹" +
                    temp.data.fare);
            temp = temp.next;
        }
    }

    // Search by location
    void searchRide(String location) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.pickup.equalsIgnoreCase(location) ||
                temp.data.drop.equalsIgnoreCase(location)) {

                System.out.println("Found: " + temp.data.rideId);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) System.out.println("No ride found.");
    }

    // Reverse history
    void reverseHistory() {
        Node prev = null, curr = head, next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
    }
}

public class Ride_Booking {
    public static void main(String[] args) {
        RideHistory rh = new RideHistory();

        rh.addRide(new Ride(1, "Chennai", "Bangalore", 1200));
        rh.addRide(new Ride(2, "Delhi", "Agra", 800));
        rh.addRide(new Ride(3, "Mumbai", "Pune", 600));

        System.out.println("All Rides:");
        rh.displayRides();

        System.out.println("\nSearch Chennai:");
        rh.searchRide("Chennai");

        System.out.println("\nDelete last ride:");
        rh.deleteLastRide();
        rh.displayRides();

        System.out.println("\nReversed:");
        rh.reverseHistory();
        rh.displayRides();
    }
}