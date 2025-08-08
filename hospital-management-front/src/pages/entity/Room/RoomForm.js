import { Button, Input, Select } from "antd";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";
import api from "../../../services/axios";
import { fetchAllDepartments } from "../../../services/requests/department";
import { createRoom, updateRoom } from "../../../services/requests/rooms"; // Ensure this exists for updating rooms
import "./createRoom.scss";

const { Option } = Select;

const RoomForm = ({ id }) => {
  const [departments, setDepartments] = useState([]);
  const [initialData, setInitialData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const loadDependencies = async () => {
      try {
        const departmentsResponse = await fetchAllDepartments();
        setDepartments(departmentsResponse?.data || []);
      } catch (error) {
        console.error("Error fetching dependencies:", error);
      }
    };

    const loadInitialData = async () => {
      if (id) {
        try {
          const response = await api.get(`/room/${id}`);
          setInitialData(response.data.data);
        } catch (error) {
          console.error("Error fetching room data:", error);
        }
      }
    };

    const loadAllData = async () => {
      await loadDependencies();
      await loadInitialData();
    };

    loadAllData();
  }, [id]);

  const initialValues = {
    roomName: initialData?.roomName || "",
    description: initialData?.description || "",
    floor: initialData?.floor || "",
    nrOfBeds: initialData?.nrOfBeds || "",
    departamentId: initialData?.departamentId || "",
  };

  const validationSchema = Yup.object({
    roomName: Yup.string().required("Room name is required"),
    description: Yup.string(),
    floor: Yup.number().required("Floor is required"),
    nrOfBeds: Yup.number().required("Number of beds is required"),
    departamentId: Yup.string().required("Department is required"),
  });

  const handleSubmit = async (values, { setSubmitting }) => {
    console.log("Form submitted with values:", values);
    try {
      if (id) {
        await updateRoom(id, values); // Ensure updateRoom is implemented for rooms
        console.log("Room updated successfully");
      } else {
        // Create logic if needed
        await createRoom (values);
        console.log("Room created successfully");
      }
      navigate("/admin/allRoom");
    } catch (error) {
      console.error("Error saving room:", error);
      alert("Failed to save room. Please try again.");
    } finally {
      setSubmitting(false);
    }
  };

  if (!initialData && id) {
    return <p>Loading...</p>;
  }

  return (
    <Formik initialValues={initialValues} validationSchema={validationSchema} enableReinitialize onSubmit={handleSubmit}>
      {({ isSubmitting, setFieldValue }) => (
        <Form className="room-form">
          <Field name="roomName">
            {({ field }) => <Input {...field} placeholder="Room Name" />}
          </Field>
          <Field name="description">
            {({ field }) => <Input {...field} placeholder="Description" />}
          </Field>
          <Field name="floor">
            {({ field }) => <Input {...field} placeholder="Floor" />}
          </Field>
          <Field name="nrOfBeds">
            {({ field }) => <Input {...field} placeholder="Number of Beds" />}
          </Field>
          <Field name="departamentId">
            {({ field, form }) => (
              <Select
                {...field}
                placeholder="Select a Department"
                value={form.values.departamentId || undefined} // Bind value from Formik
                onChange={(value) => form.setFieldValue("departamentId", value)} // Update Formik state on change
                allowClear
              >
                {departments.map((dept) => (
                  <Option key={dept.id} value={dept.id}>
                    {dept.departamentName}
                  </Option>
                ))}
              </Select>
            )}
          </Field>

          <Button type="primary" htmlType="submit" loading={isSubmitting}>
            {id ? "Update Room" : "Create Room"}
          </Button>
          
        </Form>
      )}
    </Formik>
  );
};

export default RoomForm;
