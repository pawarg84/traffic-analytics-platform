# Real-Time Traffic Dashboard

This project is a **Real-Time Traffic Dashboard** that streams and visualizes live traffic data using Kafka and displays it using a React-based web interface. The dashboard shows vehicle count data over time for different intersections, updating dynamically as data is streamed.

# Traffic Dashboard Screenshots: 
/assets/Chart.png
/assets/TableDisplay.png

## Features

- **Real-time Streaming:** Traffic data is streamed from a backend Kafka producer.
- **Dynamic Visualization:** Vehicle count over time is visualized using a line chart.
- **Multiple Intersection Tracking:** Tracks vehicle count for multiple intersections, each with a unique dataset and color.
- **Responsive UI:** A responsive and modern web interface built with React and Bootstrap.
- **Data Persistence:** Intersection-specific traffic data with vehicle counts and timestamps are displayed in a table.

## Technologies Used

- **Frontend:**
  - React.js
  - Bootstrap
  - Chart.js (for the line chart)
- **Backend:**
  - Spring Boot
  - Apache Kafka (for real-time data streaming)
- **Other Libraries:**
  - EventSource (for real-time data)
  - Chart.js (for visualization)
  **Docker**: For containerization

## Prerequisites

To run this project locally, ensure you have the following installed:

- **Node.js** (v14 or higher)
- **Java 11+**
- **Apache Kafka**
- **Spring Boot**

## Getting Started

Follow these steps to set up and run the project locally.

### Backend Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/AkhilaSrinidhi/TrafficDashboard-Frontend.git

2. **Docker Image:**
    Build Docker Image:
      docker build -t traffic-dashboard .
    Run Docker Container
      docker run -p 3000:3000 traffic-dashboard

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

