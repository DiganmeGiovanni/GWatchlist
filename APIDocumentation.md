# API Documentation

## Entities
* User
* UserPreferences
* MoviesList
* Movie
* ListWrapper

## Endpoints

### GET /api/user/login
If user with given email exists, login. Otherwise registers user with given
email and names and login.

#### Query params:
 * `email`: User's unique email
 * `name`: User's full name
 
#### Response
Returns an `User` entity with its preferences
```json
    {
      "id": 1,
      "email": "giovanni.fi05@gmail.com",
      "name": "Giovanni Aguirre",
      "createdAt": 1480565739522,
      "lastLoginAt": 1485116054651,
      "loginCount": 19,
      "preferences": {
        "notifyOnMovieAdded": true,
        "notifyOnListShared": true,
        "theme": "BLUE_DARK"
      }
    }
```