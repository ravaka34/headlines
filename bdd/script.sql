CREATE TABLE headline (
    id SERIAL PRIMARY KEY NOT NULL,
    headline_type_id INTEGER NOT NULL,
    title varchar(250) not null ,
    picture VARCHAR(250) NOT NULL,
    date1 DATE NOT NULL,
    date2 DATE NOT NULL,
    place varchar(250) not null,
    body text not null
);

CREATE TABLE login (
    id SERIAL PRIMARY KEY NOT NULL ,
    email varchar(250) not null,
    mdp varchar(250) not null
);

CREATE TABLE headline_type (
    id SERIAL PRIMARY KEY  NOT NULL ,
    name varchar(50)
);

ALTER TABLE headline ADD FOREIGN KEY (headline_type_id) REFERENCES
headline_type(id);