import React from 'react';

function App() {
  const heading = <h1>Office Space Rental Listings</h1>;

  const officeImage = 'https://via.placeholder.com/300x200.png?text=Office+Space';

  const office = {
    name: 'Premium Office Suite',
    rent: 55000,
    address: '123 Business Street, Mumbai',
  };

  const offices = [
    { id: 1, name: 'Downtown Office', rent: 45000, address: 'Downtown Road, Delhi' },
    { id: 2, name: 'IT Park Office', rent: 70000, address: 'Tech City, Bangalore' },
    { id: 3, name: 'Business Bay Office', rent: 60000, address: 'Business Bay, Pune' },
    { id: 4, name: 'Startup Hub', rent: 30000, address: 'Startup Street, Hyderabad' },
  ];

  return (
    <div>
      {heading}

      <img src={officeImage} alt="Office Space" />

      <h2>Single Office Details</h2>
      <p>Name: {office.name}</p>
      <p>Rent: 
        <span style={{ color: office.rent < 60000 ? 'red' : 'green' }}>
          ₹{office.rent}
        </span>
      </p>
      <p>Address: {office.address}</p>

      <h2>Available Offices</h2>
      <ul>
        {offices.map((o) => (
          <li key={o.id}>
            <strong>{o.name}</strong><br/>
            Rent: <span style={{ color: o.rent < 60000 ? 'red' : 'green' }}>
              ₹{o.rent}
            </span><br/>
            Address: {o.address}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
