import api from "../axios";

// Fetch all cities
export const fetchAllCities = async () => {
  try {
    const response = await api.get('/city/all');
    return response.data;
  } catch (error) {
    console.error('Error fetching cities:', error);
    throw error;
  }
};
export const createCity = async (cityData) => {
  try {
    const response = await api.post('/city/create', cityData);
    console.log('response data city',response.data)

    return response.data;
  } catch (error) {
    console.error('Error creating city:', error);
    throw error;
  }
};

export const editCity = async (cityData) => {
  try {
    const response = await api.put('/city/update', cityData);
    return response.data;
  } catch (error) {
    console.error('Error editing city:', error);
    throw error;
  }
};

export const deleteCity = async (id) => {
  try {
    const response = await api.delete(`/city/delete/${id}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting city:', error);
    throw error;
  }
};
