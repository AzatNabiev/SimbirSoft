DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS id;
DROP TABLE IF EXISTS word;
DROP TABLE IF EXISTS word_count;

CREATE TABLE words
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    word         VARCHAR (255),
    word_count          INTEGER
);