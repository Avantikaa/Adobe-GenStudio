# ️ Adobe GenStudio - Roman Numeral Converter Service

## 📜 Overview

This project implements a backend service and frontend application to convert integers into **Roman numerals**, supporting numbers from 1 to 3999.

This project includes the following:

- Spring Boot backend with REST API
- React frontend with Spectrum UI
- Observability (logs, metrics, traces)
- Docker-based containerization
- NGINX-based reverse proxy and load balancing
- CORS configuration
- Prometheus and Grafana integration for metrics

---

## 🔢 Roman Numeral Specification

Roman numerals are a numeral system originating in ancient Rome. Numbers are represented using combinations of letters from the Latin alphabet:

| Value | Symbol |
|---------|--------|
| 1       | I      |
| 4       | IV     |
| 5       | V      |
| 9       | IX     |
| 10      | X      |
| 40      | XL     |
| 50      | L      |
| 90      | XC     |
| 100     | C      |
| 400     | CD     |
| 500     | D      |
| 900     | CM     |
| 1000    | M      |

Learn more: [Wikipedia - Roman Numerals](https://en.wikipedia.org/wiki/Roman_numerals)

---
## 💡 Approach and Solution

### Problem statement

Convert user-entered numbers to Roman numerals, with a clean UI and proper error handling.

---

### Approach

- Implemented a **React frontend** to provide a simple, interactive form for input and display.
- Built a **Spring Boot backend** using Java to handle the Roman numeral conversion logic, using a clear and maintainable lookup table strategy.
- Integrated **Prometheus and Grafana** for observability and real-time metrics visualization.

---

### Why this solution

- The **lookup table approach** for Roman numerals ensures constant-time conversion and clear logic.
- Splitting responsibilities between **backend (business logic)** and **frontend (UI and user interaction)** improves modularity and maintainability.

---

## 🗂️ Project Structure
```
project-root/
│
├── backend/ # Spring Boot backend (API & business logic)
│ ├── src/main/java/org/roman/Controller/RomanController.java
│ ├── src/main/java/org/roman/Service/RomanService.java
│ ├── src/main/java/org/roman/Model/RomanResponse.java
│ ├── src/test/java/... (unit & integration tests)
│ └── build.gradle
│
├── frontend/ # React frontend (UI)
│ ├── src/components/RomanConverter.tsx
│ ├── src/api/index.ts
│ ├── package.json
│ └── Dockerfile
│
├── nginx/ # NGINX config
│ └── nginx.conf
│
├── docker-compose.yml # Multi-service orchestration
│
├── README.md # Project documentation
│
└── .gitignore
```

---

## 🌟 Features

- Convert integers (1–3999) to Roman numerals
- UI with Adobe Spectrum components
- Error handling and validations
- Logs, metrics (via Micrometer), and traces (via OpenTelemetry and Jaeger)
- Prometheus metrics scraping & Grafana visualization
- Dockerized deployment with separate containers for frontend, backend, NGINX, Prometheus, Grafana, and Jaeger
- Supports light and dark mode in UI (based on system settings)

---


## 🚀 Installation & Running

### 1️⃣ Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

---

### 2️⃣ Clone the repository 

```bash
git clone https://github.com/Avantikaa/Adobe-GenStudio.git
````
- Build the backend
```bash
./gradlew build
```
- docker build from the root project Adobe-GenStudio

```bash
docker compose up --build
```

This will build and start:

- Backend service (Spring Boot)
- Frontend service (React with NGINX)
- NGINX reverse proxy
- Jaeger (tracing UI)
- Prometheus
- Grafana

| Component     | URL                                                                                        |
| ------------- | ------------------------------------------------------------------------------------------ |
| Frontend (UI) | [http://localhost](http://localhost)                                                       |
| Backend API   | [http://localhost:8080/romannumeral?query=10](http://localhost:8080/romannumeral?query=10) |
| Jaeger UI     | [http://localhost:16686](http://localhost:16686)                                           |
| Prometheus    | [http://localhost:9090](http://localhost:9090)                                             |
| Grafana       | [http://localhost:3001](http://localhost:3001)                                             |

## ⚙️ Configuration Highlights

CORS: Configured in backend and NGINX to allow frontend communication.

NGINX: Routes /api/ calls to backend and others to frontend.

Observability: 
Metrics exposed at /actuator/prometheus, traces sent to Jaeger.
Metrics dashboard can be created on Grafana by adding Prometheus data source.

## ⚙️ Technology and Dependency Choices

This project uses carefully chosen frameworks and libraries to ensure scalability, maintainability, and a smooth developer experience.

### 🌐 Frontend

- **React**: Chosen for its component-based architecture and wide ecosystem, enabling us to build a modular and highly interactive UI.
- **TypeScript**: Adds static typing to JavaScript, improving code safety and developer productivity, and catching errors at compile time.
- **Adobe React Spectrum**: Provides accessible, consistent, and well-designed UI components out of the box, helping deliver a professional-looking frontend quickly.

---

### ☕ Backend

- **Spring Boot**: A popular, production-ready Java framework that simplifies creating RESTful services. It provides built-in support for dependency injection, auto-configuration, and monitoring.
- **Prometheus (Micrometer metrics)**: Used for collecting metrics from the backend. Spring Boot integrates easily with Micrometer, which exposes metrics in Prometheus format.
- **Gradle**: Chosen as the build tool for managing dependencies, compiling, and packaging the backend application.

---

### 📊 Observability

- **Prometheus**: Widely adopted, open-source monitoring system that collects and stores metrics data efficiently.
- **Grafana**: Flexible dashboarding and visualization tool, ideal for creating real-time charts and monitoring panels for backend metrics.

---

### 💡 Reasoning behind these choices

- **Maintainability**: Spring Boot and React both emphasize modular design, making the codebase easier to extend and refactor.
- **Scalability & production readiness**: Using proven technologies (Spring Boot, Prometheus, Grafana) ensures the stack can handle future growth and is easy to monitor in production.
- **Community support**: All these tools have large, active communities and long-term support, reducing future risks.

---


## 🔗 References

- [Roman numerals on Wikipedia](https://en.wikipedia.org/wiki/Roman_numerals)
- [Spring Boot Actuator](https://spring.io/projects/spring-boot)
- [React Spectrum](https://react-spectrum.adobe.com/react-spectrum/)
- [OpenTelemetry](https://opentelemetry.io/)
- [Jaeger Tracing](https://www.jaegertracing.io/)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)