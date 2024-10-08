import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import QuizFromFile from './components/QuizFromFile';
import reportWebVitals from './reportWebVitals';
import LandingPage from "./components/LandingPage";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <LandingPage />
  </React.StrictMode>
);

reportWebVitals();
