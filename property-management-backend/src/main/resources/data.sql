-- data.sql
-- Users
INSERT INTO users (id, username, password, email, role) VALUES
                                                            (1, 'john_doe', 'password123', 'john.doe@example.com', 'CUSTOMER'),
                                                            (2, 'jane_doe', 'password456', 'jane.doe@example.com', 'OWNER'),
                                                            (3, 'alice_smith', 'password789', 'alice.smith@example.com', 'CUSTOMER'),
                                                            (4, 'bob_jones', 'password012', 'bob.jones@example.com', 'OWNER'),
                                                            (5, 'carol_wilson', 'password345', 'carol.wilson@example.com', 'ADMIN');

-- Saved Lists
INSERT INTO saved_list (id, name, user_id) VALUES
                                               (1, 'John Favorites', 1),
                                               (2, 'Jane Favorites', 2),
                                               (3, 'Alice Favorites', 3),
                                               (4, 'Bob Favorites', 4),
                                               (5, 'Carol Favorites', 5);

-- Properties
INSERT INTO property (id, name, property_type, num_rooms, home_type, location, price, status, owner_id, saved_list_id) VALUES
                                                                                                                           (1, 'Beautiful Apartment', 'Apartment', 3, 'Condo', 'New York', 350000, 'available', 1, 1),
                                                                                                                           (2, 'Cozy House', 'House', 4, 'Detached', 'Los Angeles', 550000, 'available', 2, 2),
                                                                                                                           (3, 'Spacious Loft', 'Loft', 2, 'Open Concept', 'Chicago', 450000, 'available', 3, 3),
                                                                                                                           (4, 'Modern Townhouse', 'Townhouse', 4, 'Semi-Detached', 'Miami', 500000, 'available', 4, 4),
                                                                                                                           (5, 'Charming Cabin', 'Cabin', 3, 'Log', 'Denver', 300000, 'available', 5, 5);

-- Offers
INSERT INTO offer (id, property_id, customer_id, status, amount) VALUES
                                                                     (1, 1, 2, 'pending', 340000),
                                                                     (2, 2, 1, 'contingent', 520000),
                                                                     (3, 3, 4, 'available', 430000),
                                                                     (4, 4, 5, 'pending', 480000),
                                                                     (5, 5, 3, 'contingent', 290000);

-- Messages
INSERT INTO message (id, sender_id, recipient_id, content, subject) VALUES
                                                                        (1, 1, 2, 'Hi, I am interested in your property.', 'Property Inquiry'),
                                                                        (2, 2, 1, 'Hello, thank you for your interest. Let me know if you have any questions.', 'Re: Property Inquiry'),
                                                                        (3, 3, 4, 'Is the loft still available?', 'Loft Availability'),
                                                                        (4, 4, 5, 'I have a question about the townhouse.', 'Townhouse Question'),
                                                                        (5, 5, 1, 'Can we schedule a viewing for the cabin?', 'Cabin Viewing');

-- Applications
INSERT INTO application (id, application_type, customer_id, property_id) VALUES
                                                                                     (1, 'RENT', 1, 1),
                                                                                     (2, 'BUY', 2, 2),
                                                                                     (3, 'RENT', 3, 3),
                                                                                     (4, 'BUY', 4, 4),
                                                                                     (5, 'RENT', 5, 5);

