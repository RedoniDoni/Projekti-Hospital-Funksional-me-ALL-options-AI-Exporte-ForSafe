import api from "../axios";

export const createRoom = async (roomData) => {
    try {
      const response = await api.post('/room/create', roomData);
      return response.data;
    } catch (error) {
      console.error('Error creating room:', error);
      throw error;
    }
  };
  export const fetchAllRooms = async () => {
    try {
      const response = await api.get('/room/all');
      return response.data;
    } catch (error) {
      console.error('Error fetching rooms:', error);
      throw error;
    }
  };
  export const deleteRoom = async (id) => {
    try {
        const response = await api.delete(`/room/delete/${id}`);
        console.log('Deleted room:', response);
        return response.data;
    } catch (error) {
        console.error('Error deleting room:', error);
        throw error;
    }
  };

  export const updateRoom = async (id, roomData) => {
    console.log("Updating nurse with data:", roomData); // Debug log
    try {
      const response = await api.put(`/room/update/${id}`, roomData);
      console.log("Update response:", response.data); // Debug log
      return response.data;
    } catch (error) {
      console.error("Error updating nurse:", error);
      throw error;
    }
  };