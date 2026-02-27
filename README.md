# PetCareScheduler

## 📌 Overview
`PetCareScheduler` is a Java-based console application to help pet owners manage:
- Pet registration
- Scheduling appointments
- Displaying pet and appointment data
- Generating reports
- Saving and loading data

## 🛠 Features
- Register pets with unique details
- Schedule appointments for registered pets
- View all pets and appointments
- Generate simple reports
- Persist data using serialization (`pets.ser`)

## 📂 Project Structure
```
PetCareScheduler/
│── src/
│   ├── com/petcare/
│      ├── Appointment.java
│      ├── Pet.java
│      └── PetCareScheduler.java
│── README.md
│── .gitignore
```

## ▶️ How to Run
1. Compile the source files:
   ```bash
   javac -d bin src\com\petcare\*.java
   ```

2. Run the application:
   ```bash
   java -cp bin com.petcare.PetCareScheduler
   ```

## 📜 Requirements
- Java 8 or above
- Command-line interface (CLI)

## 📈 Future Enhancements
- Add GUI support
- Database integration instead of file serialization
- Advanced reporting features
