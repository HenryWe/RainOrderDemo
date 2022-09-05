CREATE TABLE order_item (
                            id varchar(42) NOT NULL,
                            version numeric(18) NOT NULL,
                            date_created datetime NOT NULL,
                            last_updated datetime NOT NULL,
                            description text,
                            name varchar(50) NOT NULL,
                            code varchar(5) not null,
                            PRIMARY KEY (id)
);

INSERT INTO order_item(id, version, date_created, last_updated, description, name, code) VALUES ('123456789012345678901234567890123456789001', 0, '2022-05-26 00:00:00.0', '2022-05-26 00:00:00.0', 'Description of Order Item 1', 'Order Item 1', 'IDE01');
INSERT INTO order_item(id, version, date_created, last_updated, description, name, code) VALUES ('123456789012345678901234567890123456789002', 0, '2022-05-26 00:00:00.0', '2022-05-26 00:00:00.0', 'Description of Order Item 2', 'Order Item 2', 'IDE01');
INSERT INTO order_item(id, version, date_created, last_updated, description, name, code) VALUES ('123456789012345678901234567890123456789003', 0, '2022-05-26 00:00:00.0', '2022-05-26 00:00:00.0', 'Description of Order Item 3', 'Order Item 3', 'IDE01');
