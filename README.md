# B12CL

B12CL(B12 Critically Low) is a location-based reminder application.

users can create reminders based on location and a radius. the app is designed to notify the user when they enter the specified area

## current status

backend currently provides REST API for creating, retrieving, updating and deleting reminders. reminders are persisted in a PostgreSQL database using Spring Data JPA. the frontend is currently under development

## tech stack

### backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA

### frontend
- HTML, CSS
- Leaflet.js
(Leaflet.js is coming soon, and i'll work on the frontend later)

## DB
- PostgreSQL

## planned
- integrate Leaflet.js
- connect frontend to backend REST API
- implement reminder detection and user notification
- probably use Capacitor to package the application as a mobile app

