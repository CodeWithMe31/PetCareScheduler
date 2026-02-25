import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Pet implements Serializable{
    private String UID;
    private String name;
    private String speciesOrBreed;
    private int age;
    private String ownerName;
    private String contactInfo;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    //Constructors
    public Pet(String UID, String name, String speciesOrBreed, int age, String ownerName, String contactInfo, LocalDate registrationDate) {
        this.UID = UID;
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDate = registrationDate;
        this.appointments = new ArrayList<>();
    }
    public Pet(String UID, String name, String speciesOrBreed, int age, String ownerName, String contactInfo, LocalDate registrationDate, List<Appointment> appointments) {
        this.UID = UID;
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDate = registrationDate;
        this.appointments = new ArrayList<>(appointments);
    }

    //Getters
    public String getUID() {
        return UID;
    }
    public String getName() {
        return name;
    }
    public String getSpeciesOrBreed() {
        return speciesOrBreed;
    }
    public int getAge() {
        return age;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public LocalDate getResgistrationDate() {
        return registrationDate;
    }
    public List<Appointment> getAppointments() {
        return appointments;
    }

    //Setters
    public void setUID(String UID) {
        this.UID = UID;
    } 
    public void setName(String name) {
        this.name = name;
    }
    public void setSpeciesOrBreed(String speciesOrBreed) {
        this.speciesOrBreed = speciesOrBreed;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void addAppointment(Appointment appointment) {
        if(appointment != null) {
            this.appointments.add(appointment);
        }
    }
    public boolean removeAppointment(Appointment appointment) {
        return this.appointments.remove(appointment);
    }
}
