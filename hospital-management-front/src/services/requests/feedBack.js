import api from "../axios";

// Fetch all feedback
export const fetchAllFeedBacks = async () => {
  try {
    const response = await api.get('/feed-back/all');
    return response.data;
  } catch (error) {
    console.error('Error fetching feedback:', error);
    throw error;
  }
};

//feedback for Nurses
export const feedBackForNurse = async () => {
    try {
      const response = await api.get('/feed-back/forNurse');
      return response.data;
    } catch (error) {
      console.error('Error fetching feedback:', error);
      throw error;
    }
  };


//feedback for Doctors
  export const feedBackForDoctor = async () => {
    try {
      const response = await api.get('/feed-back/forDoctor');
      return response.data;
    } catch (error) {
      console.error('Error fetching feedback:', error);
      throw error;
    }
  };

// Create feedback
export const createFeedBack = async (feedbackData) => {
  try {
    const response = await api.post('/feed-back/create', feedbackData);
    return response.data;
  } catch (error) {
    console.error('Error creating feedback:', error);
    throw error;
  }
};

// Edit feedback
export const editFeedback = async (feedbackId, updatedData) => {
  try {
    const response = await api.put(`/feed-back/update/${feedbackId}`, updatedData);
    return response.data;
  } catch (error) {
    console.error('Error editing feedback:', error);
    throw error;
  }
};

// Delete feedback
export const deleteFeedback = async (feedbackId) => {
  try {
    const response = await api.delete(`/feed-back/delete/${feedbackId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting feedback:', error);
    throw error;
  }
};

// Fetch feedback by ID
export const fetchFeedbackById = async (id) => {
  try {
    const response = await api.get(`/feed-back/${id}`);
    return response.data;  // This returns the feedback details
  } catch (error) {
    console.error('Error fetching feedback by ID:', error);
    throw error;
  }
};
