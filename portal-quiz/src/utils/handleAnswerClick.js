// utils/handleAnswerClick.js
const handleAnswerClick = (
    answer,
    question,
    userAnswered,
    setUserAnswered,
    setUserAnswerCorrect,
    score,
    setScore
) => {
    if (!userAnswered) {
        const isCorrect = answer === question.correctAnswer;
        setUserAnswered(true);
        setUserAnswerCorrect(isCorrect);

        if (isCorrect) {
            setScore(score + 10); // 10 points per correct answer
        }
    }
};

export default handleAnswerClick;
