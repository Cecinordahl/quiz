import React, { useState } from 'react';
import QuizFromFile from './QuizFromFile';  // Import the QuizFromFile component
import QuizFromApi from './QuizFromApi';    // Import the QuizFromApi component

function LandingPage() {
    // State to track the selected quiz
    const [selectedQuiz, setSelectedQuiz] = useState('QuizFromFile');  // Default to QuizFromFile
    // State to track if the user has clicked "Play" to start the quiz
    const [isQuizStarted, setIsQuizStarted] = useState(false);

    // Handle selection from the dropdown
    const handleQuizSelection = (event) => {
        setSelectedQuiz(event.target.value);
    };

    // Handle the "Play" button click, which starts the quiz
    const handlePlayClick = () => {
        setIsQuizStarted(true);  // When "Play" is clicked, start the quiz
    };

    // Render the appropriate quiz component based on the selected option
    const renderQuiz = () => {
        switch (selectedQuiz) {
            case 'QuizFromApi':
                return <QuizFromApi />;
            case 'QuizFromFile':
            default:
                return <QuizFromFile />;
        }
    };

    return (
        <div>
            {!isQuizStarted ? (
                // Show the dropdown and Play button if the quiz has not started
                <div>
                    <h1>Select Your Quiz</h1>

                    {/* Dropdown to select the quiz */}
                    <select value={selectedQuiz} onChange={handleQuizSelection}>
                        <option value="QuizFromFile">Quiz from File</option>
                        <option value="QuizFromApi">Quiz from API</option>
                    </select>

                    {/* Play button */}
                    <button onClick={handlePlayClick} className="play-button">
                        Play
                    </button>
                </div>
            ) : (
                // Render the quiz only after the user clicks "Play"
                <div className="quiz-container">
                    {renderQuiz()}
                </div>
            )}
        </div>
    );
}

export default LandingPage;
