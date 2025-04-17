import React from 'react';
import { Typography, Box } from '@mui/material';
import { useAuth } from '../hooks/useAuth';

const Documents: React.FC = () => {
  const { user } = useAuth();

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Documents
      </Typography>
      {user ? (
        <Typography>Document management coming soon...</Typography>
      ) : (
        <Typography>Please login to view documents.</Typography>
      )}
    </Box>
  );
};

export default Documents; 