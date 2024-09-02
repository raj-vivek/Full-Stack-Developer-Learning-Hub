# Spring Framework Annotations

## Theory

- Spring Annotations are a form of metadata that provides supplemental data about a program.
- They provide a way to configure and manage beans and components within a Spring Application Context (Spring IoC Container).
- They simplify configuration by reducing the need for XML-based configuration and enhance readability and maintainability of the code.

### 6 Types of Spring Annotations

1. Spring Core Annotations
2. Spring Web Annotations
3. Spring Boot Annotations
4. Spring Scheduling Annotations
5. Spring Data Annotations
6. Spring Bean Annotations

### 1. Spring Core Annotations

#### 2 categories -

1. DI-Related Annotations
   1. `@Autowired`
   2. `@Qualifier`
   3. `@Primary`
   4. `@Bean`
   5. `@Lazy`
   6. `@Required`
   7. `@Value`
   8. `@Scope`
   9. `@Lookup`
2. Context Configuration Annotations
   1. `@Profile`
   2. `@Import`
   3. `@ImportResource`
   4. `@PropertySource`

### 2. Spring Web Annotations

1. `@RequestMapping`
2. `@RequestBody`
3. `@PathVariable`
4. `@RequestParam`
5. Response Handling Annotations
   1. `@ResponseBody`
   2. `@ExceptionHandler`
   3. `@ResponseStatus`
6. `@Controller`
7. `@RestController`
8. `@ModelAttribute`
9. `@CrossOrigin`

### 3. Spring Boot Annotations

1. `@SpringBootApplication`
2. `@EnableAutoConfiguration`
3. Auto-Configuration Conditions
   1. `@ConditionalOnClass`, and `@ConditionalOnMissingClass`
   2. `@ConditionalOnBean`, and `@ConditionalOnMissingBean`
   3. `@ConditionalOnProperty`
   4. `@ConditionalOnResource`
   5. `@ConditionalOnWebApplication and @ConditionalOnNotWebApplication`
   6. `@ConditionalExpression`
   7. `@Conditional`

### 4. Spring Scheduling Annotations

1. `@EnableAsync`
1. `@EnableScheduling`
1. `@Async`
1. `@Scheduled`
1. `@Schedules`

### 5. Spring Data Annotations

#### Common Spring Data Annotations

1. `@Transactional`
2. `@NoRepositoryBean`
3. `@Param`
4. `@Id`
5. `@Transient`
6. `@CreatedBy, @LastModifiedBy, @CreatedDate, @LastModifiedDate`

#### Spring Data JPA Annotations

1. `@Query`
2. `@Procedure`
3. `@Lock`
4. `@Modifying`
5. `@EnableJpaRepositories`

#### Spring Data Mongo Annotations

1. `@Document`
2. `@Field`
3. `@Query`
4. `@EnableMongoRepositories`

### 6. Spring Bean Annotations

1. `@ComponentScan`
2. `@Configuration`
3. Stereotype Annotations
   1. `@Component`
   2. `@Service`
   3. `@Repository`
   4. `@Controller`