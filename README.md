# Library App

Web application to manage a library, including inventory management (books available and number of copies of each
book), loans and management of library staff and members.

# Tech Stack 🛠

    - Java
    - JakartaEE
    - JPA
    - MySQL
    - Bootstrap

> [!NOTE]
> [Glassfish server](https://www.eclipse.org/downloads/download.php?file=/ee4j/glassfish/web-6.2.5.zip) is required to
> run this project

# Database schema

Database schema is dynamically generated based on the Java Persistence API (JPA) annotations in entities.
Any change whithin the database schema can be managed by editing `persistence.xml` file
(`/resources/META-INF/persistence.xml`)

```xml

<persistence-unit name="db_name">

    <class>com.biblioteca.model.entidades.Libro</class>
    <class>com.biblioteca.model.entidades.Autor</class>
    <class>com.biblioteca.model.entidades.Genero</class>
    <class>com.biblioteca.model.entidades.Usuario</class>
    <class>com.biblioteca.model.entidades.Prestamo</class>
    <properties>
        <property name="jakarta.persistence.jdbc.driver" value="org.mariadb.jdbc.Driver"/>
        <property name="jakarta.persistence.jdbc.url" value="jdbc:mariadb://localhost:3306/db_name"/>
        <property name="jakarta.persistence.jdbc.user" value="user"/>
        <property name="jakarta.persistence.jdbc.password" value="password"/>
        <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
        <property name="eclipselink.target-database" value="org.eclipse.persistence.platform.database.MySQLPlatform"/>
    </properties>
</persistence-unit>
```

[imagen]

# Roles

There are several roles which can make different actions:

## Admin
Admin users can perform CRUD operations over all entities
(books, authors, genres and users)

### Admin main page  
[imagen]

- Employee management

  - Employee edit page
- 
- Books management

  - Book edit page

- Author management

- Genre management

## Employee
Employees are responsible for making loans 
They can select members and assign them a book (if they are not banned)
The member get banned if returns the book late.

When a loan is made a nice card with all the information appears

## Member
Members can check in their profiles the books they have been borrows and
returnin them back on time or not (got banned)


# API Rest

This app also provides a Rest API with two endpoints:

| Method | URL            | Action                |
|--------|----------------|-----------------------|
| GET    | /api/libros    | retrieve all books    |
| GET    | /api/libros/id | retrieve a book by id |
