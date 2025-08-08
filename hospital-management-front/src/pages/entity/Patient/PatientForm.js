import { Button, Input, Select } from "antd";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";
import api from "../../../services/axios";
import { registerUser } from "../../../services/requests/auth";
import { fetchAllCities } from "../../../services/requests/city";
import { updatePatient } from "../../../services/requests/patient";
import { fetchAllRooms } from "../../../services/requests/rooms";
import "./Patient.scss";

const { Option } = Select;

const PatientForm = ({ id }) => {
  const [cities, setCities] = useState([]);
  const [rooms, setRooms] = useState([]);
  const [initialData, setInitialData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const loadDependencies = async () => {
      try {
        const [citiesResponse, roomsResponse] = await Promise.all([
          
          fetchAllCities(),
          fetchAllRooms(),
        ]);
        setCities(citiesResponse?.data || []);
        setRooms(roomsResponse?.data || []);
      } catch (error) {
        console.error("Error fetching dependencies:", error);
      }
    };

    const loadInitialData = async () => {
      if (id) {
        try {
          const response = await api.get(`/patient/${id}`);
          setInitialData(response.data.data);
        } catch (error) {
          console.error("Error fetching patient data:", error);
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
    street: initialData?.street || "",
    phoneNumber: initialData?.phoneNumber || "",
    age :initialData?.age||"",
    roomId: initialData?.roomId || "",
    cityId: initialData?.cityId || "",
  };

  const validationSchema = Yup.object({
    firstName: Yup.string().required("First name is required"),
    lastName: Yup.string().required("Last name is required"),
    email: Yup.string().email("Invalid email address").required("Email is required"),
    password: Yup.string(),
    street: Yup.string(),
    phoneNumber: Yup.string(),
    roomId: Yup.string().required("Room is required"),
    cityId: Yup.string().required("City is required"),
  });

  const handleSubmit = async (values, { setSubmitting }) => {
    console.log("Form submitted with values:", values);
    try {
      if (id) {
        await updatePatient(id, values);
        console.log("Patient updated successfully");
      } else {
        await registerUser({ ...values, role: "PATIENT" });
        console.log("Patient created successfully");
      }
      navigate("/admin/allpatient");
    } catch (error) {
      console.error("Error saving patient:", error);
      alert("Failed to save patient. Please try again.");
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
          <Field name="street">
            {({ field }) => <Input {...field} placeholder="Street" />}
          </Field>
          <Field name="phoneNumber">
            {({ field }) => <Input {...field} placeholder="Phone Number" />}
          </Field>
          <Field name="dateOfBirth">
            {({ field }) => <Input {...field} placeholder="Date Of birth" />}
          </Field>
          <Field name="age">
            {({ field }) => <Input {...field} placeholder="Age" />}
          </Field>

  <Field name="roomId">
    {({ field, form }) => (
      <Select
        {...field}
        placeholder="Select a Room" // Placeholder for Room dropdown
        value={form.values.roomId || undefined}
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
        value={form.values.cityId || undefined}
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
            {id ? "Update Patient" : "Create Patient"}
          </Button>
        </Form>
      )}
    </Formik>
  );
};

export default PatientForm;
