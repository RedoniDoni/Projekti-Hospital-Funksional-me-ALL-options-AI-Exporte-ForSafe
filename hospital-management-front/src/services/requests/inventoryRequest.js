// services/requests/inventory.js
import api from "../axios";

// Create an inventory request
export const createInventoryRequest = async (inventoryRequestData) => {
  try {
    const response = await api.post('/inventory/request', inventoryRequestData);
    return response.data; // Return the created inventory request
  } catch (error) {
    console.error('Error creating inventory request:', error);
    throw error;
  }
};

// Fetch all inventory items
export const fetchAllInventory = async () => {
  try {
    const response = await api.get('/inventory/all');
    return response.data.data; // Extract the inventory array
  } catch (error) {
    console.error('Error fetching inventory:', error);
    throw error;
  }
};

// Delete an inventory item by ID
export const deleteInventory = async (id) => {
  try {
    const response = await api.delete(`/inventory/${id}`);
    console.log('Deleted Inventory:', response);
    return response.data; // Return the deleted inventory item
  } catch (error) {
    console.error('Error deleting inventory:', error);
    throw error;
  }
};
