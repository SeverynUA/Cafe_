
INSERT INTO Languages (code, name) VALUES
('en', 'English'),
('uk', 'Ukrainian'),
('es', 'Spanish');

INSERT INTO Category (name) VALUES
('Beverages'),
('Snacks'),
('Desserts');

INSERT INTO SubCategory (name, category_Id) VALUES
('Coffee', 1), -- ID '1' відповідає "Beverages"
('Tea', 1),
('Chips', 2), -- ID '2' відповідає "Snacks"
('Cookies', 3); -- ID '3' відповідає "Desserts"

INSERT INTO Products (name, category_id, subCategory_id, price) VALUES
('Espresso', 1, 1, 2.99), -- Category: Beverages, SubCategory: Coffee
('Green Tea', 1, 2, 1.99), -- Category: Beverages, SubCategory: Tea
('Potato Chips', 2, 3, 1.49), -- Category: Snacks, SubCategory: Chips
('Chocolate Cookies', 3, 4, 3.99); -- Category: Desserts, SubCategory: Cookies

INSERT INTO Statuses (name, description) VALUES
('Pending', 'Order is pending confirmation'),
('Completed', 'Order has been completed'),
('Cancelled', 'Order has been cancelled');

INSERT INTO Customer (firstName, lastName, middleName, birthDate, email, phoneNumber, sale) VALUES
('John', 'Doe', 'A.', '1990-05-15', 'john.doe@example.com', '555-1234', 10.00),
('Jane', 'Smith', 'B.', '1985-08-20', 'jane.smith@example.com', '555-5678', 5.00);

INSERT INTO Orders (order_date, order_amount, status_id) VALUES
('2024-12-30 10:00:00', 15.97, 1), -- Status: Pending
('2024-12-30 12:00:00', 9.98, 2); -- Status: Completed

INSERT INTO Order_details (order_id, product_id, quantity) VALUES
(1, 1, 2), -- Order 1, Espresso x2
(1, 2, 1), -- Order 1, Green Tea x1
(2, 3, 3); -- Order 2, Potato Chips x3

INSERT INTO Job_positions (name, description) VALUES
('Barista', 'Prepares coffee and other beverages'),
('Confectioner', 'Prepares meals and snacks'),
('Cashier', 'Handles payments and customer orders');

INSERT INTO Employees (first_name, last_name, middle_name, job_position_id, hire_date) VALUES
('Alice', 'Johnson', 'C.', 1, '2022-01-15'), -- Barista
('Bob', 'Williams', 'D.', 2, '2023-03-10'); -- Confectioner

INSERT INTO Employee_contacts (employee_id, phone_number, email) VALUES
(1, '555-8765', 'alice.johnson@example.com'),
(2, '555-4321', 'bob.williams@example.com');

INSERT INTO Employee_addresses (employee_id, address_line1, address_line2, city, country, postal_code) VALUES
(1, '123 Coffee Lane', NULL, 'Brewtown', 'CoffeeLand', '12345'),
(2, '456 Snack Street', 'Apt 9', 'Snackville', 'SnackLand', '67890');

INSERT INTO Shifts (name, description, start_date, end_date, start_time, end_time, employee_id) VALUES
('Morning Shift', 'Morning working hours', '2024-12-30', '2024-12-30', '08:00:00', '12:00:00', 1),
('Afternoon Shift', 'Afternoon working hours', '2024-12-30', '2024-12-30', '13:00:00', '17:00:00', 2);

INSERT INTO Work_schedules (employee_id, start_work_day, end_work_day, start_time, end_time, is_holiday) VALUES
(1, '2024-12-30', '2024-12-30', '08:00:00', '12:00:00', FALSE),
(2, '2024-12-30', '2024-12-30', '13:00:00', '17:00:00', FALSE);
