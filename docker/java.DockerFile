FROM openjdk:17
WORKDIR /usr/src/app
COPY {{FILENAME}} /usr/src/app/
RUN javac {{FILENAME}}
CMD ["java", "Main"]
