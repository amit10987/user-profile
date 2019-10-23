FROM java:8 
COPY target/user-profile-0.0.1-SNAPSHOT.jar /
COPY clicks.csv /
COPY selections.csv /
CMD ["java", "-jar", "user-profile-0.0.1-SNAPSHOT.jar"]
