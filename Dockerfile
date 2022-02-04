FROM openjdk:11.0.7
ADD target/terrains*.jar app.jar
CMD ["java","-jar","/app.jar"]
EXPOSE 8083
