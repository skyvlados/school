CREATE TABLE persons
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    age SERIAL,
    isLicense BOOLEAN,
    cars_id SERIAL REFERENCES cars (id)
);
CREATE TABLE cars
(
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    prise SERIAL
);

