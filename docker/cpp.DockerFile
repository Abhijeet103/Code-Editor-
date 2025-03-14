FROM gcc:latest
WORKDIR /usr/src/app
COPY {{FILENAME}} /usr/src/app/
RUN g++ -o Main {{FILENAME}}
CMD ["./Main"]
