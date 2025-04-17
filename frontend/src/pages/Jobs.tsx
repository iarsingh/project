import React from 'react';
import { Typography, Box } from '@mui/material';
import { useAuth } from '../hooks/useAuth';

const Jobs: React.FC = () => {
  const { user } = useAuth();

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Jobs
      </Typography>
      {user ? (
        <Typography>Job listings coming soon...</Typography>
      ) : (
        <Typography>Please login to view jobs.</Typography>
      )}
    </Box>
  );
};

export default Jobs; 