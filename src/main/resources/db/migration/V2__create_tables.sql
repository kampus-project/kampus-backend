create TABLE ${flyway:defaultSchema}.students
(
    students_id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    middle_name         VARCHAR(255),
    date_birth          DATE,
    receipt_date        DATE,
    university_name     VARCHAR(255),
    course_title        VARCHAR(255),
    course_number       INTEGER,
    average_grade       DECIMAL(4),
--    Форма образования очно/заочно
    study_form          VARCHAR(255),
--    Форма обучения бюджет/контракт
    education_form      VARCHAR(255),
    type_high_education VARCHAR(255),
    local_resident      BOOLEAN,

    CONSTRAINT pk_students PRIMARY KEY (students_id)
);