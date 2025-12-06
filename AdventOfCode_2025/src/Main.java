import days.DayChallenge;

void main() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n===== Advent of Code Runner =====");
        System.out.println("Select a day (0 to exit):\n");

        for (int i = 1; i <= 25; i++) {
            System.out.printf("%2d ", i);
            if (i % 5 == 0) System.out.println();
        }

        System.out.println("\n\n0 - Exit");
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

//this main should be static
private static DayChallenge getDayChallenge(int day) {
    try {
        String className = String.format("days.day%02d.Day%02d", day, day);
        Class<?> clazz = Class.forName(className);
        return (DayChallenge) clazz.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
        return null;
    }
}
