# 🚦 Traffic Analytics Platform

A full-stack real-time traffic monitoring and analytics platform built using **Spring Boot**, **Apache Kafka (Confluent Cloud)**, **MySQL**, and **React**. It visualizes live traffic data with dynamic charts and tables powered by **Server-Sent Events (SSE)**.

> 🔗 **Live Frontend:** [https://traffic-analytics-platform.vercel.app](https://traffic-analytics-platform.vercel.app)  
> 🔗 **Live Backend SSE Endpoint:** [https://traffic-analytics-platform.onrender.com/traffic-stream](https://traffic-analytics-platform.onrender.com/traffic-stream)

---

## 🧰 Tech Stack

| Layer       | Technology                                      |
|-------------|--------------------------------------------------|
| Frontend    | React, Chart.js, Bootstrap                      |
| Backend     | Spring Boot, Apache Kafka, SSE                  |
| Database    | MySQL (Railway)                                 |
| Streaming   | Kafka on Confluent Cloud + Server-Sent Events   |
| Deployment  | Vercel (Frontend), Render (Backend)             |

---

## 🚀 Features

- 📡 Real-time traffic data simulation using Kafka Producer
- 🎯 Kafka Consumer that streams + persists data to MySQL
- 📊 React dashboard with live charts and tabular view
- 🔁 Live updates via Server-Sent Events (SSE)
- 🧪 Testable via REST and SSE endpoints
- 🔐 Secure backend with CORS support for frontend integration

---

## 📁 Project Structure

```
traffic-analytics/
├── TrafficDashboard-Backend/     # Spring Boot + Kafka + MySQL
└── TrafficDashboard-Frontend/    # React + Chart.js + Bootstrap
```

---

## ⚙️ Backend Setup (Spring Boot + Kafka)

### 1. Requirements

- Java 17+
- Maven
- Kafka (Provisioned via Confluent Cloud)
- MySQL (hosted on Railway or local)

### 2. Environment Variables (on Render)

```env
DB_URL=jdbc:mysql://<your-mysql-url>
DB_USERNAME=root
DB_PASSWORD=********

# Confluent Kafka Credentials (set these via environment variables, not hardcoded)

KAFKA_BOOTSTRAP_SERVERS=<your_bootstrap_server>
KAFKA_API_KEY=<your_api_key>
KAFKA_API_SECRET=<your_api_secret>
```


### 3. Run Backend Locally

```bash
cd TrafficDashboard-Backend
./mvnw spring-boot:run
```

> Streams traffic data every 2 seconds and broadcasts it at `/traffic-stream`.

---

## 💻 Frontend Setup (React + Chart.js)

### 1. Install & Run

```bash
cd TrafficDashboard-Frontend
npm install
npm start
```

### 2. Production Build

```bash
npm run build
```

Deploy the `build/` folder to **Vercel** or **Netlify**.

---

## ☁️ Live Demo Access

- **Frontend Dashboard** → [https://traffic-analytics-platform.vercel.app](https://traffic-analytics-platform.vercel.app)
- **Real-time Data Stream** → [https://traffic-analytics-platform.onrender.com/traffic-stream](https://traffic-analytics-platform.onrender.com/traffic-stream)
- **Backend Root** → [https://traffic-analytics-platform.onrender.com](https://traffic-analytics-platform.onrender.com)  
  (_Returns service confirmation message_)

---

## 🧪 Sample SQL Query

```sql
SELECT * FROM trafficdata ORDER BY timestamp DESC LIMIT 10;
```

**Sample Output:**

| id | location        | timestamp     | vehicle_count |
|----|------------------|---------------|----------------|
|  1 | Intersection-5   | 1750507130000 | 23             |
|  2 | Intersection-8   | 1750507233000 | 12             |
|  3 | Intersection-2   | 1750507331000 | 41             |

---

## 📸 Screenshots

| Live Chart View                      | Real-time Table                     |
|--------------------------------------|-------------------------------------|
| ![Chart](./screenshots/traffic-dashboard.JPG) | ![Table](./screenshots/traffic-dashboard-table.JPG) |

> _Ensure these screenshots exist under `screenshots/` folder in your frontend repo._

---

## 🙋‍♂️ Author

Developed with ❤️ by [Ganesh Pawar](https://github.com/pawarg84)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE)