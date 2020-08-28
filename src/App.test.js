import React from 'react';
import { render } from '@testing-library/react';
import StudentViewComponent from './StudentViewComponent';

test('renders learn react link', () => {
  const { getByText } = render(<StudentViewComponent />);
  const linkElement = getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
