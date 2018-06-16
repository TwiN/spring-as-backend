# spring-as-backend




## Endpoints

Only the necessary endpoints will be listed here, as all other endpoints can be found using the Swagger API.


### Swagger API

You can view the swagger API by going at the following url: [/swagger-ui.html](http://localhost/swagger-ui.html)


### Login

Initially, there is no user other than `root:root`, which is an admin.

By default, there's a login form at the following url: [/login](http://localhost/login)
    
Alternatively, you can use the **login processing url** to login from your frontend: 

`POST` [/api/v1/login?username=&password=](http://localhost/api/v1/login?username=&password=)

Due to the nature of authentication itself, you'll need to use `POST` in order to login using the **login processing url**.


### Logout

`GET` [/logout](http://localhost/logout)


### Creating an user






	
	