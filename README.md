

# Social Plan Management Application

This is a Java-based application that allows users to manage social plans. It provides a command-line interface for users to interact with the system.

## Features

- User registration and login
- Creation, deletion, and modification of social plans
- Adding activities to social plans
- Joining and leaving social plans
- Scoring social plans
- Viewing details of social plans, including activities and participants

## Demo

![](demo.webm)

## Project Structure

The project follows a standard Maven project structure and is organized into several packages:

- `ES.UPM.ETSISI.CITIT21G6`: Contains the main entry point of the application.
- `ES.UPM.ETSISI.CITIT21G6.CLI`: Contains classes for handling command-line interactions.
- `ES.UPM.ETSISI.CITIT21G6.controller`: Contains controllers for handling user and social plan operations.
- `ES.UPM.ETSISI.CITIT21G6.exception`: Contains custom exception classes.
- `ES.UPM.ETSISI.CITIT21G6.model`: Contains model classes representing the domain objects.
- `ES.UPM.ETSISI.CITIT21G6.repository`: Contains repository classes for data persistence.
- `ES.UPM.ETSISI.CITIT21G6.service`: Contains service classes for business logic.
- `ES.UPM.ETSISI.CITIT21G6.view`: Contains view classes for presenting information to the user.

## Setup

To run the application, you need to have Java and Maven installed on your system. You can then clone the repository and run the application using the following commands:

```bash
git clone <repository-url>
cd <repository-directory>
mvn clean install
java -jar target/<jar-file-name>.jar
```

## Usage
After starting the application, you will be presented with a command-line prompt. You can enter commands to interact with the system. The available commands are:  
- user register: Register a new user.
- user login: Log in as an existing user.
- user logout: Log out the current user..
- user whoami: Indicates the currently logged in user (if any)
- social-plan create: Create a new social plan.
- social-plan delete: Delete an existing social plan.
- social-plan add-activity: Add an activity to a social plan.
- social-plan join: Join a social plan.
- social-plan unjoin: Leave a social plan.
- social-plan list: List all social plans.
- social-plan set-score: Set a score for a social plan.
- exit: Exit the application.


Each command has its own set of arguments and options. 


You can use the help command to get more information about a specific command
