import React, { useState, useEffect } from "react";
import {
  deleteCity,
  editCity,
  fetchAllCities,
} from "../../../services/requests/city";
import { useNavigate } from "react-router-dom";
import { Modal, Input, Button } from "antd";
import "./city.scss";

const CitiesTable = () => {
  const navigate = useNavigate();
  const [cities, setCities] = useState([]);
  const [editingCity, setEditingCity] = useState(null);
  const [cityName, setCityName] = useState("");
  const [isModalVisible, setIsModalVisible] = useState(false);

  useEffect(() => {
    fetchCities();
  }, []);

  const fetchCities = async () => {
    try {
      const data = await fetchAllCities();
      setCities(data);
    } catch (error) {
      console.error("Error fetching cities:", error);
    }
  };

  const handleDelete = async (id) => {
    Modal.confirm({
      title: "Are you sure you want to delete this city?",
      content: "This action cannot be undone.",
      okText: "Yes",
      cancelText: "No",
      onOk: async () => {
        try {
          await deleteCity(id);
          fetchCities(); 
        } catch (error) {
          console.error("Error deleting city:", error);
        }
      },
    });
  };

  const handleEdit = (city) => {
    setEditingCity(city);
    setCityName(city.name);
    setIsModalVisible(true); // Show the modal when editing
  };

  const handleUpdate = async () => {
    if (editingCity) {
      try {
        await editCity({ ...editingCity, name: cityName });
        setIsModalVisible(false); // Close the modal after saving
        setEditingCity(null);
        setCityName("");
        fetchCities();
      } catch (error) {
        console.error("Error updating city:", error);
      }
    }
  };

  const handleCancel = () => {
    setIsModalVisible(false);
    setEditingCity(null);
    setCityName("");
  };

  return (
    <div className="cities-table-container">
      <h1>Cities</h1>
      <button
        className="add-button"
        onClick={() => navigate("/admin/city/create")}
      >
        Create City
      </button>
      <table className="doctor-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(cities?.data) ? (
            cities.data.map((city) => (
              <tr key={city.id}>
                <td>{city.id}</td>
                <td>{city.name}</td>
                <td>
                  <button
                    onClick={() => handleEdit(city)}
                    className="edit-button"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDelete(city.id)}
                    className="delete-button"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3">No cities available</td>
            </tr>
          )}
        </tbody>
      </table>
      <Modal
        title="Edit City"
        visible={isModalVisible}
        onOk={handleUpdate}
        onCancel={handleCancel}
      >
        <div className="edit-form">
          <label>
            Name:
            <Input
              value={cityName}
              onChange={(e) => setCityName(e.target.value)}
            />
          </label>
        </div>
      </Modal>
    </div>
  );
};

export default CitiesTable;
