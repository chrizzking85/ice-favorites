import React, { useState, useEffect } from 'react';
import axios from 'axios';
import constants from './constants';

const FavoritesPage = () => {
  const [favoriteSongs, setFavoriteSongs] = useState([]);
  const [yearFilter, setYearFilter] = useState('');
  const [artistFilter, setArtistFilter] = useState('');
  const [sortCriteria, setSortCriteria] = useState(null)

  useEffect(() => {
    axios.get(`${constants.baseurl}/favsongs`, constants.authHeader)
      .then(response => setFavoriteSongs(response.data.content))
      .catch(error => console.error('Error fetching favorite songs:', error));
  }, [])

  // Filter favorites based on year and artist
  const filteredFavorites = favoriteSongs.filter(song => {
    const yearMatch = yearFilter === '' || song.year.toString().includes(yearFilter);
    const artistMatch = artistFilter === '' || song.artist.toLowerCase().includes(artistFilter.toLowerCase());
    return yearMatch && artistMatch;
  })
  .sort((a, b) => {
    // Sort based on the selected criteria
    if (sortCriteria === 'year') {
      return a.year.toString().localeCompare(b.year.toString());
    } else if (sortCriteria === 'name') {
      return a.name.localeCompare(b.name);
    }
    return 0; // No sorting
  })

  return (
    <div>
      <h1>Favorites</h1>
      <div>
        <label>Filter by Year </label>
        <input
          type="text"
          value={yearFilter}
          onChange={(e) => setYearFilter(e.target.value)}
        />
      </div>
      <div>
        <label>Filter by Artist </label>
        <input
          type="text"
          value={artistFilter}
          onChange={(e) => setArtistFilter(e.target.value)}
        />
      </div>
      <div>
        <button onClick={() => setSortCriteria('year')}>Sort by Release Year</button>
        <button onClick={() => setSortCriteria('name')}>Sort by Name</button>
        <button onClick={() => setSortCriteria(null)}>Clear Sorting</button>
      </div>
      <table className="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Artist</th>
            <th>Release Year</th>
          </tr>
        </thead>
        <tbody>
          {filteredFavorites.map(song => (
            <tr key={song.id}>
              <td>{song.name}</td>
              <td>{song.artist}</td>
              <td>{song.year}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default FavoritesPage;
