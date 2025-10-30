-- Table rides
CREATE TABLE rides (
    ride_id SERIAL PRIMARY KEY,
    vehicle_id INT NOT NULL,
    driver_id INT NOT NULL,
    origin TEXT NOT NULL,
    destination TEXT NOT NULL,
    start_date TIMESTAMP NOT NULL
);

-- Index pour faciliter les recherches par conducteur, véhicule ou date
CREATE INDEX idx_rides_driver_id ON rides(driver_id);
CREATE INDEX idx_rides_vehicle_id ON rides(vehicle_id);
CREATE INDEX idx_rides_start_date ON rides(start_date);

-- (Optionnel) si tu fais souvent des recherches par origine/destination
CREATE INDEX idx_rides_origin ON rides(origin);
CREATE INDEX idx_rides_destination ON rides(destination);

-- Table ride_passengers
CREATE TABLE ride_passengers (
    ride_id INT REFERENCES rides(ride_id) ON DELETE CASCADE,
    passenger_id INT NOT NULL,
    PRIMARY KEY (ride_id, passenger_id)
);

-- Index utile pour retrouver tous les trajets d’un passager
CREATE INDEX idx_ride_passengers_passenger_id ON ride_passengers(passenger_id);
