--CREATE TABLE IF NOT EXISTS customer (id SERIAL PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255));

CREATE TABLE IF NOT EXISTS employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    position VARCHAR(30),
    department VARCHAR(30),
    date_of_joining DATE
);