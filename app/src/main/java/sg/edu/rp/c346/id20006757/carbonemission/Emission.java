package sg.edu.rp.c346.id20006757.carbonemission;

import java.io.Serializable;



public class Emission implements Serializable {
    private int id;
    private String date;
    private String vehicle_code;
    private String fuel;
    private double distance;
    private double CO2e;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVehicle_code() {
        return vehicle_code;
    }

    public void setVehicle_code(String vehicle_code) {
        this.vehicle_code = vehicle_code;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCO2e() {
        return CO2e;
    }

    public void setCO2e(double CO2e) {
        this.CO2e = CO2e;
    }

    public Emission(int id, String date, String vehicle_code, String fuel, double distance, double CO2e) {
        this.id = id;
        this.date = date;
        this.vehicle_code = vehicle_code;
        this.fuel = fuel;
        this.distance = distance;
        this.CO2e = CO2e;
    }


}





