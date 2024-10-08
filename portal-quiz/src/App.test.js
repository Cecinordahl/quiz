import { render, screen } from '@testing-library/react';
import QuizFromFile from './components/QuizFromFile';

test('renders learn react link', () => {
  render(<QuizFromFile />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
