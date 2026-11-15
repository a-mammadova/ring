# B12CL

B12CL(B12 Critically Low) is a location-based reminder application.

users can create reminders based on location and a radius. the app is designed to notify the user when they enter the specified area

## current status

backend currently provides REST API for creating, retrieving, updating and deleting reminders. reminders are persisted in a PostgreSQL database using Spring Data JPA. 

## tech stack

### backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA

### frontend
- HTML, CSS
- Leaflet.js

## DB
- PostgreSQL

## planned
- implement reminder detection and user notification
- probably use Capacitor to package the application as a mobile app

