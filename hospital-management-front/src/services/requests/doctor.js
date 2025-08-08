import api from "../axios";

export const createDoctor = async (doctorData) => {
    try {
      const response = await api.post('/doctor/create', doctorData);
      return response.data;
    } catch (error) {
      console.error('Error creating doctor:', error);
      throw error;
    }
  };
export const fetchAllDoctors = async () => {
    try {
        const response = await api.get('/doctor/all');
        console.log('response',response)
        return response.data;
    } catch (error) {
        console.error('Error fetching doctors:', error);
        throw error;
    }
};
export const updateDoctor = async (doctorId, doctorData) => {
  try {
    const response = await api.put(`/doctor/update/${doctorId}`, doctorData);
    return response.data;
  } catch (error) {
    console.error('Error updating doctor:', error);
    throw error;
  }
};
export const deleteDoctor = async (doctorId) => {
  try {
    await api.delete(`/doctor/deleted/${doctorId}`);
  } catch (error) {
    console.error('Error deleting doctor:', error);
    throw error;
  }
};