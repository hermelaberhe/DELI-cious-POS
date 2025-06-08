FROM openjdk:21

WORKDIR /app

COPY . .

RUN mkdir -p out && javac -d out \
  src/MainApp.java \
  src/gui/*.java \
  src/models/*.java \
  src/models/enums/*.java \
  src/models/signature/*.java \
  src/utils/*.java \

CMD ["java", "-cp", "out", "MainApp"]
