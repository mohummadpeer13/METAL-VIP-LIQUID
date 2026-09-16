# Étape 1
FROM gradle:8.10.2-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle lwjgl3:dist

# Étape 2
FROM eclipse-temurin:17-jre

# variables d'environnement
ENV DISPLAY=:0
ENV XDG_RUNTIME_DIR=/tmp

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    libwayland-client0 \
    libwayland-server0 \
    libwayland-cursor0 \
    libxcb1 \
    libx11-xcb1 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    mesa-utils \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=builder /app/lwjgl3/build/libs/*.jar /app

CMD ["java", "-jar", "Metal Vip Liquid-1.0.0.jar"]
  
  
  
  
  
  
  
  
  
