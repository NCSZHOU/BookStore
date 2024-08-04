# Zand Book Store

This a simple book store backend project for Zand company test, do not use it for other commercial purposes.

The project contains REST APIs of an online bookstore, there are two mainly functions: Book Management and Shopping cart
management.

# Table of contents

- [Zand Book Store](#nsfw-filter)
- [Table of contents](#table-of-contents)
- [Usage](#usage)
- [Development](#development)
- [Test](#contribute)

# Usage  
  
_You can do_:   
  
  **Book Management:**  
    1. Retrieve books: it will retrieve all the available books in our system  
    2. Add books: you can add one or more books to our system  
  
  **Shopping-Cart Management:**   
    1. Retrieve shopping-cart contents: you can retrieve all the contents of shopping-cart by your userId  
    2. Save shopping-cart contents   
    3. Checkout: you can do checkout for your shopping cart, then it will calculate the books' total amount and return it back, meanwhile our system will remove all the contents of you shopping-cart and do the stock management  
  
# Development    
   This document will take Windows System as example:    
   **Prerequisite**:  
    JDK 17 or higher  
    maven 3.6.3 or higher  
    Git bash  

   **Steps**
   1. [Download project](https://github.com/NCSZHOU/ZandBookStore) by Git Bash
   2. Open the Command Prompt, and go to the project folder and input: "mvn clean install"
   3. Go to folder target, and type the command : "java -jar Bookstore-0.0.1-SNAPSHOT.jar"
   4. In browser, open http://localhost:8082/swagger-ui/index.html, you can check the apis detail
   5. Open http://localhost:8082/h2 can check database detail, authorization:
       Driver Class: org.h2.Driver
       JDBC URL: jdbc:h2:D:/H2/db/db02
       User Name: root
       Password: syc123

# Test  
  
  **Unit Test**  
  At classpath:/test/java/com.zand.bookstore, we can see all the unit tests  
    
  **Integration Test**  
