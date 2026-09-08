# OliveBridge

OliveBridge is a digital showcase and e-commerce platform for an Italian extra virgin olive oil producer from Puglia, with a focus on the Japanese market.

🌿 **Live Demo:** https://olive-bridge.netlify.app

🔗 **Backend API:** https://olivebridgebe-production.up.railway.app

## Links

- **Live Demo:** https://olive-bridge.netlify.app
- **Frontend Repository:** https://github.com/VALS098/olive_bridge_FE
- **Backend Repository:** https://github.com/VALS098/olive_bridge_BE

## About

The platform supports both B2C and B2B interactions, including:

- Product browsing
- Product management
- Online orders
- Guest checkout
- User accounts
- Saved addresses
- Favourite products
- Business inquiries
- Product sample requests
- Admin management

---

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Spring Security
- JWT
- Lombok

---

## Frontend

The frontend is developed separately with:

- React
- TypeScript
- Vite
- Redux Toolkit
- React Bootstrap
- Bootstrap

[OliveBridge Frontend Repository](https://github.com/Vals098/olive_bridge_FE)

---

## API

The backend exposes REST API endpoints for:

- Authentication
- Users
- Products
- Product variants
- Categories
- Technical information
- Orders
- Addresses
- Favourites
- Sample requests
- Business inquiries
- Admin operations

API endpoints are protected according to the user's authentication status and role.

---

## Authentication and Authorization

Authentication is implemented using JSON Web Tokens (JWT).

The application supports:

- Guest users
- Individual users
- Business users
- Administrators

The `ADMIN` and `BUYER` roles are used for authorization, while `INDIVIDUAL` and `BUSINESS` define the user's account type.

Business-only operations are validated on the backend.

---

## Database

The application uses PostgreSQL as its relational database.

Data persistence is handled through Spring Data JPA and Hibernate.

The main entities include:

- User
- Role
- Product
- Product Variant
- Category
- Technical Information
- Order
- Address
- Favourite
- Sample Request
- Business Inquiry

---

## Installation

Clone the repository:

```bash
git clone https://github.com/Vals098/olive_bridge_BE.git
```

---

## Environment Variables

The application uses environment-specific configuration for sensitive information such as:

- Database credentials
- JWT configuration
- External service credentials

Sensitive configuration files and credentials must not be committed to the repository.

Configure the required variables according to the local development environment before starting the application.

---

## API Documentation

API requests can be tested using tools such as Postman.

The project does not currently include an automated API documentation interface such as Swagger/OpenAPI.

---

## Frontend Integration

The backend is designed to work with the OliveBridge React frontend.

The frontend communicates with the backend through REST API requests.

Frontend repository:

[OliveBridge Frontend](https://github.com/Vals098/olive_bridge_FE.git)

---

## Development

The project was developed incrementally using Git feature branches and milestone releases.

The `main` branch represents the stable version of the application, while new features were developed through dedicated feature branches.

---

## Future Improvements

Possible future developments include:

- API documentation with Swagger/OpenAPI
- Email notifications
- Payment integration
- Production deployment
- Additional product management features

---

## Project

**OliveBridge**

Full Stack Web Development Capstone Project

Frontend developed with React, TypeScript, Redux Toolkit and React Bootstrap.

---

## Author

Valeria Farinosi
