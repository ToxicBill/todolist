CREATE DATABASE IF NOT EXISTS tododb;
USE tododb;
DROP TABLE IF EXISTS todo;

CREATE TABLE IF NOT EXISTS todo
(
    id              BIGINT AUTO_INCREMENT,
    description     VARCHAR(400),
    done            BOOLEAN,
    priority        BIGINT,
    duedate         DATETIME,
    PRIMARY KEY (id)
);