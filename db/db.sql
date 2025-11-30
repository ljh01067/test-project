DROP TABLE request;

CREATE TABLE request (
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME               VARCHAR(50) NOT NULL,
    contact            VARCHAR(50),
    email              VARCHAR(100) NOT NULL,
    work_scope         VARCHAR(200),
    deadline           DATE,
    genre              VARCHAR(100),
    reference_link     VARCHAR(300),
    note               TEXT,
    guide_file         LONGBLOB,
    midi_file          LONGBLOB,
    reference_file     LONGBLOB,
    guide_filename     VARCHAR(300),
    midi_filename      VARCHAR(300),
    reference_filename VARCHAR(300),
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SELECT * FROM request;