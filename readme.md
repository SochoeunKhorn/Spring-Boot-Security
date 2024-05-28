== Security With JWT Token ==
- How to access
    - change data source (.yaml)
      ```agsl
      spring:
          datasource:
          url: jdbc:postgresql://localhost:5432/database_name
          username: postgres_name
          password: password
          driver-class-name: org.postgresql.Driver
      jpa:
          hibernate:
              ddl-auto: create // for first run app -> after change to update
      show-sql: false
      properties:
          hibernate:
              format_sql: true
      database: postgresql
      database-platform: org.hibernate.dialect.PostgreSQLDialect
      ```
    - create super-admin default for first run
      ```agsl
        private final PasswordEncoder passwordEncoder;
        @Bean
      public CommandLineRunner commandLineRunner(
      UserRepository userRepository,
      RoleRepository roleRepository
      ) {
      return args -> {
      if (roleRepository.findByName("SUPER-ADMIN").isEmpty()) {
      roleRepository.save(Role.builder().name("SUPER-ADMIN").build());
      }
    
            User super_admin = User.builder()
                    .firstname("SUPER")
                    .lastname("ADMIN")
                    .email("super-admin@gmail.com")
                    .password(passwordEncoder.encode("super-admin"))
                    .profile("")
                    .status(true)
                    .roles(roleRepository.findAll())
                    .build();
            userRepository.save(super_admin);
        };
        }
      ```
- after first run success don't forget to remove
  - create super-admin default
  - change ddl-auto = update

- api access
  ```agsl
    login
    method: POST
    url: http://localhost:8080/api/auth/login
    body:
  {
    "email":"super-admin@gmail.com",
    "password":"super-admin"
  }
  ```

- play api
  ```agsl
    Register:(only super-admin-role)
    url: http://localhost:8080/api/auth/register
    body:
        {
    "firstName":"user",
    "lastName":"user",
    "email":"user@gmail.com",
    "password":"123",
    "roles":[
        "ROLE1","ROLE2"
    ]
  }
  
    Get All Users: (only admin)
    url : http://localhost:8080/api/users
    Authorization(Bearer Token) = token get after login
  ```