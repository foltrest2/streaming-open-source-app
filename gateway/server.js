import axios from 'axios';
import express from 'express';

const app = express();

app.get('/health', (req, res) => {
  res.send('Gateway OK');
});

const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

const services = {
  users: 'http://users:5000/actuator/health',
  billing: 'http://billing:8081/actuator/health',
  catalog: 'http://catalog:4000/actuator/health',
  streaming: 'http://streaming:8080/actuator/health',
  recommendation: 'http://recommendation:8082/actuator/health'
};

app.get('/health/all', async (req, res) => {
  const results = {};

  await Promise.all(
    Object.entries(services).map(async ([name, url]) => {
      try {
        const response = await axios.get(url);
        results[name] = response.data.status || 'UNKNOWN';
      } catch (error) {
        results[name] = 'DOWN';
      }
    })
  );

  res.json(results);
});