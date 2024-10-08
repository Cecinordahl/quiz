import React, { useEffect, useState } from 'react';
import Question from './Question';
import Result from './Result';
import '../styles/App.css';
import handleAnswerClick from '../utils/handleAnswerClick';
import handleNextClick from '../utils/handleNextClick';

function CommonQuizApp({ questions }) {
    const [question, setQuestion] = useState(null);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [score, setScore] = useState(0);
    const [userAnswered, setUserAnswered] = useState(false);
    const [userAnswerCorrect, setUserAnswerCorrect] = useState(false);
    const [finished, setFinished] = useState(false);
    const [shuffledAnswers, setShuffledAnswers] = useState([]);

    // useEffect hook to handle loading the current question and shuffling the answers
    useEffect(() => {
        if (questions.length > 0 && currentQuestionIndex < questions.length) {
            const currentQuestion = questions[currentQuestionIndex];
            setQuestion(currentQuestion);

            // Shuffle the answers only once when the question is loaded
            const answers = [
                ...currentQuestion.incorrectAnswers,
                currentQuestion.correctAnswer,
            ];
            setShuffledAnswers(shuffleArray(answers));

            // Reset the userAnswered flag so they can answer the new question
            setUserAnswered(false);
        }
    }, [questions, currentQuestionIndex]);

    // Function to shuffle an array (Fisher-Yates algorithm)
    function shuffleArray(array) {
        const shuffledArray = [...array];
        for (let i = shuffledArray.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
        }
        return shuffledArray;
    }

    // If the quiz is finished, display the result screen
    if (finished) {
        return <Result score={score} />;
    }

    // Helper function to render the current question
    function renderQuestion() {
        return (
            <Question
                question={question} // Pass the current question as a prop
                shuffledAnswers={shuffledAnswers} // Pass the shuffled answers
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
