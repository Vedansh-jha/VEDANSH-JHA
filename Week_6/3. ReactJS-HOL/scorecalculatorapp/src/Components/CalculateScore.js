import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const average = total / goal;

  return (
    <div className="score-container">
      <h1>Student Score Calculator</h1>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>School:</strong> {school}</p>
      <p><strong>Total Marks:</strong> {total}</p>
      <p><strong>Subjects:</strong> {goal}</p>
      <p className="average">Average Score: {average.toFixed(2)}</p>
    </div>
  );
}

export default CalculateScore;
