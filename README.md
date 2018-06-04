# Voting system to decide where to have lunch today

## Task 

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users <br />
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price) <br />
Menu changes each day (admins do the updates) <br />
Users can vote on which restaurant they want to have lunch at <br />
Only one vote counted per user <br />
If user votes again the same day: <br />
If it is before 11:00 we assume that he changed his mind. <br />
If it is after 11:00 then it is too late, vote can't be changed <br />
Each restaurant provides new menu each day. <br />

## Installation

`git clone https://github.com/kek5/lunch_voting_REST_API`

## Run (from project folder)

`mvn spring-boot:run` <br/>
after that the application should be running at your http://localhost:8080

## Database
You can access the dabase (H2) by the following address:<br/>
> http://localhost:8080/h2 <br/>
> username:sa <br/>
> no password

## Entry points
http://localhost:8080 <br/>
http://localhost:8080/api/v1 <br/>
http://localhost:8080/api/v1/signup <br/>
http://localhost:8080/api/v1/restaurants (GET included)<br/> 
http://localhost:8080/api/v1/restaurants/1 (restaurant pathing is defined by it's id) (GET included)<br/> 
http://localhost:8080/api/v1/restaurants/1/menu (GET included)<br/> 
http://localhost:8080/api/v1/vote?rest_id=1 (rest_id is a restaurant id for which user decides to vote) <br/>


### Authentication
All actions within the system (except registration) require authentication of a user. 2 Default users are created at start<br/>
of the application:<br/>
1) Admin (username:admin, password:admin) with admin role<br/>
2) User (username:user, password:user) with user role<br/>

All the following cURL commands will include user to be made on his behalf

### SignUp
You can also create your own user with USER role with the following URL request<br/>
`curl -d '{"username" : "myusername", "password" : "mypassword"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/signup`

### Admin
**Create new Restaurant** <br/>
`curl -u admin:admin -d '{"name" : "restaurant test name"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/restaurants`<br/>

**Remove Restaurant** <br/>
`curl -u admin:admin -X DELETE http://localhost:8080/api/v1/restaurants/2` <br/>

**Create/Udate menu** <br/>
`curl -u admin:admin -d '{"dishes" : [{"name" : "new dish", "price" : 123}, {"name" : "brand total new", "price" : 221} ]}' -H "Content-Type: application/json" -X PUT http://localhost:8080/api/v1/restaurants/1/menu`

### User
**Vote** <br/>
Voting is available only after 00:00 and before 11:00 each day by UTC zone. <br/>
`curl -u user:user -X PUT http://localhost:8080/api/v1/vote?rest_id=1`
