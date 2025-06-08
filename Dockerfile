FROM openjdk:21

WORKDIR /app

# Copy everything from your repo
COPY . .

# Compile your Java file
RUN mkdir -p out && javac src/MainApp.java -d out

# Run the app
CMD ["java", "-cp", "out", "MainApp"]
