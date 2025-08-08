import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getUserData } from '../../../services/requests/auth';
import { createInventoryRequest, deleteInventory, fetchAllInventory } from '../../../services/requests/inventoryRequest';

const CreateInventoryRequest = () => {
  const [inventorys, setInventotys] = useState([]);
  const [selectedInventory, setSelectedInventory] = useState(null); 
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate(); 

  useEffect(() => {
    const fetchData = async () => {
      try {
        const inventoryResponse = await fetchAllInventory();
        setInventotys(inventoryResponse);

    
        const userResponse = await getUserData();
        if (userResponse) {
          setUserData(userResponse); 
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData(); 
  }, []);


  const handleEdit = (inventoryId) => {
    navigate(`/admin/inventory/edit/${inventoryId}`);
  };


  const handleDelete = async (inventoryId) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this inventory item?');
    if (confirmDelete) {
      try {
        await deleteInventory(inventoryId);
        const updatedInventory = inventorys.filter(item => item.id !== inventoryId);
        setInventotys(updatedInventory);
      } catch (error) {
        console.error('Error deleting inventory:', error);
      }
    }
  };

  const handleSelectInventory = (inventory) => {
    setSelectedInventory(inventory);
  };

  const handleCreateInventoryRequest = async () => {
    if (!selectedInventory) {
      alert('Please select an inventory item.');
      return;
    }

    if (!userData) {
      alert('User data is not available.');
      return;
    }

    const inventoryRequestData = {
      inventoryId: selectedInventory.id,
      quantityRequested: 1,
      doctorId: userData.roles.includes('DOCTOR') ? userData.id : null,
      nurseId: userData.roles.includes('NURSE') ? userData.id : null,
    };

    try {
      await createInventoryRequest(inventoryRequestData); 
      alert('Inventory request created successfully!');
      setSelectedInventory(null);
    } catch (error) {
      console.error('Error creating inventory request:', error);
    }
  };

  return (
    <div className="show-doctor">
      <h2>All Inventory</h2>
      <button className="add-button" onClick={() => navigate("/admin/inventory/add")}>
        Add Inventory
      </button>

      {userData && (
        <div>
          <h3>Welcome, {userData.firstName} {userData.lastName}</h3>
        </div>
      )}

      <table className="doctor-table">
        <thead>
          <tr>
            <th>Article</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(inventorys) && inventorys.length > 0 ? (
            inventorys.map((inventory, index) => (
              <tr key={index}>
                <td>{inventory.article}</td>
                <td>{inventory.description}</td>
                <td>{inventory.quantity}</td>
                <td>{inventory.totalPrice}</td>
                <td>
                  <button onClick={() => handleEdit(inventory.id)} className='edit-button'>Edit</button>
                  <button onClick={() => handleDelete(inventory.id)}  className='delete-button'>Delete</button>
                  <button onClick={() => handleSelectInventory(inventory)}  className='details-button'>Select</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No inventory items available</td>
            </tr>
          )}
        </tbody>
      </table>

      {selectedInventory && (
        <div>
          <h4>Selected Inventory: {selectedInventory.article}</h4>
          <button onClick={handleCreateInventoryRequest}>
            Create Inventory Request
          </button>
        </div>
      )}
    </div>
  );
};

export default CreateInventoryRequest;
