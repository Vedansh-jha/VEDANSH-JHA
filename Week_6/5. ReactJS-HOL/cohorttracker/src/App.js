import React from 'react';
import CohortDetails from './CohortDetails';

function App() {
  return (
    <div className="App">
      <h1>My Academy Dashboard</h1>
      <CohortDetails
        name="React Bootcamp"
        status="Ongoing"
        startDate="Jan 2025"
        endDate="Mar 2025"
      />
      <CohortDetails
        name="Node.js Cohort"
        status="Completed"
        startDate="Sep 2024"
        endDate="Dec 2024"
      />
    </div>
  );
}

export default App;
