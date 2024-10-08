# Quiz Game

## Overview

This project is a simple quiz game using the [Open Trivia Database](https://opentdb.com/) API. The user will be presented with 10 multiple-choice questions pulled from all available categories and difficulties.

## Features

1. Displays 10 questions with four answer choices.
2. Uses React for the UI and Spring Boot for the backend.
3. The backend fetches questions from the Open Trivia Database API.
4. Future plan: allow users to select specific categories and difficulty levels.
5. Future plan: store user scores in a database, and trigger a Kafka event after quiz completion to populate a high score table.

## Technology Stack

- Frontend: React
- Backend: Java, Spring Boot
- Build tool: Maven
- Trivia API: Open Trivia Database API
- Future: Kafka for event-driven architecture, PostgreSQL for storing high scores

## Project Structure

- `webapp`: Handles RESTful endpoints.
- `model`: Contains DTOs for data transfer.
- `domain`: Holds core domain objects (questions, answers).
- `service`: Business logic for orchestrating trivia questions.
- `consumer`: Responsible for consuming external APIs like Open Trivia Database.

## How to Run

1. **Backend**:
    - Navigate to the project folder and run:
      ```bash
      mvn spring-boot:run
      ```

2. **Frontend**:
    - Navigate to the React app directory and run:
      ```bash
      npm start
      ```

3. **Future Enhancements**:
    - Allow user to select quiz categories and difficulty.
    - Save high scores to a database and populate a leaderboard via Kafka event streams.


## TODOs
- Remove duplicate questions from json
- Create Tests
- Add design
- Create functionality to play again from the result page