import { Table } from "antd";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../../services/axios";

const ViewPatientsInRoom = () => {
  const { roomId } = useParams(); // Extract room ID from the URL
  const [patients, setPatients] = useState([]); // State to store patient data
  const [loading, setLoading] = useState(false); // Loading state for Table

  useEffect(() => {
    const fetchPatients = async () => {
      setLoading(true); // Show loading spinner
      try {
        // Fetch data from the backend endpoint
        const response = await api.get(`/patient/room/${roomId}`);
        // Directly set the response data (ensure it matches the columns)
        setPatients(response.data);
      } catch (error) {
        console.error("Error fetching patients:", error);
      } finally {
        setLoading(false); // Hide loading spinner
      }
    };

    fetchPatients();
  }, [roomId]);
  

  // Columns for the Ant Design Table
  const columns = [
    { title: "Patient ID", dataIndex: "id", key: "id" },
    { title: "Name", dataIndex: "firstName", key: "firstName" }, 
    { title: "Email", dataIndex: "email", key: "email" }, 
    { title: "Phone Number", dataIndex: "phoneNumber", key: "phoneNumber" },
    { title: "Room", dataIndex: "roomName", key: "roomName" }, 
    { title: "Street", dataIndex: "street", key: "street" }, 
    { title: "Age", dataIndex: "age", key: "age" },
    { title: "Condition", dataIndex: "condition", key: "condition" }, 
  ];

  return (
    <div>
      <h1>Patient in Room {roomId}</h1>
      <Table
        dataSource={patients}
        columns={columns}
        loading={loading}
        rowKey="id" 
      />
    </div>
  );
};

export default ViewPatientsInRoom;
