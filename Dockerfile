FROM openjdk:21

WORKDIR /app

COPY src/ ./src

RUN mkdir out && javac src/MainApp.java -d out

CMD ["java", "-cp", "out", "MainApp"]
