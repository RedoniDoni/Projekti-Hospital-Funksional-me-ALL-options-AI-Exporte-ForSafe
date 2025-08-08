import api from "../axios";

// Fetch all vacations
export const fetchAllVacations = async () => {
  try {
    const response = await api.get('/vacation/all');
    return response.data;
  } catch (error) {
    console.error('Error fetching vacations:', error);
    throw error;
  }
};

// Fetch vacations by doctor
export const fetchVacationsByDoctor = async () => {
  try {
    const response = await api.get('/vacation/all-by-doctor');
    return response.data;
  } catch (error) {
    console.error('Error fetching vacations for doctor:', error);
    throw error;
  }
};

// Fetch vacations by nurse
export const fetchVacationsByNurse = async () => {
  try {
    const response = await api.get('/vacation/all-by-nurse');
    return response.data;
  } catch (error) {
    console.error('Error fetching vacations for nurse:', error);
    throw error;
  }
};

// Create vacation
export const createVacation = async (vacationData) => {
  try {
    const response = await api.post('/vacation/create', vacationData);
    return response.data;
  } catch (error) {
    console.error('Error creating vacation:', error);
    throw error;
  }
};

// Update vacation
export const updateVacation = async (id, updatedData) => {
  try {
    const response = await api.put(`/vacation/update/${id}`, updatedData);
    return response.data;
  } catch (error) {
    console.error('Error updating vacation:', error);
    throw error;
  }
};

// Delete vacation
export const deleteVacation = async (id) => {
  try {
    const response = await api.delete(`/vacation/deleted/${id}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vacation:', error);
    throw error;
  }
};
