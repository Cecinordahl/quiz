import React, { useState, useEffect } from 'react';

function App() {
  const [question, setQuestion] = useState(null);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [score, setScore] = useState(0);
  const [userAnswered, setUserAnswered] = useState(false);
  const [userAnswerCorrect, setUserAnswerCorrect] = useState(false);
  const [questions, setQuestions] = useState([]);
  const [finished, setFinished] = useState(false);

  useEffect(() => {
    // Fetch the quiz questions
    fetch('/api/quiz/questions') // Assuming your backend serves questions from this endpoint
        .then(response => response.json())
        .then(data => setQuestions(data))
        .catch(error => console.error('Error fetching questions:', error));
  }, []);

  useEffect(() => {
    // Update current question if the question set and index are valid
    if (questions.length > 0 && currentQuestionIndex < questions.length) {
      setQuestion(questions[currentQuestionIndex]);
      setUserAnswered(false);
    }
  }, [questions, currentQuestionIndex]);

  const handleAnswerClick = (answer) => {
    if (!userAnswered) {
      setUserAnswered(true);
      const isCorrect = answer === question.correctAnswer;
      setUserAnswerCorrect(isCorrect);

      if (isCorrect) {
        setScore(score + 10); // 10 points per correct answer
      }

      // Move to the next question after a short delay
      setTimeout(() => {
        if (currentQuestionIndex < 9) {
          setCurrentQuestionIndex(currentQuestionIndex + 1);
        } else {
          setFinished(true); // Quiz finished
        }
      }, 1000); // Short delay to allow user to see feedback
    }
  };

  if (finished) {
    return (
        <div>
          <h1>Quiz Finished!</h1>
          <p>Your final score is: {score} out of 100</p>
        </div>
    );
  }

  return (
      <div className="App">
        {question ? (
            <div>
              <h1>{question.question}</h1>
              <div>
                {[...question.incorrectAnswers, question.correctAnswer]
                    .sort(() => Math.random() - 0.5) // Shuffle the answers
                    .map((answer, index) => (
                        <div
                            key={index}
                            style={{
                              border: '1px solid black',
                              padding: '10px',
                              margin: '5px',
                              cursor: 'pointer',
                              backgroundColor: userAnswered
                                  ? answer === question.correctAnswer
                                      ? 'green'
                                      : userAnswerCorrect && answer === question.correctAnswer
                                          ? 'green'
                                          : 'red'
                                  : 'white',
                            }}
                            onClick={() => handleAnswerClick(answer)}
                        >
                          {answer}
                        </div>
                    ))}
              </div>
              {userAnswered && (
                  <p>
                    {userAnswerCorrect ? 'Correct!' : `Wrong! The correct answer was: ${question.correctAnswer}`}
                  </p>
              )}
            </div>
        ) : (
            <p>Loading question...</p>
        )}
      </div>
  );
}

export default App;