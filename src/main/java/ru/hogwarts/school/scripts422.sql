CREATE TABLE person
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INTEGER,
    licensed BOOLEAN,
    cars_id SERIAL REFERENCES cars (id)
);
CREATE TABLE cars
(
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER
);