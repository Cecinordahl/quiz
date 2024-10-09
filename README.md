# Quiz Game

## Overview

This project is a simple quiz game using the [Open Trivia Database](https://opentdb.com/) API. The user will be presented with 10 multiple-choice questions pulled from all available categories and difficulties. The user also has the option to choose questions that are fetched from a local file, consisting of developer-relevant questions.

## Features

1. Displays 10 questions with four answer choices.
2. Uses React for the UI and Spring Boot for the backend.
3. The backend fetches questions from the Open Trivia Database API or a local file.

## Technology Stack

- Frontend: Javascript, React
- Backend: Java, Spring Boot
- Build tool: Maven
- Trivia API: Open Trivia Database API
- Future: Kafka for event-driven architecture, MySQL for storing high scores

## Project Structure

- `webapp`: Handles RESTful endpoints.
- `model`: Contains DTOs for data transfer.
- `domain`: Holds core domain objects (questions, answers).
- `service`: Business logic for orchestrating trivia questions.
- `consumer`: Responsible for consuming external APIs like Open Trivia Database.

## How to Run

1. **Backend**:
    - Navigate to the src/main/java/com/ceci/games/quiz/Main.java class and run it

2. **Frontend**:
    - Navigate to the React app directory and run:
      ```bash
      npm start
      ```

3. **Future Enhancements**:
    - Allow user to select quiz categories and difficulty from API.
    - Save high scores to a database and populate a leaderboard via Kafka event streams.

## Important Note on Strict Mode in React

In React 18+, Strict Mode intentionally re-renders components twice in development to identify side effects. However, I had to remove Strict Mode in my application because one of the external APIs I use has a rate limit, allowing only one request per IP address every 5 seconds. This rate limit caused issues when React re-rendered components multiple times, leading to frequent 429 errors (Too Many Requests).

## TODOs
- Create Tests
- Add design
- Create functionality to play again from the result page