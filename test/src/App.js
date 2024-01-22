// App.js

import React from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import SongsPage from './SongsPage';
import FavoritesPage from './FavoritesPage';
import SongDetailsPage from './SongDetailsPage';

const App = () => {
  return (
    <Router>
      <div>
        <nav className="navbar">
          <ul>
            <li><Link to="/songs">Songs</Link></li>
            <li><Link to="/favorites">Favorites</Link></li>
          </ul>
        </nav>

        <Routes>
          <Route path="/songs/:id" element={<SongDetailsPage/>} />
          <Route path="/songs" element={<SongsPage/>} />
          <Route path="/favorites" element={<FavoritesPage/>} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;