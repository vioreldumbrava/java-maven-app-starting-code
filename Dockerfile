FROM amazoncorretto:21-alpine-jdk

EXPOSE 8080

WORKDIR /usr/app

COPY ./target/java-maven-app-*.jar ./

CMD java -jar java-maven-app-*.jar
