FROM gradle:jdk11 AS BUILD
WORKDIR /todo_build
COPY . /todo_build
RUN ./gradlew build

FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
WORKDIR /todolist
COPY --from=BUILD /todo_build/build/libs/todolist-0.0.1-SNAPSHOT.jar \
                  /todolist/todolist.jar

CMD ["java", "-jar", "/todolist/todolist.jar"]
