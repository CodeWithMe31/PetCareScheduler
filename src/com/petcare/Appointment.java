package com.petcare

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;


public class Appointment implements Serializable {
    private String appointmentType;
    private LocalDateTime dateTime;
    private String notes;

    //Constructors
    public Appointment(String appointmentType, LocalDateTime dateTime, String notes) {
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
        this.notes = notes;
    }
    public Appointment(String appointmentType, LocalDateTime dateTime) {
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
    }

    //Getters
    public String getAppointmentType() {
        return appointmentType;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getNotes() {
        return notes;
    }

    //Setters
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        return "Appointment{" + "Type: '" + appointmentType + "\'" + ", DateTime: " + formattedDateTime + (notes != null && !notes.isEmpty() ? ", Notes: '" + notes + "\'" : "") + "}";
    }
}
