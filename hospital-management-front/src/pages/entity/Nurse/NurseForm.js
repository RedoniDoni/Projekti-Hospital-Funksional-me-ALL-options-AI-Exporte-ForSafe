import { Button, Input, Select } from "antd";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";
import api from "../../../services/axios";
import { registerUser } from "../../../services/requests/auth";
import { fetchAllCities } from "../../../services/requests/city";
import { fetchAllDepartments } from "../../../services/requests/department";
import { updateNurse } from "../../../services/requests/nurse";
import { fetchAllRooms } from "../../../services/requests/rooms";
import "./Nurse.scss";

const { Option } = Select;

const NurseForm = ({ id }) => {
  const [departments, setDepartments] = useState([]);
  const [cities, setCities] = useState([]);
  const [rooms, setRooms] = useState([]);
  const [initialData, setInitialData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const loadDependencies = async () => {
      try {
        const [departmentsResponse, citiesResponse, roomsResponse] = await Promise.all([
          fetchAllDepartments(),
          fetchAllCities(),
          fetchAllRooms(),
        ]);
        setDepartments(departmentsResponse?.data || []);
        setCities(citiesResponse?.data || []);
        setRooms(roomsResponse?.data || []);
      } catch (error) {
        console.error("Error fetching dependencies:", error);
      }
    };

    const loadInitialData = async () => {
      if (id) {
        try {
          const response = await api.get(`/nurse/${id}`);
          setInitialData(response.data.data);
        } catch (error) {
          console.error("Error fetching nurse data:", error);
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
    firstName: initialData?.firstName || "",
    lastName: initialData?.lastName || "",
    email: initialData?.email || "",
    password: initialData?.password || "",
    description: initialData?.description || "",
    street: initialData?.street || "",
    category: initialData?.category || "",
    phoneNumber: initialData?.phoneNumber || "",
    departmentId: initialData?.departmentId || "",
    roomId: initialData?.roomId || "",
    cityId: initialData?.cityId || "",
  };

  const validationSchema = Yup.object({
    firstName: Yup.string().required("First name is required"),
    lastName: Yup.string().required("Last name is required"),
    email: Yup.string().email("Invalid email address").required("Email is required"),
    password: Yup.string(),
    description: Yup.string(),
    street: Yup.string(),
    category: Yup.string(),
    phoneNumber: Yup.string(),
    departmentId: Yup.string().required("Department is required"),
    roomId: Yup.string().required("Room is required"),
    cityId: Yup.string().required("City is required"),
  });

  const handleSubmit = async (values, { setSubmitting }) => {
    try {
      if (id) {
        await updateNurse(id, values);
        console.log("Nurse updated successfully");
      } else {
        await registerUser({ ...values, role: "NURSE" });
        console.log("Nurse created successfully");
      }
      navigate("/admin/allnurse");
    } catch (error) {
      console.error("Error saving nurse:", error);
      alert("Failed to save nurse. Please try again.");
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
        <Form className="nurse-form">
          <Field name="firstName">
            {({ field }) => <Input {...field} placeholder="First Name" />}
          </Field>
          <Field name="lastName">
            {({ field }) => <Input {...field} placeholder="Last Name" />}
          </Field>
          <Field name="email">
            {({ field }) => <Input {...field} placeholder="Email" type="email" />}
          </Field>
          <Field name="password">
            {({ field }) => <Input {...field} placeholder="Password" type="password" />}
          </Field>
          <Field name="description">
            {({ field }) => <Input {...field} placeholder="Description" />}
          </Field>
          <Field name="street">
            {({ field }) => <Input {...field} placeholder="Street" />}
          </Field>
          <Field name="category">
            {({ field }) => <Input {...field} placeholder="Category" />}
          </Field>
          <Field name="phoneNumber">
            {({ field }) => <Input {...field} placeholder="Phone Number" />}
          </Field>
          <Field name="departmentId">
    {({ field, form }) => (
      <Select
        {...field}
        placeholder="Select a Department" 
        value={form.values.departmentId} // Bind value from Formik
        onChange={(value) => form.setFieldValue("departmentId", value)} // Update Formik state on change
        allowClear // Optional: Allow clearing the selection
      >
        {departments.map((dept) => (
          <Option key={dept.id} value={dept.id}>
            {dept.departamentName}
          </Option> 
        ))}
      </Select>
    )}
  </Field>

  <Field name="roomId">
    {({ field, form }) => (
      <Select
        {...field}
        placeholder="Select a Room" // Placeholder for Room dropdown
        value={form.values.roomId}
        onChange={(value) => form.setFieldValue("roomId", value)}
        allowClear // Optional: Allow clearing the selection
      >
        {rooms.map((room) => (
          <Option key={room.id} value={room.id}>
            {room.roomName}
          </Option>
        ))}
      </Select>
    )}
  </Field>

  <Field name="cityId">
    {({ field, form }) => (
      <Select
        {...field}
        placeholder="Select a City" // Placeholder for City dropdown
        value={form.values.cityId}
        onChange={(value) => form.setFieldValue("cityId", value)}
        allowClear // Optional: Allow clearing the selection
      >
        {cities.map((city) => (
          <Option key={city.id} value={city.id}>
            {city.name}
          </Option>
        ))}
      </Select>
    )}
  </Field>


          <Button type="primary" htmlType="submit" loading={isSubmitting}>
            {id ? "Update Nurse" : "Create Nurse"}
          </Button>
        </Form>
      )}
    </Formik>
  );
};

export default NurseForm;
