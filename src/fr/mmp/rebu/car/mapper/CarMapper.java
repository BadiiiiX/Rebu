package fr.mmp.rebu.car.mapper;

import fr.mmp.rebu.car.model.Car;
import fr.mmp.rebu.car.model.CarInterface;
import fr.mmp.rebu.exception.MappingException;
import fr.mmp.rebu.user.model.UserInterface;

import java.sql.ResultSet;

public class CarMapper {
    public static CarInterface databaseToCar(ResultSet rs, UserInterface user) throws MappingException {

        try {
            return new Car(
                    rs.getString("car_plate"),
                    rs.getInt("car_passengers_number"),
                    user
            );
        } catch (Exception e) {
            throw new MappingException("Cannot map Car", e);
        }

    }
}
