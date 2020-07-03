DROP TABLE IF EXISTS hibernate_sequence;

CREATE TABLE hibernate_sequence
(
    next_val bigint DEFAULT NULL
);

DROP TABLE IF EXISTS terrains;

CREATE TABLE terrains
(
    id_court   bigint(20) NOT NULL,
    actif      bit(1)       DEFAULT NULL,
    court_name varchar(255) DEFAULT NULL,
    PRIMARY KEY (id_court),
    UNIQUE KEY court_name (court_name)
);



DROP TABLE IF EXISTS terrain_entity_horaires_names;

CREATE TABLE terrain_entity_horaires_names
(
    terrain_entity_id_court bigint(20) NOT NULL,
    horaires_names          varchar(255) DEFAULT NULL,

    CONSTRAINT FK332q3qmxmdqerfcol05ogtva0 FOREIGN KEY (terrain_entity_id_court) REFERENCES terrains (id_court)
);

DROP TABLE IF EXISTS terrain_entity_tarifs_ids;

CREATE TABLE terrain_entity_tarifs_ids
(
    terrain_entity_id_court bigint(20) NOT NULL,
    tarifs_ids              bigint(20) DEFAULT NULL,
    CONSTRAINT FK24vwxc6xju6l4d1r1jkxc2ps8 FOREIGN KEY (terrain_entity_id_court) REFERENCES terrains (id_court)
);

DROP TABLE IF EXISTS terrain_entity_tarifs_names;
CREATE TABLE terrain_entity_tarifs_names
(
    terrain_entity_id_court bigint(20) NOT NULL,
    tarifs_names            varchar(255) DEFAULT NULL,

    CONSTRAINT FK5x1e91ks2vgtpgmggcmsbg43i FOREIGN KEY (terrain_entity_id_court) REFERENCES terrains (id_court)
);



