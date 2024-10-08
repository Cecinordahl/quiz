import React from 'react';

function Result({score}) {
    return (
        <div>
            <h1>Quiz Finished!</h1>
            <p>Your final score is: {score} out of 100</p>
        </div>
    );
}

export default Result;
