CREATE TABLE IF NOT EXISTS opt (
    opt_id int NOT NULL AUTO_INCREMENT,
    option_set_id int NOT NULL,
    opt_name varchar(45),
    price float NOT NULL,
    FOREIGN KEY (option_set_id) REFERENCES option_set(option_set_id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(opt_id)
)
