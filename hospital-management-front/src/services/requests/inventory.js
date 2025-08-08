import api from "../axios";

export const fetchAllInventory = async () => {
  try {
    const response = await api.get('/inventory/all');
    return response.data;
  } catch (error) {
    console.error('Error fetching citys:', error);
    throw error;
  }
};

export const createInventory = async (inventoryData) => {
  try {
    const response = await api.post('/inventory/create', inventoryData);
    return response.data;
  } catch (error) {
    console.error('Error creating inventory:', error);
    throw error;
  }
};

export const editInventory = async (inventoryId, updatedData) => {
  try {
    const response = await api.put(`/inventory/update/${inventoryId}`, updatedData);
    return response.data;
  } catch (error) {
    console.error('Error editing inventory:', error);
    throw error;
  }
};

// Delete an inventory item
export const deleteInventory = async (inventoryId) => {
  try {
    const response = await api.delete(`/inventory/deleted/${inventoryId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting inventory:', error);
    throw error;
  }
};

export const fetchInventoryById = async (id) => {
  try {
    const response = await api.get(`/inventory/${id}`);
    return response.data;  // This should return the data object wrapped in ResponseObject
  } catch (error) {
    console.error('Error fetching inventory by ID:', error);
    throw error;
  }
};

