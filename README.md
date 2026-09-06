# B12CL

B12CL(B12 Critically Low) is a location-based reminder application.

users can create reminders based on location. the app is designed to notify the user when they enter the specified area

## current status

the backend currently provides REST API for creating, retrieving, updating and deleting reminders. reminders are persisted in a PostgreSQL database using Spring Data JPA. a basic frontend is implemented using HTML, CSS, JS, Leaflet.js
currently, it lets us create a reminder, view existing reminders and delete reminders. the reminder detection and notification system is not implemented yet.

## tech stack

### backend
- Java, Spring (Boot, Web, Data JPA)

### frontend
- HTML, CSS
- JS
- Leaflet.js

## DB
- PostgreSQL

## planned
- improve frontend
- implement reminder detection based on user's location and user notification
- probably use Capacitor to package the application as a mobile app

