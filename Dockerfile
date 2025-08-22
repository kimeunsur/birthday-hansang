# ---- Build stage ----
FROM gradle:8.4.0-jdk17 AS builder
WORKDIR /app

# 캐시 최적화: 먼저 빌드 스크립트/래퍼만
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew gradlew
RUN ./gradlew --version >/dev/null || true

# 소스 복사 후 빌드
COPY . .
RUN ./gradlew clean build -x test

# ---- Runtime stage ----
FROM amazoncorretto:17
WORKDIR /app

# (헬스체크용) curl 설치
RUN yum -y install curl && yum clean all


# JAR 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
