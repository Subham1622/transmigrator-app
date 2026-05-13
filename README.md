Transmigrator App (Spring Boot) 

Overview 

The Transmigrator App is a Spring Boot REST API that manages meaningful events related to finance, technology, geopolitics, and decision-making. 
 
Features include saving events, filtering, and role-based security using Spring Security. 

Tech Stack 

Java 17 
Spring Boot 3.x 
Spring Data JPA 
H2 Database 
Spring Security 
Actuator 
Swagger 

Features 

• Save events (bulk) 
• Get events by date range 
• Filter by suggestion and category 
• Get event by ID 
• Delete event (role-based) 

Security Configuration 

Spring Security is configured using Basic Authentication with roles ADMIN, USER, and MANAGER. 

Users 

ADMIN1 / Password1 → ADMIN 
USER1 / Password2 → USER 
MANAGER1 / Password3 → MANAGER 

Important Endpoints 

GET /events 
POST /events/saveEvent 
GET /events/{id} 
DELETE /events/delete/{id} 
GET /events/by-suggestion/{suggestion} 
GET /events/by-category/{category} 
GET /events/filter/{suggestion}/{category} 

Actuator 

/actuator/health 
/actuator/metrics 
/actuator/events-stats 
