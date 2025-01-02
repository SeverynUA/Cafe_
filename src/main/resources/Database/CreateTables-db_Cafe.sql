CREATE TABLE Languages
(
    code VARCHAR(5) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE SubCategory (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Category(id) ON DELETE CASCADE
);

CREATE TABLE Products (
    id BIGSERIAL  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    subCategory_id BIGINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Category(id) ON DELETE CASCADE,
    FOREIGN KEY (subCategory_id) REFERENCES SubCategory(id) ON DELETE CASCADE
);


CREATE TABLE Translations (
    id BIGSERIAL PRIMARY KEY ,
    product_id BIGINT NOT NULL,
    language_code VARCHAR(5) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (language_code) REFERENCES Languages(code) ON DELETE CASCADE
);


CREATE TABLE Customer(
    id BIGSERIAL  PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    middleName VARCHAR(255),
    birthDate DATE NOT NULL ,
    email VARCHAR(255),
    phoneNumber VARCHAR(255) NOT NULL,
    sale DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Statuses (
    id BIGSERIAL  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Job_positions (
    id BIGSERIAL  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Employees (
    id BIGSERIAL  PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    job_position_id BIGINT NOT NULL,
    hire_date DATE NOT NULL,
    FOREIGN KEY (job_position_id) REFERENCES Job_positions(id) ON DELETE CASCADE
);

CREATE TABLE Orders (
   id BIGSERIAL  PRIMARY KEY,
   order_date TIMESTAMP NOT NULL,
   order_amount DECIMAL(10, 2) NOT NULL,
   status_id BIGINT NOT NULL,
   employee_id BIGINT  NOT NULL,
   customer_id BIGINT  NOT NULL,
   FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE,
   FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE,
   FOREIGN KEY (status_id) REFERENCES Statuses(id) ON DELETE CASCADE
);

CREATE TABLE Order_Details (
    id BIGSERIAL  PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(id) ON DELETE CASCADE
);

CREATE TABLE Employee_contacts (
    id BIGSERIAL  PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    phone_number VARCHAR(15),
    email VARCHAR(255),
    FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE
);

CREATE TABLE Employee_addresses (
    id BIGSERIAL  PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    postal_code VARCHAR(20),
    FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE
);

CREATE TABLE Shifts (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    employee_id BIGINT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE
);

CREATE TABLE Work_schedules (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    start_work_day DATE NOT NULL,
    end_work_day DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_holiday BOOLEAN NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE
);