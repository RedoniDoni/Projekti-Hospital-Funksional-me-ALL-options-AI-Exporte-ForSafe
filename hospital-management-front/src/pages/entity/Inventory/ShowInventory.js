import { Modal } from 'antd';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import * as XLSX from "xlsx";
import { getUserData } from '../../../services/requests/auth';
import { deleteInventory, fetchAllInventory } from '../../../services/requests/inventory';


const ShowInventory = () => {
  const [inventorys, setInventotys] = useState([]);
  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    const fetchInventotys = async () => {
      const response = await fetchAllInventory();
      setInventotys(response.data);
    };
    fetchInventotys();
    getUserData();
  }, []);


  // Handle edit inventory item
  const handleEdit = (inventoryId) => {
    navigate(`/admin/inventory/edit/${inventoryId}`);
  };

  // Handle delete inventory item
  const handleDelete = async (inventoryId) => {
    Modal.confirm({
      title: "Are you sure you want to delete this inventory item?",
      content: "This action cannot be undone.",
      okText: "Yes",
      cancelText: "No",
      onOk: async () => {
        try {
          await deleteInventory(inventoryId);
          const updatedInventory = inventorys.filter((item) => item.id !== inventoryId);
          setInventotys(updatedInventory);
        } catch (error) {
          console.error("Error deleting inventory:", error);
        }
      },
    });
  };

  const handleExportToExcel = () => {
  const worksheet = XLSX.utils.json_to_sheet(inventorys);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "Inventory");
  XLSX.writeFile(workbook, "InventoryData.xlsx");
};

  return (
    <div className="show-doctor">
      <h2>All Inventory</h2>
      <button className="add-button" onClick={() => navigate("/admin/inventory/add")}>
        Add Inventory
      </button>
      <button
    style={{
      backgroundColor: '#4CAF50',
      color: 'white',
      padding: '8px 12px',
      border: 'none',
      borderRadius: '5px',
      cursor: 'pointer',
      transition: '0.3s'
    }}
    onClick={handleExportToExcel}
  >
    Export to Excel
    </button>
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
          {inventorys.map((inventory, index) => (
            <tr key={index}>
              <td>{inventory.article}</td>
              <td>{inventory.description}</td>
              <td>{inventory.quantity}</td>
              <td>{inventory.totalPrice}</td>
              <td>
                <button onClick={() => handleEdit(inventory.id)} className="edit-button">Edit</button>
                <button onClick={() => handleDelete(inventory.id)}className="delete-button">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ShowInventory;

