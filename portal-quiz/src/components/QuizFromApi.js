import React, { useEffect, useState } from 'react';
import '../styles/App.css';
import useQuizQuestions from '../hooks/useQuizQuestions';
import CommonQuizApp from "./CommonQuizApp";

function QuizFromApi() {
    const { questions, error } = useQuizQuestions('/api/quiz/questions/external-api');

    if (error) return <p>{error}</p>;
    if (questions.length === 0) return <p>Loading questions...</p>;

    return (
        <CommonQuizApp questions={questions} />
    );
}

export default QuizFromApi;
