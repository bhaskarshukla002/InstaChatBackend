# InstaChatBackend

**InstaChatBackend** is the backend service for a social media application that offers features like user authentication, real-time chat, and media sharing, similar to Instagram. Built with Java and Spring Boot, the application leverages RESTful APIs and WebSockets to handle real-time communication.

## Features

- **User Authentication**: Supports registration, login, and profile management.
- **Real-Time Chat**: Utilizes WebSockets to enable instant messaging between users.
- **Media Sharing**: Allows users to upload, like, and comment on posts.
- **Follow System**: Manages user following/followers lists and enables content visibility control.
- **Notifications**: Provides real-time updates for messages, likes, and comments.

## Tech Stack

- **Java** and **Spring Boot**: For server-side application logic and REST APIs.
- **MySQL**: For relational database management.
- **WebSockets**: For real-time chat functionality.
- **AWS S3** (or local file storage): For media storage (images, videos).
- **JWT**: For secure user authentication.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/bhaskarshukla002/InstaChatBackend.git
   cd InstaChatBackend
   ```
2. **Configure Database: Update the application.properties file with your MySQL database credentials**:

3. **Install Dependencies: Ensure you have Java and Maven installed. Then run:**
  ```bash
  mvn install
  ```
4. **Run the Application:**
  ```bash
  mvn spring-boot:run
  ```
5. **Access API Documentation: The application runs on http://localhost:8080 by default. Visit /swagger-ui.html (if integrated) for API documentation.**

## API Endpoints
| Endpoint |	Method |	Description |
| ---------|---------|--------------|
|/api/auth/register |	POST	| Register a new user|
|/api/auth/login	| POST	| Log in a user|
|/api/users/{id}	| GET	| Fetch user profile|
|/api/posts	| GET	| Get all posts|
|/api/posts/{id} |	POST | Like or comment on a post|
|/api/chat/{userId} |	GET  | 	Fetch chat with a user|


## Contributing
Contributions are welcome! Fork the repository and make a pull request with your proposed changes.

## License
This project is open-source and available under the MIT License.
