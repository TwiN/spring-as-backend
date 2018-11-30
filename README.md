# spring-as-backend

A ready-to-go secure Spring backend.

This setup assumes that anybody can access your website, 
but only specific features are meant to be accessed for authenticated users.

If you wish to force everything to be only accessible by authenticated users, uncomment the following lines
in `SecurityConfiguration.java`:

	.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
		.anyRequest().authenticated()
		.and()
		
By doing so, any request to the API will redirect you to the default [login page](#login) except for:
 
| Method |     Path      | Justification |
|:------:|:-------------:|:-------------:|
| POST   | /api/v1/users |   Register    |
| POST   | /api/v1/login |    Login      |


## Usage with Docker

```
docker-compose up
```


## Features

- Swagger UI
- Spring Security
- Spring Session
- RESTful API
- User authentication + register
- Page with some example


## Sample Page

A index page is included to help developers getting started.
It contains the link to the application's Swagger, the link to the default login form
as well as another login form that uses the [login processing url](#login-processing-url)
 with a container for the server response of said example.

![Sample page](/docs/img/sab-sample-page.png)


## Endpoints

Only the necessary endpoints will be listed here. A full list of endpoints can be found using the Swagger UI.


### Swagger UI

The Swagger UI is there to facilitate the development of your application, as it can allow you
to easily view and interact with the backend's API.

You can view the swagger API by going at the following url: `/swagger-ui.html`


### Login

Initially, there is no user other than `root:root`, which is an admin.

By default, there's a login form at the following url: `/login`


#### Login Processing URL

Alternatively, you can use the **login processing url** to login from your frontend.

| Key | Value | 
|:---:|:---:|
| **Method** | POST |
| **Path** | `/api/v1/login` |
| **Parameters** | username, password |

Due to the nature of authentication itself, you'll need to use `POST` in order to login using the **login processing url**.


### Logout

| Key | Value | 
|:---:|:---:|
| **Method** | GET |
| **Path** | `/logout` |
| **Parameters** | - |


### Creating an user

By default, the username must be at least 4 characters long and the password must be at least 8 characters long.
The only exception is the default `root:root` user, which **you should remove before publishing the project in production**.

| Key | Value | 
|:---:|:---:|
| **Method** | POST |
| **Path** | `/api/v1/users` |
| **Parameters** | username, password |


### Getting the Authenticated User

| Key | Value | 
|:---:|:---:|
| **Method** | GET |
| **Path** | `/api/v1/users/me` |
| **Parameters** | - |


## TODO

- Authentication button on Swagger UI 
- More tests
