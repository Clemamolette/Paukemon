# Paukemon - JEE Project

### Version configuration

This project uses Java 21.

### Set environment variables

You have to change the path "spring.datasource.url" in the application.properties file.
This path is the absolute one to the folder "resources" where the application.properties file is.

The variable should have this syntax :
```
spring.datasource.url=jdbc:h2:file:/absolute/path/to/projectFolder/paukemon/src/main/resources
```
Only the "/absolute/path/to/projectFolder" has to be changed, projectFolder being the folder where the project has been downloaded.

### (optional) Set up the API key

The API PokemonTCG uses a key to have access to data. To get a key, you have to create a free account here : https://dev.pokemontcg.io/

But this step is not mandatory as you can still make requests to the API without a key. The rate will just be limited to 30 requests per minute, but as this project is only making 2, it will be fine.

If you want to set it up nonetheless, you have to copy/paste the key given to you by the API in the PokemonService.java file in this String variable : 
```java
private String apiKEY = "";
```
Located line 24.

### Explore the website

You have to go to http://localhost:8080 on your browser and be connected to an Internet connection.
