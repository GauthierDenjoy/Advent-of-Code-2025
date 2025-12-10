import days.DayChallenge;

void main() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n===== Advent of Code 2025 Runner =====");
        System.out.println("(This year is a short Advent of code)\n");
        System.out.println("Select a day from 1 to 12 (0 to exit):\n");

        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        System.out.println();

        if (choice == 0) {
            System.out.println("Goodbye!");
            break;
        }

        DayChallenge day = getDayChallenge(choice);
        if (day != null) {
            day.run();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    scanner.close();
}

private static DayChallenge getDayChallenge(int day) {
    try {
        String className = String.format("days.day%02d.Day%02d", day, day);
        Class<?> clazz = Class.forName(className);
        return (DayChallenge) clazz.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
        return null;
    }
}
