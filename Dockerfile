FROM openjdk:21

WORKDIR /app

COPY . .

# Compile all Java files, including packages like gui, models, utils
RUN mkdir -p out && javac $(find src -name "*.java") -d out

# Since MainApp is NOT in a package, just call it directly
CMD ["java", "-cp", "out", "MainApp"]
