package fr.mmp.rebu.ride.mapper;

import fr.mmp.rebu.Rebu;
import fr.mmp.rebu.ride.Factory.RideFactory;
import fr.mmp.rebu.ride.model.RideInterface;

import java.sql.ResultSet;
import java.util.Date;

public class RideMapper {
    public static RideInterface databaseToRide(ResultSet rs)  {
        try {
            return RideFactory.build(
                    rs.getInt("ride_id"),
                    Rebu.getCarService().findCarByPlate(rs.getString("car_plate")),
                    Rebu.getUserService().findUserById(rs.getInt("driver_id")),
                    rs.getString("ride_origin"),
                    rs.getString("ride_destination"),
                    new Date(rs.getTimestamp("ride_start_date").getTime())
            );
        } catch (Exception e) {
            throw new RuntimeException("Cannot map Ride", e);
        }
    }
}
