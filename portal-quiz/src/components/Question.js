import React from 'react';
import Answer from './Answer';

const Question = ({ question, shuffledAnswers, handleAnswerClick, userAnswered, userAnswerCorrect }) => {
    return (
        <div className="question-container">
            <h1>{question.question}</h1>
            <div>
                {shuffledAnswers.map((answer, index) => (
                    <Answer
                        key={index}
                        answer={answer}
                        correctAnswer={question.correctAnswer}
                        userAnswered={userAnswered}
                        userAnswerCorrect={userAnswerCorrect}
                        onClick={() => handleAnswerClick(answer)}
                    />
                ))}
            </div>
            {userAnswered && (
                <p className="result-message">
                    {userAnswerCorrect
                        ? 'Correct!'
                        : `Wrong! The correct answer was: ${question.correctAnswer}`}
                </p>
            )}
        </div>
    );
};

export default Question;
