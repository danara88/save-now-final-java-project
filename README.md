# Project Overview

This project is built using **Java**, following **Clean Architecture** principles while incorporating the **Model-View-Controller (MVC)** pattern. The goal is to ensure modularity, maintainability, and scalability.

## Architecture

### Clean Architecture & MVC
- The project is structured using **Clean Architecture**, which separates concerns into different layers, ensuring flexibility and testability.
- Additionally, the **MVC** pattern is applied within the UI layer to manage user interactions effectively.

## Data Persistence
- Instead of using a database, this project **persists data using native JSON files**.
- Data is stored and retrieved from `.json` files to maintain simplicity while ensuring structured data management.

## Repository Pattern
- The **Repository Pattern** is implemented to provide an abstraction over data storage.
- This pattern separates data access logic from business logic, making it easier to switch to a different storage solution if needed in the future.

## Features
- Modular architecture following Clean Architecture and MVC principles.
- JSON-based data persistence for lightweight storage.
