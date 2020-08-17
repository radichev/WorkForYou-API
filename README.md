# WorkForYou-API

WorkForYou-API is a REST API for freelance platform which provide CRUD functionality. The main focus is to make it easier for the user to publish and find a job. Every registered user can publish a job and buy existing one. The published and bought jobs are stored in his profile where he can access them. A user can update or delete job if he is the author of it. The application has auditing and soft delete functionality which stores created, modified and deleted date and also who created and modified the record. The admin functionality is secured in separate controller which can only be accessed by user with admin authority, from there the admin can manage users roles.

![Build](https://github.com/radichev/WorkForYou-API/workflows/Build/badge.svg?branch=master&event=push) [![codecov](https://codecov.io/gh/radichev/WorkForYou-API/branch/master/graph/badge.svg)](https://codecov.io/gh/radichev/WorkForYou-API)

# :hammer_and_wrench: Build with
- Java 14
- Spring Boot 2.3.0
- Spring Security
- Spring Data JPA
- Hibernate Validator
- MySql
- H2
- JWT
- Swagger 2
- JUnit 5
- Mockito
- AWS S3
- Jacoco
- ModelMapper
- Guava
