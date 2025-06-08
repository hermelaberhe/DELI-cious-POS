# Use OpenJDK 21 as base image
FROM openjdk:21

# Set working directory
WORKDIR /app

# Create lib folder for dependencies
RUN mkdir lib

# Download SQLite JDBC driver and Jakarta Mail jar
ADD https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.2.0/sqlite-jdbc-3.45.2.0.jar lib/sqlite-jdbc.jar
ADD https://repo1.maven.org/maven2/com/sun/mail/jakarta.mail/2.0.1/jakarta.mail-2.0.1.jar lib/jakarta.mail.jar

# Copy all source files into the image
COPY . .

# Compile all .java files, using both JARs on classpath
RUN mkdir -p out && javac -cp "lib/*" -d out \
  src/MainApp.java \
  src/gui/*.java \
  src/models/*.java \
  src/models/enums/*.java \
  src/models/signature/*.java \
  src/utils/*.java

# Run the application
CMD ["java", "-cp", "out:lib/*", "MainApp"]
