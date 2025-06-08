FROM openjdk:21

WORKDIR /app

# Download Jakarta Mail
ADD https://repo1.maven.org/maven2/com/sun/mail/jakarta.mail/2.0.1/jakarta.mail-2.0.1.jar lib/jakarta.mail.jar

# Copy your project files into the image
COPY . .

# Compile and include Jakarta Mail on the classpath
RUN mkdir -p out && javac -cp lib/jakarta.mail.jar -d out \
  src/MainApp.java \
  src/gui/*.java \
  src/models/*.java \
  src/models/enums/*.java \
  src/models/signature/*.java \
  src/utils/*.java

# Run with Jakarta Mail on the classpath
CMD ["java", "-cp", "out:lib/jakarta.mail.jar", "MainApp"]
