-- Вставка даних у таблицю languages
INSERT INTO languages (code, name) VALUES
   ('en', 'English'),
   ('uk', 'Ukrainian'),
   ('fr', 'French');

-- Вставка даних у таблицю job_positions
INSERT INTO job_positions (name, description) VALUES
    ('Manager', 'Oversees operations'),
    ('Developer', 'Builds and maintains software'),
    ('Designer', 'Creates design and user interfaces');

-- Вставка даних у таблицю employees
INSERT INTO employees (first_name, last_name, middle_name, job_position_id, hire_date)VALUES
    ('John', 'Doe', 'A.', 1, '2020-01-15'),
    ('Jane', 'Smith', 'B.', 2, '2021-05-20'),
    ('Alice', 'Johnson', NULL, 3, '2019-11-30');

-- Вставка даних у таблицю employee_contacts
INSERT INTO employee_contacts (employee_id, phone_number, email)VALUES
    (1, '+123456789', 'john.doe@example.com'),
    (2, '+987654321', 'jane.smith@example.com'),
    (3, '+192837465', 'alice.johnson@example.com');

-- Вставка даних у таблицю employee_addresses
INSERT INTO employee_addresses (employee_id, address_line1, address_line2, city, country, postal_code) VALUES
    (1, '123 Main St', 'Apt 4B', 'New York', 'USA', '10001'),
    (2, '456 Oak Ave', NULL, 'Los Angeles', 'USA', '90001'),
    (3, '789 Pine Rd', 'Suite 300', 'Chicago', 'USA', '60601');

-- Вставка даних у таблицю products
INSERT INTO products (name, category_id, price) VALUES
    ('Coffee', 1, 4.99),
    ('Tea', 1, 3.50),
     ('Sandwich', 2, 5.99);

-- Вставка даних у таблицю translations
INSERT INTO translations (product_id, language_code, name) VALUES
     (1, 'en', 'Coffee'),
     (1, 'uk', 'Кава'),
     (1, 'fr', 'Café'),
     (2, 'en', 'Tea'),
     (2, 'uk', 'Чай'),
     (2, 'fr', 'Thé');

-- Вставка даних у таблицю statuses
INSERT INTO statuses (name, description) VALUES
     ('Pending', 'Order is pending'),
     ('Confirmed', 'Order is confirmed'),
     ('Shipped', 'Order is shipped');

-- Вставка даних у таблицю orders
INSERT INTO orders (order_date, order_amount, status_id) VALUES
      ('2023-12-30 10:00:00', 15.48, 1),
      ('2023-12-30 11:00:00', 9.99, 2),
      ('2023-12-30 12:00:00', 25.50, 3);

-- Вставка даних у таблицю order_details
INSERT INTO order_details (order_id, product_id, quantity) VALUES
     (1, 1, 2),
     (1, 2, 1),
     (2, 3, 3);

-- Вставка даних у таблицю shifts
INSERT INTO shifts (name, description, start_date, end_date, start_time, end_time, employee_id) VALUES
     ('Morning Shift', 'Work from 9 AM to 5 PM', '2023-12-30', '2023-12-30', '09:00:00', '17:00:00', 1),
     ('Evening Shift', 'Work from 5 PM to 1 AM', '2023-12-30', '2023-12-30', '17:00:00', '01:00:00', 2);

-- Вставка даних у таблицю work_schedules
INSERT INTO work_schedules (employee_id, start_work_day, end_work_day, start_time, end_time, is_holiday) VALUES
     (1, '2023-12-30', '2023-12-30', '09:00:00', '17:00:00', FALSE),
     (2, '2023-12-30', '2023-12-30', '17:00:00', '01:00:00', FALSE),
     (3, '2023-12-31', '2023-12-31', '10:00:00', '14:00:00', TRUE);