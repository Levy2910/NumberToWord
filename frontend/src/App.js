import { useState } from 'react';
import './App.css';

function App() {
  const [number, setNumber] = useState('');
  const [word, setWord] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/converting', {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain',
        },
        body: number,
      });

      if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }

      const result = await response.text();
      setWord(result);
      setError('');
    } catch (err) {
      setError(err.message);
      setWord('');
    }
  };


  return (
    <div className="App">
      <h2>Enter a Number</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="number"
          placeholder="Please enter a valid number"
          value={number}
          onChange={(e) => setNumber(e.target.value)}
          required
        />
        <p className="note">
          Valid numbers may include digits, ".", "+" or "-". No other characters are allowed.
        </p>
        <button type="submit">Convert</button>
      </form>

      {word && (
        <div className="result">
          <h3>Result:</h3>
          <p>{word}</p>
        </div>
      )}

      {error && (
        <div className="error-box">
          <strong className="error-title">⚠️ Error:</strong>
          <p className="error-text">{error}</p>
          <button className="dismiss-button" onClick={() => setError('')}>
            Dismiss
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
