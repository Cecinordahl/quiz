import React, { useEffect, useState } from 'react';
import Question from './Question';
import Result from './Result';
import '../styles/App.css';
import handleAnswerClick from '../utils/handleAnswerClick';
import handleNextClick from '../utils/handleNextClick';

function CommonQuizApp ({ questions }) {
    // State to store the current question
    const [question, setQuestion] = useState(null);
    // State to store the index of the current question being displayed
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    // State to store the player's score (starts at 0)
    const [score, setScore] = useState(0);
    // State to track if the user has answered the current question
    const [userAnswered, setUserAnswered] = useState(false);
    // State to track if the user's answer to the current question is correct
    const [userAnswerCorrect, setUserAnswerCorrect] = useState(false);
    // State to track if the quiz is finished (i.e., when all questions are answered)
    const [finished, setFinished] = useState(false);

    // Custom hook to fetch quiz questions from the API

    // useEffect hook to handle loading the current question when questions or index change
    useEffect(() => {
        // Check if we have questions and the index is valid
        if (questions.length > 0 && currentQuestionIndex < questions.length) {
            // Set the current question to be displayed
            setQuestion(questions[currentQuestionIndex]);

            // Reset the userAnswered flag so they can answer the new question
            setUserAnswered(false);
        }
    }, [questions, currentQuestionIndex]); // Dependency array: runs when `questions` or `currentQuestionIndex` changes

    // If the quiz is finished, display the result screen
    if (finished) {
        return <Result score={score} />;
    }

    // Helper function to render the current question
    function renderQuestion() {
        return (
            <Question
                question={question} // Pass the current question as a prop
                handleAnswerClick={(answer) =>
                    handleAnswerClick(
                        answer, // The answer selected by the user
                        question, // The current question being answered
                        userAnswered, // Whether the user has answered the question
                        setUserAnswered, // Function to set that the user has answered
                        setUserAnswerCorrect, // Function to set if the answer is correct
                        score, // The current score of the user
                        setScore // Function to update the score based on correctness
                    )
                }
                userAnswered={userAnswered} // Pass whether the user has already answered
                userAnswerCorrect={userAnswerCorrect} // Pass whether the user's answer was correct
            />
        );
    }

    // Helper function to render the "Next" button that moves to the next question
    function renderNextButton() {
        return (
            <button
                onClick={() =>
                    handleNextClick(
                        currentQuestionIndex, // The current index of the question
                        questions, // All available questions
                        setCurrentQuestionIndex, // Function to move to the next question
                        setFinished // Function to mark the quiz as finished
                    )
                }
                className="next-button"
            >
                Next
            </button>
        );
    }

    // Main return statement: This is what gets rendered to the screen
    return (
        <div className="App">
            {question ? (
                <div>
                    {/* Render the question if there is one */}
                    {renderQuestion()}

                    {/* Render the "Next" button only after the user has answered */}
                    {userAnswered && renderNextButton()}
                </div>
            ) : (
                // Display a loading message while waiting for the questions to load
                <p>Loading question...</p>
            )}
        </div>
    );
}

export default CommonQuizApp;
