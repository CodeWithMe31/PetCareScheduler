package com.petcare;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.stream.*;


public class PetCareScheduler {
    private static final String PETS_FILE = "pets.ser";
    private static Map<String, Pet> pets = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        boolean running = true;
        while(running) {
            System.out.println("\n=== Pet Care Scheduler ===");
            System.out.println("1. Register Pet");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Display Records");
            System.out.println("4. Generate Reports");
            System.out.println("5. Store Data");
            System.out.println("6. Exit");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                    registerPet();
                    break;
                    case 2:
                    scheduleAppointment();
                    break;
                    case 3: 
                    displayRecords();
                    break;
                    case 4:
                    generateReports();
                    break;
                    case 5:
                    storeData();
                    break;
                    case 6: {
                        running = false;
                        storeData(); //Auto Save before exit;
                        System.out.println("Exiting.....Data Saved.");
                        break;
                    }
                    default:
                    System.out.println("Invalid choice! Try Again");

                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid Input! please enter a valid number");
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void registerPet() {
        try {
            System.out.println("Enter pet UID: ");
            String uniqueID = scanner.nextLine();
            if(pets.containsKey(uniqueID)) {
                System.out.println("Pet UID already Exists! Choose another");
                return;
            }
            System.out.println("Enter the pet name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Species/breed: ");
            String speciesOrBreed = scanner.nextLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter owner name: ");
            String ownerName = scanner.nextLine();
            System.out.println("Enter contact info: ");
            String contactInfo = scanner.nextLine();
            LocalDate registrationDate = LocalDate.now();

            Pet pet = new Pet(uniqueID, name, speciesOrBreed, age, ownerName, contactInfo, registrationDate);
            pets.put(uniqueID, pet);

            System.out.println("Pet registered Successfully!");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error registering pet: " + e.getMessage());
        }
    }

    private static void scheduleAppointment() {
        try {
            if(pets.isEmpty()) {
                System.out.println("No pets registered yet!");
                return;
            }
            System.out.println("Enter the pet UID: ");
            String uniqueID = scanner.nextLine();

            Pet pet = pets.get(uniqueID);
            if (pet == null) {
                System.out.println("No pet found with UID: " + uniqueID);
                return;
            }
            System.out.println("Enter the Appointment Type: (Vet Visit, Vaccination, Grooming etc.,)");
            String type = scanner.nextLine();

            System.out.println("Enter Date and Time(dd/MM/yyyy HH:mm): ");
            String dateTimeStr = scanner.nextLine();
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            System.out.println("Enter notes (Optional): ");
            String notes = scanner.nextLine();

            Appointment appointment = new Appointment(type, dateTime, notes);
            pet.addAppointment(appointment);

            System.out.println("Appointment Scheduled Successfully for " + pet.getName());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error Scheduling Appointment " + e.getMessage());
        }
    }

    private static void displayRecords() {
        if (pets.isEmpty()) {
            System.out.println("No records found!");
            return;
        }

        System.out.println("\n--- Display Records ---");
        System.out.println("1. All Registered Pets");
        System.out.println("2. All Appointments for a Specific Pet");
        System.out.println("3. Upcoming Appointments for All Pets");
        System.out.println("4. Past Appointment History for Each Pet");
        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                displayAllPets();
                break;
                case 2:
                displayAppointmentsForSpecificPet();
                break;
                case 3:
                displayUpcomingAppointments();
                break;
                case 4:
                displayPastAppointments();
                break;
                default:
                System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    private static void displayAllPets() {
        System.out.println("\n--- All Registered Pets ---");
        for (Pet pet : pets.values()) {
            System.out.println(pet);
        }
    }

    private static void displayAppointmentsForSpecificPet() {
        System.out.print("Enter Pet ID: ");
        String uniqueId = scanner.nextLine();
        Pet pet = pets.get(uniqueId);

        if (pet == null) {
            System.out.println("No pet found with ID: " + uniqueId);
            return;
        }

        System.out.println("\nAppointments for " + pet.getName() + ":");
        if (pet.getAppointments().isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : pet.getAppointments()) {
                System.out.println(a);
            }
        }
    }

    private static void displayUpcomingAppointments() {
        System.out.println("\n--- Upcoming Appointments ---");
        LocalDateTime now = LocalDateTime.now();

        for (Pet pet : pets.values()) {
            for (Appointment a : pet.getAppointments()) {
                if (a.getDateTime().isAfter(now)) {
                    System.out.println(pet.getName() + " (" + pet.getUID() + "): " + a);
                }
            }
        }
    }

    private static void displayPastAppointments() {
        System.out.println("\n--- Past Appointments ---");
        LocalDateTime now = LocalDateTime.now();

        for (Pet pet : pets.values()) {
            System.out.println("History for " + pet.getName() + " (" + pet.getUID() + "):");
            boolean hasPast = false;

            for (Appointment a : pet.getAppointments()) {
                if (a.getDateTime().isBefore(now)) {
                    System.out.println("   " + a);
                    hasPast = true;
                }
            }

            if (!hasPast) {
                System.out.println("   No past appointments.");
            }
        }
    }

    private static void generateReports() {
        if (pets.isEmpty()) {
            System.out.println("No pets available for reports.");
            return;
        }

        System.out.println("\n--- Reports ---");
        System.out.println("1. Pets with Upcoming Appointments in the Next Week");
        System.out.println("2. Pets Overdue for a Vet Visit (last visit > 6 weeks ago)");
        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                reportUpcomingAppointments();
                break;
                case 2:
                reportOverdueVetVisits();
                break;
                default:
                System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    private static void reportUpcomingAppointments() {
        System.out.println("\n--- Pets with Upcoming Appointments (Next 7 Days) ---");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekLater = now.plusWeeks(1);
        boolean found = false;

        for (Pet pet : pets.values()) {
            for (Appointment a : pet.getAppointments()) {
                if (!a.getDateTime().isBefore(now) && a.getDateTime().isBefore(oneWeekLater)) {
                    System.out.println(pet.getName() + " (" + pet.getUID() + ") → " + a);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No pets with appointments in the next week.");
        }
    }

    private static void reportOverdueVetVisits() {
        System.out.println("\n--- Pets Overdue for Vet Visit (last visit > 6 weeks ago) ---");
        LocalDateTime sixWeeksAgo = LocalDateTime.now().minusWeeks(6);
        boolean found = false;

        for (Pet pet : pets.values()) {
            // filter only "Vet Visit" type appointments
            List<Appointment> vetVisits = pet.getAppointments().stream()
                    .filter(a -> a.getAppointmentType().equalsIgnoreCase("Vet Visit"))
                    .collect(Collectors.toList());

            if (vetVisits.isEmpty()) {
                // No vet visits at all → automatically overdue
                System.out.println(pet.getName() + " (" + pet.getUID() + ") → No vet visit recorded.");
                found = true;
            } else {
                // Get last vet visit
                Appointment lastVetVisit = Collections.max(vetVisits, Comparator.comparing(Appointment::getDateTime));
                if (lastVetVisit.getDateTime().isBefore(sixWeeksAgo)) {
                    System.out.println(pet.getName() + " (" + pet.getUID() + ") → Last Vet Visit: " + lastVetVisit);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("All pets are up-to-date with vet visits.");
        }
    }

    private static void storeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PETS_FILE))){
            oos.writeObject(pets);
            System.out.println("Data stores/saved successfully!");
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PETS_FILE))){
            pets = (Map<String, Pet>) ois.readObject();
            System.out.println("Data loaded successfully!");
        } catch (FileNotFoundException fnfe) {
            // TODO: handle exception
            System.out.println("No Existing data found, starting fresh.");
        } catch(Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
