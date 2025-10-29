package fr.mmp.rebu.Vehicle;

public class Car implements VehicleInterface {
    String licensePlate;
    int passengersNumber;
    public Car(String licensePlate,  int passengersNumber) {
        this.licensePlate = licensePlate;
        this.passengersNumber = passengersNumber;
    }
    public String getPlate(){
        return this.licensePlate;
    }

    public int getPassengersNumber(){
        return this.passengersNumber;
    }

    public void setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }

    public void setPassengersNumber(int passengersNumber){
        this.passengersNumber = passengersNumber;
    }
}
