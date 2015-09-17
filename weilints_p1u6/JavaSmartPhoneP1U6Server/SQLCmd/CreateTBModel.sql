CREATE TABLE IF NOT EXISTS model (
    model_id int NOT NULL AUTO_INCREMENT,
    model_name varchar(45),
    make varchar(45),
    base_price float NOT NULL,
    PRIMARY KEY(model_id)
)
