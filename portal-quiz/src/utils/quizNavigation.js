export const navigateToNextQuestion = (
    currentQuestionIndex,
    setCurrentQuestionIndex,
    setFinished,
    totalQuestions = 10,
    delay = 1000
) => {
    setTimeout(() => {
        if (currentQuestionIndex < totalQuestions - 1) {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
        } else {
            setFinished(true); // Quiz finished
        }
    }, delay);
};