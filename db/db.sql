DROP TABLE request;

CREATE TABLE request (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    contact         VARCHAR(50),
    email           VARCHAR(100) NOT NULL,
    work_scope      VARCHAR(200),
    deadline        DATE,
    genre           VARCHAR(100),
    reference_link  VARCHAR(300),
    note            TEXT,
    guide_file      VARCHAR(300),
    midi_file       VARCHAR(300),
    reference_file  VARCHAR(300),
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP
);