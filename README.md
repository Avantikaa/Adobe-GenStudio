# ️ Roman Numeral Converter Service

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
git clone https://github.com/your-username/your-repo.git
cd your-repo
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

Observability: Metrics exposed at /actuator/prometheus, traces sent to Jaeger.

## 🔗 References
## 🔗 References

- [Roman numerals on Wikipedia](https://en.wikipedia.org/wiki/Roman_numerals)
- [Spring Boot Actuator](https://spring.io/projects/spring-boot)
- [React Spectrum](https://react-spectrum.adobe.com/react-spectrum/)
- [OpenTelemetry](https://opentelemetry.io/)
- [Jaeger Tracing](https://www.jaegertracing.io/)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)