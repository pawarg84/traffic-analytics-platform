import React, { useEffect, useState } from 'react';
import { Line } from 'react-chartjs-2';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

// Register the components for Chart.js
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const Home = () => {
  const [trafficData, setTrafficData] = useState({});
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [],
  });

  useEffect(() => {
    const eventSource = new EventSource('http://localhost:8080/traffic-stream');

    eventSource.onmessage = function (event) {
      const data = JSON.parse(event.data);
      console.log('New message from server:', data);

      setTrafficData((prevData) => {
        const updatedData = { ...prevData };

        if (!updatedData[data.intersectionId]) {
          updatedData[data.intersectionId] = { labels: [], data: [] };
        }

        updatedData[data.intersectionId].labels.push(new Date(data.timestamp).toLocaleTimeString());
        updatedData[data.intersectionId].data.push(data.vehicleCount);

        // Update chart datasets based on intersection-specific data
        const datasets = Object.keys(updatedData).map((intersectionId) => ({
          label: `Intersection ${intersectionId}`,
          data: updatedData[intersectionId].data,
          fill: false,
          backgroundColor: getRandomColor(),
          borderColor: getRandomColor(),
        }));

        setChartData({
          labels: updatedData[data.intersectionId].labels,
          datasets: datasets,
        });

        return updatedData;
      });
    };

    eventSource.onerror = function (event) {
      console.error('Error in SSE:', event);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, []);

  // Helper function to generate random colors for each intersection line
  const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  };

  return (
    <div className="container">
      <h2>Real-Time Traffic Dashboard</h2>

      {/* Line Chart for Vehicle Count */}
      <div className="chart">
        <Line data={chartData} key={JSON.stringify(chartData)} />
      </div>

      {/* Traffic Data Table */}
      <div className="traffic-table">
        <h3>Intersection Traffic Details</h3>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Intersection ID</th>
              <th>Vehicle Count</th>
              <th>Timestamp</th>
            </tr>
          </thead>
          <tbody>
            {Object.keys(trafficData).length > 0 ? (
              Object.keys(trafficData).map((intersectionId, index) => (
                trafficData[intersectionId].data.map((count, idx) => (
                  <tr key={`${intersectionId}-${idx}`}>
                    <td>{intersectionId}</td>
                    <td>{count}</td>
                    <td>{trafficData[intersectionId].labels[idx]}</td>
                  </tr>
                ))
              ))
            ) : (
              <tr>
                <td colSpan="3">No data available</td>
              </tr>
            )}
          </tbody>
        </Table>
      </div>
    </div>
  );
};

export default Home;
