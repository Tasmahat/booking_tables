FROM openjdk:19
WORKDIR /app
COPY ./build/libs/book_tables-0.0.1-SNAPSHOT.jar app/build/libs/book_tables-0.0.1-SNAPSHOT.jar
CMD ["./gradlew", "build"]
ENTRYPOINT ["java", "-jar", "app/build/libs/book_tables-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080