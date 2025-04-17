import React from 'react';
import { Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

const Home: React.FC = () => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();

  return (
    <Box sx={{ py: 8, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <Box sx={{ textAlign: 'center', mb: 4, width: '100%' }}>
        <Typography variant="h2" component="h1" gutterBottom>
          Welcome to NaukriSetu
        </Typography>
        <Typography variant="h5" color="text.secondary" paragraph>
          Your one-stop platform for job search and career growth
        </Typography>
      </Box>
      
      <Box sx={{ textAlign: 'center', width: '100%', maxWidth: '600px' }}>
        {!isAuthenticated ? (
          <>
            <Button
              variant="contained"
              color="primary"
              size="large"
              onClick={() => navigate('/register')}
              sx={{ mr: 2 }}
            >
              Register
            </Button>
            <Button
              variant="outlined"
              color="primary"
              size="large"
              onClick={() => navigate('/login')}
            >
              Login
            </Button>
          </>
        ) : (
          <Button
            variant="contained"
            color="primary"
            size="large"
            onClick={() => navigate('/jobs')}
          >
            Browse Jobs
          </Button>
        )}
      </Box>
    </Box>
  );
};

export default Home; 