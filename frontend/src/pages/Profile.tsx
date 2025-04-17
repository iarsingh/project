import React from 'react';
import { Typography, Box } from '@mui/material';
import { useAuth } from '../hooks/useAuth';

const Profile: React.FC = () => {
  const { user } = useAuth();

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Profile
      </Typography>
      {user ? (
        <Box>
          <Typography variant="h6">Name: {user.name}</Typography>
          <Typography variant="h6">Email: {user.email}</Typography>
          <Typography variant="h6">Role: {user.role}</Typography>
        </Box>
      ) : (
        <Typography>Loading profile...</Typography>
      )}
    </Box>
  );
};

export default Profile; 