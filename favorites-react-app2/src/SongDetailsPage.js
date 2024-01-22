import React, {useState} from 'react';
import { Link, useLocation } from 'react-router-dom';
import axios from 'axios';
import constants from './constants';

const SongDetailsPage = () => {

  const { state } = useLocation()
  const { song } = state
  const [isFavorite, setIsFavorite] = useState(state.isFavorite);
    
  const addToFavorites = () => {
    let url = `${constants.baseurl}/favsongs/${song.id}`

    axios.post(url, null, constants.authHeader)    
      .then(response => setIsFavorite(true))
      .catch(error => console.error('Error fetching songs:', error))
  };

  var favorite
  if (isFavorite) {
    favorite = <p>This song is in your favorites</p>
  } else {
    favorite = <button onClick={addToFavorites}>Add to Favorites &#10084;&#65039;</button>
  }

  return (
    <div>
      <h2>Song Details</h2>
      <p>Name: {song.name}</p>
      <p>Artist: {song.artist}</p>
      <p>Album: {song.album}</p>
      <p>Year: {song.year}</p>
      <p>Lentgh: {song.songLengthSecs} seconds</p>
      <p>Genre: {song.genre}</p>
      {favorite}
      <br/><br/>
      <Link to="/songs">Back to Songs</Link>
    </div>
  );
};

export default SongDetailsPage;
