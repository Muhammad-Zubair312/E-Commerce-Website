# E-Commerce Website

A comprehensive **Java Servlet-based E-Commerce Management System** built with Jakarta Servlets, MySQL database, and deployed on Apache Tomcat. This application provides role-based access control for both administrators and users, featuring complete product management, inventory control, shopping cart functionality, and sales reporting.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### User Authentication
- **User Registration** - New users can sign up with email and password
- **Secure Login** - Authentication with role-based access (Admin/User)
- **Session Management** - HTTP session tracking for logged-in users
- **Logout Functionality** - Secure session termination

### Admin Features
- **Product Management**
  - Add new products with details (name, quantity, price, category, description)
  - Update product information (price, quantity, category, description)
  - Delete products from inventory
  - View all products in the system
  - Search for specific products
- **Inventory Management**
  - Update stock levels
  - Track product availability
  - Inventory status monitoring
- **Sales Reporting**
  - Generate comprehensive sales reports
  - View cart/order history
  - Track customer purchases

### User Features
- **Product Browsing**
  - View all available products
  - Search for specific products
  - Check product details and availability
- **Shopping Cart**
  - Add products to cart
  - Automatic inventory deduction
  - Quantity validation (prevents over-ordering)
  - Process ID tracking for orders

## 🛠 Technology Stack

### Backend
- **Java** - Core programming language
- **Jakarta Servlets** - Web application framework
- **JDBC** - Database connectivity
- **Apache Tomcat 11.0.1** - Application server

### Frontend
- **HTML** - User interface structure
- **HTTP Forms** - Data submission

### Database
- **MySQL 5.x** - Relational database
- **MySQL Connector/J 5.0.8** - JDBC driver

### Architecture
- **DAO Pattern** - Data Access Object for database operations
- **MVC Pattern** - Model-View-Controller separation
- **Session-based Authentication** - HTTP session management

## 📁 Project Structure

```
E-Commerce Website/
├── WEB-INF/
│   ├── classes/
│   │   ├── ShopDAO.java              # Database Access Object
│   │   ├── Login.java                # Login servlet
│   │   ├── Signup.java               # User registration servlet
│   │   ├── Addproduct.java           # Add product servlet
│   │   ├── Updateproduct.java        # Update product servlet
│   │   ├── UpdateHandler.java        # Update operation handler
│   │   ├── Updateprice.java          # Update product price
│   │   ├── Updatequantity.java       # Update product quantity
│   │   ├── Updatecategory.java       # Update product category
│   │   ├── Updatedescription.java    # Update product description
│   │   ├── Deleteproduct.java        # Delete product servlet
│   │   ├── Getproduct.java           # Retrieve all products
│   │   ├── Searchproduct.java        # Search products servlet
│   │   ├── Updateinventory.java      # Inventory update servlet
│   │   ├── Inventoryhandler.java     # Inventory operations
│   │   ├── Addcart.java              # Add to cart servlet
│   │   ├── Cart.java                 # Cart display servlet
│   │   ├── Getreport.java            # Sales report servlet
│   │   └── Logout.java               # Logout servlet
│   ├── lib/
│   │   └── mysql-connector-java-5.0.8-bin.jar
│   └── web.xml                       # Servlet configuration
├── Login.html                        # Login page
├── Signup.html                       # Registration page
├── admininterface.html               # Admin dashboard
├── userinterface.html                # User dashboard
├── Addproduct.html                   # Add product form
├── Updateproduct.html                # Update product form
├── UpdateInventory.html              # Inventory update form
├── Deleteproduct.html                # Delete product form
├── Getallproduct.html                # View all products
├── Searchproduct.html                # Search product form
├── Addcart.html                      # Add to cart form
└── Getreport.html                    # Sales report page
```

## 🗄 Database Schema

The application uses a MySQL database named `project_data` with the following tables:

### `data` Table (User Authentication)
```sql
CREATE TABLE data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    Usertype VARCHAR(50) DEFAULT 'user'  -- 'admin' or 'user'
);
```

### `product` Table (Product Information)
```sql
CREATE TABLE product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Productname VARCHAR(255) UNIQUE NOT NULL,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Category VARCHAR(100),
    Description TEXT
);
```

### `cart` Table (Order Tracking)
```sql
CREATE TABLE cart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Product VARCHAR(255) NOT NULL,
    Quantity INT NOT NULL,
    ProcessID INT NOT NULL,
    Delivery VARCHAR(255),
    FOREIGN KEY (Username) REFERENCES data(username)
);
```

## 🚀 Installation & Setup

### Prerequisites
- **Java Development Kit (JDK)** 8 or higher
- **Apache Tomcat** 11.0.1 or compatible version
- **MySQL Server** 5.x or higher
- **MySQL Connector/J** 5.0.8 (included in `/WEB-INF/lib/`)

### Step 1: Database Setup
1. Install and start MySQL server
2. Create the database and tables:

```sql
-- Create database
CREATE DATABASE project_data;
USE project_data;

-- Create users table
CREATE TABLE data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    Usertype VARCHAR(50) DEFAULT 'user'
);

-- Create products table
CREATE TABLE product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Productname VARCHAR(255) UNIQUE NOT NULL,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Category VARCHAR(100),
    Description TEXT
);

-- Create cart table
CREATE TABLE cart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Product VARCHAR(255) NOT NULL,
    Quantity INT NOT NULL,
    ProcessID INT NOT NULL,
    Delivery VARCHAR(255)
);

-- Insert default admin user (password: admin123)
INSERT INTO data (username, password, Usertype) 
VALUES ('admin@shop.com', 'admin123', 'admin');
```

### Step 2: Database Configuration
Update the database connection details in `ShopDAO.java`:

```java
String url = "jdbc:mysql://127.0.0.1:3307/project_data";
this.con = DriverManager.getConnection(url, "root", "your_password");
```

**Update:**
- Port number (default: `3306`, currently set to `3307`)
- Username (replace `"root"` with your MySQL username)
- Password (replace `"zubair"` with your MySQL password)

### Step 3: Compile Java Classes
Navigate to the project directory and compile all Java servlets:

```bash
cd "WEB-INF/classes"
javac -cp "../../lib/mysql-connector-java-5.0.8-bin.jar;$CATALINA_HOME/lib/servlet-api.jar" *.java
```

### Step 4: Deploy on Tomcat
1. Copy the entire `E-Commerce Website` folder to Tomcat's `webapps` directory:
   ```
   apache-tomcat-11.0.1/webapps/E-Commerce Website/
   ```

2. Start Tomcat server:
   ```bash
   # Windows
   cd apache-tomcat-11.0.1\bin
   startup.bat
   
   # Linux/Mac
   cd apache-tomcat-11.0.1/bin
   ./startup.sh
   ```

3. Access the application at:
   ```
   http://localhost:8080/E-Commerce%20Website/Login.html
   ```

## 💻 Usage

### For Users

1. **Sign Up**
   - Navigate to `Signup.html`
   - Enter your email and password
   - System creates a user account with role 'user'

2. **Login**
   - Navigate to `Login.html`
   - Enter your credentials
   - Upon successful login, redirected to user dashboard

3. **Browse Products**
   - View all products via `Getallproduct.html`
   - Search for specific products using `Searchproduct.html`

4. **Add to Cart**
   - Select desired products
   - Specify quantity
   - System validates stock availability before adding

### For Administrators

1. **Login as Admin**
   - Use admin credentials: `admin@shop.com` / `admin123`
   - Redirected to admin dashboard (`admininterface.html`)

2. **Product Management**
   - **Add Product**: Fill product details (name, quantity, price, category, description)
   - **Update Product**: Modify existing product information
   - **Delete Product**: Remove products from inventory
   - **View Products**: See all products in system

3. **Inventory Control**
   - Update stock levels via `UpdateInventory.html`
   - System prevents negative inventory

4. **Generate Reports**
   - Access sales reports showing all cart/order data
   - View customer purchase history

## 🔌 API Endpoints

All endpoints accept `POST` requests with form data:

### Authentication
- `POST /signup` - User registration
- `POST /login` - User authentication
- `POST /Logout` - Session termination

### Product Management (Admin)
- `POST /addproduct` - Add new product
- `POST /updateProduct` - Update product details
- `POST /Updateprice` - Update product price
- `POST /Updatequantity` - Update product quantity
- `POST /Updatecategory` - Update product category
- `POST /Updatedescription` - Update product description
- `POST /delete` - Delete product

### Product Browsing
- `POST /getproduct` - Get all products
- `POST /searchproduct` - Search for products

### Inventory
- `POST /updateinventory` - Update inventory levels
- `POST /Inventoryhandler` - Handle inventory operations

### Shopping Cart
- `POST /cart` - Add items to cart
- `POST /Cart` - View cart contents

### Reporting (Admin)
- `POST /getreport` - Generate sales reports

## 🎨 Screenshots

*Add screenshots of your application here:*
- Login page
- Admin dashboard
- User dashboard
- Product listing
- Add product form
- Shopping cart
- Sales report

## 🔮 Future Enhancements

- [ ] **Enhanced Security**
  - Password encryption (BCrypt/SHA-256)
  - HTTPS support
  - CSRF protection
  - Input validation and sanitization

- [ ] **UI/UX Improvements**
  - Responsive design with CSS frameworks (Bootstrap/Tailwind)
  - AJAX for asynchronous operations
  - Modern frontend framework (React/Vue.js)
  - Image upload for products

- [ ] **Feature Additions**
  - Product categories and filtering
  - Payment gateway integration
  - Order tracking system
  - Email notifications
  - Product reviews and ratings
  - Wishlist functionality
  - Advanced search with filters

- [ ] **Performance Optimization**
  - Connection pooling (HikariCP)
  - Caching (Redis)
  - Pagination for product listing
  - Database indexing

- [ ] **Testing**
  - JUnit tests for business logic
  - Integration tests
  - Load testing

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java naming conventions
- Comment complex logic
- Test thoroughly before submitting
- Update documentation for new features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

Created as a semester project demonstrating:
- Java Servlet development
- Database integration with JDBC
- Session management
- Role-based access control
- E-commerce system architecture

---

## 📞 Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Contact: [Your Email]

## ⭐ Acknowledgments

- Apache Tomcat team for the excellent servlet container
- MySQL team for the robust database system
- Jakarta EE community for servlet specifications

---

**Note**: This is an educational project. For production deployment, implement proper security measures including password hashing, HTTPS, input validation, and SQL injection prevention using PreparedStatements (already partially implemented).
