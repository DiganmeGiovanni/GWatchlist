# API Documentation

## Contents
    * Entities
    * Endpoints

## Entities
* User
* UserPreferences
* MoviesList
* Movie
* ListWrapper

## Endpoints | Users

### GET /api/user/login
If user with given email exists, login. Otherwise registers user with given
email and names and login.

#### Query params:
 * `email`: User's unique email
 * `name`: User's full name
 
#### Response
Returns an `User` entity with its corresponding `Preferences`
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

### POST /api/user/{userId}/preferences

#### Post body
Receives a `UserPreferences` object in `JSON` format. 
```json
{
  "notifyOnListShared": true,
  "notifyOnMovieAdded": true,
  "theme": "DARK_PINK"
}
```

#### Response
Returns an **empty** response with `204` code if all goes ok.

## Endpoints | Lists of movies

### GET /api/movies/list/{list_id}
Returns the list with given id or throws a `404` is list does not exists

#### Response
```json
{
  "id": number,
  "name": string,
  "createdAt": date,
  "sharedWith": [string],
  "isPersonalList": boolean,
  "ownerEmail": string,
  "movies": [Movie]
}
```

### POST /api/movies/list
Creates a new list with given name

#### Post body
Receives the list name and owner email form params
*  `email`
* `owner_email`

#### Response
```json
{
  "id": number,
  "name": string,
  "createdAt": date,
  "sharedWith": [string],
  "isPersonalList": boolean,
  "ownerEmail": string,
  "movies": [Movie]
}
```


### DELETE /api/movies/list/{list_id}
Deletes the list with given id

#### Response
Empty body with `204` code if the list was deleted correctly or `404|409`


### GET /api/movies/lists
Returns all lists for a given user (Including lists shared with him). NOTE: Due to
performance the returned lists does not include its related movies

#### Query params
* `owner_email` User's Email

#### Response
```json
[
  {
    id: number,
    name: string,
    ownerEmail: string,
    personal: boolean,
    sharedWith: [string]
  },
  {...}, ...
]
```


### GET /api/movies/list/personal
Returns the personal list of a given user

#### Query params
* 'owner_email' User's email

#### Response
```json
{
  "id": number,
  "name": string,
  "createdAt": date,
  "sharedWith": [string],
  "isPersonalList": boolean,
  "ownerEmail": string,
  "movies": [Movie]
}
```


### POST /api/movies/list/{list_id}/share
Shares a given list with an email

#### Posted form params
* `email` Email which to share the list

#### Response
Empty body with `201` code if the list was shared correctly or `404|409`


## Endpoints | Movies in list

### POST /api/list/{list_id}/movie
Adds a given movie to indicated list

#### Post body
```json
{
  tmdbId: string,
  title: string,
  synopsis: string,
  posterPath: string,
  releaseDate: string,
  voteAverage: number,
  genres: [string],
  directors: [string],
  addedByEmail: string,
  addedByName: string,
  watched: boolean,
  active: boolean
}
```

#### Response
Empty body with `201` code if the movie was added correctly or `404`

### PUT /api/list/{list_id}/movie
Updates a given movie in the indicated list

#### Put body
```json
{
  tmdbId: string,
  title: string,
  synopsis: string,
  posterPath: string,
  releaseDate: string,
  voteAverage: number,
  genres: [string],
  directors: [string],
  addedByEmail: string,
  addedByName: string,
  watched: boolean,
  active: boolean
}
```

#### Response
Empty body with `204` code if the movie was updated correctly or `404`

### DELETE /api/list/{list_id}/movie
Removes a given movie from indicated list

#### Delete body
```json
{
  tmdbId: string,
  title: string,
  synopsis: string,
  posterPath: string,
  releaseDate: string,
  voteAverage: number,
  genres: [string],
  directors: [string],
  addedByEmail: string,
  addedByName: string,
  watched: boolean,
  active: boolean
}
```

#### Response
Empty body with `204` code if the movie was deleted correctly or `404`