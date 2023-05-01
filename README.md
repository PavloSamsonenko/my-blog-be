# my-blog-be

MyBlog backend application

# Requirements

1. Java 17 or newer.
2. Maven 3.8.6 or newer.
3. Postgres db.

# Steps to start application:

1. Initialize new postgres db and configure user.
2. Define system variables -

   MyBlogGmailPassword - genreated gmai password.

   MyBlogGmailUser - Gmail(format example@gmail.com)

   MyBlogJwtSecret - Generated jwt secret using aes-128-cbc (example online tool - https://encode-decode.com/aes-128-cbc-encrypt-online/)

   MyBlogPostgrePassword - Postgres user password

   MyBlogPostgreUrl - Postgres db Url(example jdbc:postgresql://localhost:5432/myblog_db)

   MyBlogPostgreUser - Postgres username
3. Open terminal in project location.
4. Run "mvn clean install".
5. Run "java -jar .\target\my-blog-be-0.0.1-SNAPSHOT.jar".