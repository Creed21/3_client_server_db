/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Aleksandar Jankovic
 * Created: Jan 25, 2025
 */

CREATE DATABASE if not exists `test` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

use `test`;
-- test.predmet definition

CREATE TABLE `predmet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` tinytext DEFAULT NULL,
  `godina_na_koj_se_slusa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


-- test.profesor definition

CREATE TABLE `profesor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` tinytext DEFAULT NULL,
  `prezime` tinytext DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


-- test.student definition

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` tinytext DEFAULT NULL,
  `prezime` tinytext DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `godina_upisa` int(11) DEFAULT NULL,
  `esp_bodovi` int(11) DEFAULT NULL,
  `prosek` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


-- test.ispit definition

CREATE TABLE `ispit` (
  `student_id` int(11) NOT NULL,
  `profesor_id` int(11) NOT NULL,
  `predmet_id` int(11) NOT NULL,
  `datum_polaganja` date NOT NULL,
  `ocena` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`profesor_id`,`predmet_id`,`datum_polaganja`),
  KEY `fk_profesor` (`profesor_id`),
  KEY `fk_predmet` (`predmet_id`),
  CONSTRAINT `fk_predmet` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`),
  CONSTRAINT `fk_profesor` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`),
  CONSTRAINT `fk_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


-- data insertion

INSERT INTO test.student
(id, ime, prezime, datum_rodjenja, godina_upisa, esp_bodovi, prosek)
VALUES(1, 'aleksandar', 'jankovic', '1995-10-29', 2016, 240, 7.9);
INSERT INTO test.student
(id, ime, prezime, datum_rodjenja, godina_upisa, esp_bodovi, prosek)
VALUES(2, 'marija', 'totovic', '2000-06-07', 2020, 230, 9.3);
INSERT INTO test.student
(id, ime, prezime, datum_rodjenja, godina_upisa, esp_bodovi, prosek)
VALUES(3, 'aleksa', 'tacic', '2000-06-07', 2020, 220, 9.1);
INSERT INTO test.student
(id, ime, prezime, datum_rodjenja, godina_upisa, esp_bodovi, prosek)
VALUES(5, 'jelena', 'nikolic', '2000-06-07', 2020, 225, 9.2);
INSERT INTO test.student
(id, ime, prezime, datum_rodjenja, godina_upisa, esp_bodovi, prosek)
VALUES(6, 'x', 'x', '2002-02-02', 2021, 100, 6.6);

INSERT INTO test.profesor
(id, ime, prezime, datum_rodjenja)
VALUES(1, 'dule', 'savic', '1960-01-01');
INSERT INTO test.profesor
(id, ime, prezime, datum_rodjenja)
VALUES(2, 'ilija', 'antonovic', '1970-02-02');
INSERT INTO test.profesor
(id, ime, prezime, datum_rodjenja)
VALUES(3, 'sinisa', 'vlajic', '1950-03-03');
INSERT INTO test.profesor
(id, ime, prezime, datum_rodjenja)
VALUES(4, 'sasa', 'lazarevic', '1955-05-05');

INSERT INTO test.predmet
(id, naziv, godina_na_koj_se_slusa)
VALUES(1, 'projektovanje softvera', 4);
INSERT INTO test.predmet
(id, naziv, godina_na_koj_se_slusa)
VALUES(2, 'programiranje 1', 1);
INSERT INTO test.predmet
(id, naziv, godina_na_koj_se_slusa)
VALUES(3, 'programiranje 2', 2);


INSERT INTO test.ispit
(student_id, profesor_id, predmet_id, datum_polaganja, ocena)
VALUES(1, 1, 1, '2025-01-22', 8);
INSERT INTO test.ispit
(student_id, profesor_id, predmet_id, datum_polaganja, ocena)
VALUES(1, 2, 2, '2025-01-22', 9);
