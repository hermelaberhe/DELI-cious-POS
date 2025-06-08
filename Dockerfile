FROM openjdk:21

WORKDIR /app

COPY . .

# Compile ALL Java files recursively using Java's native wildcard
RUN mkdir -p out && javac -d out $(find src -type f -name "*.java" || echo src/**/*.java)

CMD ["java", "-cp", "out", "MainApp"]
