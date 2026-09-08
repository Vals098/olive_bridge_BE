# OliveBridge

Backend for OliveBridge, a B2C & B2B e-commerce platform connecting an Italian extra virgin olive oil producer with the Japanese market.

## About

OliveBridge is a web platform designed to connect one Italian extra virgin olive oil producer with customers and business buyers in Japan.

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