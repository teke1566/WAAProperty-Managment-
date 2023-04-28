
INSERT INTO users (email, name, password)
VALUES ('admin@gmail.com', 'Admin', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (email, name, password)
VALUES ('source@gmail.com', 'abe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (email, name, password)
VALUES ('foo@gmail.com', 'Mtek', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2');
--123
INSERT INTO role (role)
VALUES ('ADMIN');
INSERT INTO role (role)
VALUES ('OWNER');
INSERT INTO role (role)
VALUES ('CUSTOMER');


INSERT INTO users_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO users_role (user_id, role_id)
VALUES (2, 2);
INSERT INTO users_role (user_id, role_id)
VALUES (3, 3);
-- Address data
-- INSERT INTO address (id, street, city, state, zip_code)
-- VALUES (100, '123 Main St', 'New York', 'NY', '10001'),
--        (101, '456 Central Ave', 'Los Angeles', 'CA', '90001'),
--        (102, '789 Park Ave', 'Chicago', 'IL', '60601'),
--        (103, '321 Oak St', 'Houston', 'TX', '77001'),
--        (104, '654 Elm St', 'Miami', 'FL', '33101');

-- FavoriteList data
INSERT INTO saved_list (id, name, user_id)
VALUES (100, 'Johns Favorite Properties', 1),
       (101, 'Janes Favorite Properties', 2),
       (102, 'Bobs Favorite Properties', 3);



--property



INSERT INTO property (id, property_name, property_type, description, number_of_rooms, rent_Amount, security_deposit_amount, number_of_bath_rooms, image_url, price, street, city, state, zip_code, posted_date, status, owner_id, favorite_list_id)
VALUES
    (100, 'Cozy Apartment', 'Apartment', 'A cozy apartment with a great view of the city', 2, 1000.00, 2000.00, 1, 'https://www.example.com/images/1.jpg', 120000.00, '123 Main St', 'boston', 'CA', '12345', '2022-01-01', 'AVAILABLE', 1, 100),
    (101, 'Spacious Condo', 'Condo', 'A spacious condo with plenty of room for a family', 3, 2000.00, 3000.00, 2, 'https://www.example.com/images/2.jpg', 250000.00, '456 Elm St', 'miami', 'fl', '12345', '2022-02-15', 'AVAILABLE', 2, 101),
    (102, 'Luxury Home', 'House', 'A luxurious home with all the amenities', 5, 5000.00, 10000.00, 4, 'https://www.example.com/images/3.jpg', 750000.00, '789 Oak St', 'Atlanta', 'CA', '12345', '2022-03-30', 'AVAILABLE', 3, 102),

    (103, 'Spacious Condo', 'Condo', 'A spacious condo with plenty of room for a family', 3, 2000.00, 3000.00, 2, 'https://www.example.com/images/2.jpg', 250000.00, '456 Elm St', 'miami', 'fl', '12345', '2022-02-15', 'AVAILABLE', 2, 101),
    (104, 'Downtown Loft', 'Apartment', 'A modern loft apartment in the heart of the city', 1, 1500.00, 3000.00, 1, 'https://www.example.com/images/4.jpg', 180000.00, '10 Main St', 'atlanta', 'CA', '54321', '2022-04-15', 'AVAILABLE', 1, 100),
    (105, 'Lakefront Cabin', 'Cabin', 'A cozy cabin on the shores of a peaceful lake', 2, 800.00, 1600.00, 1, 'https://www.example.com/images/5.jpg', 100000.00, '1234 Lakeside Dr', 'dc', 'CA', '54321', '2022-05-01', 'AVAILABLE', 3, 102);

-- -- Property data
-- INSERT INTO property (id, property_name, property_type, description, number_of_rooms, rent_Amount, security_deposit_amount, number_of_bath_rooms, image_url, price, posted_date, status, id_address, owner_id, favotite_list_id)
-- VALUES (100, 'Luxury Apartment', 'APARTMENT', 'A beautiful luxury apartment in a great location.', 3, 2500.00, 5000.00, 2, 'image_url_1', 350000.00, '2023-04-20', 'AVAILABLE', 100, 1, 100),
--        (101, 'Cozy Condo', 'CONDO', 'A cozy condo with modern amenities.', 2, 1800.00, 3600.00, 1, 'image_url_2', 250000.00, '2023-04-22', 'AVAILABLE', 101, 2, 101),
--        (102, 'Spacious House', 'HOUSE', 'A spacious house with a large backyard.', 4, 3200.00, 6400.00, 3, 'image_url_3', 450000.00, '2023-04-24', 'AVAILABLE', 102, 3, 102);

-- Offer data
INSERT INTO offer (id, property_id, customer_id, status, amount)
VALUES (100, 100, 1, 'PENDING', 340000.00),
       (101, 101, 2, 'PENDING', 245000.00),
       (102, 102, 3, 'PENDING', 440000.00);
-- -- ROLES

---- Insert properties
--INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (1, 'Beautiful House', 'House', 4, 'Single Family', 'New York', 500000, 3, 'AVAILABLE');
--INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (2, 'Cozy Apartment', 'Apartment', 2, 'Multi-Family', 'Boston', 300000, 3, 'AVAILABLE');
--INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (3, 'Spacious Condo', 'Condo', 3, 'Townhouse', 'Chicago', 400000, 4, 'PENDING');
--INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (4, 'Lakefront Cottage', 'Cottage', 2, 'Single Family', 'Seattle', 350000, 4, 'CONTINGENT');
--INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (5, 'Luxury Villa', 'Villa', 5, 'Single Family', 'Miami', 1000000, 3, 'AVAILABLE');


--Insert saved lists(ManyToOne)
--INSERT INTO saved_list (id, user_id, name) VALUES (1, 5, 'My Favorite Properties');
--INSERT INTO saved_list (id, user_id, name) VALUES (2, 5, 'Holiday Homes');
--INSERT INTO saved_list (id, user_id, name) VALUES (3, 6, 'Investment Properties');
--INSERT INTO saved_list (id, user_id, name) VALUES (4, 6, 'Dream Homes');
--INSERT INTO saved_list (id, user_id, name) VALUES (5, 6, 'Rental Properties');

---- Insert saved_list_property(ManyToMany)
--INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (1, 1);
--INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (1, 2);
--INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (2, 3);
--INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (2, 4);
--INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (3, 5);
--
---- Insert applications
--INSERT INTO application (id, customer_id, property_id, application_type) VALUES (1, 5, 1, 'BUY');
--INSERT INTO application (id, customer_id, property_id, application_type) VALUES (2, 5, 2, 'RENT');
--INSERT INTO application (id, customer_id, property_id, application_type) VALUES (3, 6, 3, 'BUY');
--INSERT INTO application (id, customer_id, property_id, application_type) VALUES (4, 6, 4, 'RENT');
--INSERT INTO application (id, customer_id, property_id, application_type) VALUES (5, 6, 5, 'BUY');
--
---- Insert messages
--INSERT INTO message (id, sender_id, recipient_id, content) VALUES (1, 5, 3, 'Is the property still available?');
--INSERT INTO message (id, sender_id, recipient_id, content) VALUES (2, 6, 3, 'Can I schedule a visit?');
--INSERT INTO message (id, sender_id, recipient_id, content) VALUES (3, 6, 4, 'What is the pet policy?');
--INSERT INTO message (id, sender_id, recipient_id, content) VALUES (4, 5, 4, 'Are utilities included?');
--INSERT INTO message (id, sender_id, recipient_id, content) VALUES (5, 6, 3, 'Is the property furnished?');
--
---- Insert offers
--INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (1, 5, 1, 490000, 'PENDING');
--INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (2, 5, 2, 290000, 'ACCEPTED');
--INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (3, 6, 3, 390000, 'REJECTED');
--INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (4, 6, 4, 330000, 'PENDING');
--INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (5, 6, 5, 950000, 'ACCEPTED');
--
--