FROM openjdk:12-alpine
COPY ./build/libs/*.jar shaworld-backend.jar
ENTRYPOINT ["java", "-Xmx100m", "-jar", "-Duser.timezone=Asia/Seoul", "/shaworld-backend.jar"]