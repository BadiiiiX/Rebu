package fr.mmp.rebu.Ride;

public interface RideRepository {
    Ride save(Ride ride);
    Ride findById(Long id);
    void deleteById(Long id);

}
