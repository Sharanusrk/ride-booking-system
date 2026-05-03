class Timetable {
    String[] subjects = {"Math", "Physics", "Chemistry"};
    String[] slots = {"9AM", "10AM", "11AM"};

    String[] result = new String[slots.length];

    // Check if safe to assign
    boolean isSafe(int subjectIndex, int slotIndex) {
        // No duplicate subject assignment
        for (int i = 0; i < slotIndex; i++) {
            if (result[i] != null && result[i].equals(subjects[subjectIndex])) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function
    boolean assignSlot(int slotIndex) {
        if (slotIndex == slots.length) return true;

        for (int i = 0; i < subjects.length; i++) {
            if (isSafe(i, slotIndex)) {
                result[slotIndex] = subjects[i];

                if (assignSlot(slotIndex + 1)) return true;

                // backtrack
                result[slotIndex] = null;
            }
        }
        return false;
    }

    void generateTimetable() {
        if (assignSlot(0)) {
            System.out.println("Generated Timetable:");
            for (int i = 0; i < slots.length; i++) {
                System.out.println(slots[i] + " -> " + result[i]);
            }
        } else {
            System.out.println("No valid timetable found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Timetable tt = new Timetable();
        tt.generateTimetable();
    }
}