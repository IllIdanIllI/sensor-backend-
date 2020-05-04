# Backend
## Stack
- **Database**: PostgreSQL 11
- **Java**: 11
- **Lombok**: 0.9
- **Spring**: 5
- **Hibernate**: 5
- **Tomcat**: 9

### Steps to start application:
1) Set up database with name _"sensor"_ via scripts. Location: `resources/script` of `dao` module
2) Change user, password and url if necessary in `application.properties`. Location: `resources` of `dao` module
3) Install **lombok** plugin if `IDE` does not have it
4) Set `8090` port for Tomcat server