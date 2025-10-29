CREATE TABLE IF NOT EXISTS cars (
    id BIGSERIAL PRIMARY KEY,
    license_plate VARCHAR(15) NOT NULL UNIQUE,
    passengers_number INTEGER NOT NULL CHECK (passengers_number >= 1 AND passengers_number <= 9),
);

CREATE INDEX idx_cars_license_plate ON cars(license_plate);

COMMENT ON TABLE cars IS 'Table des véhicules pour le système de carpooling';
COMMENT ON COLUMN cars.id IS 'Identifiant unique du véhicule';
COMMENT ON COLUMN cars.license_plate IS 'Plaque d''immatriculation (unique)';
COMMENT ON COLUMN cars.passengers_number IS 'Nombre de places passagers (1-9)';