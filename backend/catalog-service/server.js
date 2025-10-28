const express = require('express');
const app = express();

app.get('/health', (req, res) => {
  res.send('Catalog OK');
});

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
