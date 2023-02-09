-- grant all privileges on test_cluster.* to 'test_cluster'@'localhost';
-- CREATE SCHEMA test_cluster;

CREATE  TABLE test_cluster.headline_type ( 
	id                   INT  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	name                 VARCHAR(255)      
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE  TABLE test_cluster.login ( 
	id                   INT  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	email                VARCHAR(255)      ,
	mdp                  VARCHAR(255)      
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE  TABLE test_cluster.login_auteur ( 
	id                   INT  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	email                VARCHAR(255)      ,
	mdp                  VARCHAR(255)      
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE  TABLE test_cluster.headline ( 
	id                   INT  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	body                 VARCHAR(255)      ,
	date1                DATE      ,
	date2                DATE      ,
	date_creation        DATETIME(6)      ,
	date_publication     DATETIME(6)      ,
	headline_status_id   INT      ,
	picture              VARCHAR(255)      ,
	place                VARCHAR(255)      ,
	title                VARCHAR(255)      ,
	headline_type_id     INT      ,
	CONSTRAINT unq_headline_headline_status_id UNIQUE ( headline_status_id ) 
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE INDEX `FKf04044jgiqq1l5dfdk6s6j4v1` ON test_cluster.headline ( headline_type_id );

CREATE  TABLE test_cluster.headline_status ( 
	id                   INT  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	name                 VARCHAR(255)      
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE test_cluster.headline ADD CONSTRAINT `FKf04044jgiqq1l5dfdk6s6j4v1` FOREIGN KEY ( headline_type_id ) REFERENCES test_cluster.headline_type( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE test_cluster.headline ADD CONSTRAINT fk_headline_headline_status FOREIGN KEY ( headline_status_id ) REFERENCES test_cluster.headline_status( id ) ON DELETE NO ACTION ON UPDATE NO ACTION

CREATE VIEW test_cluster.v_headline_publie AS
    select `test_cluster`.`headline`.`id` AS `id`,`test_cluster`.`headline`.`body` AS `body`,`test_cluster`.`headline`.`date1` AS `date1`,`test_cluster`.`headline`.`date2` AS `date2`,`test_cluster`.`headline`.`date_creation` AS `date_creation`,`test_cluster`.`headline`.`date_publication` AS `date_publication`,`test_cluster`.`headline`.`headline_status_id` AS `headline_status_id`,`test_cluster`.`headline`.`picture` AS `picture`,`test_cluster`.`headline`.`place` AS `place`,`test_cluster`.`headline`.`title` AS `title`,`test_cluster`.`headline`.`headline_type_id` AS `headline_type_id` from `test_cluster`.`headline` where ((`test_cluster`.`headline`.`headline_status_id` = 2) and (`test_cluster`.`headline`.`date_publication` <= now()));

INSERT INTO login_auteur (email, mdp) VALUES ('aina@gmail.com', 'aina');
INSERT INTO headline_status (name) VALUES ('Cree'), ('Publie');
INSERT INTO headline_type (name) values ('Event'),('Article');
INSERT INTO login (email, mdp) values ('johndoe@gmail.com','johndoe');
