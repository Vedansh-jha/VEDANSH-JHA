import React from 'react';

const ListofPlayers = () => {
  // ✅ Declare an array with 11 players
  const players = [
    { name: 'Player 1', score: 80 },
    { name: 'Player 2', score: 65 },
    { name: 'Player 3', score: 90 },
    { name: 'Player 4', score: 50 },
    { name: 'Player 5', score: 100 },
    { name: 'Player 6', score: 30 },
    { name: 'Player 7', score: 75 },
    { name: 'Player 8', score: 95 },
    { name: 'Player 9', score: 45 },
    { name: 'Player 10', score: 85 },
    { name: 'Player 11', score: 70 },
  ];

  // ✅ Filter players with score below 70 using arrow function
  const lowScorers = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>{player.name} - Score: {player.score}</li>
        ))}
      </ul>

      <h2>Players with score below 70</h2>
      <ul>
        {lowScorers.map((player, index) => (
          <li key={index}>{player.name} - Score: {player.score}</li>
        ))}
      </ul>
    </div>
  );
};

export default ListofPlayers;
