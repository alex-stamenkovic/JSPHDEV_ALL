CREATE TABLE IF NOT EXISTS option_set (
    option_set_id int NOT NULL AUTO_INCREMENT,
    model_id int NOT NULL,
    set_name varchar(45),
    FOREIGN KEY (model_id) REFERENCES model(model_id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(option_set_id)
)
