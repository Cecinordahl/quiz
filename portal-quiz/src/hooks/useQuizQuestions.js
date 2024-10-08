import {useEffect, useState} from 'react';

const useQuizQuestions = (url) => {
    const [questions, setQuestions] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Make sure we have a valid URL before attempting to fetch
        if (url) {
            fetch(url)
                .then(response => response.json())
                .then(data => setQuestions(data))
                .catch(err => {
                    console.error('Error fetching questions:', err);
                    setError('Error fetching questions');
                });
        }
    }, [url]); // The hook will re-run whenever the URL changes

    return {questions, error};
};

export default useQuizQuestions;