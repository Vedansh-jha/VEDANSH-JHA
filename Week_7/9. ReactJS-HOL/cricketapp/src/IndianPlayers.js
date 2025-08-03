import React from 'react';

const IndianPlayers = () => {
  const OddPlayers = ['Player 1', 'Player 3', 'Player 5', 'Player 7', 'Player 9', 'Player 11'];
  const EvenPlayers = ['Player 2', 'Player 4', 'Player 6', 'Player 8', 'Player 10'];
  const [odd1, odd2, ...restOdd] = OddPlayers;
  const [even1, even2, ...restEven] = EvenPlayers;
  const T20players = ['Virat', 'Rohit', 'Surya'];
  const RanjiTrophy = ['Pujara', 'Rahane'];
  const AllPlayers = [...T20players, ...RanjiTrophy];

  return (
    <div>
      <h2>Odd Team Players</h2>
      <p>{odd1}, {odd2}, {restOdd.join(', ')}</p>

      <h2>Even Team Players</h2>
      <p>{even1}, {even2}, {restEven.join(', ')}</p>

      <h2>All Players (Merged)</h2>
      <p>{AllPlayers.join(', ')}</p>
    </div>
  );
};

export default IndianPlayers;
