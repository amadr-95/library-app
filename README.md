# Library App 📚

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

![db-schema](https://github.com/amadr-95/library-management/assets/122611230/7ff19620-713b-4686-9678-a74d00a6c303)


# Roles

There are several roles which can make different actions:

## Admin
Admin users can perform CRUD operations over all entities
(books, authors, genres and users)

![admin](https://github.com/amadr-95/library-management/assets/122611230/d8c877a8-55b3-4cbc-9f94-5609460713a4)

### Employee management
  ![employee-management](https://github.com/amadr-95/library-management/assets/122611230/7c2257ad-6842-467b-82cf-2585a581b105)

#### Employee edit page
![edit-employee](https://github.com/amadr-95/library-management/assets/122611230/6a63bc6c-592d-4b65-a9c8-f866a5338352)

### Books management
![books-management](https://github.com/amadr-95/library-management/assets/122611230/e7265341-4e01-4d9f-819f-fa672983f86d)

#### Book edit page
![edit-book](https://github.com/amadr-95/library-management/assets/122611230/e31ebbf7-ace0-4040-b48d-45d18bc093ce)

### Author management
![authors-management](https://github.com/amadr-95/library-management/assets/122611230/eb04cc97-044a-4dbc-b4a4-c7b36b574809)

### Genre management
![genres-management](https://github.com/amadr-95/library-management/assets/122611230/f1c5bd30-1a10-47b8-8fa3-28955cb0566d)

### Loans management
![loans-management](https://github.com/amadr-95/library-management/assets/122611230/ee456934-28db-4047-b3c6-45c25779b055)


## Employee
Employees are responsible for making loans 
They can select members and assign them a book (if they are not banned)
The member get banned if returns the book late.
![loan](https://github.com/amadr-95/library-management/assets/122611230/70817b22-4cab-4d3c-8b29-05dd2cf3d4e8)

When a loan is made a nice card with all the information appears
![loan-card](https://github.com/amadr-95/library-management/assets/122611230/045d5977-6227-4a49-b0bf-bde8499dd73a)


## Member
Members can check in their profiles the books they have been borrows and
returnin them back on time or not (got banned)
![user-books](https://github.com/amadr-95/library-management/assets/122611230/dbdfae64-7bdf-497d-9c43-9d687677ac97)


# API Rest

This app also provides a Rest API with two endpoints:

| Method | URL            | Action                |
|--------|----------------|-----------------------|
| GET    | /api/libros    | retrieve all books    |
| GET    | /api/libros/id | retrieve a book by id |
