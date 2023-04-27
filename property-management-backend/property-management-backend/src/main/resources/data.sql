--Insert users (Admins, Owners, and Customers)
INSERT INTO users (id, username, password, email, role) VALUES (1, 'admin1', 'admin123', 'admin1@example.com', 'ADMIN');
INSERT INTO users (id, username, password, email, role) VALUES (2, 'admin2', 'admin123', 'admin2@example.com', 'ADMIN');
INSERT INTO users (id, username, password, email, role) VALUES (3, 'owner1', 'owner123', 'owner1@example.com', 'OWNER');
INSERT INTO users (id, username, password, email, role) VALUES (4, 'owner2', 'owner123', 'owner2@example.com', 'OWNER');
INSERT INTO users (id, username, password, email, role) VALUES (5, 'customer1', 'customer123', 'customer1@example.com', 'CUSTOMER');
INSERT INTO users (id, username, password, email, role) VALUES (6, 'customer2', 'customer123', 'customer2@example.com', 'CUSTOMER');

-- Insert properties
INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (1, 'Beautiful House', 'House', 4, 'Single Family', 'New York', 500000, 3, 'AVAILABLE');
INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (2, 'Cozy Apartment', 'Apartment', 2, 'Multi-Family', 'Boston', 300000, 3, 'AVAILABLE');
INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (3, 'Spacious Condo', 'Condo', 3, 'Townhouse', 'Chicago', 400000, 4, 'PENDING');
INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (4, 'Lakefront Cottage', 'Cottage', 2, 'Single Family', 'Seattle', 350000, 4, 'CONTINGENT');
INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, owner_id, status) VALUES (5, 'Luxury Villa', 'Villa', 5, 'Single Family', 'Miami', 1000000, 3, 'AVAILABLE');


Insert saved lists(ManyToOne)
INSERT INTO saved_list (id, user_id, name) VALUES (1, 5, 'My Favorite Properties');
INSERT INTO saved_list (id, user_id, name) VALUES (2, 5, 'Holiday Homes');
INSERT INTO saved_list (id, user_id, name) VALUES (3, 6, 'Investment Properties');
INSERT INTO saved_list (id, user_id, name) VALUES (4, 6, 'Dream Homes');
INSERT INTO saved_list (id, user_id, name) VALUES (5, 6, 'Rental Properties');

-- Insert saved_list_property(ManyToMany)
INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (1, 1);
INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (1, 2);
INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (2, 3);
INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (2, 4);
INSERT INTO saved_list_property (saved_list_id, property_id) VALUES (3, 5);

-- Insert applications
INSERT INTO application (id, customer_id, property_id, application_type) VALUES (1, 5, 1, 'BUY');
INSERT INTO application (id, customer_id, property_id, application_type) VALUES (2, 5, 2, 'RENT');
INSERT INTO application (id, customer_id, property_id, application_type) VALUES (3, 6, 3, 'BUY');
INSERT INTO application (id, customer_id, property_id, application_type) VALUES (4, 6, 4, 'RENT');
INSERT INTO application (id, customer_id, property_id, application_type) VALUES (5, 6, 5, 'BUY');

-- Insert messages
INSERT INTO message (id, sender_id, recipient_id, content) VALUES (1, 5, 3, 'Is the property still available?');
INSERT INTO message (id, sender_id, recipient_id, content) VALUES (2, 6, 3, 'Can I schedule a visit?');
INSERT INTO message (id, sender_id, recipient_id, content) VALUES (3, 6, 4, 'What is the pet policy?');
INSERT INTO message (id, sender_id, recipient_id, content) VALUES (4, 5, 4, 'Are utilities included?');
INSERT INTO message (id, sender_id, recipient_id, content) VALUES (5, 6, 3, 'Is the property furnished?');

-- Insert offers
INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (1, 5, 1, 490000, 'PENDING');
INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (2, 5, 2, 290000, 'ACCEPTED');
INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (3, 6, 3, 390000, 'REJECTED');
INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (4, 6, 4, 330000, 'PENDING');
INSERT INTO offer (id, customer_id, property_id, amount, status) VALUES (5, 6, 5, 950000, 'ACCEPTED');
