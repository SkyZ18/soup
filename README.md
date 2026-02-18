![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

# SOUP | SkyzObservedUserProvider 🍜
A simple IDP service that lets you register Application and create Clients. Makes authentication easier and provides extra security because of blocking unknown applications.

# Setup guide
To run a local database-container in Docker, you can run `sh dockerDB.sh` in the root directory of the project

# Creating client
When creating a client you can choose to either directly register an application to it, or just create a new client with no registrations.<br>
When creating a client with an application, it must be registered in the application.

Post on **"/api/v1/client/register"** will create a new client with an application bound to it.

# API Documentation
Here you can find each request and request-body.

## Client
Register new client with application
```Java
@PostMapping("/register")
public ResponseEntity<CreateClientResponse> register(
  @RequestBody CreateClientWithAppDto createClientWithAppDto
) {
  return clientService.createNewClientWithAppInfo(createClientWithAppDto)
    .map(ResponseEntity::ok)
    .orElseThrow(RuntimeException::new);
}
```
