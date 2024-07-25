---
title: Spring Security 6.3.3 with JWT 12.5 and Spring Boot 3.1
---

# Spring Security 6.3.3 with JWT 12.5 and Spring Boot 3.1

This project demonstrates the implementation of Spring Security 6.3.3 using JWT 12.5 and Spring Boot 3.1. It focuses on authenticating users with their email addresses and passwords.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Endpoints](#endpoints)
- [Authentication Flow](#authentication-flow)
- [Usage](#usage)

## Features

- User authentication using email and password.
- JWT token generation and validation.
- Secure endpoints with role-based access control.
- Stateless session management using JWT.
- Clear implementation of Spring Security 6.3.3 flow.

## Technologies Used

- Spring Boot 3.1
- Spring Security 6.3.3
- JWT 12.5
- JPA (Hibernate)
- SQL Database

## Endpoints

- `POST /register`: Register a new user.
- `POST /userlogin`: Authenticate user and get JWT token.
- `GET /secure/fetch-user`: Access secure endpoint (requires authentication) to fetch the Authenticated user's data.


## Authentication Flow

### User Registration:

- Users register by providing their email, password, and other details.
- Passwords are hashed using `BCryptPasswordEncoder`.

### User Login:

- Users log in by providing their email and password.
- Upon successful authentication, a JWT token is generated and returned to the client.

### Accessing Secure Endpoints:

- Clients include the JWT token in the `Authorization` header of requests to secure endpoints.
- The server validates the JWT token and grants access to the requested resource if the token is valid.


## Usage

- Register a New User
- Register a New User
- Register a New User

## Created By

- Created By Nilam Jyoti Sharma
