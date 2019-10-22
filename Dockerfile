FROM java:8 
COPY target/user-profile-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "user-profile-0.0.1-SNAPSHOT.jar"]
