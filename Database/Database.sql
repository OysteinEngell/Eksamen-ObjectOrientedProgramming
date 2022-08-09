CREATE SCHEMA quiz2DB;
USE quiz2DB;

CREATE TABLE multiQuiz (
    ID INT NOT NULL AUTO_INCREMENT,
    question varchar(150),
    alternativeA varchar(150),
    alternativeB varchar(150),
    alternativeC varchar(150),
    alternativeD varchar(150),
    correct varchar(4),

    PRIMARY KEY (ID)
);

CREATE TABLE binaryQuiz (
    ID INT NOT NULL AUTO_INCREMENT,
    question varchar(150),
    correct BOOLEAN,

    PRIMARY KEY (ID)
);

CREATE TABLE scoreboard (
    ID INT NOT NULL AUTO_INCREMENT,
    username varchar(25),
    score INT,
    topic varchar(25),

    PRIMARY KEY (ID)
);

