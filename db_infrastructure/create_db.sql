CREATE DATABASE Antuanette;
CREATE TABLE restaurant_tables(
	id_t serial PRIMARY KEY,
	name_t varchar NOT NULL
);

CREATE TABLE booking(
    id_b serial PRIMARY KEY,
    customer_name varchar(100) NOT NULL,
    phone_number varchar(100) NOT NULL,
    id_t serial,
    date_b date NOT NULL,
    time_b time NOT NULL,
    comment_b varchar NOT NULL,
	FOREIGN KEY (id_t) REFERENCES restaurant_tables(id_t)
);
