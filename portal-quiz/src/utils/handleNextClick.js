const handleNextClick = (
    currentQuestionIndex,
    questions,
    setCurrentQuestionIndex,
    setFinished
) => {
    if (currentQuestionIndex < questions.length - 1) {
        setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
        setFinished(true);
    }
};

export default handleNextClick;
