import React, { Component } from 'react';

class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = {
      rupees: '',
      euro: '',
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({ rupees: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    const rupees = parseFloat(this.state.rupees);
    const euro = rupees / 90; // Example: 1 Euro = ₹90
    this.setState({ euro: euro.toFixed(2) });
  }
render() {
    return (
      <div>
        <h2>Currency Convertor</h2>
        <form onSubmit={this.handleSubmit}>
          <label>
            Rupees:
            <input
              type="number"
              value={this.state.rupees}
              onChange={this.handleChange}
            />
          </label>
          <button type="submit">Convert</button>
        </form>
        {this.state.euro && (
          <p>Euro: €{this.state.euro}</p>
        )}
      </div>
    );
  }
}

export default CurrencyConvertor;
