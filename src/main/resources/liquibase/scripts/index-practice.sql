-- liquibase formatted sql

-- changeset repin:1
CREATE INDEX students_name_index ON student (name);

-- changeset repin:2
CREATE INDEX faculties_name_color_index ON faculty (color,name);
