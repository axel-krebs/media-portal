/* Define reducers */
import { configureStore } from '@reduxjs/toolkit'
import movies from './movies';

export default configureStore({
  reducer: {
    movies
  }
})