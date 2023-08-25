# Student Mentor API

This is a Spring Boot application that provides an API for managing mentors and students.

> ## ***You can also use Swagger to Test all the endpoints of API*** ##
> **Endpoint**: `http://localhost:8080/swagger-ui/index.html#/`



## Endpoints

### Recommend Student

**Endpoint**: `POST /api/recommend-student`

Recommends a student to a mentor.

**Parameters**:
- `mentorId` (Long): ID of the mentor.
- `recommendation` (String): Recommendation message.

### Rate Mentor

**Endpoint**: `POST /api/rate-mentor`

Rates a mentor.

**Parameters**:
- `mentorId` (Long): ID of the mentor.
- `rating` (int): Rating value (between 1 and 5).

### Review Mentor

**Endpoint**: `POST /api/review-mentor`

Adds a review for a mentor.

**Parameters**:
- `userId` (Long): ID of the user.
- `mentorId` (Long): ID of the mentor.
- `reviewText` (String): Review text.

### Get Mentors

**Endpoint**: `GET /api/mentors`

Retrieves a list of mentors.

**Parameters**:
- `rating` (Integer, optional): Filter mentors by rating.

### Get Recommendation

**Endpoint**: `GET /api/recommendation/{mentorId}`

Retrieves a recommendation for a mentor.

**Path Variable**:
- `mentorId` (Long): ID of the mentor.

### Create Mentor

**Endpoint**: `POST /api/create-mentor`

Creates a new mentor.

**Request Body**:
```json
{
  "id": null,
  "name": "Mentor Name",
  // Other mentor fields//
}
```
### Usage

#### Clone this repository.

1. Install the required dependencies using your preferred build tool (Maven, Gradle).

2. Configure the database settings in the application.properties file.

3. Build and run the application.

4. Access the API using the provided endpoints and parameters.

### Contributing

Contributions are welcome! If you find any issues or want to add new features, feel free to submit a pull request.
