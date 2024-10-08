import React from 'react';

const Answer = ({ answer, correctAnswer, userAnswered, userAnswerCorrect, onClick }) => {
    const getAnswerClass = () => {
        if (userAnswered) {
            return answer === correctAnswer ? 'answer correct' : 'answer incorrect';
        }
        return 'answer';
    };

    return (
        <div className={getAnswerClass()} onClick={onClick}>
            {answer}
        </div>
    );
};

export default Answer;
