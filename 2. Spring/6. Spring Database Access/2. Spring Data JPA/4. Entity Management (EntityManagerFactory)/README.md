# Entity Management

An EntityManager is a lower-level interface that is part of the Java Persistence API (JPA). It provides a set of methods for managing the lifecycle of persistent entities, including creating, updating, and deleting entities.

### Steps:

- We will follow DAO (Data Access Object) design pattern.

1. Entity Class

   - An Entity class represents a table in the database. It is annotated with @Entity and typically contains fields that map to columns in the table.

     ```java
     import javax.persistence.Entity;
     import javax.persistence.GeneratedValue;
     import javax.persistence.GenerationType;
     import javax.persistence.Id;

     @Entity
     public class Book {

         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         private String title;
         private String author;

         // Constructors
         public Book() {}

         public Book(String title, String author) {
             this.title = title;
             this.author = author;
         }

         // Getters and setters
     }
     ```

2. Define the DAO Interface

   - The DAO interface declares methods for accessing and manipulating the Entity. It provides an abstraction layer for data access operations.

     ```java
     import java.util.List;

     public interface BookDAO {
         void save(Book book);
         Book findById(Long id);
         List<Book> findAll();
         void update(Book book);
         void delete(Long id);
     }
     ```

3. Implement the DAO Interface

   - The DAO implementation uses EntityManager to interact with the database. It provides the concrete implementation of the data access methods declared in the DAO interface.

     ```java
     import javax.persistence.EntityManager;
     import javax.persistence.EntityManagerFactory;
     import javax.persistence.Persistence;
     import javax.persistence.TypedQuery;
     import java.util.List;

     public class BookDAOImpl implements BookDAO {

         private EntityManagerFactory emf;
         private EntityManager em;

         public BookDAOImpl() {
             emf = Persistence.createEntityManagerFactory("my-persistence-unit");
             em = emf.createEntityManager();
         }

         @Override
         public void save(Book book) {
             em.getTransaction().begin();
             em.persist(book);
             em.getTransaction().commit();
         }

         @Override
         public Book findById(Long id) {
             return em.find(Book.class, id);
         }

         @Override
         public List<Book> findAll() {
             TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
             return query.getResultList();
         }

         @Override
         public void update(Book book) {
             em.getTransaction().begin();
             em.merge(book);
             em.getTransaction().commit();
         }

         @Override
         public void delete(Long id) {
             em.getTransaction().begin();
             Book book = em.find(Book.class, id);
             if (book != null) {
                 em.remove(book);
             }
             em.getTransaction().commit();
         }
     }
     ```

4. Use DAO in the Service Layer

   - The service layer interacts with the DAO to perform business logic. The service is typically where you would implement transactional boundaries and business rules.

   - Example: BookService Interface

     ```java
     import java.util.List;

     public interface BookService {
         void saveBook(Book book);
         Book getBookById(Long id);
         List<Book> getAllBooks();
         void updateBook(Book book);
         void deleteBook(Long id);
     }
     ```

   - Example: BookServiceImpl Implementation

     ```java
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.stereotype.Service;
     import org.springframework.transaction.annotation.Transactional;

     import java.util.List;

     @Service
     public class BookServiceImpl implements BookService {

         private final BookDAO bookDAO;

         @Autowired
         public BookServiceImpl(BookDAO bookDAO) {
             this.bookDAO = bookDAO;
         }

         @Override
         @Transactional
         public void saveBook(Book book) {
             bookDAO.save(book);
         }

         @Override
         public Book getBookById(Long id) {
             return bookDAO.findById(id);
         }

         @Override
         public List<Book> getAllBooks() {
             return bookDAO.findAll();
         }

         @Override
         @Transactional
         public void updateBook(Book book) {
             bookDAO.update(book);
         }

         @Override
         @Transactional
         public void deleteBook(Long id) {
             bookDAO.delete(id);
         }
     }
     ```

### Advantages of Using DAO with JPA

1. Separation of Concerns: Keeps the persistence logic separate from business logic.
2. Flexibility: Allows changing the data access strategy (e.g., switching from JPA to JDBC) with minimal impact on the business logic.
3. Testability: Facilitates easier unit testing by allowing the DAO to be mocked or stubbed.

## Connect to 2 Databases

- Summary

  1. Define Properties: Configure properties for both data sources.
  2. Configure DataSources: Create configuration classes for each database, defining DataSource, EntityManagerFactory, and TransactionManager.
  3. Define Entities and Repositories: Create entities and repositories for each database, ensuring correct packages and configurations.
  4. Use Repositories: Inject and use the repositories in your service layer.

- Folder Structure

  ```css
  src/
  └── main/
      ├── java/
      │   └── com/
      │       └── example/
      │           ├── config/
      │           │   ├── PrimaryDatabaseConfig.java
      │           │   └── SecondaryDatabaseConfig.java
      │           ├── primary/
      │           │   ├── model/
      │           │   │   └── PrimaryEntity.java
      │           │   ├── repository/
      │           │   │   └── PrimaryRepository.java
      │           │   └── service/
      │           │       ├── PrimaryService.java
      │           │       └── PrimaryServiceImpl.java
      │           ├── secondary/
      │           │   ├── model/
      │           │   │   └── SecondaryEntity.java
      │           │   ├── repository/
      │           │   │   └── SecondaryRepository.java
      │           │   └── service/
      │           │       ├── SecondaryService.java
      │           │       └── SecondaryServiceImpl.java
      │           └── Application.java
      └── resources/
          ├── application.properties
          └── application-dev.properties
  ```

1. Define the Database Properties

   - In your application.properties or application.yml, add properties for both databases.
   - For example: application.properties

     ```properties
     # Primary DataSource
     spring.datasource.primary.url=jdbc:mysql://localhost:3306/primarydb
     spring.datasource.primary.username=root
     spring.datasource.primary.password=root
     spring.datasource.primary.driver-class-name=com.mysql.cj.jdbc.Driver

     # Secondary DataSource
     spring.datasource.secondary.url=jdbc:mysql://localhost:3306/secondarydb
     spring.datasource.secondary.username=root
     spring.datasource.secondary.password=root
     spring.datasource.secondary.driver-class-name=com.mysql.cj.jdbc.Driver
     ```

2. Use @ConfigurationProperties to get DataSource Properties from application.properties

   1. Enable Configuration Properties

      - Make sure to enable configuration properties in your main application class or a configuration class:

        ```java
        import org.springframework.boot.context.properties.EnableConfigurationProperties;
        import org.springframework.context.annotation.Configuration;

        @Configuration
        @EnableConfigurationProperties
        public class ApplicationConfig {
        }
        ```

   2. Create Configuration Properties Classes

      - Create properties classes to bind the data source properties.

      1. PrimaryDatabaseProperties.java

         ```java
         import org.springframework.boot.context.properties.ConfigurationProperties;
         import org.springframework.stereotype.Component;

         @Component
         @ConfigurationProperties(prefix = "spring.datasource.primary")
         public class PrimaryDatabaseProperties {
             private String url;
             private String username;
             private String password;
             private String driverClassName;

             // Getters and setters
         }
         ```

      2. SecondaryDatabaseProperties.java

         ```java
         import org.springframework.boot.context.properties.ConfigurationProperties;
         import org.springframework.stereotype.Component;

         @Component
         @ConfigurationProperties(prefix = "spring.datasource.secondary")
         public class SecondaryDatabaseProperties {
             private String url;
             private String username;
             private String password;
             private String driverClassName;

             // Getters and setters
         }
         ```

3. Configure the DataSources

   - Create two @Configuration classes, each defining a DataSource, EntityManagerFactory, and TransactionManager for one of the databases.

   1. Primary Database Configuration

      ```java
      import org.springframework.beans.factory.annotation.Qualifier;
      import org.springframework.context.annotation.Bean;
      import org.springframework.context.annotation.Configuration;
      import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
      import org.springframework.jdbc.datasource.DriverManagerDataSource;
      import org.springframework.orm.jpa.JpaTransactionManager;
      import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
      import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
      import org.springframework.transaction.PlatformTransactionManager;

      import javax.sql.DataSource;

      @Configuration
      @EnableJpaRepositories(
          basePackages = "com.example.primary.repository",
          entityManagerFactoryRef = "primaryEntityManagerFactory",
          transactionManagerRef = "primaryTransactionManager"
      )
      public class PrimaryDatabaseConfig {

          @Autowired
          private PrimaryDatabaseProperties properties;

          @Bean(name = "primaryDataSource")
          public DataSource dataSource() {
              DriverManagerDataSource dataSource = new DriverManagerDataSource();
              dataSource.setDriverClassName(properties.getDriverClassName());
              dataSource.setUrl(properties.getUrl());
              dataSource.setUsername(properties.getUsername());
              dataSource.setPassword(properties.getPassword());
              return dataSource;
          }

          @Bean(name = "primaryEntityManagerFactory")
          public LocalContainerEntityManagerFactoryBean entityManagerFactory(
              @Qualifier("primaryDataSource") DataSource dataSource) {
              LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
              em.setDataSource(dataSource);
              em.setPackagesToScan("com.example.primary.model");
              em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
              return em;
          }

          @Bean(name = "primaryTransactionManager")
          public PlatformTransactionManager transactionManager(
              @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
              JpaTransactionManager transactionManager = new JpaTransactionManager();
              transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
              return transactionManager;
          }
      }
      ```

   2. Secondary Database Configuration

      ```java
      import org.springframework.beans.factory.annotation.Qualifier;
      import org.springframework.context.annotation.Bean;
      import org.springframework.context.annotation.Configuration;
      import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
      import org.springframework.jdbc.datasource.DriverManagerDataSource;
      import org.springframework.orm.jpa.JpaTransactionManager;
      import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
      import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
      import org.springframework.transaction.PlatformTransactionManager;

      import javax.sql.DataSource;

      @Configuration
      @EnableJpaRepositories(
          basePackages = "com.example.secondary.repository",
          entityManagerFactoryRef = "secondaryEntityManagerFactory",
          transactionManagerRef = "secondaryTransactionManager"
      )
      public class SecondaryDatabaseConfig {

          @Bean(name = "secondaryDataSource")
          public DataSource dataSource() {
              DriverManagerDataSource dataSource = new DriverManagerDataSource();
              dataSource.setDriverClassName(properties.getDriverClassName());
              dataSource.setUrl(properties.getUrl());
              dataSource.setUsername(properties.getUsername());
              dataSource.setPassword(properties.getPassword());
              return dataSource;
          }

          @Bean(name = "secondaryEntityManagerFactory")
          public LocalContainerEntityManagerFactoryBean entityManagerFactory(
              @Qualifier("secondaryDataSource") DataSource dataSource) {
              LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
              em.setDataSource(dataSource);
              em.setPackagesToScan("com.example.secondary.model");
              em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
              return em;
          }

          @Bean(name = "secondaryTransactionManager")
          public PlatformTransactionManager transactionManager(
              @Qualifier("secondaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
              JpaTransactionManager transactionManager = new JpaTransactionManager();
              transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
              return transactionManager;
          }
      }
      ```

4. Define Entities and Repositories

   - For each database, define the entities and repositories.

   - Primary Database Example

     ```java
     // Entity for Primary Database
     import javax.persistence.Entity;
     import javax.persistence.Id;

     @Entity
     public class PrimaryEntity {

         @Id
         private Long id;
         private String data;

         // Getters and setters
     }

     // Repository for Primary Database
     import org.springframework.data.jpa.repository.JpaRepository;
     import org.springframework.stereotype.Repository;

     @Repository
     public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long> {
     }
     ```

   - Secondary Database Example

     ```java
     // Entity for Secondary Database
     import javax.persistence.Entity;
     import javax.persistence.Id;

     @Entity
     public class SecondaryEntity {

         @Id
         private Long id;
         private String info;

         // Getters and setters
     }

     // Repository for Secondary Database
     import org.springframework.data.jpa.repository.JpaRepository;
     import org.springframework.stereotype.Repository;

     @Repository
     public interface SecondaryRepository extends JpaRepository<SecondaryEntity, Long> {
     }
     ```

5. Use Repositories in Your Services

   - In your service classes, you can inject and use the repositories for each database:

     ```java
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.stereotype.Service;

     @Service
     public class MyService {

         private final PrimaryRepository primaryRepository;
         private final SecondaryRepository secondaryRepository;

         @Autowired
         public MyService(PrimaryRepository primaryRepository, SecondaryRepository secondaryRepository) {
             this.primaryRepository = primaryRepository;
             this.secondaryRepository = secondaryRepository;
         }

         public void performDatabaseOperations() {
             // Use primaryRepository and secondaryRepository as needed
         }
     }
     ```
