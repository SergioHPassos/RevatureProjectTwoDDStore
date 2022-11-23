# Discounts & Deliveries
Fantasy themed storefront with game inspired displays

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)


## General Information
- Our team selected the storefront project to develop.
- We setup our Trello board and assigned our Scrum Master.
- We conducted daily standups on our past and future progress.
- Each Sprint we made great strides towards accomplishing our Minial Viable Product (MVP) goals.
- Additionally, we conducted our Retrospectives on how each Sprint was going.
- We made pull requests on each feature branch throughout the development, keeping everything organized and safe.
- Our database we setup using Heroku, we setup a PostgreSQL databse, allowing our development team persistant data throught development cycle.


## Technologies Used
- Typescript v4.7.2
- Java v8
- Angular v14.2.10
- Javalin v4.1.1
- Tailwind v3.2.4


## Features
- CRUD operations created with all endpoints
- Card based system reminiscent of classic fantasy games
- Non-standard currency display based on gold pieces
- 3D rotational effect on product items
- register/login
- persistent cart
- Dynamic color assignment for item rarities
- checkout capabilities


## Screenshots
![Home page](./img/homepage.png)
![Profile page](./img/profilepage.png)
![Cart page](./img/cartpage.png)
![Login page](./img/loginpage.png)
![Register page](./img/registerpage.png)
![API Flow](./img/apiflow.png)
![DB Tables](./img/db.png)
<!-- If you have screenshots you'd like to share, include them here. -->


## Setup
For the frontend, you need to have node installed. Once node is installed, you can clone the repository and cd into frontend folder. Secondly, enter "npm i" in the terminal to install the dependecies/devdependecies. Lastly, once the node modules have been installed, enter "ng serve -o" in the terminal to start the Angular server and automatically open the default browser to the default address.

For the backend, you need to install Java 8. Once Java 8 is installed, you can clone the repository and cd into the backend folder. Secondly, open the backend folder in your development environment of choice, mine is Intellij Free Edition. Open the "pom.xml" file and reload those Maven dependencies. Lastly, run your program, the main entry point is the "/driver/Main.java".


## Project Status
Discounts & Deliveries is functionally complete!


## Room for Improvement
Many ideas we had were unable to be fully implemented, due to time constraints.

Room for improvement:
- Notifications based on user action
- Theme selection
- Individual product pages
