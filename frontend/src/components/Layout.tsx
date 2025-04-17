import React from 'react';
import { AppBar, Toolbar, Typography, Container, Box } from '@mui/material';
import { Link } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

const Layout: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { isAuthenticated, logout } = useAuth();

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component={Link} to="/" sx={{ flexGrow: 1, textDecoration: 'none', color: 'inherit' }}>
            NaukriSetu
          </Typography>
          {isAuthenticated ? (
            <>
              <Typography component={Link} to="/profile" sx={{ mx: 2, textDecoration: 'none', color: 'inherit' }}>
                Profile
              </Typography>
              <Typography component={Link} to="/documents" sx={{ mx: 2, textDecoration: 'none', color: 'inherit' }}>
                Documents
              </Typography>
              <Typography component={Link} to="/jobs" sx={{ mx: 2, textDecoration: 'none', color: 'inherit' }}>
                Jobs
              </Typography>
              <Typography
                component="button"
                onClick={logout}
                sx={{ mx: 2, textDecoration: 'none', color: 'inherit', border: 'none', background: 'none', cursor: 'pointer' }}
              >
                Logout
              </Typography>
            </>
          ) : (
            <>
              <Typography component={Link} to="/login" sx={{ mx: 2, textDecoration: 'none', color: 'inherit' }}>
                Login
              </Typography>
              <Typography component={Link} to="/register" sx={{ mx: 2, textDecoration: 'none', color: 'inherit' }}>
                Register
              </Typography>
            </>
          )}
        </Toolbar>
      </AppBar>
      <Container component="main" sx={{ mt: 4, mb: 4, flex: 1 }}>
        {children}
      </Container>
      <Box component="footer" sx={{ py: 3, bgcolor: 'background.paper' }}>
        <Container maxWidth="lg">
          <Typography variant="body2" color="text.secondary" align="center">
            © {new Date().getFullYear()} NaukriSetu. All rights reserved.
          </Typography>
        </Container>
      </Box>
    </Box>
  );
};

export default Layout; 