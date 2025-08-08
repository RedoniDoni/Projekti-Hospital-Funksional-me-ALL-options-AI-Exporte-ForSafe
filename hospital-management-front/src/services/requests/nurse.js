import api from "../axios";

export const createNurse = async (nurseData) => {
    try {
      const response = await api.post('/nurse/create', nurseData);
      return response.data;
    } catch (error) {
      console.error('Error creating nurse:', error);
      throw error;
    }
  };
  export const fetchAllNurses = async () => {
    try {
      const response = await api.get('/nurse/all');
      return response.data.data; // Extract the nurses array
    } catch (error) {
      console.error('Error fetching nurses:', error);
      throw error;
    }
  };
  
export const deleteNurse = async (id) => {
  try {
      const response = await api.delete(`/nurse/${id}`);
      console.log('Deleted Nurse:', response);
      return response.data;
  } catch (error) {
      console.error('Error deleting nurse:', error);
      throw error;
  }
};

/**
* Update a nurse by ID
*/
export const updateNurse = async (id, nurseData) => {
  console.log("Updating nurse with data:", nurseData); // Debug log
  try {
    const response = await api.put(`/nurse/update/${id}`, nurseData);
    console.log("Update response:", response.data); // Debug log
    return response.data;
  } catch (error) {
    console.error("Error updating nurse:", error);
    throw error;
  }
};



  