import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import constants from './constants';

const SongsPage = () => {
  const [songs, setSongs] = useState([]);
  const [favoriteSongs, setFavoriteSongs] = useState([]);

  useEffect(() => {
    axios.get(`${constants.baseurl}/songs`, constants.authHeader)    
      .then(response => setSongs(response.data))
      .catch(error => console.error('Error fetching songs:', error));

    axios.get(`${constants.baseurl}/favsongs`, constants.authHeader)
    .then(response => setFavoriteSongs(response.data.content))
    .catch(error => console.error('Error fetching favorite songs:', error));  
  }, []);

  return (
    <div>
      <h1>Songs</h1>
      <table className="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Artist</th>
            <th>Year</th>
          </tr>
        </thead>
        <tbody>
          {songs.map(song => (
            <tr key={song.id}>
              <td>
                <Link 
                  to={`/songs/${song.id}`} 
                  state={{ song: song, isFavorite: favoriteSongs.map(s => s.id).includes(song.id) }}
                >{song.name}</Link>
              </td>
              <td>{song.artist}</td>
              <td>{song.year}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default SongsPage;
