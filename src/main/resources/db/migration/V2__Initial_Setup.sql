
USE `greenthumb_db`;


-- Inserting data into the role table
INSERT INTO `role` (`id`, `name`) VALUES
                                      (1, 'ROLE_ADMIN'),
                                      (2, 'ROLE_USER'),
                                      (3, 'ROLE_VOLUNTEER');



-- Associating users with roles in user_roles table
-- Ensure user IDs referenced here exist in the users table.
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
                                                    (1, 1),
                                                    (2, 2),
                                                    (3, 2),
                                                    (4, 2),
                                                    (5, 2),
                                                    (6, 3),
                                                    (7, 3);


INSERT INTO `community_garden` (`name`, `location`, `sunlight`, `soil_type`) VALUES
                                                                                 ('Garden A', 'Location 1', 'Full Sun', 'Loamy'),
                                                                                 ('Garden B', 'Location 2', 'Partial Shade', 'Clay'),
                                                                                 ('Garden C', 'Location 3', 'Full Sun', 'Sandy'),
                                                                                 ('Garden D', 'Location 4', 'Shade', 'Silt'),
                                                                                 ('Garden E', 'Location 5', 'Full Sun', 'Loamy'),
                                                                                 ('Garden F', 'Location 6', 'Partial Shade', 'Loamy'),
                                                                                 ('Garden G', 'Location 7', 'Full Sun', 'Clay');


-- Associating users with community gardens in user_community_garden table
INSERT INTO `user_community_garden` (`user_id`, `community_garden_id`) VALUES
                                                                           (1, 1),
                                                                           (2, 2),
                                                                           (3, 3),
                                                                           (2, 4),
                                                                           (3, 5),
                                                                           (4, 6),
                                                                           (5, 7);

-- Inserting data into the volunteers table
INSERT INTO `volunteers` (`name`, `contact_info`, `skills`, `availability`) VALUES
                                                                                      ( 'Alice', 'alice@example.com', 'Gardening', 'Weekends'),
                                                                                      ( 'Bob', 'bob@example.com', 'Soil Testing', 'Weekdays'),
                                                                                      ( 'Charlie', 'charlie@example.com', 'Composting', 'Weekends'),
                                                                                      ( 'David', 'david@example.com', 'Planting', 'Weekdays'),
                                                                                      ( 'Eve', 'eve@example.com', 'Water Conservation', 'Weekends'),
                                                                                      ( 'Frank', 'frank@example.com', 'Garden Design', 'Weekdays'),
                                                                                      ( 'Grace', 'grace@example.com', 'Community Outreach', 'Weekends');


INSERT INTO `volunteer_activity` (`activity_name`,`date`, `description`) VALUES
                                                                                    ('Tree Planting', '2024-06-10', 'Planting trees in the community garden'),
                                                                                    ('Soil Testing', '2024-06-12', 'Testing soil quality in different areas'),
                                                                                    ('Garden Cleanup', '2024-06-14', 'Cleaning and maintaining the community garden'),
                                                                                    ('Composting Workshop', '2024-06-16', 'Learning how to compost organic waste'),
                                                                                    ('Water Conservation', '2024-06-18', 'Workshop on water conservation techniques'),
                                                                                    ('Plant Sale', '2024-06-20', 'Selling plants to raise funds'),
                                                                                    ('Garden Tour', '2024-06-22', 'Touring local community gardens');

-- Associating volunteers with activities in volunteer_activity_volunteer table
INSERT INTO `volunteer_activity_volunteer` (`volunteer_activity_id`, `volunteer_id`) VALUES
                                                                                         (1, 1),
                                                                                         (2, 2),
                                                                                         (3, 3),
                                                                                         (4, 4),
                                                                                         (5, 5),
                                                                                         (6, 6),
                                                                                         (7, 7);

-- Inserting data into the plot table
INSERT INTO `plot` (`plot_name`, `sunlight`, `soil_type`, `community_garden_id`) VALUES
                                                                                           ('Plot 1', 'Full Sun', 'Loamy', 1),
                                                                                           ('Plot 2', 'Partial Shade', 'Clay', 2),
                                                                                           ('Plot 3', 'Full Sun', 'Sandy', 3),
                                                                                           ('Plot 4', 'Shade', 'Silt', 4),
                                                                                           ('Plot 5', 'Full Sun', 'Loamy', 5),
                                                                                           ('Plot 6', 'Partial Shade', 'Loamy', 6),
                                                                                           ('Plot 7', 'Full Sun', 'Clay', 7);



-- Inserting data into the crop_plans table
-- Inserting data into the crop_plans table
INSERT INTO `crop_plans` (`crop_name`, `planting_date`, `harvest_date`, `plot_id`, `user_id`) VALUES
                                                                                                        ('Tomatoes', '2024-06-01', '2024-09-01', 1, 1),
                                                                                                        ('Lettuce', '2024-06-05', '2024-07-20', 2, 2),
                                                                                                        ('Carrots', '2024-06-10', '2024-08-25', 3, 3),
                                                                                                        ('Peppers', '2024-06-15', '2024-09-05', 4, 1),
                                                                                                        ('Cucumbers', '2024-06-20', '2024-08-30', 5, 1),
                                                                                                        ('Beans', '2024-06-25', '2024-09-10', 6, 2),
                                                                                                        ('Spinach', '2024-06-30', '2024-08-15', 7, 3);

-- Inserting data into the knowledge_shares table
INSERT INTO `knowledge_shares` (`title`, `content`, `user_id`) VALUES
                                                                         ('Tomato Growing Tips', 'How to grow the best tomatoes.', 1),
                                                                         ('Organic Pest Control', 'Natural ways to control garden pests.', 2),
                                                                         ('Soil Preparation', 'Preparing soil for planting.', 3),
                                                                         ('Composting Basics', 'How to start composting.', 1),
                                                                         ('Watering Techniques', 'Efficient watering techniques.', 2),
                                                                         ('Seed Saving', 'How to save seeds for next season.', 3),
                                                                         ('Garden Planning', 'Planning your garden layout.', 1);


-- Inserting data into the local_partners table
INSERT INTO `local_partners` (`name`, `service`, `email`) VALUES
                                                              ('Partner A', 'Soil Testing', 'partnera@example.com'),
                                                              ('Partner B', 'Garden Supplies', 'partnerb@example.com'),
                                                              ('Partner C', 'Organic Fertilizers', 'partnerc@example.com'),
                                                              ('Partner D', 'Pest Control', 'partnerd@example.com'),
                                                              ('Partner E', 'Watering Systems', 'partnere@example.com');

-- Inserting data into the resource_exchanges table
INSERT INTO `resource_exchanges` (`resource_type`, `description`, `local_partner_id`,`quantity`) VALUES
                                                                                          ('Soil', 'High-quality loamy soil', 1,1),
                                                                                          ('Fertilizer', 'Organic compost', 2,2),
                                                                                          ('Pest Control', 'Natural pesticide', 3,3),
                                                                                          ('Seeds', 'Organic vegetable seeds', 4,4),
                                                                                          ('Watering System', 'Drip irrigation system', 5,5);

-- Associating users with resource exchanges in resource_exchange_user table
INSERT INTO `resource_exchange_user` (`resource_exchange_id`, `user_id`) VALUES
                                                                             (1, 1),
                                                                             (2, 2),
                                                                             (3, 3);


-- Inserting data into the soil_pest_data table
INSERT INTO `soil_pest_data` (`resource_type`, `description`, `community_garden_id`) VALUES
                                                                                         ('Pest', 'Aphid infestation detected', 1),
                                                                                         ('Soil', 'Nutrient deficiency in soil', 2),
                                                                                         ('Pest', 'Fungal growth on plants', 3),
                                                                                         ('Soil', 'Low soil pH level', 4),
                                                                                         ('Pest', 'Presence of nematodes', 5);

-- Inserting data into the weather_data table
INSERT INTO `weather_data` (`date`, `temperature`, `weather_condition`, `community_garden_id`) VALUES
('2024-06-01', 30.5, 'Sunny', 1),
('2024-06-02', 28.4, 'Cloudy', 2),
('2024-06-03', 26.8, 'Rainy', 3),
('2024-06-04', 32.0, 'Sunny', 4),
('2024-06-05', 29.1, 'Partly Cloudy', 5);

-- Inserting data into the users table with volunteer_activity_id values

