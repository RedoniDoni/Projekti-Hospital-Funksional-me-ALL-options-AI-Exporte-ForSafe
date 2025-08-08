import api from "../axios";


export const createAppointment = async (appointmentData) => {
  try {
    const response = await api.post('/appointment/create', appointmentData);
    return response.data;
  } catch (error) {
    console.error('Error creating appointment:', error);
    throw error;
  }
};

export const getAppointmentByDoctorId = async (doctorId) => {
  try {
      const response = await api.get(`/appointment/${doctorId}`);
      console.log('response app', response);
      return response.data;
  } catch (error) {
      console.error('Error fetching appointment:', error);
      throw error;
  }
};
