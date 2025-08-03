import React, { Component } from 'react';
import CurrencyConvertor from './CurrencyConvertor';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0,
    };
    this.increment = this.increment.bind(this);
    this.sayHello = this.sayHello.bind(this);
    this.handleIncrementClick = this.handleIncrementClick.bind(this);
    this.sayWelcome = this.sayWelcome.bind(this);
  }

  increment() {
    this.setState((prevState) => ({
      count: prevState.count + 1,
    }));
  }

  decrement = () => {
    this.setState((prevState) => ({
      count: prevState.count - 1,
    }));
  };

  sayHello() {
    alert('Hello! This is a static message.');
  }
  handleIncrementClick() {
    this.increment();
    this.sayHello();
  }

  sayWelcome(message) {
    alert(`Welcome! ${message}`);
  }

  handleOnPress = (e) => {
    alert('I was clicked');
  };

  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h1>Event Examples App</h1>
        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.handleIncrementClick}>Increment</button>
        <button onClick={this.decrement}>Decrement</button>
        <br /><br />
        <button onClick={() => this.sayWelcome('You are welcome here!')}>Say Welcome</button>
        <br /><br />
        <button onClick={this.handleOnPress}>OnPress (Synthetic Event)</button>

        <br /><br />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
