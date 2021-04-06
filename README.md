# Jwt-SpringBoot-Security
##### Step 1: Open Xampp Server
##### Step 2: Open This project in Intellij  Idea
##### Step 3: Run this

## Test Using Postman in below instruction 
## sign up
###### Post
http://localhost:20230/public/signup
```
{
    "userId":"123",
    "username":"123",
    "role":"123",
    "password":"123",
    "applicationName":"123"
}
```
## signin
###### Post
http://localhost:20230/public/signin
````
{
  "username": "roni2923",
   "password":"roni2923"
}

````
## Get User Details After Login
###### Get
##### Must be provide JWT token (which is you get when you login) in  Header 
http://localhost:20230/user-details


